package com.wilfried.spring5recipeapp.controllers;

import com.wilfried.spring5recipeapp.commands.RecipeCommand;
import com.wilfried.spring5recipeapp.repositories.UnitOfMeasureRepository;
import com.wilfried.spring5recipeapp.service.IngredientService;
import com.wilfried.spring5recipeapp.service.RecipeService;
import com.wilfried.spring5recipeapp.service.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }


    @RequestMapping({"/recipe/{recipeId}/ingredients"})
    public String showListIngredient(@PathVariable String recipeId, Model model) {
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.parseLong(recipeId));
        model.addAttribute("recipe", recipeCommand);
        return "recipe/ingredient/list";
    }

    @RequestMapping({"/recipe/{recipeId}/ingredient/{id}/show"})
    public String showOneIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.parseLong(recipeId), Long.parseLong(id)));
        return "recipe/ingredient/show";
    }

    public String updateRecipeIngredient(@PathVariable("recipeId") String recipeId, @PathVariable("id") String id, Model model) {

        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.parseLong(recipeId), Long.parseLong(id)));
        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
        return "/recipe/ingredient/ingredientForm";
    }

}
