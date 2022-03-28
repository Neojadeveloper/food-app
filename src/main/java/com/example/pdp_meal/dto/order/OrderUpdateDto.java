package com.example.pdp_meal.dto.order;

import com.example.pdp_meal.dto.GenericDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.Timestamp;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderUpdateDto extends GenericDto {


    private Integer userId;

    private Integer mealId;

}
