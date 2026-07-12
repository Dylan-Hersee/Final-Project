package com.dylanhersee.Project.User.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dylanhersee.Project.User.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   
    User findByUsername(String username);
    User findByEmail(String email);

}
