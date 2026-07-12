package com.dylanhersee.Project.Budget.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dylanhersee.Project.User.model.User;

@RestController
public class BudgetController{

    @GetMapping
   public User getUser(@RequestParam String firebaseId) {
    return null;
   }
    
}



