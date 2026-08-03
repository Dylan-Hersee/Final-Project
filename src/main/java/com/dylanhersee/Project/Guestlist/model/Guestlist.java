package com.dylanhersee.Project.Guestlist.model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "guestlist")

public class Guestlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String username;
    private String eventName;
    private String guestName;
    private String guestEmail;
    private String guestPhoneNo;
    private boolean guestRSVP;
    private boolean rsvpSent;


    //Setters
    public Guestlist(String username, String eventName) {
        this.username = username;
        this.eventName = eventName;
    }
    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }

    public void setGuestPhoneNo(String guestPhoneNo) {
        this.guestPhoneNo = guestPhoneNo;
    }

    public void setGuestRSVP(boolean guestRSVP) {
        this.guestRSVP = guestRSVP;
    }

    //Getters
    public String getUsername() {
        return username;
    }

    public String getEventName() {
        return eventName;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public String getGuestPhoneNo() {
        return guestPhoneNo;
    }
    

    public boolean isGuestRSVP() {
        return guestRSVP;
    }

    public boolean isRsvpSent() {
        return rsvpSent;
    }

    public void setRsvpSent(boolean rsvpSent) {
        this.rsvpSent = rsvpSent;
    }

   


}
