package com.wilfried.spring5recipeapp.controllers;

import com.wilfried.spring5recipeapp.repositories.CategoryRepository;
import com.wilfried.spring5recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"","/", "/index"})
    public String getIndexPage(){
        System.out.println("Category" + categoryRepository.findByDescription("American"));
        System.out.println("UOM" + unitOfMeasureRepository.findByDescription("Teaspoon"));
        return "index";
    }
}
