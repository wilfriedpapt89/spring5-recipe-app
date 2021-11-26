package com.wilfried.spring5recipeapp.controllers;

import com.wilfried.spring5recipeapp.domain.Recipe;
import com.wilfried.spring5recipeapp.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping({"/recipe"})
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"/show/{id}"})
    public String getRecipe(@PathVariable String id, Model model) {
        Recipe recipe
                = recipeService.findById(new Long(id));
        model.addAttribute("recipe",recipe);
        return "recipe/show";
    }
}
