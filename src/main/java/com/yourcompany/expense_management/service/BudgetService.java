package com.yourcompany.expense_management.service;

import com.yourcompany.expense_management.entity.Budget;
import com.yourcompany.expense_management.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    public Budget save(Budget budget) {
        return budgetRepository.save(budget);
    }

    public List<Budget> findAll() {
        return budgetRepository.findAll();
    }

    public Budget findById(Long id) {
        return budgetRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        budgetRepository.deleteById(id);
    }

    public Budget update(Long id, Budget budgetDetails) {
        Optional<Budget> optionalBudget = budgetRepository.findById(id);

        if (optionalBudget.isPresent()) {
            Budget existingBudget = optionalBudget.get();

            existingBudget.setName(budgetDetails.getName());
            existingBudget.setAmount(budgetDetails.getAmount());
            existingBudget.setStartDate(budgetDetails.getStartDate());
            existingBudget.setEndDate(budgetDetails.getEndDate());

            return budgetRepository.save(existingBudget);
        } else {
            return null;
        }
    }
}
