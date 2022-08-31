package com.wilfried.spring5recipeapp.service;

import com.wilfried.spring5recipeapp.commands.IngredientCommand;
import com.wilfried.spring5recipeapp.converters.IngredientCommandToIngredient;
import com.wilfried.spring5recipeapp.converters.IngredientToIngredientCommand;
import com.wilfried.spring5recipeapp.domain.Ingredient;
import com.wilfried.spring5recipeapp.domain.Recipe;
import com.wilfried.spring5recipeapp.repositories.IngredientRepository;
import com.wilfried.spring5recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;


    public IngredientServiceImpl(IngredientRepository ingredientRepository, RecipeRepository recipeRepository, IngredientToIngredientCommand ingredientToIngredientCommand) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);

        if (!optionalRecipe.isPresent()) {
//            log.debug("Recipe id not found: ID: " + recipeId);
            throw new RuntimeException();
        }

        Recipe recipe = optionalRecipe.get();
        Optional<Ingredient> ingredientCommand = recipe.getIngredients().stream().filter(ingredient -> ingredient.getId().equals(ingredientId)).findFirst();
        System.out.println(" ===>===> ===> ===> ====> ===> " + ingredientCommand.orElse(new Ingredient()).getId());
        System.out.println(" ===>===> ===> ===> ====> ===> " + ingredientCommand.orElse(new Ingredient()).getId());
        System.out.println(" ===>===> ===> ===> ====> ===> " + ingredientCommand.orElse(new Ingredient()).getId());

        IngredientCommand result = ingredientToIngredientCommand.convert(ingredientCommand.orElse(null));
//        Optional<IngredientCommand> optionalIngredientCommand = recipe.getIngredients().stream().filter(ingredient -> ingredient.getId().equals(ingredientId)).map(ingredientToIngredientCommand::convert).findFirst();
//
//        return optionalIngredientCommand.orElse(null);
        System.out.println("result est est getRecipeId " + result.getRecipeId());
        System.out.println("result est est id() " + result.getId());
        return result;
    }
}
