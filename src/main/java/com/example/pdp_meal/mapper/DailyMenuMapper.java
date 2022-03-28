package com.example.pdp_meal.mapper;

import com.example.pdp_meal.dto.dailyMenu.DailyMenuCreateDto;
import com.example.pdp_meal.dto.dailyMenu.DailyMenuDto;
import com.example.pdp_meal.dto.dailyMenu.DailyMenuUpdateDto;
import com.example.pdp_meal.entity.DailyMenu;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Mapper(componentModel = "spring")
public interface DailyMenuMapper extends BaseMapper <DailyMenu,
        DailyMenuDto,
        DailyMenuCreateDto,
        DailyMenuUpdateDto> {

    @Override
    DailyMenuDto toDto(DailyMenu dailyMenu);

    @Override
    List<DailyMenuDto> toDto(List<DailyMenu> e);

    @Override
    DailyMenu fromCreateDto(DailyMenuCreateDto dto);

    @Override
    DailyMenu fromUpdateDto(DailyMenuUpdateDto dto);

}
