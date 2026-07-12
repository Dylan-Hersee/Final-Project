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
    private int guestPhoneNo;
    private boolean guestRSVP;
    private boolean rsvpSent;



    public Guestlist(String username, String eventName, String guestName, String guestEmail, int guestPhoneNo, boolean guestRSVP, boolean rsvpSent) {
        this.username = username;
        this.eventName = eventName;
        this.guestName = guestName;
        this.guestEmail = guestEmail;
        this.guestPhoneNo = guestPhoneNo;
        this.guestRSVP = guestRSVP;
        this.rsvpSent = rsvpSent;
    }

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

    public int getGuestPhoneNo() {
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
