package com.codewithjyoti.blog.services;

import com.codewithjyoti.blog.entities.UserEntity;
import com.codewithjyoti.blog.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserEntity> createUser(User user);

    Optional<List<User>> getAllUser();

    Optional<UserEntity> updateUser(User user, Long id);

    Optional<User> getUserById(Long id);
}
