package com.egommerce.demo.seeder;

import com.egommerce.demo.model.Address.Address;
import com.egommerce.demo.model.Order.Order;
import com.egommerce.demo.model.Product.Product;
import com.egommerce.demo.model.User.User;
import com.egommerce.demo.service.Address.AddressService;
import com.egommerce.demo.service.Order.OrderService;
import com.egommerce.demo.service.Product.ProductService;
import com.egommerce.demo.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class OrderSeeder {

    private final OrderService orderService;
    private final AddressService addressService;
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public OrderSeeder(OrderService orderService, AddressService addressService, ProductService productService, UserService userService) {
        this.orderService = orderService;
        this.addressService = addressService;
        this.productService = productService;
        this.userService = userService;
    }

    public void seed() {
        if (orderService.getAllOrders().isEmpty()) {
            // Get address by ID
            Long addressId = 1L;
            Address address = addressService.findById(addressId);

            // Get all products
            List<Product> products = productService.getAllProducts();

            // Get user by ID
            Long userId = 1L;
            User user = userService.findById(userId);

            // Create order
            Order order = new Order();
            order.setAmountPaid(new BigDecimal("99.99"));
            order.setOrderStatus("Pending");
            order.setAddress(address);
            order.setProducts(products);
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setUser(user);

            orderService.createOrder(order);

            System.out.println("Order seeding completed.");
        }
    }
}
