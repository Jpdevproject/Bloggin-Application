package com.codewithjyoti.blog.controllers;

import com.codewithjyoti.blog.entities.UserEntity;
import com.codewithjyoti.blog.model.User;
import com.codewithjyoti.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public UserEntity createUser(@RequestBody User user) {
        return userService.createUser(user)
                .orElse(null);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUser()
                .orElse(null);
    }
    @PutMapping ("/{id}")
    public UserEntity createUser(@RequestBody User user,@PathVariable Long id) {
        return userService.updateUser(user,id)
                .orElse(null);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .orElse(null);
    }

}
