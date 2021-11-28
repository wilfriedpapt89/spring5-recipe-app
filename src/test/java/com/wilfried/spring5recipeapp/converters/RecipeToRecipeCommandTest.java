package com.wilfried.spring5recipeapp.converters;

import com.wilfried.spring5recipeapp.commands.*;
import com.wilfried.spring5recipeapp.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RecipeToRecipeCommandTest {


    private static final Long NOTES_ID = 5L;
    private static final String RECIPE_NOTE_STRING = "Recipe description";
    private static final Long UOM_ID = 55L;
    private static final String UOM_DESCRIPTION = "Uom description";
    private static final Long INGREDIENT_ID = 555L;
    private static final String INGREDIENT_DESCRIPTION = "Ingredient command description";
    private static final BigDecimal INGREDIENT_AMOUNT = BigDecimal.valueOf(500L);
    private static final Long CATEGORY_ID = 5555L;
    private static final String CATEGORY_DESCRIPTION = "Category command description";
    private static final Long LONG_ID = 55555L;
    private static final String URL_STRING = "Url description";
    private static final String SOURCE_STRING = "Source string";
    private static final Integer COOK_TIME_INT = 22;
    private static final Integer PREP_TIME_INT = 222;
    private static final String DESCRIPTION_STRING = "Recipe description";
    private static final Integer SERVINGS_INT = 333;


    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
    NotesToNotesCommand notesToNotesCommand;
    IngredientToIngredientCommand ingredientToIngredientCommand;
    CategoryToCategoryCommand categoryToCategoryCommand;
    RecipeToRecipeCommand recipeToRecipeCommand;

    @BeforeEach
    void setUp() {
        unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
        notesToNotesCommand = new NotesToNotesCommand();
        ingredientToIngredientCommand = new IngredientToIngredientCommand(unitOfMeasureToUnitOfMeasureCommand);
        categoryToCategoryCommand = new CategoryToCategoryCommand();
        recipeToRecipeCommand = new RecipeToRecipeCommand(ingredientToIngredientCommand,categoryToCategoryCommand,notesToNotesCommand);
    }

    @Test
    void testWithEmptyObject() {
        assertNotNull(recipeToRecipeCommand.convert(new Recipe()));
    }

    @Test
    void testWithNullParameter() {
        assertNull(recipeToRecipeCommand.convert(null));
    }

    @Test
    void convert() {

        Notes notes = new Notes();
        notes.setId(NOTES_ID);
        notes.setRecipeNotes(RECIPE_NOTE_STRING);

        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(UOM_ID);
        unitOfMeasure.setDescription(UOM_DESCRIPTION);

       Ingredient ingredient = new Ingredient();
       ingredient.setId(INGREDIENT_ID);
       ingredient.setDescription(INGREDIENT_DESCRIPTION);
       ingredient.setUom(unitOfMeasure);
       ingredient.setAmount(INGREDIENT_AMOUNT);

        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(ingredient);

        Category category = new Category();
        category.setId(CATEGORY_ID);
        category.setDescription(CATEGORY_DESCRIPTION);

        Set<Category> categories = new HashSet<>();
        categories.add(category);


        Recipe recipe = new Recipe();
        recipe.setId(LONG_ID);
        recipe.setUrl(URL_STRING);
        recipe.setSource(SOURCE_STRING);
        recipe.setDifficulty(Difficulty.EASY);
        recipe.setCookTime(COOK_TIME_INT);
        recipe.setPrepTime(PREP_TIME_INT);
        recipe.setDescription(DESCRIPTION_STRING);
        recipe.setServings(SERVINGS_INT);
        recipe.setNotes(notes);
        recipe.setCategories(categories);
        recipe.setIngredients(ingredients);

        RecipeCommand recipeCommand = recipeToRecipeCommand.convert(recipe);
        assertNotNull(recipeCommand);
        assertEquals(LONG_ID,recipeCommand.getId());
        assertEquals(DESCRIPTION_STRING,recipeCommand.getDescription());
        assertEquals(URL_STRING, recipeCommand.getUrl());
        assertEquals(PREP_TIME_INT, recipeCommand.getPrepTime());
        assertEquals(COOK_TIME_INT, recipeCommand.getCookTime());
        assertEquals(SOURCE_STRING,recipeCommand.getSource());
        assertEquals(Difficulty.EASY,recipeCommand.getDifficulty());
        assertEquals(SERVINGS_INT,recipeCommand.getServings());
        assertEquals(NOTES_ID,recipeCommand.getNotes().getId());
        assertEquals(1,recipeCommand.getCategories().size());
        assertEquals(1,recipeCommand.getIngredients().size());
        assertEquals(INGREDIENT_ID,recipeCommand.getIngredients().stream().findFirst().get().getId());
        assertEquals(INGREDIENT_DESCRIPTION,recipeCommand.getIngredients().stream().findFirst().get().getDescription());
        assertEquals(INGREDIENT_AMOUNT.longValue(),recipeCommand.getIngredients().stream().findFirst().get().getAmount().longValue());
        assertEquals(CATEGORY_ID,recipeCommand.getCategories().stream().findFirst().get().getId());
        assertEquals(CATEGORY_DESCRIPTION,recipeCommand.getCategories().stream().findFirst().get().getDescription());
    }
}