package com.dylanhersee.Project.Budget.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dylanhersee.Project.Budget.Repository.BudgetRepository;
import com.dylanhersee.Project.Budget.model.Budget;

@Service
public class BudgetService {


    @Autowired
    private BudgetRepository budgetRepository;


    public Budget createBudget(Budget budget) {
       
    }


    public Budget changeBudget(String username, String eventName, String eventType, double budget){
        
    }
    

    public Budget getPurchase(String firebaseId, String eventName, String eventType, double purchase, String purchaseName, String category){
        
        
    }

    public Budget addPurchase(String firebaseId, String eventName, String eventType, double purchase, String purchaseName, String category){
       
        
    }


    public List<Budget> getAllBudgets(){
        
    }

    public Budget getBudget(String username, String username){
        
    }

    public Budget updateBudget(Long id, Budget budget) {
      
    }

} 

