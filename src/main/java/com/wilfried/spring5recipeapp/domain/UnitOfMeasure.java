package com.wilfried.spring5recipeapp.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
public class UnitOfMeasure {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
}
