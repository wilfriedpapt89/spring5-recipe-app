package com.wilfried.spring5recipeapp.service;

import com.wilfried.spring5recipeapp.commands.RecipeCommand;
import com.wilfried.spring5recipeapp.converters.RecipeCommandToRecipe;
import com.wilfried.spring5recipeapp.converters.RecipeToRecipeCommand;
import com.wilfried.spring5recipeapp.domain.Recipe;
import com.wilfried.spring5recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeToRecipeCommand recipeToRecipeCommand;
    private final RecipeCommandToRecipe recipeCommandToRecipe;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeToRecipeCommand recipeToRecipeCommand, RecipeCommandToRecipe recipeCommandToRecipe) {
        this.recipeRepository = recipeRepository;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("I'm in the recipe service");
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().forEach(recipeSet::add);
        return recipeSet;
    }

    @Override
    public Recipe findById(Long id) {
        return recipeRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(recipeCommand);
        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        RecipeCommand returnedRecipeCommand = recipeToRecipeCommand.convert(savedRecipe);
        return returnedRecipeCommand;
    }
}
