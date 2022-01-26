package com.wilfried.spring5recipeapp.mapper;

import com.wilfried.spring5recipeapp.domain.Category;
import com.wilfried.spring5recipeapp.dto.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDto categorytoCategoryDto(Category category);

}
