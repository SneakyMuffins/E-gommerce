package com.egommerce.demo.repository;

import com.egommerce.demo.model.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);

    List<Product> findAll();

    List<Product> findByCategory(String category);

    Product save(Product product);

    void deleteById(Long id);
}
