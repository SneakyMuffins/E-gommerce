package com.egommerce.demo.model.Product;

import com.egommerce.demo.annotation.ExcludeUpdate;
import com.egommerce.demo.model.Category.Category;
import com.egommerce.demo.model.Seller.Seller;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "product")
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
    @JoinColumn(name = "Seller_id", nullable = false)
    @NotNull(message = "Seller is mandatory")
    private Seller Seller;

    @Column(name = "price", nullable = false)
    @NotNull(message = "Price is mandatory")
    private Double price;

    @Column(name = "description")
    private String description;

    @Column(name = "image_urls")
    private List<String> imageUrls;

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

    public Product(String name, Category category, Seller Seller, Double price, String description) {
        this.name = name;
        this.category = category;
        this.Seller = Seller;
        this.price = price;
        this.description = description;
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
        return Seller;
    }

    public void setSeller(Seller Seller) {
        this.Seller = Seller;
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

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
}
