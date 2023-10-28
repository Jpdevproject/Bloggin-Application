package com.codewithjyoti.blog.controllers;

import com.codewithjyoti.blog.entities.CategoriesEntity;
import com.codewithjyoti.blog.model.Categories;
import com.codewithjyoti.blog.services.CategoriesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(CategoriesController.class)
public class CategoriesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoriesService categoriesService;

    private Categories categories;
    private CategoriesEntity categoriesEntity;

    @BeforeEach
    public void setUp()throws Exception{
         categories = Categories.builder()
                .categoryId(1L)
                .title("catTitle")
                .description("catDescription")
                 .build();
         categoriesEntity = CategoriesEntity.builder()
                 .categoryId(1L)
                 .title("catTitle")
                 .description("catDescription")
                 .build();

    }

    @Test
    public void testCreateCategoryWhenValidCategoriesThenReturnCategoriesEntity() throws Exception {

        when(categoriesService.createCategories(any())).thenReturn(Optional.of(categoriesEntity));

        mockMvc.perform(post("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(categories)))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(categoriesEntity)));
    }

    @Test
    public void testUpdateCategoriesWhenValidCategoriesAndIdThenReturnCategoriesEntity() throws Exception {

        when(categoriesService.updateCategories(any(), anyLong())).thenReturn(Optional.of(categoriesEntity));

        mockMvc.perform(put("/api/categories/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(categories)))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(categoriesEntity)));
    }

    @Test
    public void testGetAllCategoriesWhenCalledThenReturnListOfCategories() throws Exception {

        when(categoriesService.getAllCategories()).thenReturn(Optional.of(Arrays.asList(categories)));

        mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(Arrays.asList(categories))));
    }

    @Test
    public void testGetCategoriesByIdWhenValidIdThenReturnCategories() throws Exception {

        when(categoriesService.getCategoryById(anyLong())).thenReturn(Optional.of(categories));

        mockMvc.perform(get("/api/categories/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(categories)));
    }
}