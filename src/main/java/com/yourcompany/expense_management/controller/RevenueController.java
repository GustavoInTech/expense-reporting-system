package com.yourcompany.expense_management.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yourcompany.expense_management.entity.Revenue;
import com.yourcompany.expense_management.service.RevenueService;

@RestController
@RequestMapping("/revenues")
public class RevenueController {

    @Autowired
    private RevenueService revenueService;

    @PostMapping
    public ResponseEntity<Revenue> createRevenue(@RequestBody Revenue revenue) {
        Revenue createdRevenue = revenueService.createRevenue(revenue);
        return new ResponseEntity<>(createdRevenue, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Revenue> getRevenueById(@PathVariable Long id) {
        Optional<Revenue> revenue = revenueService.getRevenueById(id);
        return revenue.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Revenue>> getAllRevenues() {
        List<Revenue> revenues = revenueService.getAllRevenues();
        return ResponseEntity.ok(revenues);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Revenue> updateRevenue(@PathVariable Long id, @RequestBody Revenue updatedRevenue) {
        Revenue revenue = revenueService.updateRevenue(id, updatedRevenue);
        return ResponseEntity.ok(revenue);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRevenue(@PathVariable Long id) {
        revenueService.deleteRevenue(id);
        return ResponseEntity.noContent().build();
    }
}
