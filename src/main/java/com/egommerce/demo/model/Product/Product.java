package com.egommerce.demo.model.Product;

import com.egommerce.demo.annotation.ExcludeUpdate;
import com.egommerce.demo.model.Category.Category;
import com.egommerce.demo.model.Seller.Seller;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "product")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ExcludeUpdate
    @Column(name = "entity_id")
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Product name is mandatory")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @NotNull(message = "Category is mandatory")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    @NotNull(message = "Seller is mandatory")
    private Seller seller;

    @Column(name = "price", nullable = false)
    @NotNull(message = "Price is mandatory")
    private Double price;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "stock")
    @NotNull(message = "Stock is mandatory")
    private Long stock;

    @Column(name = "created_at", nullable = false, updatable = false)
    @ExcludeUpdate
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    @ExcludeUpdate
    @UpdateTimestamp
    private Timestamp updatedAt;

    public Product() {
    }

    public Product(String name, Category category, Seller seller, Double price, String description, String imageUrl, Long stock) {
        this.name = name;
        this.category = category;
        this.seller = seller;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }
}
