package com.egommerce.demo.service.Order;

import com.egommerce.demo.exception.ResourceNotFoundException;
import com.egommerce.demo.model.Order.Order;
import com.egommerce.demo.repository.OrderRepository;
import com.egommerce.demo.utility.EntityUpdater;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final EntityUpdater entityUpdater;

    @Autowired
    public OrderService(OrderRepository orderRepository, EntityUpdater entityUpdater) {
        this.orderRepository = orderRepository;
        this.entityUpdater = entityUpdater;
    }

    public void createOrder(Order order) {
        orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + orderId));
    }

    public List<Order> getAllOrdersByUserId(Long userId) {
        return orderRepository.findAllByUserId(userId);
    }

    public void updateOrderDetails(Long orderId, Order orderUpdates) {
        Order order = getOrderById(orderId);
        if (order != null) {
            Order updatedOrder = entityUpdater.updateEntity(order, orderUpdates);
            orderRepository.save(updatedOrder);
        } else {
            throw new ResourceNotFoundException("Order not found with id " + orderId);
        }
    }

    public void deleteOrder(Long orderId) {
        Order order = getOrderById(orderId);
        orderRepository.delete(order);
    }
}