package com.wilfried.spring5recipeapp.dto;

import com.wilfried.spring5recipeapp.domain.Category;
import com.wilfried.spring5recipeapp.domain.Difficulty;
import com.wilfried.spring5recipeapp.domain.Ingredient;
import com.wilfried.spring5recipeapp.domain.Notes;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class RecipeDto {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Byte[] image;
    private String difficulty;
    private NotesDto notes;
    private Set<IngredientDto> ingredients = new HashSet<>();
    private Set<CategoryDto> categories = new HashSet<>();
    private Timestamp createdOn;
    private Timestamp updatedOn;
}
