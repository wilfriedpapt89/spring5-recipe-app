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
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final NotesCommandToNotes notesCommandToNotes;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final CategoryCommandToCategory categoryCommandToCategory;

    public RecipeCommandToRecipe(NotesCommandToNotes notesCommandToNotes, IngredientCommandToIngredient ingredientCommandToIngredient, CategoryCommandToCategory categoryCommandToCategory) {
        this.notesCommandToNotes = notesCommandToNotes;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.categoryCommandToCategory = categoryCommandToCategory;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {

        if (source == null)
            return null;
        else {
            final Recipe recipe = new Recipe();
            recipe.setId(source.getId());
            recipe.setPrepTime(source.getPrepTime());
            recipe.setCookTime(source.getCookTime());
            recipe.setDescription(source.getDescription());
            recipe.setUrl(source.getUrl());
            recipe.setServings(source.getServings());
            recipe.setDirections(source.getDirections());
            recipe.setNotes(notesCommandToNotes.convert(source.getNotes()));
            recipe.setSource(source.getSource());
            recipe.setDifficulty(source.getDifficulty());
            Set<Ingredient> ingredients = new HashSet<>();
            for(IngredientCommand  igcommand : source.getIngredients()){
                Ingredient ingredient = ingredientCommandToIngredient.convert(igcommand);
                ingredients.add(ingredient);
            }
            recipe.setIngredients(ingredients);
            Set<Category> categories = new HashSet<>();
            for(CategoryCommand categoryCommand : source.getCategories()){
                Category category = categoryCommandToCategory.convert(categoryCommand);
                categories.add(category);
            }
            recipe.setCategories(categories);
            return recipe;
        }
    }
}
