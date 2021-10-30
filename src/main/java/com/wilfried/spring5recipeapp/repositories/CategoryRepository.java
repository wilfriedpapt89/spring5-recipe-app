package com.wilfried.spring5recipeapp.repositories;

import com.wilfried.spring5recipeapp.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
