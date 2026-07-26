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
    public ResponseEntity<List<Guestlist>> getGuestlist(@RequestParam String username){

       List<Guestlist> guests = guestlistService.getGuestlist(username);

        return ResponseEntity.ok(guests);
        

    }

    @PutMapping("/alter")
    public ResponseEntity<Guestlist> alterGuestlist(@PathVariable Long id, @RequestBody Guestlist guestlist){
        Guestlist update = guestlistService.updateGuestlist(id, guestlist);
        return ResponseEntity.ok(update);

    }

    
}
