package com.dylanhersee.Project.Budget.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dylanhersee.Project.Budget.Service.BudgetService;
import com.dylanhersee.Project.Budget.model.Budget;
import com.google.firebase.auth.FirebaseAuthException;


//Controller Class 
@RestController
@RequestMapping("/api/budget")
public class BudgetController{

    @Autowired
    private BudgetService budgetService;

//Using HTTP Post Method to create and pass Budget information for new Event to Service and Repo 
    @PostMapping("/create")
    public ResponseEntity<Budget> createBudget(@RequestParam String username, @RequestParam String firebaseToken, @RequestParam String eventName, @RequestParam String eventType, @RequestParam double budget) throws FirebaseAuthException{

        Budget newBudget = budgetService.createBudget(username, firebaseToken, budget);

        return ResponseEntity.ok(newBudget);
    }

    //HTTP get method to look for budget data as requested
    @GetMapping("/get")
    public ResponseEntity<Budget> getBudget(@RequestParam String username, @RequestParam Long id) throws FirebaseAuthException{

       Budget budget = budgetService.getBudget(username, username, id);

        return ResponseEntity.ok(budget);
        

    }

    //put method is being used to alter already exisiting budget data 
    @PutMapping("/alter")
    public ResponseEntity<Budget> alterBudget(@PathVariable Long id, @RequestParam String firebaseToken, @RequestParam String username, @RequestBody Budget budget, @RequestBody Budget updateBudget) throws FirebaseAuthException{
        Budget update = budgetService.getUpdatedBudget(id, firebaseToken, username, budget, updateBudget);
        return ResponseEntity.ok(update);

    }

    
}



