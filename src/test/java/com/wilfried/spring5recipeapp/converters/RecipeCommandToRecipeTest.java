package com.wilfried.spring5recipeapp.converters;

import com.wilfried.spring5recipeapp.commands.*;
import com.wilfried.spring5recipeapp.domain.Difficulty;
import com.wilfried.spring5recipeapp.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RecipeCommandToRecipeTest {

    private static final Long NOTES_COMMAND_ID = 5L;
    private static final String RECIPE_NOTE_STRING = "Recipe description";
    private static final Long UOM_ID = 55L;
    private static final String UOM_DESCRIPTION = "Uom description";
    private static final Long INGREDIENT_COMMAND_ID = 555L;
    private static final String INGREDIENT_COMMAND_DESCRIPTION = "Ingredient command description";
    private static final BigDecimal INGREDIENT_COMMAND_AMOUNT = BigDecimal.valueOf(500L);
    private static final Long CATEGORY_COMMAND_ID = 5555L;
    private static final String CATEGORY_COMMAND_DESCRIPTION = "Category command description";
    private static final Long LONG_ID = 55555L;
    private static final String URL_STRING = "Url description";
    private static final String SOURCE_STRING = "Source string";
    private static final Integer COOK_TIME_INT = 22;
    private static final Integer PREP_TIME_INT = 222;
    private static final String DESCRIPTION_STRING = "Recipe description";
    private static final Integer SERVINGS_INT = 333;


    UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;
    NotesCommandToNotes notesCommandToNotes;
    IngredientCommandToIngredient ingredientCommandToIngredient;
    CategoryCommandToCategory categoryCommandToCategory;
    RecipeCommandToRecipe recipeCommandToRecipe;

    @BeforeEach
    void setUp() {
        unitOfMeasureCommandToUnitOfMeasure = new UnitOfMeasureCommandToUnitOfMeasure();
        notesCommandToNotes = new NotesCommandToNotes();
        ingredientCommandToIngredient = new IngredientCommandToIngredient(unitOfMeasureCommandToUnitOfMeasure);
        categoryCommandToCategory = new CategoryCommandToCategory();
        recipeCommandToRecipe = new RecipeCommandToRecipe(notesCommandToNotes,ingredientCommandToIngredient,categoryCommandToCategory);
    }

    @Test
    void testWithEmptyObject() {

        assertNotNull(recipeCommandToRecipe.convert(new RecipeCommand()));
    }

    @Test
    void testWithNullParameter() {
        assertNull(recipeCommandToRecipe.convert(null));
    }

    @Test
    void convert() {

        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(NOTES_COMMAND_ID);
        notesCommand.setRecipeNotes(RECIPE_NOTE_STRING);

        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(UOM_ID);
        unitOfMeasureCommand.setDescription(UOM_DESCRIPTION);

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(INGREDIENT_COMMAND_ID);
        ingredientCommand.setDescription(INGREDIENT_COMMAND_DESCRIPTION);
        ingredientCommand.setUom(unitOfMeasureCommand);
        ingredientCommand.setAmount(INGREDIENT_COMMAND_AMOUNT);

        Set<IngredientCommand> ingredientCommands = new HashSet<>();
        ingredientCommands.add(ingredientCommand);

        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(CATEGORY_COMMAND_ID);
        categoryCommand.setDescription(CATEGORY_COMMAND_DESCRIPTION);

        Set<CategoryCommand> categoryCommands = new HashSet<>();
        categoryCommands.add(categoryCommand);


        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(LONG_ID);
        recipeCommand.setUrl(URL_STRING);
        recipeCommand.setSource(SOURCE_STRING);
        recipeCommand.setDifficulty(Difficulty.EASY);
        recipeCommand.setCookTime(COOK_TIME_INT);
        recipeCommand.setPrepTime(PREP_TIME_INT);
        recipeCommand.setDescription(DESCRIPTION_STRING);
        recipeCommand.setServings(SERVINGS_INT);
        recipeCommand.setNotes(notesCommand);
        recipeCommand.setCategories(categoryCommands);
        recipeCommand.setIngredients(ingredientCommands);

        Recipe recipe = recipeCommandToRecipe.convert(recipeCommand);
        assertNotNull(recipe);
        assertEquals(LONG_ID,recipe.getId());
        assertEquals(DESCRIPTION_STRING,recipe.getDescription());
        assertEquals(URL_STRING, recipe.getUrl());
        assertEquals(PREP_TIME_INT, recipe.getPrepTime());
        assertEquals(COOK_TIME_INT, recipe.getCookTime());
        assertEquals(SOURCE_STRING,recipe.getSource());
        assertEquals(Difficulty.EASY,recipe.getDifficulty());
        assertEquals(SERVINGS_INT,recipe.getServings());
        assertEquals(NOTES_COMMAND_ID,recipe.getNotes().getId());
        assertEquals(1,recipe.getCategories().size());
        assertEquals(1,recipe.getIngredients().size());
        assertEquals(INGREDIENT_COMMAND_ID,recipe.getIngredients().stream().findFirst().get().getId());
        assertEquals(INGREDIENT_COMMAND_DESCRIPTION,recipe.getIngredients().stream().findFirst().get().getDescription());
        assertEquals(INGREDIENT_COMMAND_AMOUNT.longValue(),recipe.getIngredients().stream().findFirst().get().getAmount().longValue());
        assertEquals(CATEGORY_COMMAND_ID,recipe.getCategories().stream().findFirst().get().getId());
        assertEquals(CATEGORY_COMMAND_DESCRIPTION,recipe.getCategories().stream().findFirst().get().getDescription());
    }
}