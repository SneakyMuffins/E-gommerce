package com.egommerce.demo.service;

import com.egommerce.demo.model.Product.Product;
import com.egommerce.demo.model.User.User;
import com.egommerce.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private final UserService userService;
    private final ProductRepository productRepository;

    @Autowired
    public AdminService(UserService userService, ProductRepository productRepository) {
        this.userService = userService;
        this.productRepository = productRepository;
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
        return productRepository.findAll();
    }

    public void createProduct(Product product) {
        productRepository.save(product);
    }

    public void updateProductDetails(Long id, Product productUpdates) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));

        // Update the product details with the provided updates
        product.setName(productUpdates.getName());
        product.setDescription(productUpdates.getDescription());
        product.setPrice(productUpdates.getPrice());

        productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + productId));

        productRepository.delete(product);
    }
}
