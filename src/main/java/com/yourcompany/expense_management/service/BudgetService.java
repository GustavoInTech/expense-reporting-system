package com.yourcompany.expense_management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yourcompany.expense_management.entity.Budget;
import com.yourcompany.expense_management.repository.BudgetRepository;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    public Budget createBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }

    public Budget getBudgetById(Long id) {
        Optional<Budget> budget = budgetRepository.findById(id);
        if (budget.isPresent()) {
            return budget.get();
        } else {
            throw new RuntimeException("Orçamento não encontrado com ID: " + id);
        }
    }

    public Budget updateBudget(Long id, Budget budgetDetails) {
        Optional<Budget> existingBudget = budgetRepository.findById(id);
        if (existingBudget.isPresent()) {
            Budget budget = existingBudget.get();
            budget.setName(budgetDetails.getName());
            budget.setAmount(budgetDetails.getAmount());
            return budgetRepository.save(budget);
        } else {
            throw new RuntimeException("Orçamento não encontrado com ID: " + id);
        }
    }

    public void deleteBudget(Long id) {
        Optional<Budget> existingBudget = budgetRepository.findById(id);
        if (existingBudget.isPresent()) {
            budgetRepository.delete(existingBudget.get());
        } else {
            throw new RuntimeException("Orçamento não encontrado com ID: " + id);
        }
    }

}
