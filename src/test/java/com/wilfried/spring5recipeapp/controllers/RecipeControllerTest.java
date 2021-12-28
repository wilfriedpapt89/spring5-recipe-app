package com.wilfried.spring5recipeapp.controllers;

import com.wilfried.spring5recipeapp.commands.RecipeCommand;
import com.wilfried.spring5recipeapp.domain.Recipe;
import com.wilfried.spring5recipeapp.repositories.RecipeRepository;
import com.wilfried.spring5recipeapp.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {


    MockMvc mockMvc;

    @Mock
    RecipeService recipeService;

    @Mock
    RecipeRepository recipeRepository;

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
    void testgetRecipe() throws Exception {
        when(recipeService.findById(anyLong())).thenReturn(returnedRecipe);
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/show"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.view().name("recipe/show"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));
    }

    @Test
    void testGetNewRecipe() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/new")).andExpect(MockMvcResultMatchers.view().
                name("recipe/recipeform")).andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));
    }

    @Test
    void testPostNewRecipe() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(2L);

        when(recipeService.saveRecipeCommand(any()))
                .thenReturn(recipeCommand);

        mockMvc.perform(MockMvcRequestBuilders.post("/recipe/saverecipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED).param("id", "")
                .param("description", "some string")).andExpect(MockMvcResultMatchers.status().is3xxRedirection()).andExpect(MockMvcResultMatchers.view().name("redirect:/recipe/2/show"));
    }

    @Test
    void testGetUpdateView() throws Exception {

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);
        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);
        mockMvc.perform(MockMvcRequestBuilders.
                get("/recipe/1/update").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("id", "1")
                .param("description", "some description")).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/recipeform"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));
    }

    @Test
    void testDeleteAction() throws Exception {

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/delete"))
                //then
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                //then
                .andExpect(MockMvcResultMatchers.view()
                        //then
                        .name("redirect:/"));
        verify(recipeService, times(1)).deleteById(anyLong());
    }
}