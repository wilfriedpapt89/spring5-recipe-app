package com.wilfried.spring5recipeapp.controllers;

import com.wilfried.spring5recipeapp.commands.RecipeCommand;
import com.wilfried.spring5recipeapp.domain.Recipe;
import com.wilfried.spring5recipeapp.service.RecipeService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller()
@RequestMapping({"/recipe"})
@Slf4j
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"/{id}/show"})
    public String getRecipe(@PathVariable String id, Model model) {
        Recipe recipe
                = recipeService.findById(new Long(id));
        model.addAttribute("recipe", recipe);
        return "recipe/show";
    }

    @RequestMapping({"/new"})
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeform";
    }

    @PostMapping("saverecipe")
    public String saveOrUpdateRecipe(@ModelAttribute RecipeCommand recipeCommand) {
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(recipeCommand);
        return "redirect:/recipe/"+ savedRecipeCommand.getId() + "/show";
    }

    @RequestMapping({"/{id}/update"})
    public String updateRecipe(@PathVariable String id, Model model){
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.parseLong(id));
        model.addAttribute("recipe", recipeCommand);
        return "recipe/recipeform";
    }

    @RequestMapping({"/{id}/delete"})
    public String deleteRecipe(@PathVariable String id, Model model){

        recipeService.deleteById(Long.parseLong(id));
        return "redirect:/";
    }
}
