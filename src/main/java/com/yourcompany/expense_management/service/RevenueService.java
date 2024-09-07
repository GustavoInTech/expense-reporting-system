package com.yourcompany.expense_management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yourcompany.expense_management.entity.Revenue;
import com.yourcompany.expense_management.repository.RevenueRepository;

@Service
public class RevenueService {

    @Autowired
    private RevenueRepository revenueRepository;

    public Revenue createRevenue(Revenue revenue) {
        return revenueRepository.save(revenue);
    }

    public Optional<Revenue> getRevenueById(Long id) {
        return revenueRepository.findById(id);
        // .orElseThrow(() -> new ResourceNotFoundException("Revenue not found with id:
        // " + id));
    }

    public List<Revenue> getAllRevenues() {
        return revenueRepository.findAll();
    }

    public Revenue updateRevenue(Long id, Revenue updatedRevenue) {
        Optional<Revenue> optionalRevenue = getRevenueById(id);
        if (optionalRevenue.isPresent()) {
            Revenue existingRevenue = optionalRevenue.get();
            existingRevenue.setAmount(updatedRevenue.getAmount());
            existingRevenue.setDescription(updatedRevenue.getDescription());
            return revenueRepository.save(existingRevenue);
        } else {
            // throw new ResourceNotFoundException("Revenue not found with id " + id);
        }
        return updatedRevenue;
    }

    public void deleteRevenue(Long id) {
        Optional<Revenue> optionalRevenue = getRevenueById(id);
        if (optionalRevenue.isPresent()) {
            revenueRepository.delete(optionalRevenue.get());
        } else {
            // throw new ResourceNotFoundException("Revenue not found with id " + id);
        }
    }
}
