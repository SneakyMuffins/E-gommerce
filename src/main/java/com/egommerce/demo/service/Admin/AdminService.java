package com.egommerce.demo.service.Admin;

import com.egommerce.demo.model.Category.Category;
import com.egommerce.demo.model.Product.Product;
import com.egommerce.demo.model.Seller.Seller;
import com.egommerce.demo.model.User.User;
import com.egommerce.demo.service.Category.CategoryService;
import com.egommerce.demo.service.Product.ProductService;
import com.egommerce.demo.service.Seller.SellerService;
import com.egommerce.demo.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final SellerService sellerService;

    @Autowired
    public AdminService(UserService userService, ProductService productService, CategoryService categoryService, SellerService sellerService) {
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.sellerService = sellerService;
    }

    public List<User> getAllUsers() {
        return userService.findAll();
    }

    public void updateUserDetails(Long id, User userUpdates) {
        userService.updateUserDetails(id, userUpdates);
    }

    public void deleteUser(Long userId) {
        User user = userService.findById(userId);

        if (user != null) {
            userService.deleteUser(userId);
        } else {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }
    }

    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    public Product findById(Long id) {
        return productService.findById(id);
    }

    public void createProduct(Product product) {
        productService.createProduct(product);
    }

    public void updateProductDetails(Long id, Product productUpdates) {
        productService.updateProductDetails(id, productUpdates);
    }

    public void deleteProduct(Long productId) {
        productService.deleteProduct(productId);
    }

    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    public void createCategory(Category category) {
        categoryService.createCategory(category);
    }

    public void updateCategoryDetails(Long id, Category categoryUpdates) {
        categoryService.updateCategoryDetails(id, categoryUpdates);
    }

    public void deleteCategory(Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }

    public List<Seller> getAllSellers() {
        return sellerService.getAllSellers();
    }

    public void createSeller(Seller seller) {
        sellerService.createSeller(seller);
    }

    public void updateSellerDetails(Long id, Seller sellerUpdates) {
        sellerService.updateSellerDetails(id, sellerUpdates);
    }

    public void deleteSeller(Long sellerId) {
        sellerService.deleteSeller(sellerId);
    }
}
