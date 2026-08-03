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
import com.google.firebase.auth.FirebaseAuthException;





@RestController
@RequestMapping("/api/user")
public class UserController {

   @Autowired
   private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody String username, @RequestParam String firstName, @RequestParam String secondName, @RequestParam String DOB, @RequestParam String email) throws Exception {

        User newUser = userService.createUser(
            username,
            firstName,
            secondName,
            DOB,
            email
        );

        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUser(@RequestParam String username, @RequestParam String firebaseToken) throws FirebaseAuthException {

        Optional<User> user = userService.getUser(username, firebaseToken);

        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        

    }

    @PutMapping("/alter")
    public ResponseEntity<User> alterUser(@RequestParam String username, @RequestBody String updateUser, @PathVariable User currentUser) throws FirebaseAuthException {
        User user = userService.updateUser(username, updateUser, currentUser);
        return ResponseEntity.ok(user);

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id, @RequestParam String username, @RequestParam String firebaseToken) throws FirebaseAuthException {
        userService.deleteUser(id, username, firebaseToken);

        return ResponseEntity.ok("user " + id +" has been successfully deleted");
    }
    
}
