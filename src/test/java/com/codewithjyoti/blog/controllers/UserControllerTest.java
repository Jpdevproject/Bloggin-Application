package com.codewithjyoti.blog.controllers;

import com.codewithjyoti.blog.entities.UserEntity;
import com.codewithjyoti.blog.model.User;
import com.codewithjyoti.blog.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private User user;
    private UserEntity userEntity;
    private   List<User> users;

    @BeforeEach
    public void setUp()throws Exception{
        MockitoAnnotations.openMocks(this);
        user = new User(1L, "John Doe", "john.doe@example.com", "password", "About John");
        userEntity =UserEntity.builder()
                .id(user.getId())
                .about(user.getAbout())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
        users=List.of(user);

    }

    @Test
    public void testCreateUserWhenUserIsCreatedThenReturnUserEntity() throws Exception {
        when(userService.createUser(any())).thenReturn(Optional.of(userEntity));
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(userEntity)));
    }

    @Test
    public void testGetAllUsersWhenNoUsersThenReturnEmptyList() throws Exception {
        when(userService.getAllUser()).thenReturn(Optional.of(Arrays.asList()));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void testGetAllUsersWhenMultipleUsersThenReturnUserList() throws Exception {
        when(userService.getAllUser()).thenReturn(Optional.of(users));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(users)));
    }
}