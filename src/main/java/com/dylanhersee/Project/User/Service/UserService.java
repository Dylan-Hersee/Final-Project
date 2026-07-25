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
       Optional<User> optionalUser = Optional.empty();
        for (User user : userList) {
            if (username == user.getUsername()) {
                optionalUser = Optional.of(user);
                return optionalUser;
            }
        }
        return optionalUser;
    }

}
