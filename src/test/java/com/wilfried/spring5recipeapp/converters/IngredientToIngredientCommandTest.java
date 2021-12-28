package com.wilfried.spring5recipeapp.converters;

import com.wilfried.spring5recipeapp.commands.IngredientCommand;
import com.wilfried.spring5recipeapp.domain.Ingredient;
import com.wilfried.spring5recipeapp.domain.Recipe;
import com.wilfried.spring5recipeapp.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientToIngredientCommandTest {

    public static final Long LONG_ID = 1l;
    public static final Long LONG_RECIPE_ID = 1l;
    public static final Long UOM_LONG_ID = 2L;
    public static final String DESCRIPTION = "Description";
    public static final String UOM_DESCRIPTION = "Description";
    public static final int INGREDIENT_AMOUNT = 300;
    IngredientToIngredientCommand ingredientToIngredientCommand;
    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
    @BeforeEach
    void setUp() {
        unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
        ingredientToIngredientCommand = new IngredientToIngredientCommand(unitOfMeasureToUnitOfMeasureCommand);
    }

    @Test
    void testWithEmptyObject() {
       assertNotNull(ingredientToIngredientCommand.convert(new Ingredient()));
    }

    @Test
    void testWihNullParameter() {
        assertNull(ingredientToIngredientCommand.convert(null));
    }

    @Test
    void convert() {

        Ingredient ingredient = new Ingredient();
        ingredient.setId(LONG_ID);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(new BigDecimal(300));
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(UOM_LONG_ID);
        unitOfMeasure.setDescription(UOM_DESCRIPTION);
        ingredient.setUom(unitOfMeasure);

        Recipe recipe = new Recipe();
        recipe.setId(LONG_RECIPE_ID);
        ingredient.setRecipe(recipe);

        IngredientCommand ingredientCommand = ingredientToIngredientCommand.convert(ingredient);
        assertNotNull(ingredientCommand);
        assertNotNull(ingredientCommand.getUom());
        assertEquals(LONG_ID,ingredientCommand.getId());
        assertEquals(DESCRIPTION,ingredientCommand.getDescription());
        assertEquals(LONG_RECIPE_ID,ingredientCommand.getRecipeId());
        assertEquals(INGREDIENT_AMOUNT, ingredientCommand.getAmount().longValue());
        assertEquals(UOM_LONG_ID, ingredientCommand.getUom().getId());
    }
}