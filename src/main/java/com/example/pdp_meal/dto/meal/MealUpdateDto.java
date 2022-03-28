package com.example.pdp_meal.dto.meal;

import com.example.pdp_meal.dto.GenericDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author Bekpulatov Shoxruh, Wed 10:56 PM. 3/2/2022
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MealUpdateDto extends GenericDto {

    @NotBlank
    private String name;

    private String ingredient;


    @NotBlank
    private String fileId;
}
