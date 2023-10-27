package com.codewithjyoti.blog.repositories;

import com.codewithjyoti.blog.entities.CategoriesEntity;
import org.springframework.data.repository.CrudRepository;

public interface CategoriesRepository extends CrudRepository<CategoriesEntity,Long> {
}
