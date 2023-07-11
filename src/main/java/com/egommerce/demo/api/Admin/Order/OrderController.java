package com.egommerce.demo.api.Admin.Order;

import com.egommerce.demo.annotation.AdminOnly;
import com.egommerce.demo.annotation.RequireAuthorization;
import com.egommerce.demo.exception.ResourceNotFoundException;
import com.egommerce.demo.model.Address.Address;
import com.egommerce.demo.model.Category.Category;
import com.egommerce.demo.model.Order.Order;
import com.egommerce.demo.model.Product.Product;
import com.egommerce.demo.model.User.User;
import com.egommerce.demo.service.Address.AddressService;
import com.egommerce.demo.service.Admin.AdminService;
import com.egommerce.demo.service.Product.ProductService;
import com.egommerce.demo.service.User.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin/orders")
@CrossOrigin
public class OrderController {

    private final AdminService adminService;
    private final UserService userService;
    private final AddressService addressService;
    private final ProductService productService;

    @Autowired
    public OrderController(AdminService adminService, UserService userService, AddressService addressService, ProductService productService) {
        this.adminService = adminService;
        this.userService = userService;
        this.addressService = addressService;
        this.productService = productService;
    }

    @RequireAuthorization
    @PostMapping
    public ResponseEntity<Object> createOrder(HttpServletRequest request, @RequestBody Order order) {
        try {
            User authenticatedUser = userService.getAuthenticatedUser(request);

            if (authenticatedUser == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
            }
            order.setUser(authenticatedUser);
            order.setOrderNumber(UUID.randomUUID().toString());

            List<Product> orderProducts = new ArrayList<>();
            for (Product product : order.getProducts()) {
                Product fetchedProduct = productService.findById(product.getId());
                orderProducts.add(fetchedProduct);
            }
            order.setProducts(orderProducts);

            adminService.createOrder(order);

            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @AdminOnly
    @RequireAuthorization
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = adminService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @RequireAuthorization
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
        Order order = adminService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }

    @RequireAuthorization
    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUserId(@PathVariable Long userId) {
        return adminService.getAllOrdersByUserId(userId);
    }

    @AdminOnly
    @RequireAuthorization
    @PatchMapping("/{orderId}")
    public ResponseEntity<Void> updateOrder(@PathVariable Long orderId, @RequestBody Order orderUpdates) {
        adminService.updateOrderDetails(orderId, orderUpdates);
        return ResponseEntity.noContent().build();
    }

    @AdminOnly
    @RequireAuthorization
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        adminService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}
