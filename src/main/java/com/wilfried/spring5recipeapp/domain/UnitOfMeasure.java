package com.wilfried.spring5recipeapp.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class UnitOfMeasure {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

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

    @Override
    public String toString() {
        return "UnitOfMeasure{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
