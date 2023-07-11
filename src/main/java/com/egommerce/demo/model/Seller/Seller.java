package com.egommerce.demo.model.Seller;

import com.egommerce.demo.annotation.ExcludeUpdate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "seller")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ExcludeUpdate
    @Column(name = "entity_id")
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Seller name is mandatory")
    private String name;

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
}
