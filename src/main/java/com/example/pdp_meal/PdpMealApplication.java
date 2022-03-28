package com.example.pdp_meal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class PdpMealApplication {
    public static void main(String[] args) {
        SpringApplication.run(PdpMealApplication.class, args);
    }

}
