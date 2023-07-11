package com.egommerce.demo.service.Admin;

import com.egommerce.demo.model.Category.Category;
import com.egommerce.demo.model.Order.Order;
import com.egommerce.demo.model.Product.Product;
import com.egommerce.demo.model.Seller.Seller;
import com.egommerce.demo.model.User.User;
import com.egommerce.demo.service.Category.CategoryService;
import com.egommerce.demo.service.Order.OrderService;
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
    private final OrderService orderService;

    @Autowired
    public AdminService(UserService userService, ProductService productService, CategoryService categoryService,
                        SellerService sellerService, OrderService orderService) {
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.sellerService = sellerService;
        this.orderService = orderService;
    }

    public List<User> getAllUsers() {
        return userService.findAll();
    }

    public User findUserById(Long id) {
        return userService.findById(id);
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

    public Product findProductById(Long id) {
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

    public Category getCategoryById(Long id) {
        return categoryService.getCategoryById(id);
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

    public Seller getSellerById(Long id) {
        return sellerService.getSellerById(id);
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

    public void createOrder(Order order) {
        orderService.createOrder(order);
    }

    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    public Order getOrderById(Long orderId) {
        return orderService.getOrderById(orderId);
    }

    public List<Order> getAllOrdersByUserId(Long userId) {
        return orderService.getAllOrdersByUserId(userId);
    }

    public void updateOrderDetails(Long orderId, Order orderUpdates) {
        orderService.updateOrderDetails(orderId, orderUpdates);
    }

    public void deleteOrder(Long orderId) {
        orderService.deleteOrder(orderId);
    }
}
