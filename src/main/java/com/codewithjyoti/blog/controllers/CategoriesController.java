package com.codewithjyoti.blog.controllers;

import com.codewithjyoti.blog.entities.CategoriesEntity;
import com.codewithjyoti.blog.model.Categories;
import com.codewithjyoti.blog.services.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    @PostMapping
    public CategoriesEntity createCategory(@RequestBody Categories categories){
        return categoriesService.createCategories(categories)
                .orElse(null);
    }

    @PutMapping ("/{id}")
    public CategoriesEntity updateCategories(@RequestBody Categories categories,@PathVariable Long id){
        return categoriesService.updateCategories(categories,id).orElse(null);
    }
    @GetMapping
    public List<Categories> getAllCategories(){

        return categoriesService.getAllCategories().orElse(null);
    }

    @GetMapping("/{id}")
    public Categories getCategoriesById(@PathVariable Long id){
        return categoriesService.getCategoryById(id).orElse(null);
    }

}
