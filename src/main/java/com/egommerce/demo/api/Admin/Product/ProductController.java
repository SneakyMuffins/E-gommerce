package com.egommerce.demo.api.Admin;

import com.egommerce.demo.annotation.AdminOnly;
import com.egommerce.demo.annotation.RequireAuthorization;
import com.egommerce.demo.model.Category.Category;
import com.egommerce.demo.model.Product.Product;
import com.egommerce.demo.model.Seller.Seller;
import com.egommerce.demo.service.Admin.AdminService;
import com.egommerce.demo.service.Category.CategoryService;
import com.egommerce.demo.service.Seller.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        return adminService.findById(id);
    }

    @AdminOnly
    @RequireAuthorization
    @PostMapping
    public ResponseEntity<String> createProduct(@ModelAttribute Product product,
                                                @RequestParam("images") List<MultipartFile> images,
                                                @Value("${images.directory}") String imagesDirectory) {

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
            product.setSeller(seller);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        try {
            adminService.createProduct(product);

            // Store images in the resources/images folder
            for (MultipartFile image : images) {
                if (!image.isEmpty()) {
                    try {
                        String fileName = UUID.randomUUID().toString() + "-" + image.getOriginalFilename();
                        Path destinationPath = Path.of(imagesDirectory, fileName);
                        Files.copy(image.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        // Handle exception if unable to store the image
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to store image: " + e.getMessage());
                    }
                }
            }
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
