package com.wilfried.spring5recipeapp.service;

import com.wilfried.spring5recipeapp.commands.IngredientCommand;
import com.wilfried.spring5recipeapp.commands.RecipeCommand;
import com.wilfried.spring5recipeapp.converters.IngredientToIngredientCommand;
import com.wilfried.spring5recipeapp.domain.Ingredient;
import com.wilfried.spring5recipeapp.domain.Recipe;
import com.wilfried.spring5recipeapp.repositories.IngredientRepository;
import com.wilfried.spring5recipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class IngredientServiceImplTest {

    @Mock
    IngredientRepository ingredientRepository;

    @Mock
    IngredientToIngredientCommand ingredientToIngredientCommand;

    @Mock
    RecipeRepository recipeRepository;

    IngredientServiceImpl ingredientServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ingredientServiceImpl = new IngredientServiceImpl(ingredientRepository, recipeRepository, ingredientToIngredientCommand);
    }

    @Test
    void findByRecipeIdAndIngredientId() {

        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);
        recipe.addIngredients(ingredient);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);
        recipe.addIngredients(ingredient2);


        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);
        recipe.addIngredients(ingredient3);

        Optional<Recipe> optionalRecipe = Optional.of(recipe);
        Mockito.when(recipeRepository.findById(ArgumentMatchers.anyLong())).thenReturn(optionalRecipe);

        IngredientCommand ingredientCommand = ingredientServiceImpl.findByRecipeIdAndIngredientId(1L, 3L);

        //then
        assertNotNull(ingredientCommand);
        assertEquals(1L, ingredientCommand.getRecipeId());
        assertEquals(3L, ingredientCommand.getId());


    }
}