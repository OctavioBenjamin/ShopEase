package com.shopease.productosycategorias.web.services;

import com.shopease.productosycategorias.dal.CategoryRepository;
import com.shopease.productosycategorias.entity.CategoryEntity;
import com.shopease.productosycategorias.exception.CategoryNotFoundException;
import com.shopease.productosycategorias.web.dto.CategoryDTO;
import com.shopease.productosycategorias.web.dto.CategoryDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository cRep;

    @Autowired
    private CategoryDTOMapper cMapper;

    // ? Validar que existe una categoria
    public boolean categoryexists(Integer id){
        return cRep.findById(id).isPresent();
    }

    // ? Obtener una categoria
    public CategoryDTO findById(Integer id) {
        return cRep.findById(id)
                .map(cMapper::apply)
                .orElseThrow(() -> new CategoryNotFoundException("Categoría con id " + id + " no encontrada."));
    }

    // ? Obtener todas las categorias
    public List<CategoryDTO> findAll(){
        return cRep.findAll().stream().map(cMapper::apply).toList();
    }


}
