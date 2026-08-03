package com.dylanhersee.Project.Checklist.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dylanhersee.Project.Checklist.model.Checklist;
import com.dylanhersee.Project.Checklist.Repository.ChecklistRepository;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;


public class ChecklistService {

    @Autowired
    private ChecklistRepository checklistRepository;

    public Checklist createChecklist(String username, String firebaseToken, Checklist checklist) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(firebaseToken);
       String firebaseUid = decodedToken.getUid();
        
        if(!firebaseUid.equals(username)){
            throw new RuntimeException("Unauthorised Access");
        }

        return checklistRepository.save(checklist);
    }

    public Checklist getChecklist(String username, String firebaseToken, Long id) throws FirebaseAuthException {
        try{
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(firebaseToken);
            String firebaseUid = decodedToken.getUid();

            if(!firebaseUid.equals(username)){
                throw new RuntimeException("Unauthorised Access");
            }
            Checklist checklist = checklistRepository.findById(id).orElseThrow(() -> new RuntimeException("Checklist not found"));

            return checklist;

        }catch(FirebaseAuthException e){
            throw new RuntimeException("User Not Found" + e.getMessage());
        }
    }

    public List<Checklist> getAllChecklist(String username, String firebaseToken) throws FirebaseAuthException {
        try{
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(firebaseToken);
       String firebaseUid = decodedToken.getUid();

       if(!firebaseUid.equals(username)){
        throw new RuntimeException("Unauthorised Access");
       }

       List<Checklist> checklist = checklistRepository.findByUsername(username);
       if(checklist == null){
        throw new RuntimeException("Checklist not found");
       }

       return checklist;


        }catch(FirebaseAuthException e){
            throw new RuntimeException("User Not Found" + e.getMessage());
        }
        
    }

    public Checklist updateChecklist(Long id, String username, String firebaseToken, Checklist checklist) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(firebaseToken);
       String firebaseUid = decodedToken.getUid();
        if(!firebaseUid.equals(username)){
            throw new RuntimeException("Unauthorised Access");
        }

        Checklist currentItem = checklistRepository.findById(id).orElseThrow(() -> new RuntimeException("item not found"));
        

        if(checklist.getTarget() != null){
            currentItem.setTarget(checklist.getTarget());
        }

        if(checklist.getDate() != null){
            currentItem.setDate(checklist.getDate());
        }

        currentItem.setCompleted(checklist.isCompleted());

        return checklistRepository.save(currentItem);
    }
    
}
