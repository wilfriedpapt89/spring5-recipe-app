package com.wilfried.spring5recipeapp.domain;

import javax.persistence.*;

@Entity
public class Notes {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String recipeNotes;
    @OneToOne
    private Recipe recipe;

    public Notes() {
    }

    public Notes(String recipeNotes, Recipe recipe) {
        this.recipeNotes = recipeNotes;
        this.recipe = recipe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipeNotes() {
        return recipeNotes;
    }

    public void setRecipeNotes(String recipeNotes) {
        this.recipeNotes = recipeNotes;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
