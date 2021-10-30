package com.wilfried.spring5recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface RecipeRepository extends CrudRepository<Repository, Long> {
}
