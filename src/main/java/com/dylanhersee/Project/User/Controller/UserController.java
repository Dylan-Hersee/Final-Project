package com.dylanhersee.Project.User.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.dylanhersee.Project.User.Service.UserService;
import com.dylanhersee.Project.User.model.User;


@RestController
@RequestMapping("/api/user")
public class UserController {

   @Autowired
   private UserService userService;


    @GetMapping("/health")
    public String health(){
        return "API is running";
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user, @RequestParam firebaseToken){

        User newUser = userService.createUser(
            user.getUsername(),
            user.getFirstName(),
            user.getSecondName(),
            user.getDOB(),
            user.getEmail()
        );

        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUser(@RequestParam String username){

        Optional<User> user = userService.getUser(username);

        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        

    }

    @PutMapping("/alter")
    public ResponseEntity<User> alterUser(@PathVariable String username, @RequestBody String updateUser){
        User user = userService.updateUser(username, updateUser);
        return ResponseEntity.ok(user);

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);

        return ResponseEntity.ok("user " + id +" has been successfully deleted");
    }
    
}
