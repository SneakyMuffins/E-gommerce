package com.egommerce.demo.api.Admin.Product;

import com.egommerce.demo.annotation.AdminOnly;
import com.egommerce.demo.annotation.RequireAuthorization;
import com.egommerce.demo.model.Category.Category;
import com.egommerce.demo.model.Product.Product;
import com.egommerce.demo.model.Seller.Seller;
import com.egommerce.demo.service.Admin.AdminService;
import com.egommerce.demo.service.Category.CategoryService;
import com.egommerce.demo.service.Seller.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/products")
@CrossOrigin
public class ProductController {

    private final AdminService adminService;
    private final CategoryService categoryService;
    private final SellerService sellerService;

    @Autowired
    public ProductController(AdminService adminService, CategoryService categoryService, SellerService sellerService) {
        this.adminService = adminService;
        this.categoryService = categoryService;
        this.sellerService = sellerService;
    }

    @GetMapping
    public List<Product> fetchAllProducts() {
        return adminService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product fetchProductById(@PathVariable Long id) {
        return adminService.findProductById(id);
    }

    @AdminOnly
    @RequireAuthorization
    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        try {
            Category category = categoryService.getCategoryById(product.getCategory().getId());
            if (category == null) {
                throw new IllegalArgumentException("Invalid category provided.");
            }
            product.setCategory(category);

            Seller seller = sellerService.getSellerById(product.getSeller().getId());
            if (seller == null) {
                throw new IllegalArgumentException("Invalid seller provided.");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        try {
            adminService.createProduct(product);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok().build();
    }

    @AdminOnly
    @RequireAuthorization
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProductDetails(@PathVariable Long id, @RequestBody Product productUpdates) {
        adminService.updateProductDetails(id, productUpdates);
    }

    @AdminOnly
    @RequireAuthorization
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        adminService.deleteProduct(id);
    }
}
