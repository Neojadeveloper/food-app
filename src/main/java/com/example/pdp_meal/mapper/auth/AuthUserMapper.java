package com.example.pdp_meal.mapper.auth;

import com.example.pdp_meal.dto.auth.AuthUserCreateDto;
import com.example.pdp_meal.dto.auth.AuthUserDto;
import com.example.pdp_meal.dto.auth.AuthUserUpdateDto;
import com.example.pdp_meal.entity.AuthUser;
import com.example.pdp_meal.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author Botirov Najmiddin, Thu 2:28 PM. 3/3/2022
 */

@Component
@Mapper(componentModel = "spring")
public interface AuthUserMapper extends BaseMapper<AuthUser,
        AuthUserDto,
        AuthUserCreateDto,
        AuthUserUpdateDto
        > {

}
