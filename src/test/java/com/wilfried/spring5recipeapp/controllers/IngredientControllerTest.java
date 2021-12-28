package com.wilfried.spring5recipeapp.controllers;

import com.wilfried.spring5recipeapp.commands.IngredientCommand;
import com.wilfried.spring5recipeapp.commands.RecipeCommand;
import com.wilfried.spring5recipeapp.service.IngredientService;
import com.wilfried.spring5recipeapp.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class IngredientControllerTest {

    @InjectMocks
    IngredientController ingredientController;

    @Mock
    IngredientService ingredientService;

    @Mock
    RecipeService recipeService;

    MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
    }

    @Test
    void testListIngredient() throws Exception {

        //Given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);
        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredients"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/ingredient/list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));

        //Then
        verify(recipeService, times(1)).findCommandById(anyLong());
    }

    @Test
    void testShowOneIngredient() throws Exception {

        //Given
        IngredientCommand ingredientCommand = new IngredientCommand();
//        ingredientCommand.setId(1L);
        //when
        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);
        //Then
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredient/1/show")).
                andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.view().name("recipe/ingredient/show"));
    }
}