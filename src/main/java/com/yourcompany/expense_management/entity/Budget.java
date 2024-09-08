package com.yourcompany.expense_management.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "budgets")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDate starDate;

    @Column(nullable = false)
    private LocalDate endData;

    // Getters and Setters

    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return BigDecimal return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * @return LocalDate return the starDate
     */
    public LocalDate getStarDate() {
        return starDate;
    }

    /**
     * @param starDate the starDate to set
     */
    public void setStarDate(LocalDate starDate) {
        this.starDate = starDate;
    }

    /**
     * @return LocalDate return the endData
     */
    public LocalDate getEndData() {
        return endData;
    }

    /**
     * @param endData the endData to set
     */
    public void setEndData(LocalDate endData) {
        this.endData = endData;
    }

}
