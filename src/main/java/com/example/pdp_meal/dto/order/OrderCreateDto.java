package com.example.pdp_meal.dto.order;

import com.example.pdp_meal.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateDto implements BaseDto {
    private Integer userId;
    private Integer mealId;

}
