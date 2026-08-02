package com.dylanhersee.Project.Budget.Controller;

import java.util.List;

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

@RestController
@RequestMapping("/api/budget")
public class BudgetController{

    @Autowired
    private BudgetService budgetService;

    @PostMapping("/create")
    public ResponseEntity<Budget> createBudget(@RequestBody Budget budget, @RequestParam String username, @RequestParam String eventName, @RequestParam String eventType){

        Budget newBudget = budgetService.createBudget(budget);

        return ResponseEntity.ok(newBudget);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Budget>> getBudget(@RequestParam String username){

       List<Budget> budgets = budgetService.getBudget(username);

        return ResponseEntity.ok(budgets);
        

    }

    @PutMapping("/alter")
    public ResponseEntity<Budget> alterBudget(@PathVariable Long id, @RequestBody Budget budget){
        Budget update = budgetService.updateBudget(id, budget);
        return ResponseEntity.ok(update);

    }

    
}



