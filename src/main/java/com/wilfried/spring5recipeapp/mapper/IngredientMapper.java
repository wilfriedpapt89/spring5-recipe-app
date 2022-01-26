package com.wilfried.spring5recipeapp.mapper;

import com.wilfried.spring5recipeapp.domain.Ingredient;
import com.wilfried.spring5recipeapp.dto.IngredientDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IngredientMapper {

    IngredientMapper INSTANCE = Mappers.getMapper(IngredientMapper.class);

    IngredientDto ingredientToIngredientDto(Ingredient ingredient);
}
