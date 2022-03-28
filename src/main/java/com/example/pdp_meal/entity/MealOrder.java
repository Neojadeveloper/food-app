package com.example.pdp_meal.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;


@Getter
@Setter
@Entity
public class MealOrder implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer userId;

    private Integer mealId;

    private String createDate = LocalDate.now().toString();

}
