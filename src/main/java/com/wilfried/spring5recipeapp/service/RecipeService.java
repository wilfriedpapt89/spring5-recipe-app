package com.wilfried.spring5recipeapp.service;

import com.wilfried.spring5recipeapp.commands.RecipeCommand;
import com.wilfried.spring5recipeapp.domain.Recipe;
import com.wilfried.spring5recipeapp.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.Set;


public interface RecipeService {

    Set<Recipe> getRecipes();
    Recipe findById(Long id);
    RecipeCommand findCommandById(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
    void deleteById(Long id);
}
