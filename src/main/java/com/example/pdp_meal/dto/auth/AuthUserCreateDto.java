package com.example.pdp_meal.dto.auth;

import com.example.pdp_meal.dto.BaseDto;
import com.example.pdp_meal.enums.Status;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserCreateDto implements BaseDto {

    private String fullName;
    private String username;
    private String password;
    private String chatId;
    private String phone;
    private String role;
    private String department;
    private String position;
    private String state;
    private boolean active;
}
