package com.dylanhersee.Project.Checklist.Controller;


import java.time.LocalDate;

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

    //Creates User Checklist
    @PostMapping("/create")
    public ResponseEntity<Checklist> createChecklist(@RequestBody Checklist checklist, @RequestParam String username, @RequestParam String eventName, @RequestParam String eventType) throws FirebaseAuthException{

        Checklist newChecklist = checklistService.createChecklist(username, username, checklist);

        return ResponseEntity.ok(newChecklist);
    }

    //Grabs requested checklist
    @GetMapping("/get")
    public ResponseEntity<Checklist> getChecklist(@RequestParam String username, @PathVariable Long id ) throws FirebaseAuthException{

       Checklist checklist = checklistService.getChecklist(username, username, id);

        return ResponseEntity.ok(checklist);
        

    }
    //Edits targeted Checklist
    @PutMapping("/alter")
    public ResponseEntity<Checklist> alterChecklist(@PathVariable Long id, @RequestParam String username, @RequestBody Checklist checklist) throws FirebaseAuthException{
        Checklist update = checklistService.updateChecklist(id, username, username, checklist);
        return ResponseEntity.ok(update);

    }
    //Checks whether target is due based on the current date and if the due date is within the next 7 days
    public Boolean isDue(LocalDate dueDate) throws FirebaseAuthException {
        return LocalDate.now().plusDays(7).isAfter(dueDate);
    }


    
}
