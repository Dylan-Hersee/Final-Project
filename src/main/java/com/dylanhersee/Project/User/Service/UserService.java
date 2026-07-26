package com.dylanhersee.Project.User.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dylanhersee.Project.User.model.User;


@Service
public class UserService {

    private List<User> userList;

    public UserService() {
        userList = new ArrayList<>();

    }  

    public Optional<User> getUser(String username) {
        return null;
      
    }

    public User createUser(String username) {
        
    }

    public void deleteUser(Long id) {
        
    }

    public User updateUser(String username, String updateUser) {
      
    }

    public User createUser(String username, String firstName, String secondName, String dob, String email) {


    }
}
