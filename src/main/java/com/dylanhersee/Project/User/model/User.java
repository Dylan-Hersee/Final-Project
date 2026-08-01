package com.dylanhersee.Project.User.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "Users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)

    private Long id;
    private String username = "";
    private String DOB = "";
    private String firstName = "";
    private String secondName = "";
    private String email = "";
   

    public User(String username, String firstName, String secondName, String DOB, String email){
        this.username = username;
        this.firstName = firstName;
        this.secondName = secondName;
        this.DOB = DOB;
        this.email = email;
    }


    public String getUsername(){
        return username;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getSecondName(){
        return secondName;

    }
    public String getDOB(){
        return DOB;
    }

    public String getEmail(){
        return email;
    }

 
}
