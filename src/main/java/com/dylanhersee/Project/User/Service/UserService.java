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
    //method creates a new user and stores all the users details in the DB
     public User createUser(String username, String firstName, String secondName, String DOB, String email) throws FirebaseAuthException {
        String firebaseToken = "";
        
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(firebaseToken);
            String firebaseUid = decodedToken.getUid();

            try{
                if(userRepository.findByUsername(username) != null){
                    throw new RuntimeException("user already exists");
                } else {
                    User user = new User();
                    user.setUsername(username);
                    user.setFirstName(firstName);
                    user.setSecondName(secondName);
                    user.setDOB(DOB);
                    user.setEmail(email);
                    return userRepository.save(user);
                }
            }catch(RuntimeException e){
                throw new RuntimeException("Error creating User" + e.getMessage());
            }
            
    }
    
    //method gets user data 
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
      return Optional.empty();
    }

        //method deletes users data
    public void deleteUser(Long id, String username, String firebaseToken) throws FirebaseAuthException {
        
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
    //updates user data
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
