package com.example.pdp_meal.mapper;

import com.example.pdp_meal.dto.meal.MealCreateDto;
import com.example.pdp_meal.dto.meal.MealDto;
import com.example.pdp_meal.dto.meal.MealUpdateDto;
import com.example.pdp_meal.entity.Meal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

/**
 * @author Bekpulatov Shoxruh, Wed 11:16 PM. 3/2/2022
 */

@Component
@Mapper(componentModel = "spring")
public interface MealMapper extends BaseMapper<Meal, MealDto, MealCreateDto, MealUpdateDto> {

    @Override
    Meal fromCreateDto(MealCreateDto mealCreateDto);


    @Override
    Meal fromUpdateDto(MealUpdateDto mealUpdateDto);
}
