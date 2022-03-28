package com.example.pdp_meal.dto.feedback;

import com.example.pdp_meal.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

/**
 * @author Panjiyev Javohir, ср 21:08. 02.03.2022
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedBackCreateDto implements BaseDto {


    private String message;

    private Integer userId;

    private String type;
}
