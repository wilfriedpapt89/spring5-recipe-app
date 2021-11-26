package com.wilfried.spring5recipeapp.converters;

import com.wilfried.spring5recipeapp.commands.IngredientCommand;
import com.wilfried.spring5recipeapp.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientCommand source) {

        if (source == null)
            return null;
        else {
            final Ingredient ingredient = new Ingredient();
            ingredient.setId(source.getId());
            ingredient.setAmount(source.getAmount());
            ingredient.setDescription(source.getDescription());
            return ingredient;
        }
    }
}
