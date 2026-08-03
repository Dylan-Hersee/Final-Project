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

    public Checklist getChecklist(String username, String firebaseToken, Checklist checklist) throws FirebaseAuthException {
        try{
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(firebaseToken);
            String firebaseUid = decodedToken.getUid();

            if(!firebaseUid.equals(username)){
                throw new RuntimeException("Unauthorised Access");
            }
        

            return checklistRepository.save(checklist);

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

    public Checklist updateChecklist(String username, String firebaseToken, Checklist checklist, Checklist newItem) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(firebaseToken);
       String firebaseUid = decodedToken.getUid();
        if(!firebaseUid.equals(username)){
            throw new RuntimeException("Unauthorised Access");
        }

        Checklist currentItem = checklistRepository.findById(checklist.getId()).orElseThrow(() -> new RuntimeException("item not found"));

        if(newItem.getTarget() != null){
            currentItem.setTarget(newItem.getTarget());
        }

        if(newItem.getDueDate() != null){
            currentItem.setDueDate(newItem.getDueDate());
        }

        currentItem.setCompleted(newItem.isCompleted());

        return checklistRepository.save(currentItem);
    }
    
}
