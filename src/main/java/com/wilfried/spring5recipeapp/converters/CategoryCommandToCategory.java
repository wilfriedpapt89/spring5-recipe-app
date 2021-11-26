package com.wilfried.spring5recipeapp.converters;

import com.wilfried.spring5recipeapp.commands.CategoryCommand;
import com.wilfried.spring5recipeapp.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand source) {

        if (source == null)
            return null;
        else {
            final Category category = new Category();
            category.setId(source.getId());
            category.setDescription(source.getDescription());
            return category;
        }
    }
}
