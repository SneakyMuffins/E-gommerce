package com.egommerce.demo.model.Seller;

import com.egommerce.demo.annotation.ExcludeUpdate;
import com.egommerce.demo.model.Product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "Seller")
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ExcludeUpdate
    @Column(name = "entity_id")
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Seller name is mandatory")
    private String name;

    @OneToMany(mappedBy = "Seller", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

    public Seller() {
    }

    public Seller(String name) {
        this.name = name;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
