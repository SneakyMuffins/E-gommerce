package com.egommerce.demo.service.Category;

import com.egommerce.demo.exception.ResourceNotFoundException;
import com.egommerce.demo.model.Category.Category;
import com.egommerce.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category updateCategoryDetails(Long id, Category categoryUpdates) {
        Category category = getCategoryById(id);
        if (category != null) {
            category.setName(categoryUpdates.getName());
            categoryRepository.save(category);
        } else {
            throw new ResourceNotFoundException("Category not found with id " + id);
        }

        return category;
    }

    public void deleteCategory(Long id) {
        Category category = getCategoryById(id);
        categoryRepository.delete(category);
    }
}
