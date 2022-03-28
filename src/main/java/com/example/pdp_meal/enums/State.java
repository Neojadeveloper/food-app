package com.example.pdp_meal.enums;

import ch.qos.logback.classic.Logger;
import lombok.Getter;

@Getter
public enum State {
    UNAUTHORIZED("UNAUTHORIZED"),
    PHONE_NUMBER("PHONE_NUMBER"),
    FULL_NAME("FULL_NAME"),
    USER_NAME("USER_NAME"),
    PASSWORD("PASSWORD"),
    REGISTERED("REGISTERED"),
    ORDERING("ORDERING"),
    ORDERED("ORDERED"),
    PREPARING("PREPARING"),
    START("START"),
    DEPARTMENT("DEPARTMENT"),
    POSITION("POSITION"),
    DONE("DONE"),


    /// meal state

    MEAL_NAME("MEAL_NAME"),
    MEAL_INGREDIENT("MEAL_INGREDIENT"),
    MEAL_PATH("MEAL_PATH"),
    MEAL_ADDED("MEAL_ADDED"),
    OFFER("OFFER"),
    DISAPPROVAL("DISAPPROVAL");


    private final String name;

    State(String name) {
        this.name = name;
    }

    public static State findByText(String text) {
        for (State value : State.values()) {
            if (value.getName().equalsIgnoreCase(text)) {
                return value;
            }
        }
        return null;
    }
}
