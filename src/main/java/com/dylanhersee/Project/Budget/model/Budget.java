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
    
    private long id;
    
    private String username;
    private double budget;
    private double purchase;
    private String eventName;
    private String eventType;
    private String purchaseName;
    private String category;

    public Budget(double budget, String username, String eventName, String eventType){
        this.budget = budget;
        this.username = username;
        this.eventName = eventName;
        this.eventType = eventType;
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
}
