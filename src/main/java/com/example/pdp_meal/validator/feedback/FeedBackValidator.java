package com.example.pdp_meal.validator.feedback;

import com.example.pdp_meal.dto.feedback.FeedBackCreateDto;
import com.example.pdp_meal.dto.feedback.FeedBackUpdateDto;
import com.example.pdp_meal.exception.NotFoundException;
import com.example.pdp_meal.validator.AbstractValidator;
import org.springframework.stereotype.Component;

import java.util.Objects;


/**
 * @author Panjiyev Javohir, чт 14:52. 03.03.2022
 */
@Component
public class FeedBackValidator extends AbstractValidator<FeedBackCreateDto, FeedBackUpdateDto, InstantiationError> {

    @Override
    public void validateKey(InstantiationError id) throws NotFoundException {

    }

    @Override
    public void validOnCreate(FeedBackCreateDto feedBackCreateDto) throws NotFoundException {
        if (Objects.isNull(feedBackCreateDto)) {
            throw new NotFoundException("Invalid data");
        }
    }

    @Override
    public void validOnUpdate(FeedBackUpdateDto cd) throws NotFoundException {
        if (Objects.isNull(cd)) {
            throw new NotFoundException("Invalid data");
        }
    }
}
