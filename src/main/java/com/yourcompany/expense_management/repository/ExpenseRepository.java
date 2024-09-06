package com.yourcompany.expense_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yourcompany.expense_management.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
