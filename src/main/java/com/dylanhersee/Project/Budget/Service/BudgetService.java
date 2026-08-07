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

//this is the service class that will look after my main budget logic
@Service

public class BudgetService extends Object {
  

    @Autowired
    private BudgetRepository budgetRepository;
    
    
    
    public Budget createBudget(String firebaseToken, String username, double budget) throws FirebaseAuthException {
       
       try{
            //These Variables are user to take in current users firebase authorisation data, decode it and set as a new string to check it with customers info
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(firebaseToken);
            String firebaseUid = decodedToken.getUid();

       
        // if the decoded firebase token is not equal to username then exception is thrown
            if(!firebaseUid.equals(username)){
                throw new RuntimeException("Unauthorised Access");
            }

            Budget newBudget = new Budget();
            newBudget.setUsername(username);
            newBudget.setBudget(budget);

            //otherwise save inputted budget to customers database
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

            //this is used to call budget repo find the budget linked to customer via the id assigned or else throw runtime exception of not found
            Budget budget = budgetRepository.findById(id).orElseThrow(() -> new RuntimeException("Budget not found"));

            //if there is no budget found in the customers table 
            if(budget.isEmpty()){
                throw new RuntimeException("No budgets found for the provided username.");
            }

            //otherwise return budget
            return budget;

            }catch(FirebaseAuthException e){
            System.out.println("Error verifying ID token: " + e.getMessage());
            }
            //return nothing if the try fails
            return null;
    }


    public Budget getUpdatedBudget(Long id, String firebaseToken, String username, Budget budget, Budget updateBudget) throws FirebaseAuthException{
        try{
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(firebaseToken);
            String firebaseUid = decodedToken.getUid();

            if(!firebaseUid.equals(username)){
                throw new RuntimeException("Unauthorised Access");
            }
            //creating a current budget to find the users budget in their DB 
            Budget currentBudget = budgetRepository.findById(id).orElseThrow(() -> new RuntimeException("Budget not found"));

            //if the budget is equal to 0 then call the create budget to make sure a budget is made
            if(budget.getBudget() == 0.0){
                createBudget(firebaseToken, username, budget.getBudget());
            }

            // call the get purchase method to update the current budget if the purchase is not 0, 
            if(updateBudget.getPurchase() != 0.0){
                //remaining budget is the current budget minus the update with get purchase call
                double remainingBudget = currentBudget.getBudget() - updateBudget.getPurchase();
                //current budget is now the remaining budget
                currentBudget.setBudget(remainingBudget);
                //checks the get purchase to call again if it is not 0
                currentBudget.setPurchase(updateBudget.getPurchase());
            }
            //checks and saves the name of the new purchase
            if(updateBudget.getPurchaseName() != null){
                currentBudget.setPurchaseName(updateBudget.getPurchaseName());
            }
            //checks and saves the cat of purchase
            if(updateBudget.getCategory() != null){
                currentBudget.setCategory(updateBudget.getCategory());
            }
            //saves the current budget to the DB
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
            //finds any new purhcases in the DB
            Budget newPurchase = budgetRepository.findById(id).orElseThrow(() -> new RuntimeException("Purchase not found"));
            if(!Objects.equals(newPurchase.getUsername(), username)){
                throw new RuntimeException("Unauthorized access: Purchase does not belong to the provided username.");
            }

            //returns new puchase
            return Optional.of(newPurchase);

        }catch(FirebaseAuthException e){
            System.out.println("Error verifying ID token: " + e.getMessage());
        }   
        //returns nothing if the try catch fails
        return Optional.empty();     
    }

    public List<Budget> getAllBudgets(String firebaseToken, String username, Budget budget) throws FirebaseAuthException{
        try{
        
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(firebaseToken);
            String firebaseUid = decodedToken.getUid();

            if(!firebaseUid.equals(username)){
                throw  new RuntimeException ("Unauthorized access: UID does not match the provided username.");
            }

            //finds the list of budgets base on customers username 
            List<Budget> budgets = budgetRepository.findByUsername(username);

            //if its empty return error
            if(budgets.isEmpty()){
                throw new RuntimeException("No budgets found for the provided username."); 
            }

            //returns
            return budgets;
        }
    catch(FirebaseAuthException e){
        System.out.println("Error verifying ID token: " + e.getMessage());
    }
    
        return null;
    }

} 

