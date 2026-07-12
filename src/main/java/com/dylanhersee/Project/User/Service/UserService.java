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

        User user1 = new User("dylanHersee", "05/12/1997", "Dylan", "Hersee", "herseed10@gmail.com");
        User user2 = new User("BeccaMcd", "09/01/1997", "Rebecca", "McDonald", "beccaMcd97@icloud.com");

        userList.addAll((Collection<? extends User>) Arrays.asList(user1, user2));
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
