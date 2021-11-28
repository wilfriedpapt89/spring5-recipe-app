package com.wilfried.spring5recipeapp.converters;

import com.wilfried.spring5recipeapp.commands.IngredientCommand;
import com.wilfried.spring5recipeapp.commands.UnitOfMeasureCommand;
import com.wilfried.spring5recipeapp.domain.Ingredient;
import com.wilfried.spring5recipeapp.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientCommandToIngredientTest {

    public static final Long LONG_ID = 1l;
    public static final Long UOM_LONG_ID = 2L;
    public static final String DESCRIPTION = "Description";
    public static final String UOM_DESCRIPTION = "Description";
    public static final int INGREDIENT_AMOUNT = 300;
    IngredientCommandToIngredient ingredientCommandToIngredient;
    UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;
    @BeforeEach
    void setUp() {
        unitOfMeasureCommandToUnitOfMeasure = new UnitOfMeasureCommandToUnitOfMeasure();
        ingredientCommandToIngredient = new IngredientCommandToIngredient(unitOfMeasureCommandToUnitOfMeasure);
    }

    @Test
    void testWithEmptyObject() {
        assertNotNull(ingredientCommandToIngredient.convert(new IngredientCommand()));
    }

    @Test
    void testWihNullParameter() {
        assertNull(ingredientCommandToIngredient.convert(null));
    }

    @Test
    void convert() {

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(LONG_ID);
        ingredientCommand.setDescription(DESCRIPTION);
        ingredientCommand.setAmount(new BigDecimal(300));
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(UOM_LONG_ID);
        unitOfMeasureCommand.setDescription(UOM_DESCRIPTION);
        ingredientCommand.setUom(unitOfMeasureCommand);

        Ingredient ingredient = ingredientCommandToIngredient.convert(ingredientCommand);
        assertNotNull(ingredient);
        assertNotNull(ingredient.getUom());
        assertEquals(LONG_ID,ingredient.getId());
        assertEquals(DESCRIPTION,ingredient.getDescription());
        assertEquals(INGREDIENT_AMOUNT, ingredient.getAmount().longValue());
        assertEquals(UOM_LONG_ID, ingredient.getUom().getId());
    }
}