package com.codewithjyoti.blog.repositories;

import com.codewithjyoti.blog.entities.PostsEntity;
import org.springframework.data.repository.CrudRepository;

public interface PostsRepository extends CrudRepository<PostsEntity,Long> {
}
