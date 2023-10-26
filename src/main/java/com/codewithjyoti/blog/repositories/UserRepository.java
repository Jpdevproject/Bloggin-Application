package com.codewithjyoti.blog.repositories;

import com.codewithjyoti.blog.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity,Long> {
}
