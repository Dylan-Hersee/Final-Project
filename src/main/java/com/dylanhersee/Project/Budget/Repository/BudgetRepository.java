package com.dylanhersee.Project.Budget.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dylanhersee.Project.Budget.model.Budget;


@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long>{
List<Budget> findByUsername(String username);

}
