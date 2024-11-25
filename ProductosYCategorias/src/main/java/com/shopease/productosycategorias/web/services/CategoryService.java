package com.shopease.productosycategorias.web.services;

import com.shopease.productosycategorias.dal.CategoryRepository;
import com.shopease.productosycategorias.entity.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository cRep;

    public boolean categoryexists(Integer id){
        return cRep.findById(id).isPresent();
    }

    public Optional<CategoryEntity> findById(Integer id){
        return cRep.findById(id);
    }
}
