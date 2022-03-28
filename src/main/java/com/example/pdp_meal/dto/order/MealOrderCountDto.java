package com.example.pdp_meal.dto.order;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MealOrderCountDto {
    private String mealId;
    private Long countMeal;

}
