package com.example.pdp_meal.validator.auth;


import com.example.pdp_meal.dto.auth.AuthUserCreateDto;
import com.example.pdp_meal.dto.auth.AuthUserUpdateDto;
import com.example.pdp_meal.exception.NotFoundException;
import com.example.pdp_meal.validator.AbstractValidator;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Botirov Najmiddin, Thu 2:44 PM. 3/3/2022
 */

@Component
public class AuthUserValidator extends AbstractValidator<AuthUserCreateDto, AuthUserUpdateDto, InstantiationError> {
    @Override
    public void validateKey(InstantiationError id) throws NotFoundException {

    }

    @Override
    public void validOnCreate(AuthUserCreateDto dto) throws NotFoundException {
        if (Objects.isNull(dto)) {
            throw new NotFoundException("Invalid data");
        }
    }

    @Override
    public void validOnUpdate(AuthUserUpdateDto cd) throws NotFoundException {
        if (Objects.isNull(cd)) {
            throw new NotFoundException("Invalid data");
        }
    }
}
