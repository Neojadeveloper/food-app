package com.example.pdp_meal.dto.meal;

import com.example.pdp_meal.dto.GenericDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.time.Instant;

/**
 * @author Bekpulatov Shoxruh, Wed 10:56 PM. 3/2/2022
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MealDto extends GenericDto {

    private String name;
    private String ingredient;

    private String fileId;

    private Instant createDate = Instant.now();
}
