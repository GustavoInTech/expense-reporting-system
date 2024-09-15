package com.yourcompany.expense_management.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yourcompany.expense_management.dto.ExpenseDTO;
import com.yourcompany.expense_management.entity.Category;
import com.yourcompany.expense_management.entity.Expense;
import com.yourcompany.expense_management.repository.CategoryRepository;
import com.yourcompany.expense_management.repository.ExpenseRepository;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Converte Expense para ExpenseDTO
    private ExpenseDTO convertToDto(Expense expense) {
        return modelMapper.map(expense, ExpenseDTO.class);
    }

    // Converte ExpenseDTO para Expense
    private Expense convertToEntity(ExpenseDTO expenseDTO) {
        return modelMapper.map(expenseDTO, Expense.class);
    }

    public List<ExpenseDTO> getAllExpenses() {
        return expenseRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<ExpenseDTO> getExpenseById(Long id) {
        return expenseRepository.findById(id)
                .map(this::convertToDto);
    }

    public ExpenseDTO saveExpense(ExpenseDTO expenseDTO) {
        Expense expense = convertToEntity(expenseDTO);
        Expense savedExpense = expenseRepository.save(expense);
        return convertToDto(savedExpense);
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    public Optional<ExpenseDTO> updateExpense(Long id, ExpenseDTO newExpenseData) {
        return expenseRepository.findById(id).map(expense -> {
            expense.setDescription(newExpenseData.getDescription());
            expense.setAmount(newExpenseData.getAmount());
            expense.setDate(newExpenseData.getDate());

            if (newExpenseData.getCategoryId() != null) {
                Optional<Category> category = categoryRepository.findById(newExpenseData.getCategoryId());
                category.ifPresent(expense::setCategory);
            }

            Expense updatedExpense = expenseRepository.save(expense);

            return modelMapper.map(updatedExpense, ExpenseDTO.class);
        });
        // .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id "
        // + id));
    }

}
