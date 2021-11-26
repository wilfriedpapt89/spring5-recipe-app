package com.wilfried.spring5recipeapp.controllers;

import com.wilfried.spring5recipeapp.domain.Recipe;
import com.wilfried.spring5recipeapp.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {


    MockMvc mockMvc;

    @Mock
    RecipeService recipeService;

    @InjectMocks
    RecipeController recipeController;

    Recipe returnedRecipe;

    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
        returnedRecipe = new Recipe();
        returnedRecipe.setId(1L);
    }

    @Test
    void getRecipe() throws Exception {
        when(recipeService.findById(anyLong())).thenReturn(returnedRecipe);
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/show/1"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.view().name("recipe/show"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));
    }
}