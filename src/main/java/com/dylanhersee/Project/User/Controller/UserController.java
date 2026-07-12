package com.dylanhersee.Project.User.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dylanhersee.Project.User.model.User;
import com.dylanhersee.Project.User.Service.UserService;


@RestController
public class UserController {

    private UserService userService;

    @Autowired
    private UserController(UserService userService){
        this.userService = userService;
    }


    private List<User> usersList;

    @GetMapping("/user")
    public User getUser(@RequestParam String username){
        Optional<User> optionalUser = userService.getUser(username);
        if(optionalUser.isPresent()){
            return (User) optionalUser.get();
        }

        return null;
    }
    
}
