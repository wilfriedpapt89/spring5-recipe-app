package com.wilfried.spring5recipeapp.converters;

import com.wilfried.spring5recipeapp.commands.CategoryCommand;
import com.wilfried.spring5recipeapp.commands.IngredientCommand;
import com.wilfried.spring5recipeapp.commands.RecipeCommand;
import com.wilfried.spring5recipeapp.domain.Category;
import com.wilfried.spring5recipeapp.domain.Ingredient;
import com.wilfried.spring5recipeapp.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final CategoryToCategoryCommand categoryToCategoryCommand;
    private final NotesToNotesCommand notesToNotesCommand;

    public RecipeToRecipeCommand(IngredientToIngredientCommand ingredientToIngredientCommand, CategoryToCategoryCommand categoryToCategoryCommand, NotesToNotesCommand notesToNotesCommand) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.categoryToCategoryCommand = categoryToCategoryCommand;
        this.notesToNotesCommand = notesToNotesCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {

        if (source == null)
            return null;
        else {
            final RecipeCommand recipeCommand = new RecipeCommand();
            recipeCommand.setId(source.getId());
            recipeCommand.setCookTime(source.getCookTime());
            recipeCommand.setPrepTime(source.getPrepTime());
            recipeCommand.setDifficulty(source.getDifficulty());
            recipeCommand.setDirections(source.getDirections());
            recipeCommand.setSource(source.getSource());
            recipeCommand.setUrl(source.getUrl());
            recipeCommand.setNotes(notesToNotesCommand.convert(source.getNotes()));
            Set<IngredientCommand> ingredientCommands = new HashSet<>();
            for (Ingredient ingredient : source.getIngredients()) {
                IngredientCommand ingredientCommand = ingredientToIngredientCommand.convert(ingredient);
                ingredientCommands.add(ingredientCommand);
            }
            recipeCommand.setIngredients(ingredientCommands);
            Set<CategoryCommand> categoryCommands = new HashSet<>();
            for (Category category : source.getCategories()) {
                CategoryCommand categoryCommand = categoryToCategoryCommand.convert(category);
                categoryCommands.add(categoryCommand);
            }
            recipeCommand.setCategories(categoryCommands);
            return recipeCommand;
        }
    }
}
