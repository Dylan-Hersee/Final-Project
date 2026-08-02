package com.dylanhersee.Project.Budget.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dylanhersee.Project.Budget.Repository.BudgetRepository;
import com.dylanhersee.Project.Budget.model.Budget;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

@Service
public class BudgetService {
  

    @Autowired
    private BudgetRepository budgetRepository;
    
    
    public Budget createBudget(String firebaseToken, String username, Budget budget) throws FirebaseAuthException {
       FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(firebaseToken);
       String firebaseUid = decodedToken.getUid();

       try{
        if(budgetRepository.findByUsername(username) == null){
            throw new RuntimeException("Username not found");
        }

        else{
            Budget newBudget = new Budget(
                budget.getBudget(),
                budget.getUsername(), 
                budget.getEventName(), 
                budget.getEventType()
            );
        }

        return budgetRepository.save(newBudget);
       }catch(Exception e){
        throw new RuntimeException ("Unable to Create Budget" + e.getMessage());
       }
    }


    public Budget changeBudget(String firebaseToken, String username, Budget budget, Budget changeBudget) throws FirebaseAuthException{
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(firebaseToken);
        String firebaseUid = decodedToken.getUid();
    }
    

    public Budget getPurchase(String firebaseToken, String username, double purchase, String purchaseName, String category) throws FirebaseAuthException{
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(firebaseToken);
        String firebaseUid = decodedToken.getUid();
        
        
    }

    public Budget addPurchase(String firebaseToken, String username, Budget budget, double purchase, String purchaseName, String category) throws FirebaseAuthException {
       FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(firebaseToken);
       String firebaseUid = decodedToken.getUid();
        
    }


    public List<Budget> getAllBudgets(String firebaseToken, String username, Budget budget) throws FirebaseAuthException{
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(firebaseToken);
        String firebaseUid = decodedToken.getUid();
    }

    public List<Budget> getBudget(String firebaseToken, String username, Budget budget) throws FirebaseAuthException{
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(firebaseToken);
        String firebaseUid = decodedToken.getUid();
    }

    public Budget updateBudget(String firebaseToken, String username, Budget currentBudget, Budget newBudget) throws FirebaseAuthException{
      FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(firebaseToken);
      String firebaseUid = decodedToken.getUid();
    }


} 

