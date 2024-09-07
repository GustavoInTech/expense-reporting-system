package com.yourcompany.expense_management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yourcompany.expense_management.entity.Expense;
import com.yourcompany.expense_management.repository.ExpenseRepository;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Optional<Expense> getExpenseById(Long id) {
        return expenseRepository.findById(id);
    }

    public Expense saveExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    public Optional<Object> updateExpense(Long id, Expense newExpenseData) {
        return expenseRepository.findById(id)
                .map(expense -> {
                    expense.setDescription(newExpenseData.getDescription());
                    expense.setAmount(newExpenseData.getAmount());
                    expense.setCategory(newExpenseData.getCategory());
                    expense.setDate(newExpenseData.getDate());
                    return expenseRepository.save(expense);
                });
        // .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id "
        // + id));
    }

}
