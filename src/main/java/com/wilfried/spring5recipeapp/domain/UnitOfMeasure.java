package com.wilfried.spring5recipeapp.domain;

import javax.persistence.*;

@Entity
public class UnitOfMeasure {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descritpion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescritpion() {
        return descritpion;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
    }
}
