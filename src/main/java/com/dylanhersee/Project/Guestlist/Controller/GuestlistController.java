package com.dylanhersee.Project.Guestlist.Controller;

import org.springframework.stereotype.Controller;

@RestController
@RequestMapping("/api/guestlist")
public class GuestlistController {

    @Autowired
    private GuestlistService guestlistService;
    
    @PostMapping("/create")
    public ResponseEntity<Guestlist> createGuestlist(@RequestBody Guestlist guestlist){

        Guestlist newGuestlist = guestlistService.createGuestlist(guestlist);

        return ResponseEntity.ok(newGuestlist);
    }

    @GetMapping("/get")
    public ResponseEntity<Guestlist> getGuestlist(@RequestParam String username){

       List<Guestlist> guests = guestlistService.getGuestlist(username);

        return ResponseEntity.ok(guests);
        

    }

    @PutMapping("/alter")
    public ResponseEntity<Guestlist> alterGuestlist(@PathVariable Long id, @RequestBody Guestlist guestlist){
        User update = guestlistService.updateGuestlist(id, guestlist);
        return ResponseEntity.ok(update);

    }

    
}
