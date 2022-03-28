package com.example.pdp_meal.mapper.order;


import com.example.pdp_meal.dto.order.OrderCreateDto;
import com.example.pdp_meal.dto.order.OrderDto;
import com.example.pdp_meal.dto.order.OrderUpdateDto;
import com.example.pdp_meal.entity.MealOrder;
import com.example.pdp_meal.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface OrderMapper extends BaseMapper<MealOrder, OrderDto, OrderCreateDto, OrderUpdateDto> {
    @Override
     OrderDto toDto(MealOrder mealOrder);

    @Override
     List<OrderDto> toDto(List<MealOrder> e);

    @Override
     MealOrder fromCreateDto(OrderCreateDto orderCreateDto);

    @Override
     MealOrder fromUpdateDto(OrderUpdateDto orderUpdateDto);
}
