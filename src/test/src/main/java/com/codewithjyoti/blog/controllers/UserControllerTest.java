package com.codewithjyoti.blog.controllers;

import com.codewithjyoti.blog.entities.UserEntity;
import com.codewithjyoti.blog.model.User;
import com.codewithjyoti.blog.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    @Test
    public void testCreateUserWhenUserIsCreatedThenReturnUserEntity() throws Exception {
        User user = new User(1L, "John Doe", "john.doe@example.com", "password", "About John");
        UserEntity userEntity = new UserEntity(1L, "John Doe", "john.doe@example.com", "password", "About John");

        when(userService.createUser(user)).thenReturn(Optional.of(userEntity));

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
        User user1 = new User(1L, "John Doe", "john.doe@example.com", "password", "About John");
        User user2 = new User(2L, "Jane Doe", "jane.doe@example.com", "password", "About Jane");
        List<User> users = Arrays.asList(user1, user2);

        when(userService.getAllUser()).thenReturn(Optional.of(users));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(users)));
    }
}
