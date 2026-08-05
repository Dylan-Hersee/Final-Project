package com.dylanhersee.Project.Guestlist.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dylanhersee.Project.Guestlist.Repository.GuestlistRepository;
import com.dylanhersee.Project.Guestlist.model.Guestlist;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;


@Service
public class GuestlistService {

    @Autowired
    private GuestlistRepository guestlistRepository;

    public Guestlist createGuestlist(String username, String firebaseToken, Guestlist guestlist) throws FirebaseAuthException {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(firebaseToken);
       String firebaseUid = decodedToken.getUid();

       if(!firebaseUid.equals(username)){
           throw new RuntimeException("Unauthorised Access");
        }
        
        return guestlistRepository.save(guestlist);
    }

    public List<Guestlist> getGuestlist(String username, Long id, String firebaseToken) throws FirebaseAuthException{
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(firebaseToken);
       String firebaseUid = decodedToken.getUid();

       if(!firebaseUid.equals(username)){
           throw new RuntimeException("Unauthorised Access");
        }

        List<Guestlist> guests = guestlistRepository.findByUsername(username);
        if(guests == null){
            throw new RuntimeException("Guestlist not found");
        }

        return guests;
        
    }

    public Guestlist updateGuestlist(String username, Long id, Guestlist guestlist, String firebaseToken) throws FirebaseAuthException{
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(firebaseToken);
       String firebaseUid = decodedToken.getUid();

       if(!firebaseUid.equals(username)){
           throw new RuntimeException("Unauthorised Access");
        }

        Guestlist currentGuest = guestlistRepository.findById(id).orElseThrow(() -> new RuntimeException("Guest not found"));

        if(guestlist.getGuestName() != null){
            currentGuest.setGuestName(guestlist.getGuestName());
        }
        if(guestlist.getGuestEmail() != null){
            currentGuest.setGuestEmail(guestlist.getGuestEmail());
        }
        if(guestlist.getGuestPhoneNo() != null){
            guestlist.setGuestPhoneNo(guestlist.getGuestPhoneNo());
        }

        return guestlistRepository.save(currentGuest);
        
    }


    public void removeGuest(String username, Long id, Guestlist guestlist, String firebaseToken) throws FirebaseAuthException{
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(firebaseToken);
       String firebaseUid = decodedToken.getUid();

       if(!firebaseUid.equals(username)){
           throw new RuntimeException("Unauthorised Access");
        }

        Guestlist currentGuest = guestlistRepository.findById(id).orElseThrow(() -> new RuntimeException("Guest not found"));

        guestlistRepository.deleteById(currentGuest.getId());
    }
    
    public Boolean checkRSVP(String username, Long id, Guestlist guestlist, String firebaseToken) throws FirebaseAuthException{
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(firebaseToken);
       String firebaseUid = decodedToken.getUid();

       if(!firebaseUid.equals(username)){
           throw new RuntimeException("Unauthorised Access");
        }

        Guestlist currentGuest = guestlistRepository.findById(id).orElseThrow(() -> new RuntimeException("Guest not found"));

        currentGuest.setGuestRSVP(guestlist.isGuestRSVP());
        currentGuest.setRsvpSent(guestlist.isRsvpSent());

        return guestlistRepository.save(currentGuest).isGuestRSVP();
    }
}
