package com.wilfried.spring5recipeapp.converters;

import com.wilfried.spring5recipeapp.commands.IngredientCommand;
import com.wilfried.spring5recipeapp.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
        System.out.println("============================= instance ");
        System.out.println("============================= instance ");
        System.out.println("============================= instance ");
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient source) {
        System.out.println("reach the method ");
        System.out.println("reach the method with " + source.getId());
        IngredientCommand ingredientCommand = new IngredientCommand();
        try {
            if (source == null)
                return null;
            System.out.println("I'm not null ");

            ingredientCommand.setId(source.getId());
            if(source.getRecipe() != null)
                ingredientCommand.setRecipeId(source.getRecipe().getId());
            if (source.getAmount() != null)
                ingredientCommand.setAmount(source.getAmount());
            if(source.getUom() != null)
                ingredientCommand.setUom(unitOfMeasureToUnitOfMeasureCommand.convert(source.getUom()));
            if (source.getDescription() != null)
                ingredientCommand.setDescription(source.getDescription());
        } catch (Exception e){

            e.printStackTrace();
        }

        return ingredientCommand;
    }
}
