package com.shopease.productosycategorias.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "categories")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<ProductEntity> products;

    public CategoryEntity(String name, List<ProductEntity> products) {
        this.name = name;
        this.products = products;
    }
}
