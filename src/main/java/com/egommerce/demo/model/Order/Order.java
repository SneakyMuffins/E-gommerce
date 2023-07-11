package com.egommerce.demo.model.Order;

import com.egommerce.demo.annotation.ExcludeUpdate;
import com.egommerce.demo.model.Address.Address;
import com.egommerce.demo.model.Product.Product;
import com.egommerce.demo.model.User.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Order {
    @Id
    @ExcludeUpdate
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ExcludeUpdate
    @Column(name = "order_number", nullable = false)
    private String orderNumber;

    @ExcludeUpdate
    @Column(name = "amount_paid", nullable = false)
    private BigDecimal amountPaid;

    @Column(name = "order_status", nullable = false)
    private String orderStatus;

    @ExcludeUpdate
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @ExcludeUpdate
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;


    @Column(name = "created_at", nullable = false, updatable = false)
    @ExcludeUpdate
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    @ExcludeUpdate
    @UpdateTimestamp
    private Timestamp updatedAt;

    public Order() {
    }

    public Order(BigDecimal amountPaid, String orderStatus, Address address, User user) {
        this.orderNumber = UUID.randomUUID().toString();
        this.amountPaid = amountPaid;
        this.orderStatus = orderStatus;
        this.address = address;
        this.user = user;
        this.products = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }
}
