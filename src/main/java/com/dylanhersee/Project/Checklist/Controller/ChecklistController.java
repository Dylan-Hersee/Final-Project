package com.dylanhersee.Project.Checklist.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dylanhersee.Project.Checklist.Service.ChecklistService;
import com.dylanhersee.Project.Checklist.model.Checklist;
import com.google.firebase.auth.FirebaseAuthException;

@RestController
@RequestMapping("/api/checklist")
public class ChecklistController {

    @Autowired
    private ChecklistService checklistService;


    @PostMapping("/create")
    public ResponseEntity<Checklist> createChecklist(@RequestBody Checklist checklist, @RequestParam String username, @RequestParam String eventName, @RequestParam String eventType) throws FirebaseAuthException{

        Checklist newChecklist = checklistService.createChecklist(username, username, checklist);

        return ResponseEntity.ok(newChecklist);
    }

    @GetMapping("/get")
    public ResponseEntity<Checklist> getChecklist(@RequestParam String username, @RequestParam Long id ) throws FirebaseAuthException{

       Checklist checklist = checklistService.getChecklist(username, username, id);

        return ResponseEntity.ok(checklist);
        

    }

    @PutMapping("/alter")
    public ResponseEntity<Checklist> alterChecklist(@PathVariable Long id, @RequestBody Checklist checklist){
        Checklist update = checklistService.updateChecklist(id, checklist);
        return ResponseEntity.ok(update);

    }

    public Boolean isDue(@RequestParam String username, @RequestParam String firebaseToken, @RequestParam Long id) throws FirebaseAuthException {
        return localDate.plusDays(7).isAfter(checklist.getDueDate());
    }


    
}
