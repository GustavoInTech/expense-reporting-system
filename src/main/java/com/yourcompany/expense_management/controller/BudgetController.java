package com.yourcompany.expense_management.controller;

import com.yourcompany.expense_management.dto.BudgetDTO;
import com.yourcompany.expense_management.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/budgets")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @PostMapping
    public ResponseEntity<BudgetDTO> createBudget(@RequestBody BudgetDTO budgetDTO) {
        BudgetDTO newBudget = budgetService.save(budgetDTO);
        return ResponseEntity.ok(newBudget);
    }

    @GetMapping
    public ResponseEntity<List<BudgetDTO>> getAllBudgets() {
        List<BudgetDTO> budgets = budgetService.findAll();
        return ResponseEntity.ok(budgets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BudgetDTO> getBudgetById(@PathVariable Long id) {
        BudgetDTO budgetDTO = budgetService.findById(id);
        if (budgetDTO != null) {
            return ResponseEntity.ok(budgetDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BudgetDTO> updateBudget(@PathVariable Long id, @RequestBody BudgetDTO budgetDTO) {
        BudgetDTO updatedBudget = budgetService.update(id, budgetDTO);
        if (updatedBudget != null) {
            return ResponseEntity.ok(updatedBudget);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable Long id) {
        BudgetDTO budgetDTO = budgetService.findById(id);
        if (budgetDTO != null) {
            budgetService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
