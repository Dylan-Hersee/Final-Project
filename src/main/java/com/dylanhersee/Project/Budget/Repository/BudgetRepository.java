package com.dylanhersee.Project.Budget.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dylanhersee.Project.Budget.model.Budget;

//Repository class using JPA to act as my call and editor for the SQL database
@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long>{
    //created my own methods to find username and both username and eventname in my DB to allow me to gather budget in both dashboard and specific event page
List<Budget> findByUsername(String username);
List<Budget> findByUsernameAndEventName(String username, String eventName);

//request SQL delete budget based on username given
void deleteByUsername(String username);

}
