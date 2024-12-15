package com.shopease.productosycategorias.web.controllers;

import com.shopease.productosycategorias.exception.CategoryNotFoundException;
import com.shopease.productosycategorias.exception.ResponseMessage;
import com.shopease.productosycategorias.web.dto.CategoryDTO;
import com.shopease.productosycategorias.web.services.CategoryService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){
        List<CategoryDTO> categories = service.findAll();
        return categories.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Integer id){
        try{
            return ResponseEntity.ok(service.findById(id));
        } catch (CategoryNotFoundException e){
            return ResponseEntity.badRequest().body(new ResponseMessage(e.getMessage()));
        }
    }
}
