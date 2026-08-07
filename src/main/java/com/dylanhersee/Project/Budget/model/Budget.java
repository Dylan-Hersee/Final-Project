package com.dylanhersee.Project.Budget.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;




@Entity
@Table(name = "budget")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String username;
    private double budget;
    private double purchase;
    private String eventName;
    private String eventType;
    private String purchaseName;
    private String category;

    //Setters
    public Budget() {
   
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setEventName(String eventName){
        this.eventName = eventName;
    }

    public void setEventType(String eventType){
        this.eventType = eventType;
    }

    public void setBudget(double budget){
        this.budget = budget;
    }

    public void setPurchase(double purchase){
        this.purchase = purchase;
    }

    public void setPurchaseName(String purchaseName){
        this.purchaseName = purchaseName;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public void setId(Long id) {
        this.id = id;
    }


    //Getters
    public String getUsername(){
        return username;
    }

	public double getBudget(){
        return budget;
    }

    public double getPurchase(){
        return purchase;
    }

    public String getEventName(){
        return eventName;
    }

    public String getEventType(){
        return eventType;
    }

    public String getPurchaseName(){
        return purchaseName;
    }

    public String getCategory(){
        return category;
    }

    public Long getId() {
        return id;
    }

    public boolean isEmpty() {
        return false;
    }
    
}

