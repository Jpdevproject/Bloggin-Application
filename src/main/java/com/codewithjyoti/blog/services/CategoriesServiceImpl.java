package com.codewithjyoti.blog.services;

import com.codewithjyoti.blog.entities.CategoriesEntity;
import com.codewithjyoti.blog.model.Categories;
import com.codewithjyoti.blog.repositories.CategoriesRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriesServiceImpl implements CategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Override
    public Optional<CategoriesEntity> createCategories(Categories categories) {
        return Optional.of(categoriesRepository.save(CategoriesEntity.builder()
                .description(categories.getDescription())
                .title(categories.getTitle()).build()));
    }

    @Override
    public Optional<List<Categories>> getAllCategories() {
        List<CategoriesEntity> categoriesRepositoryAll = (List<CategoriesEntity>)categoriesRepository.findAll();
        return Optional.of(categoriesRepositoryAll.stream()
                .map(categoriesEntity -> {
                    Categories categories = new Categories();
                    BeanUtils.copyProperties(categoriesEntity, categories);
                    return categories;
                }).collect(Collectors.toList()));
    }

    @Override
    public Optional<CategoriesEntity> updateCategories(Categories categories, Long categoryId) {
        Optional<CategoriesEntity> categoriesEntities = categoriesRepository.findById(categoryId);
        if (categoriesEntities.isPresent()) {
            return Optional.of(categoriesRepository.save(CategoriesEntity.builder()
                    .categoryId(categoryId)
                    .description(categories.getDescription())
                    .title(categories.getTitle())
                    .build()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Categories> getCategoryById(Long categoryId) {
        Optional<CategoriesEntity> categoriesEntityById = categoriesRepository.findById(categoryId);
        if (categoriesEntityById.isPresent()) {
            CategoriesEntity categoriesEntity = categoriesEntityById.get();
            return Optional.ofNullable(Categories.builder()
                    .categoryId(categoryId)
                    .description(categoriesEntity.getDescription())
                    .title(categoriesEntity.getTitle())
                    .build());
        }
        return Optional.empty();
    }
}
