package com.wilfried.spring5recipeapp.repositories;

import com.wilfried.spring5recipeapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
