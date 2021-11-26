package com.wilfried.spring5recipeapp.converters;

import com.wilfried.spring5recipeapp.commands.CategoryCommand;
import com.wilfried.spring5recipeapp.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryCommandToCategoryTest {

    private static final Long LONGUE_VALUE = 1L;
    private static final String DESCRIPTION_VALUE = "Breive description";
    CategoryCommandToCategory categoryCommandToCategory;

    @BeforeEach
    void setUp() {
        categoryCommandToCategory = new CategoryCommandToCategory();
    }

    @Test
    void testEmptyObject() {
        assertNotNull(categoryCommandToCategory.convert(new CategoryCommand()));
    }

    @Test
    void testNullParameter() {
        assertNull(categoryCommandToCategory.convert(null));
    }

    @Test
    void convert() {
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(LONGUE_VALUE);
        categoryCommand.setDescription(DESCRIPTION_VALUE);
        Category category = categoryCommandToCategory.convert(categoryCommand);
        assertNotNull(category);
        assertEquals(LONGUE_VALUE,category.getId());
        assertEquals(DESCRIPTION_VALUE,category.getDescription());
    }
}