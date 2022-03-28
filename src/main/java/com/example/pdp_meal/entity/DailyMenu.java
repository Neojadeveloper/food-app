package com.example.pdp_meal.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;


@Getter
@Setter
@Entity
public class DailyMenu implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer mealId;


    private String date = LocalDate.now().toString();

}
