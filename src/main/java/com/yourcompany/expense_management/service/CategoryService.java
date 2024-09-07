package com.yourcompany.expense_management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yourcompany.expense_management.entity.Category;
import com.yourcompany.expense_management.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public Optional<Object> updateCategory(Long id, Category newCategoryData) {
        return categoryRepository.findById(id)
                .map(category -> {
                    category.setName(newCategoryData.getName());
                    return categoryRepository.save(category);
                });
        // .orElseThrow(() -> new ResourceNotFoundException("Category not found with id
        // " + id));
    }
}
