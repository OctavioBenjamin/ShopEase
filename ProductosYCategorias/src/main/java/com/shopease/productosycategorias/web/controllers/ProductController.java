package com.shopease.productosycategorias.web.controllers;

import com.shopease.productosycategorias.exception.CategoryNotFoundException;
import com.shopease.productosycategorias.exception.ProductNotFoundException;
import com.shopease.productosycategorias.web.dto.ProductDTO;
import com.shopease.productosycategorias.web.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.shopease.productosycategorias.exception.ResponseMessage;

@RestController
@RequestMapping("/productos")
public class ProductController {

    @Autowired
    private ProductService serviceProduct;
    @Autowired
    private ProductService productService;

    // ? GET /productos -> Listar todos los productos.

    @GetMapping("/")
    public ResponseEntity<?> getAllProducts(){
        List<ProductDTO> products = serviceProduct.getAllProducts();
        return products.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(products);
    }

    // ? GET /productos/{id} -> Obtener información de un producto específico.
    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Integer id){
        try {
            ProductDTO product = serviceProduct.getProduct(id);
            return ResponseEntity.ok(product);
        } catch (ProductNotFoundException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No existe el producto con id: " + id);
        }
    }


    // ? GET /productos?categoria={id} -> Listar productos por categoría.
    @GetMapping
    public ResponseEntity<?> getProductsForCategory(@RequestParam("categoria") Integer categoryId){
        List<ProductDTO> products = serviceProduct.getProductsForCategory(categoryId);
        return products.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(products);
    }

    // ? POST /productos -> Crear un nuevo producto (solo admins).

    @PostMapping("/")
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductDTO dto){
        try {
            ProductDTO productDTO = serviceProduct.add(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(productDTO);
        } catch (CategoryNotFoundException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseMessage("No existe la categoria con id: " + dto.getCategory()));
        }
    }

    // ? PUT /productos/{id} -> Actualizar un producto existente (solo admins).
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Integer id,@Valid @RequestBody ProductDTO dto) {
        try {
            ProductDTO updatedProduct = productService.updateProduct(id, dto);
            return ResponseEntity.ok(updatedProduct);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(e.getMessage()));

        } catch (CategoryNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(e.getMessage()));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("Error en la actualización del producto"));
        }
    }


    // ? DELETE /productos
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id){
        try {
            serviceProduct.deleteProduct(id);
            return ResponseEntity.ok(new ResponseMessage("Se ha borrado el producto id: " + id));
        } catch (ProductNotFoundException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseMessage("No se encontro el producto id: " + id));
        }
    }

}
