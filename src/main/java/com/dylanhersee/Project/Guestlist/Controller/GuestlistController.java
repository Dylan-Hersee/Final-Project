package com.dylanhersee.Project.Guestlist.Controller;

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

import com.dylanhersee.Project.Guestlist.Service.GuestlistService;
import com.dylanhersee.Project.Guestlist.model.Guestlist;
import com.google.firebase.auth.FirebaseAuthException;

@RestController
@RequestMapping("/api/guestlist")
public class GuestlistController {

    @Autowired
    private GuestlistService guestlistService;
    
    @PostMapping("/create")
    public ResponseEntity<Guestlist> createGuestlist(@RequestParam String username, String firebaseToken, @RequestBody Guestlist guestlist) throws FirebaseAuthException{

        Guestlist newGuestlist = guestlistService.createGuestlist(username, firebaseToken, guestlist);

        return ResponseEntity.ok(newGuestlist);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Guestlist>> getGuestlist(@RequestParam String username, @PathVariable Long id, @RequestParam String firebaseToken) throws FirebaseAuthException{

       List<Guestlist> guest = guestlistService.getGuestlist(username, id, firebaseToken);

        return ResponseEntity.ok(guest);
        

    }

    @PutMapping("/alter")
    public ResponseEntity<Guestlist> alterGuestlist(@RequestParam String username, @PathVariable Long id, @RequestBody Guestlist guestlist, @RequestParam String firebaseToken) throws FirebaseAuthException{
        Guestlist update = guestlistService.updateGuestlist(username, id, guestlist, firebaseToken);
        return ResponseEntity.ok(update);

    }

    
}
