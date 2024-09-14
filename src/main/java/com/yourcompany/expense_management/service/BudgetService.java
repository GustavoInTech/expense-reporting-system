package com.yourcompany.expense_management.service;

import com.yourcompany.expense_management.dto.BudgetDTO;
import com.yourcompany.expense_management.entity.Budget;
import com.yourcompany.expense_management.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    // Método para converter Budget para BudgetDTO
    private BudgetDTO convertToDTO(Budget budget) {
        BudgetDTO dto = new BudgetDTO();
        dto.setId(budget.getId());
        dto.setName(budget.getName());
        dto.setAmount(budget.getAmount());
        dto.setStartDate(budget.getStartDate());
        dto.setEndDate(budget.getEndDate());
        return dto;
    }

    // Método para converter BudgetDTO para Budget (entidade)
    private Budget convertToEntity(BudgetDTO budgetDTO) {
        Budget budget = new Budget();
        budget.setName(budgetDTO.getName());
        budget.setAmount(budgetDTO.getAmount());
        budget.setStartDate(budgetDTO.getStartDate());
        budget.setEndDate(budgetDTO.getEndDate());
        return budget;
    }

    public BudgetDTO save(BudgetDTO budgetDTO) {
        Budget budget = convertToEntity(budgetDTO);
        Budget savedBudget = budgetRepository.save(budget);
        return convertToDTO(savedBudget);
    }

    public List<BudgetDTO> findAll() {
        List<Budget> budgets = budgetRepository.findAll();
        return budgets.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public BudgetDTO findById(Long id) {
        Optional<Budget> budget = budgetRepository.findById(id);
        return budget.map(this::convertToDTO).orElse(null);
    }

    public void deleteById(Long id) {
        budgetRepository.deleteById(id);
    }

    public BudgetDTO update(Long id, BudgetDTO budgetDTO) {
        Optional<Budget> optionalBudget = budgetRepository.findById(id);

        if (optionalBudget.isPresent()) {
            Budget existingBudget = optionalBudget.get();
            existingBudget.setName(budgetDTO.getName());
            existingBudget.setAmount(budgetDTO.getAmount());
            existingBudget.setStartDate(budgetDTO.getStartDate());
            existingBudget.setEndDate(budgetDTO.getEndDate());

            Budget updatedBudget = budgetRepository.save(existingBudget);
            return convertToDTO(updatedBudget);
        } else {
            return null;
        }
    }
}
