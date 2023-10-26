package com.codewithjyoti.blog.controllers;

import com.codewithjyoti.blog.entities.UserEntity;
import com.codewithjyoti.blog.model.User;
import com.codewithjyoti.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public UserEntity createUser(@RequestBody User user) {
        return userService.createUser(user)
                .orElse(null);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUser()
                .orElse(null);
    }

}
