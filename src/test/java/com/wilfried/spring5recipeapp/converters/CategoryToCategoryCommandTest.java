package com.wilfried.spring5recipeapp.converters;

import com.wilfried.spring5recipeapp.commands.CategoryCommand;
import com.wilfried.spring5recipeapp.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryToCategoryCommandTest {

    public static final Long LONGUE_VALUE = 1L;
    public static final String DESCRIPTION_VALUE = "category_description";
    CategoryToCategoryCommand categoryToCategoryCommand;

    @BeforeEach
    void setUp() {
        categoryToCategoryCommand = new CategoryToCategoryCommand();
    }

    @Test
    void testNullParameter() {
        assertNull(categoryToCategoryCommand.convert(null));
    }

    @Test
    void testNotEmpty() {
        assertNotNull(categoryToCategoryCommand.convert(new Category()));
    }

    @Test
    void convert() {
        Category category = new Category();
        category.setId(LONGUE_VALUE);
        category.setDescription(DESCRIPTION_VALUE);
        CategoryCommand categoryCommand = categoryToCategoryCommand.convert(category);
        assertNotNull(categoryCommand);
        assertEquals(LONGUE_VALUE,categoryCommand.getId());
    }
}