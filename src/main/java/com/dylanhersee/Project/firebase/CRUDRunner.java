package com.dylanhersee.Project.firebase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

//using a Crud Runner to act as the communication point between my application and my firebase auth
@Configuration
public class CRUDRunner {

    public static void main(String[] args)throws FileNotFoundException, IOException{
        
        ClassLoader classLoader = CRUDRunner.class.getClassLoader();

        File file = new File(Objects.requireNonNull(classLoader.getResource("ServiceAccountKey.json")).getFile());
        
        FileInputStream serviceAccount =
        new FileInputStream(file.getAbsolutePath());

        //this firebase variable is used to set the user credentials from firebase  
        FirebaseOptions options = new FirebaseOptions.Builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .build();

        //intialises the credentials
        FirebaseApp.initializeApp(options);

        //runs application 
        SpringApplication.run(CRUDRunner.class, args);

       
    }

}
