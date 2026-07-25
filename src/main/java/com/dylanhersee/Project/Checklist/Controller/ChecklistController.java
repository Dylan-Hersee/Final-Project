package com.dylanhersee.Project.Checklist.Controller;

import org.springframework.stereotype.Controller;

@RestController
@RequestMapping("/api/checklist")
public class ChecklistController {

    @Autowired
    private ChecklistService checklistService;


    @PostMapping("/create")
    public ResponseEntity<Checklist> createChecklist(@RequestBody Checklist checklist){

        Checklist newChecklist = checklistService.createChecklist(checklist);

        return ResponseEntity.ok(newChecklist);
    }

    @GetMapping("/get")
    public ResponseEntity<Checklist> getChecklist(@RequestParam String username){

       List<Checklist> checklist = checklistService.getChecklist(username);

        return ResponseEntity.ok(checklist);
        

    }

    @PutMapping("/alter")
    public ResponseEntity<Checklist> alterChecklist(@PathVariable Long id, @RequestBody Checklist checklist){
        Checklist update = checklistService.updateChecklist(id, checklist);
        return ResponseEntity.ok(update);

    }

    
}
