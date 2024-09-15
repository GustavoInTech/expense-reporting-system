package com.yourcompany.expense_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yourcompany.expense_management.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
