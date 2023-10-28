package com.codewithjyoti.blog.services;

import com.codewithjyoti.blog.entities.CategoriesEntity;
import com.codewithjyoti.blog.model.Categories;
import com.codewithjyoti.blog.repositories.CategoriesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoriesServiceTest {

    @Mock
    private CategoriesRepository categoriesRepository;

    @InjectMocks
    private CategoriesService categoriesService;

    private Categories categories;
    private CategoriesEntity categoriesEntity;

    @BeforeEach
    public void setUp() {
        categories = Categories.builder()
                .categoryId(1L)
                .title("Test Title")
                .description("Test Description")
                .build();

        categoriesEntity = CategoriesEntity.builder()
                .categoryId(1L)
                .title("Test Title")
                .description("Test Description")
                .build();
    }

    @Test
    @DisplayName("Test createCategories when category is created then return category")
    public void testCreateCategoriesWhenCategoryIsCreatedThenReturnCategory() {
        when(categoriesRepository.save(any())).thenReturn(categoriesEntity);

        Optional<CategoriesEntity> result = categoriesService.createCategories(categories);

        assertThat(result).isNotEmpty();
        assertThat(result.get()).isEqualTo(categoriesEntity);
    }

    @Test
    @DisplayName("Test getAllCategories when categories are present then return categories")
    public void testGetAllCategoriesWhenCategoriesArePresentThenReturnCategories() {
        when(categoriesRepository.findAll()).thenReturn(Arrays.asList(categoriesEntity));

        Optional<List<Categories>> result = categoriesService.getAllCategories();

        assertThat(result).isNotEmpty();
        assertThat(result.get().size()).isEqualTo(1);
        assertThat(result.get().get(0).getCategoryId()).isEqualTo(categories.getCategoryId());
    }

    @Test
    @DisplayName("Test getAllCategories when no categories are present then return empty list")
    public void testGetAllCategoriesWhenNoCategoriesArePresentThenReturnEmptyList() {
        when(categoriesRepository.findAll()).thenReturn(Arrays.asList());

        Optional<List<Categories>> result = categoriesService.getAllCategories();

        assertThat(result).isNotEmpty();
        assertThat(result.get().size()).isEqualTo(0);
    }

    @Test
    @DisplayName("Test updateCategories when category is updated then return category")
    public void testUpdateCategoriesWhenCategoryIsUpdatedThenReturnCategory() {
        when(categoriesRepository.findById(anyLong())).thenReturn(Optional.of(categoriesEntity));
        when(categoriesRepository.save(any())).thenReturn(categoriesEntity);

        Optional<CategoriesEntity> result = categoriesService.updateCategories(categories, 1L);

        assertThat(result).isNotEmpty();
        assertThat(result.get()).isEqualTo(categoriesEntity);
    }

    @Test
    @DisplayName("Test updateCategories when category is not found then return empty optional")
    public void testUpdateCategoriesWhenCategoryIsNotFoundThenReturnEmptyOptional() {
        when(categoriesRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<CategoriesEntity> result = categoriesService.updateCategories(categories, 1L);

        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Test getCategoryById when category is found then return category")
    public void testGetCategoryByIdWhenCategoryIsFoundThenReturnCategory() {
        when(categoriesRepository.findById(anyLong())).thenReturn(Optional.of(categoriesEntity));

        Optional<Categories> result = categoriesService.getCategoryById(1L);

        assertThat(result).isNotEmpty();
        assertThat(result.get().getCategoryId()).isEqualTo(categories.getCategoryId());
    }

    @Test
    @DisplayName("Test getCategoryById when category is not found then return empty optional")
    public void testGetCategoryByIdWhenCategoryIsNotFoundThenReturnEmptyOptional() {
        when(categoriesRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<Categories> result = categoriesService.getCategoryById(1L);

        assertThat(result).isEmpty();
    }
}