package com.wilfried.spring5recipeapp.dto;

import com.wilfried.spring5recipeapp.domain.Recipe;

import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

public class CategoryDto {

    private Long id;
    private String description;
}
