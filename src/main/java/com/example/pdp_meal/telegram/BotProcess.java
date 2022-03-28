package com.example.pdp_meal.telegram;

import com.example.pdp_meal.dto.auth.AuthUserCreateDto;
import com.example.pdp_meal.dto.meal.MealCreateDto;
import com.example.pdp_meal.telegram.handlers.UpdateHandler;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;


@Component
public class BotProcess extends TelegramLongPollingBot {

    private final UpdateHandler updateHandler;
    public  final Map<String, String> userState = new HashMap<>();
    public  final Map<String, String> mealState = new HashMap<>();
    public  final Map<String, AuthUserCreateDto> userHashMap = new HashMap<>();
    public  final Map<String, MealCreateDto> mealHashMap = new HashMap<>();


    public BotProcess(@Lazy UpdateHandler updateHandler) {
        this.updateHandler = updateHandler;
    }


    @Override
    public String getBotUsername() {
        return "PDP_MEAL";
    }

    @Override
    public String getBotToken() {
        return "5113084360:AAGDIqDJa5hLPGwnpbbPbKKMmIlijEGt8mE";
    }

    @Override
    public void onUpdateReceived(Update update) {
        updateHandler.handle(update);
    }

    public void executeMessage(BotApiMethod<?> msg) {
        try {
            execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void executeMessage(SendMessage msg) {
        try {
            execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void executeMessage(SendDocument msg) {
        try {
            execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void executeMessage(SendPhoto msg) {
        try {
            execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void executeMessage(SendAnimation msg) {
        try {
            execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }



}
