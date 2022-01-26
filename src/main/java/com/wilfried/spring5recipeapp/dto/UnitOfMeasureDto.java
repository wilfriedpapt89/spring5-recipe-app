package com.wilfried.spring5recipeapp.dto;

public class UnitOfMeasureDto {

    private Long id;
    private String description;

    @Override
    public String toString() {
        return "UnitOfMeasureDto{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
