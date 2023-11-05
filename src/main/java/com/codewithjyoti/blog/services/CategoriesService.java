package com.codewithjyoti.blog.services;

import com.codewithjyoti.blog.entities.CategoriesEntity;
import com.codewithjyoti.blog.model.Categories;

import java.util.List;
import java.util.Optional;

public interface CategoriesService {
    Optional<CategoriesEntity> createCategories(Categories categories);

    Optional<List<Categories>> getAllCategories();

    Optional<CategoriesEntity> updateCategories(Categories categories, Long categoryId);

    Optional<Categories> getCategoryById(Long categoryId);
}
