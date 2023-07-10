package com.egommerce.demo.api.Admin.Category;

import com.egommerce.demo.annotation.AdminOnly;
import com.egommerce.demo.annotation.RequireAuthorization;
import com.egommerce.demo.model.Category.Category;
import com.egommerce.demo.service.Admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/categories")
@CrossOrigin
public class CategoryController {

    private final AdminService adminService;

    @Autowired
    public CategoryController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return adminService.getAllCategories();
    }


    @AdminOnly
    @RequireAuthorization
    @PostMapping
    public ResponseEntity<Object> createCategory(@RequestBody Category category) {
        try {
            adminService.createCategory(category);

            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @AdminOnly
    @RequireAuthorization
    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateCategoryDetails(@PathVariable Long id, @RequestBody Category categoryUpdates) {
        try {
            adminService.updateCategoryDetails(id, categoryUpdates);

            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @AdminOnly
    @RequireAuthorization
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        try {
            adminService.deleteCategory(id);

            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
