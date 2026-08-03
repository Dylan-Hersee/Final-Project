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
    public Budget(String username, String eventName, String eventType, double budget, double purchase, String purchaseName, String category) {
   
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

    public void createBudget() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Long getId() {
        return id;
    }

    public boolean isEmpty() {
        if (budget == 0.0 && purchase == 0.0 && purchaseName == null && category == null) {
            return true;
        }
        return false;
    }
}
