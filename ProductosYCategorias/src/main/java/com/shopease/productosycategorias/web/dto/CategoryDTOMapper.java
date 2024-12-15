package com.shopease.productosycategorias.web.dto;

import com.shopease.productosycategorias.entity.CategoryEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.function.Function;

@Service
public class CategoryDTOMapper implements Function<CategoryEntity, CategoryDTO> {

    @Override
    public CategoryDTO apply(CategoryEntity category){
        return new CategoryDTO(category.getName());
    }

    public CategoryEntity toEntity(CategoryDTO category){
        return new CategoryEntity(category.getName(), new ArrayList<>());
    }
}
