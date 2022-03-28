package com.example.pdp_meal.dto.auth;

import com.example.pdp_meal.dto.GenericDto;
import lombok.Getter;
import lombok.Setter;


/**
 * @author Botirov Najmiddin, Thu 2:26 PM. 3/3/2022
 */

@Getter
@Setter
public class AuthUserDto extends GenericDto {
    private String fullName;

    private String phone;

    private String username;

    private String chatId;
    private String role;
    private String department;
    private String position;
    private String state;
    private boolean active;

}
