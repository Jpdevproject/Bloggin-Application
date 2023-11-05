package com.codewithjyoti.blog.services;

import com.codewithjyoti.blog.entities.UserEntity;
import com.codewithjyoti.blog.model.User;
import com.codewithjyoti.blog.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<UserEntity> createUser(User user) {
        return Optional.of(userRepository.save(UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .about(user.getAbout())
                .email(user.getEmail())
                .password(user.getPassword())
                .build()));
    }

    @Override
    public Optional<List<User>> getAllUser() {
        List<UserEntity> allUsers = (List<UserEntity>) userRepository.findAll();
        return Optional.of(allUsers.stream()
                .map(userEntity -> {
                    User user = new User();
                    BeanUtils.copyProperties(userEntity, user);
                    return user;
                }).collect(Collectors.toList()));
    }

    @Override
    public Optional<UserEntity> updateUser(User user, Long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isPresent()) {
            return Optional.of(userRepository.save(UserEntity.builder()
                    .id(id)
                    .name(user.getName())
                    .about(user.getAbout())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .build()));
        }
        return Optional.empty();
    }
    @Override
    public Optional<User> getUserById(Long id){
        Optional<UserEntity> entity = userRepository.findById(id);
        if(entity.isPresent()){
            UserEntity userEntity = entity.get();
            return Optional.of(User.builder()
                    .id(userEntity.getId())
                            .about(userEntity.getAbout())
                            .email(userEntity.getEmail())
                            .password(userEntity.getPassword())
                            .name(userEntity.getName())
                    .build());
        }
        return Optional.empty();

    }
}
