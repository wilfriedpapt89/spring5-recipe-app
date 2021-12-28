package com.wilfried.spring5recipeapp.service;

import com.wilfried.spring5recipeapp.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId,Long ingredientId);
}
