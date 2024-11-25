package com.shopease.productosycategorias.web.services;

import com.shopease.productosycategorias.dal.ProductRepository;
import com.shopease.productosycategorias.entity.CategoryEntity;
import com.shopease.productosycategorias.entity.ProductEntity;
import com.shopease.productosycategorias.exception.CategoryNotFoundException;
import com.shopease.productosycategorias.exception.ProductNotFoundException;
import com.shopease.productosycategorias.web.dto.ProductDTO;
import com.shopease.productosycategorias.web.dto.ProductDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductDTOMapper pMapper;

    @Autowired
    private ProductRepository pRep;

    @Autowired
    private CategoryService categoryService;

    // ? GET /products -> Listar todos los productos.
    public List<ProductDTO> getAllProducts(){
        List<ProductDTO> collect = pRep.findAll()
                .stream()
                .map(p -> pMapper.apply(p))
                .collect(Collectors.toList());
        return collect;
    }

    // ? GET /productos/{id} -> Obtener información de un producto específico.

    public ProductDTO getProduct(Integer id){
        return getAllProducts()
                .stream().filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("No se encontro el producto id: " + id));
    }

    // ? GET /productos?categoria={id} -> Listar productos por categoría.

    public List<ProductDTO> getProductsForCategory(Integer categoryId){
        return getAllProducts()
                .stream()
                .filter(p -> p.getCategory().equals(categoryId))
                .toList();
    };

    // ? POST /productos -> Crear un nuevo producto (solo admins).

    public ProductDTO add(ProductDTO dto){
        //Validar que la categoria exista
        boolean existsCategory = categoryService.categoryexists(dto.getCategory());

        if (!existsCategory){
            throw new CategoryNotFoundException("No existe la categoria con id: " + dto.getCategory());
        }

        ProductEntity productEntity = pMapper.toEntity(dto);
        ProductEntity productSaved = pRep.save(productEntity);

        return pMapper.apply(productSaved);

    }

    // ? PUT /productos/{id} -> Actualizar un producto existente (solo admins).
    public ProductDTO updateProduct(Integer id, ProductDTO dto){

        //Encontrar la prueba
        ProductEntity product = pRep.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("No se encontro el producto id: " + id));

        //Validar categoria
        if (!Objects.equals(dto.getCategory(), product.getCategory().getId())){

            if (!categoryService.categoryexists(dto.getCategory())){
                throw new CategoryNotFoundException("No se encontro la categoria");
            }

            Optional<CategoryEntity> category = categoryService.findById(dto.getCategory());
            category.ifPresent(product::setCategory);

        }

        // Actualizar el producto

        //TODO: AGREGAR VALIDACIONES NOT NULL Y MAYOR A CERO
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());

        return pMapper.apply(product);
    };


    // ? DELETE /productos

    /*
    public PruebaDTO add(PruebaDTO dto) {

        Date now = new Date();

        boolean estaEnPrueba = pRep.findAll()
                .stream()
                .anyMatch(prueba -> prueba.getVehiculo().getId().equals(dto.getIdVehiculo()) &&
                        prueba.getFechaHoraInicio().before(now) &&
                        prueba.getFechaHoraFin().after(now));

        boolean aptoParaConducir = iRep.findById(dto.getIdInteresado())
                .stream()
                .anyMatch(interesado -> (interesado.getFVencimientoLicencia().before(now) || interesado.getRestringido()));

        // ! TODO: Validar que el interesado no este restringido



        if(estaEnPrueba){
            throw new VehiculoInTestException("El vehiculo ya esta en prueba");
        }

        if(aptoParaConducir){
            throw new ConductorNoAptoException("La licencia del interesado está vencida o el interesado esta restringido");
        }

        PruebaEntity pruebaEntity = pMapper.toEntity(dto);
        PruebaEntity savedEntity = pRep.save(pruebaEntity);
        return pMapper.apply(savedEntity); // Convierte la entidad guardada a DTO para devolverla
        // ? Se guarda una entity pero se devuelve un dto como respuesta
    }
     */

}
