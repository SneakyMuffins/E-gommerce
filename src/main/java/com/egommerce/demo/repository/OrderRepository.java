package com.egommerce.demo.repository;

import com.egommerce.demo.model.Order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findById(Long id);
    List<Order> findAll();
    List<Order> findAllByUserId(Long userId);
    Order save(Order order);
    void deleteById(Long id);
}