package com.dylanhersee.Project.User.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dylanhersee.Project.User.Controller.UserController;
import com.dylanhersee.Project.User.Repository.UserRepository;
import com.dylanhersee.Project.User.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;


@Service
public class UserService {

    private List<User> userList;

    UserRepository userRepository;
    

    public UserService() {
        userList = new ArrayList<>();

    }  

     public User createUser(String firebaseToken, String username, String firstName, String secondName, String DOB, String email, String request) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(firebaseToken);
            String firebaseUid = decodedToken.getUid();

            try{
                if(userRepository.findByUsername(firebaseUid) != null){
                    throw new RuntimeException("user already exists");
                }

                else{
                    User user = new User(
                        username, 
                        firstName, 
                        secondName,
                        DOB, 
                        email
                    );
                    return userRepository.save(user);
                } 
            }catch(Exception e){
                throw new RuntimeException("Error creating User" + e.getMessage());
            }
            
    }
    

    public Optional<User> getUser(String username, String firebaseToken) throws FirebaseAuthException {
        try{
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(firebaseToken);
            String firebaseUid = decodedToken.getUid();

            if(!firebaseUid.equals(username)){
                throw  new RuntimeException
                ("Unauthorized access: UID does not match the provided username.");
            }

            User user = userRepository.findByUsername(username);
            if(user == null){
                throw new RuntimeException("User not found");
            }

            return Optional.of(user);

        }catch(FirebaseAuthException e){
            System.out.println("Error verifying ID token: " + e.getMessage());
        }
      
    }


    public void deleteUser(String username, String firebaseToken) throws FirebaseAuthException {
        
        try{
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(firebaseToken);
            String firebaseUid = decodedToken.getUid();

                if(!firebaseUid.equals(username)){
                    throw new RuntimeException("Unauthorised Access");
                }

                if(userRepository.findByUsername(username) == null){
                    throw new RuntimeException("Username not Found");
                }

                User currentUser = userRepository.findByUsername(username);

                userRepository.deleteById(currentUser.getId());


            } catch(FirebaseAuthException e){
                throw new RuntimeException("User Not Found" + e.getMessage());
            }
    }

    public User updateUser(String username, String firebaseToken, User updateUser) throws FirebaseAuthException {
        try{
      FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(firebaseToken);
            String firebaseUid = decodedToken.getUid();
            

            if(!firebaseUid.equals(username)){
                throw new RuntimeException("Unauthorised Access");
            }

            
            

            if(userRepository.findByUsername(username) == null){
                throw new RuntimeException ("Username not found!");
            }

            User currentUser = userRepository.findByUsername(username);
             
            if(updateUser.getUsername()!= null){
                currentUser.setUsername(updateUser.getUsername());
            }

            if(updateUser.getFirstName()!= null){
                currentUser.setFirstName(updateUser.getFirstName());
            }

            if(updateUser.getSecondName()!= null){
                currentUser.setSecondName(updateUser.getSecondName());
            }
            if(updateUser.getDOB()!= null){
                currentUser.setDOB(updateUser.getDOB());
            }
            if(updateUser.getEmail()!= null){
                currentUser.setEmail(updateUser.getEmail());
            }

            return userRepository.save(currentUser);
            
        }catch(FirebaseAuthException e){
            throw new RuntimeException("Error verifying ID token: " + e.getMessage());
        } 
    }

}
