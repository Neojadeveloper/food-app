package com.example.pdp_meal.dto.feedback;


import com.example.pdp_meal.dto.GenericDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Panjiyev Javohir, ср 21:09. 02.03.2022
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedBackUpdateDto extends GenericDto {

    private String message;

    private String type;

}
