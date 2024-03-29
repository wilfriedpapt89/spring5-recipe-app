package com.wilfried.spring5recipeapp.service;

import com.wilfried.spring5recipeapp.commands.IngredientCommand;
import com.wilfried.spring5recipeapp.converters.IngredientToIngredientCommand;
import com.wilfried.spring5recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.wilfried.spring5recipeapp.domain.Ingredient;
import com.wilfried.spring5recipeapp.domain.Recipe;
import com.wilfried.spring5recipeapp.repositories.IngredientRepository;
import com.wilfried.spring5recipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;

class IngredientServiceImplTest {

    @Mock
    IngredientRepository ingredientRepository;


    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    @Mock
    RecipeRepository recipeRepository;

    IngredientServiceImpl ingredientServiceImpl;

    IngredientServiceImplTest() {
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ingredientServiceImpl = new IngredientServiceImpl(ingredientRepository, recipeRepository, ingredientToIngredientCommand);
    }

    @Test
    void findByRecipeIdAndIngredientId() {

        //GIven
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
        Mockito.verify(recipeRepository, times(1)).findById(anyLong());
    }
}