package com.example.pdp_meal.controller;


import com.example.pdp_meal.dto.auth.AuthUserCreateDto;
import com.example.pdp_meal.service.auth.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/*")
@RequiredArgsConstructor
public class AuthUserController {

    private final AuthUserService service;

    @RequestMapping("create")
    public ResponseEntity<Integer> create(@RequestBody AuthUserCreateDto dto) {
       return ResponseEntity.ok().body( service.create(dto) );
    }


}
