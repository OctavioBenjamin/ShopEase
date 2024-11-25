package com.shopease.productosycategorias.dal;

import com.shopease.productosycategorias.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
}
