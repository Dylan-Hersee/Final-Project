package com.dylanhersee.Project.Budget.Service;

import java.util.List;
import java.util.Optional;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dylanhersee.Project.Budget.Repository.BudgetRepository;
import com.dylanhersee.Project.Budget.model.Budget;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

@Service
public class BudgetService extends Object {
  

    @Autowired
    private BudgetRepository budgetRepository;
    
    
    
    public Budget createBudget(String firebaseToken, String username, Budget newBudget) throws FirebaseAuthException {
       
       try{
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(firebaseToken);
            String firebaseUid = decodedToken.getUid();

       
        
            if(!firebaseUid.equals(username)){
                throw new RuntimeException("Unauthorised Access");
            }

            return budgetRepository.save(newBudget);
        }catch(Exception e){
            throw new RuntimeException ("Unable to Create Budget" + e.getMessage());
        }
    }
     public Budget getBudget(String username, String firebaseToken, Long id) throws FirebaseAuthException{
        try{
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(firebaseToken);
            String firebaseUid = decodedToken.getUid();
            
            if(!firebaseUid.equals(username)){
                throw  new RuntimeException ("Unauthorized access: UID does not match the provided username.");
            }

            Budget budget = budgetRepository.findById(id).orElseThrow(() -> new RuntimeException("Budget not found"));

            if(budget.isEmpty()){
                throw new RuntimeException("No budgets found for the provided username.");
            }

            return budget;

            }catch(FirebaseAuthException e){
            System.out.println("Error verifying ID token: " + e.getMessage());
            }

            return null;
    }


    public Budget getUpdatedBudget(Long id, String firebaseToken, String username, Budget budget, Budget updateBudget) throws FirebaseAuthException{
        try{
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(firebaseToken);
            String firebaseUid = decodedToken.getUid();

            if(!firebaseUid.equals(username)){
                throw new RuntimeException("Unauthorised Access");
            }

            Budget currentBudget = budgetRepository.findById(id).orElseThrow(() -> new RuntimeException("Budget not found"));

            if(budget.getBudget() == 0.0){
                budget.createBudget();
            }

            if(!firebaseUid.equals(username)){
                throw new RuntimeException("Unauthorised Access");
            }


            if(updateBudget.getPurchase() != 0.0){
                double remainingBudget = currentBudget.getBudget() - updateBudget.getPurchase();
                currentBudget.setBudget(remainingBudget);
                currentBudget.setPurchase(updateBudget.getPurchase());
            }
            if(updateBudget.getPurchaseName() != null){
                currentBudget.setPurchaseName(updateBudget.getPurchaseName());
            }
            if(updateBudget.getCategory() != null){
                currentBudget.setCategory(updateBudget.getCategory());
            }

            return budgetRepository.save(currentBudget);
        }catch(FirebaseAuthException e){
        throw new RuntimeException("User Not Found" + e.getMessage());
    }
    }
    

    public Optional<Budget> getPurchase(Long id, String firebaseToken, String username, double purchase, String purchaseName, String category) throws FirebaseAuthException{
        try{
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(firebaseToken);
            String firebaseUid = decodedToken.getUid();

            if(!firebaseUid.equals(username)){
                throw  new RuntimeException ("Unauthorized access: UID does not match the provided username.");
            }

            Budget newPurchase = budgetRepository.findById(id).orElseThrow(() -> new RuntimeException("Purchase not found"));
            if(!Objects.equals(newPurchase.getUsername(), username)){
                throw new RuntimeException("Unauthorized access: Purchase does not belong to the provided username.");
            }

            return Optional.of(newPurchase);

        }catch(FirebaseAuthException e){
            System.out.println("Error verifying ID token: " + e.getMessage());
        }   
        return Optional.empty();     
    }

    public List<Budget> getAllBudgets(String firebaseToken, String username, Budget budget) throws FirebaseAuthException{
        try{
        
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(firebaseToken);
            String firebaseUid = decodedToken.getUid();

            if(!firebaseUid.equals(username)){
                throw  new RuntimeException ("Unauthorized access: UID does not match the provided username.");
            }

            List<Budget> budgets = budgetRepository.findByUsername(username);
            if(budgets.isEmpty()){
                throw new RuntimeException("No budgets found for the provided username."); 
            }

            return budgets;
        }
    catch(FirebaseAuthException e){
        System.out.println("Error verifying ID token: " + e.getMessage());
    }
    
        return null;
    }
    

   

    public Budget updateBudget(Long id, Budget budget) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

} 

