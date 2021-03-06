package com.wilfried.spring5recipeapp.domain;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    Category category;

    @BeforeEach
    public void setup(){
        category = new Category();
    }

    @Test
    void getId() {
        Long testId = 4L;
        category.setId(testId);
        assertEquals(testId, category.getId());
    }

    @Test
    void getDescription() {
    }

    @Test
    void getRecipes() {
    }
}