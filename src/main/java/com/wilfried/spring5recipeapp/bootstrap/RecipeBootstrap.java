package com.wilfried.spring5recipeapp.bootstrap;

import com.wilfried.spring5recipeapp.domain.*;
import com.wilfried.spring5recipeapp.repositories.CategoryRepository;
import com.wilfried.spring5recipeapp.repositories.RecipeRepository;
import com.wilfried.spring5recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    private List<Recipe> getRecipes(){
        List<Recipe> recipes = new ArrayList<>();

        // Load Unit of Measure

        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");

        if(!eachUomOptional.isPresent()){
            throw new RuntimeException("eachUomOptional is not present");
        }
        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if(!tableSpoonUomOptional.isPresent()){
            throw new RuntimeException("tableSpoonUomOptional is not present");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if(!teaSpoonUomOptional.isPresent()){
            throw new RuntimeException("teaSpoonUomOptional is not present");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");

        if(!dashUomOptional.isPresent()){
            throw new RuntimeException("dashUomOptional is not present");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");

        if(!pintUomOptional.isPresent()){
            throw new RuntimeException("pintUomOptional is not present");
        }


        // Load categories

        Optional<Category> americanCategory = categoryRepository.findByDescription("American");

        if(!americanCategory.isPresent()){
            throw new RuntimeException("americanCategory is not present");
        }

        Optional<Category> mexicanCategory = categoryRepository.findByDescription("Mexican");

        if(!mexicanCategory.isPresent()){
            throw new RuntimeException("mexicanCategory is not present");
        }


        //Yummy guac

        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.HARD);
        guacRecipe.getCategories().add(americanCategory.get());
        guacRecipe.getCategories().add(mexicanCategory.get());
        guacRecipe.setDirections("\"1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon\" +\n" +
                "                \"\\n\" +\n" +
                "                \"2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\" +\n" +
                "                \"\\n\" +\n" +
                "                \"3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\\n\" +\n" +
                "                \"Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\\n\" +\n" +
                "                \"Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\\n\" +\n" +
                "                \"4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\\n\" +\n" +
                "                \"Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"\\n\" +\n" +
                "                \"Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvpiV9Sd\"");


        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvoun5ws");

        // adding ingredients to recipe
        guacRecipe.getIngredients().add(new Ingredient("ripe avocados", new BigDecimal(2), guacRecipe, eachUomOptional.get()));
        guacRecipe.getIngredients().add(new Ingredient("Kosher salt", new BigDecimal(".5"),guacRecipe, teaSpoonUomOptional.get()));
        guacRecipe.getIngredients().add(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2),guacRecipe, tableSpoonUomOptional.get()));
        guacRecipe.setNotes(guacNotes);
        guacNotes.setRecipe(guacRecipe);
        recipes.add(guacRecipe);
        return recipes;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipes());
    }
}
