package com.dylanhersee.Project.Checklist.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "checklist")

public class Checklist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    
    private String username;
    private String eventName;
    private String target;
    private LocalDate date;
    private String timeframe;
    private boolean completed;
    
    
    private Long prevTarget;
    private Long nextTarget;

    public Checklist(String username, String eventName, String target, LocalDate date, String timeframe, boolean completed, Long prevTarget, Long nextTarget) {
        this.username = username;
        this.eventName = eventName;
        this.target = target;
        this.date = date;
        this.timeframe = timeframe;
        this.completed = completed;
        this.prevTarget = prevTarget;
        this.nextTarget = nextTarget;
    }

    public String getUsername() {
        return username;
    }

    public String getEventName() {
        return eventName;
    }

    public String getTarget() {
        return target;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getTimeframe() {
        return timeframe;
    }

    public boolean isCompleted() {
        return completed;
    }

    public Long getPrevTarget() {
        return prevTarget;
    }

    public Long getNextTarget() {
        return nextTarget;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setDueDate(LocalDate date) {
        this.date = date;
    }
    
    public void setPrevTarget(Long prevTarget) {
        this.prevTarget = prevTarget;
    }  
    
    public void setNextTarget(Long nextTarget) {
        this.nextTarget = nextTarget;
    }



}
