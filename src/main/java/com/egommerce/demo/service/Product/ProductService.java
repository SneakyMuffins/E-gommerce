package com.egommerce.demo.service.Product;

import com.egommerce.demo.exception.ResourceNotFoundException;
import com.egommerce.demo.model.Address.Address;
import com.egommerce.demo.model.Product.Product;
import com.egommerce.demo.repository.ProductRepository;
import com.egommerce.demo.utility.EntityUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final EntityUpdater entityUpdater;

    @Autowired
    public ProductService(ProductRepository productRepository, EntityUpdater entityUpdater) {
        this.productRepository = productRepository;
        this.entityUpdater = entityUpdater;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id " + id));
    }

    public void createProduct(Product product) {
        productRepository.save(product);
    }

    public void updateProductDetails(Long id, Product productUpdates) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));

        Product updatedProduct = entityUpdater.updateEntity(product, productUpdates);
        productRepository.save(updatedProduct);
    }

    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + productId));

        productRepository.delete(product);
    }
}

