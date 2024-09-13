package com.yourcompany.expense_management.dto;

import java.time.LocalDate;

public class BudgetDTO {

    private Long id;
    private String name;
    private Double amount;
    private LocalDate startDate;
    private LocalDate endDate;

    // Construtores
    public BudgetDTO() {
    }

    public BudgetDTO(Long id, String name, Double amount, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
