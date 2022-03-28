package com.example.pdp_meal.mapper;

import com.example.pdp_meal.dto.feedback.FeedBackCreateDto;
import com.example.pdp_meal.dto.feedback.FeedBackDto;
import com.example.pdp_meal.dto.feedback.FeedBackUpdateDto;
import com.example.pdp_meal.entity.FeedBack;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author Panjiyev Javohir, чт 0:33. 03.03.2022
 */

@Component
@Mapper(componentModel = "spring")
public interface FeedBackMapper extends BaseMapper<FeedBack, FeedBackDto, FeedBackCreateDto, FeedBackUpdateDto>{



}
