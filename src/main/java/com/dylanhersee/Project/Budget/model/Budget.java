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
    private double updateBudget;
    private double purchase;
    private String eventName;
    private String eventType;
    private String purchaseName;
    private String category;

    public Budget(double budget, double updateBudget, double purchase, String eventName, String eventType, 
                  String purchaseName, String category){
        this.budget = budget;
        this.updateBudget = updateBudget;
        this.purchase = purchase;
        this.eventName = eventName;
        this.eventType = eventType;
        this.purchaseName = purchaseName;
        this.category = category;
    }

    public Budget(String firebaseId, String eventName2, String eventType2, double budget2) {
        //TODO Auto-generated constructor stub
    }

    public Budget(String firebaseId, String eventName2, String eventType2, double purchase2, String purchaseName2,
			String category2) {
		//TODO Auto-generated constructor stub
	}

	public double getBudget(){
        return budget;
    }

    public double getUpdate(){
        return updateBudget;
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
