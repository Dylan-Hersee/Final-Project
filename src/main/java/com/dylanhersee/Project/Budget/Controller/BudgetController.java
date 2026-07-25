package com.dylanhersee.Project.Budget.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dylanhersee.Project.User.model.User;

@RestController
@RequestMapping("/api/budget")
public class BudgetController{

    @Autowired
    private BudgetService budgetService;

    @PostMapping("/create")
    public ResponseEntity<Budget> createBudget(@RequestBody Budget budget){

        Budget newBudget = budgetService.createBudget(budget);

        return ResponseEntity.ok(newBudget);
    }

    @GetMapping("/get")
    public ResponseEntity<Budget> getBudget(@RequestParam String username){

       List<Budget> budgets = budgetService.getBudget(username);

        return ResponseEntity.ok(budgets);
        

    }

    @PutMapping("/alter")
    public ResponseEntity<Budget> alterBudget(@PathVariable Long id, @RequestBody Budget budget){
        Budget update = budgetService.updateBudget(id, budget);
        return ResponseEntity.ok(update);

    }

    
}



