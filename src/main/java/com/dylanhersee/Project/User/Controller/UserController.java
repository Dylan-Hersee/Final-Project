package com.dylanhersee.Project.User.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dylanhersee.Project.User.Service.UserService;
import com.dylanhersee.Project.User.model.User;
import com.google.firebase.auth.FirebaseAuthException;





@RestController
@RequestMapping("/api/user")
public class UserController {

   @Autowired
   private UserService userService;
    //Creates new user
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User requestUser) throws Exception {

        User newUser = userService.createUser(
            requestUser.getEmail(), 
            requestUser.getFirstName(), 
            requestUser.getSecondName(), 
            requestUser.getUsername(), 
            requestUser.getDOB()
        );

        return ResponseEntity.ok(newUser);
    }
        //gets user data
    @GetMapping("/user")
    public ResponseEntity<User> getUser(@RequestParam String username, @RequestParam String firebaseToken) throws FirebaseAuthException {

        Optional<User> user = userService.getUser(username, firebaseToken);

        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        

    }
    //Edits data
    @PutMapping("/alter")
    public ResponseEntity<User> alterUser(@RequestParam String username, @RequestBody String updateUser, @PathVariable User currentUser) throws FirebaseAuthException {
        User user = userService.updateUser(username, updateUser, currentUser);
        return ResponseEntity.ok(user);

    }

    //deletes user
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id, @RequestParam String username, @RequestParam String firebaseToken) throws FirebaseAuthException {
        userService.deleteUser(id, username, firebaseToken);

        return ResponseEntity.ok("user " + id +" has been successfully deleted");
    }
    
}
