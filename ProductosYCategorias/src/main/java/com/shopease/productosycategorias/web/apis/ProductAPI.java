package com.shopease.productosycategorias.web.apis;

import com.shopease.productosycategorias.exception.CategoryNotFoundException;
import com.shopease.productosycategorias.exception.ProductNotFoundException;
import com.shopease.productosycategorias.web.dto.ProductDTO;
import com.shopease.productosycategorias.web.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import com.shopease.productosycategorias.exception.ErrorResponse;

@RestController
@RequestMapping("/productos")
public class ProductAPI {

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
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
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
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    // ? PUT /productos/{id} -> Actualizar un producto existente (solo admins).
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Integer id,@Valid @RequestBody ProductDTO dto) {
        try {
            ProductDTO updatedProduct = productService.updateProduct(id, dto);
            return ResponseEntity.ok(updatedProduct);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));

        } catch (CategoryNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Error en la actualización del producto"));
        }
    }


    // ? DELETE /productos

    /*
     @PostMapping("/new")
    public ResponseEntity<?> addPrueba(@RequestBody PruebaDTO pruebaDTO){
        System.out.println("PruebaDTO recibido: " + pruebaDTO);
        try {
            PruebaDTO savedPrueba = service.add(pruebaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPrueba);
        }
        catch (VehiculoInTestException | ConductorNoAptoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
     */

}
