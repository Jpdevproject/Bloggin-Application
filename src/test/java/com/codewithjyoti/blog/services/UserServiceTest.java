package com.codewithjyoti.blog.services;

import com.codewithjyoti.blog.entities.UserEntity;
import com.codewithjyoti.blog.model.User;
import com.codewithjyoti.blog.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private User user;
    private UserEntity userEntity;
    private List<UserEntity> userEntities;

    @BeforeEach
    public void setUp() {
        user = User.builder()
                .id(1L)
                .name("Test User")
                .email("testuser@gmail.com")
                .password("password")
                .about("About Test User")
                .build();

        userEntity = UserEntity.builder()
                .id(1L)
                .name("Test User")
                .email("testuser@gmail.com")
                .password("password")
                .about("About Test User")
                .build();

        userEntities = Arrays.asList(userEntity);
    }

    @Test
    public void testCreateUserWhenValidUserThenReturnOptionalUserEntity() {
        when(userRepository.save(any())).thenReturn(userEntity);

        Optional<UserEntity> result = userService.createUser(user);

        assertEquals(Optional.of(userEntity), result);
    }

    @Test
    public void testGetAllUserWhenUsersExistThenReturnOptionalListOfUsers() {
        when(userRepository.findAll()).thenReturn(userEntities);

        Optional<List<User>> result = userService.getAllUser();

        assertEquals(1, result.get().size());
        assertEquals(user.getName(), result.get().get(0).getName());
    }

    @Test
    public void testGetAllUserWhenNoUsersThenReturnOptionalEmptyList() {
        when(userRepository.findAll()).thenReturn(Arrays.asList());

        Optional<List<User>> result = userService.getAllUser();

        assertEquals(Optional.of(Arrays.asList()), result);
    }
}