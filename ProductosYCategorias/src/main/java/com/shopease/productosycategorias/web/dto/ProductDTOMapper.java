package com.shopease.productosycategorias.web.dto;

import com.shopease.productosycategorias.dal.CategoryRepository;
import com.shopease.productosycategorias.entity.CategoryEntity;
import com.shopease.productosycategorias.entity.ProductEntity;
import com.shopease.productosycategorias.exception.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductDTOMapper implements Function<ProductEntity, ProductDTO> {

    @Autowired
    private CategoryRepository cRep;

    @Override
    public ProductDTO apply(ProductEntity product){
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getCategory().getId()
        );
    }

    public ProductEntity toEntity(ProductDTO dto){
        ProductEntity product = new ProductEntity();

        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());

        CategoryEntity categoryEntity = cRep.findById(dto.getCategory())
                .orElseThrow(() -> new CategoryNotFoundException("No existe la categoria con id: " + dto.getCategory()));
        product.setCategory(categoryEntity);

        return product;
    }
}
