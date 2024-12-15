package com.shopease.productosycategorias.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryDTO {
    private Integer id;
    private String name;

    public CategoryDTO(String name) {
        this.name = name;
    }
}
