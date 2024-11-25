package com.shopease.productosycategorias.web.dto;

import com.shopease.productosycategorias.dal.CategoryRepository;
import com.shopease.productosycategorias.entity.CartItemEntity;
import com.shopease.productosycategorias.entity.CategoryEntity;
import com.shopease.productosycategorias.entity.ProductEntity;
import com.shopease.productosycategorias.exception.CategoryNotFoundException;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDTO {
    //TODO: Validar el mensaje de que falta un atributo en el request body

    private Integer id;

    @NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String name;

    @NotNull(message = "La descripcion no puede ser nula")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String description;

    @NotNull(message = "El preecio no puede ser nulo")
    @Min(value = 0, message = "El precio debe ser mayor o igual a 0")
    private Double price;

    @Min(value = 0, message = "El stock debe ser mayor o igual a 0")
    private Double stock;

    @NotNull(message = "La categoría no puede ser nula")
    private Integer category;

    /*
     @NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String name;

    @NotNull(message = "La descripción no puede ser nula")
    @Size(min = 5, max = 255, message = "La descripción debe tener entre 5 y 255 caracteres")
    private String description;

    @NotNull(message = "El precio no puede ser nulo")
    @Min(value = 0, message = "El precio debe ser mayor o igual a 0")
    private Double price;

    @NotNull(message = "El stock no puede ser nulo")
    @Min(value = 0, message = "El stock debe ser mayor o igual a 0")
    private Integer stock;

    @NotNull(message = "La categoría no puede ser nula")
    private Integer category;
     */

    public ProductDTO(String name, String description, Double price, Double stock, Integer category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }
}
