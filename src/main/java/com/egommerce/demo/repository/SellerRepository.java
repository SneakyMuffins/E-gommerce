package com.egommerce.demo.repository;

import com.egommerce.demo.model.Seller.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
    Optional<Seller> findById(Long id);
    List<Seller> findAll();
    Seller save(Seller seller);
    void deleteById(Long id);
}
