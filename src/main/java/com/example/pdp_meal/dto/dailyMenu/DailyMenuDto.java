package com.example.pdp_meal.dto.dailyMenu;

import com.example.pdp_meal.dto.GenericDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DailyMenuDto extends GenericDto {
    private Integer mealId;
    private String date;
}
