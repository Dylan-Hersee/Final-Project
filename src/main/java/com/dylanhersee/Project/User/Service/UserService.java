package com.dylanhersee.Project.User.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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

     public User createUser(String username, String firstName, String secondName, String DOB, String email) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(username);
            String firebaseUid = decodedToken.getUid();

            try{
                if(userRepository.findByUsername(firebaseUid) != null){
                    throw new RuntimeException("user already exists");
                }

                else{
                    User user = new User(
                        firebaseUid, 
                        firstName,
                        secondName,
                        DOB,  
                        email 
                    );
                    return userRepository.save(user);
                } 
            } catch(FirebaseAuthExeption e){
                throw new RuntimeException("Unauthorised Access" + e.getMessage());
            }catch(Exception e){
                throw new RuntimeException("Error creating User" + e.getMessage);
            }
            
    }
    

    public Optional<User> getUser(String username) {
        try{
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(username);
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

    public void deleteUser(Long id, String username) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(username);
            String firebaseUid = decodedToken.getUid();
    }

    public User updateUser(String username, String updateUser) throws FirebaseAuthException {
      FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(username);
            String firebaseUid = decodedToken.getUid();
    }

}
