package com.wilfried.spring5recipeapp.service;

import com.wilfried.spring5recipeapp.commands.RecipeCommand;
import com.wilfried.spring5recipeapp.converters.RecipeCommandToRecipe;
import com.wilfried.spring5recipeapp.converters.RecipeToRecipeCommand;
import com.wilfried.spring5recipeapp.domain.Recipe;
import com.wilfried.spring5recipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class RecipeServiceIT {

    public static final String NEW_DESCRIPTION = "New descritpion";

    @Autowired
    RecipeService recipeService;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;


    @Transactional
    @Test
    void testSaveOfDescription() {

        //Given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe recipe = recipes.iterator().next();
        RecipeCommand recipeCommand = recipeToRecipeCommand.convert(recipe);

        //When
        recipeCommand.setDescription(NEW_DESCRIPTION);
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(recipeCommand);

        //Then
        Assertions.assertNotNull(savedRecipeCommand);
        Assertions.assertEquals(NEW_DESCRIPTION,savedRecipeCommand.getDescription());
        Assertions.assertEquals(recipeCommand.getId(),savedRecipeCommand.getId());
        Assertions.assertEquals(recipeCommand.getCategories().size(), savedRecipeCommand.getCategories().size());
        Assertions.assertEquals(recipeCommand.getIngredients().size(),savedRecipeCommand.getIngredients().size());

    }
}
