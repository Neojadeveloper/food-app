package com.example.pdp_meal.telegram.handlers;


import com.example.pdp_meal.dto.auth.AuthUserDto;
import com.example.pdp_meal.dto.feedback.FeedBackCreateDto;
import com.example.pdp_meal.enums.FeedBackType;
import com.example.pdp_meal.enums.Role;
import com.example.pdp_meal.enums.State;
import com.example.pdp_meal.service.auth.AuthUserService;
import com.example.pdp_meal.service.fedback.FeedBackService;
import com.example.pdp_meal.telegram.BotProcess;
import com.example.pdp_meal.telegram.buttons.MarkupBoards;
import com.example.pdp_meal.telegram.emojis.Emojis;
import com.example.pdp_meal.telegram.telegramService.MealServiceTelegram;
import com.example.pdp_meal.telegram.telegramService.RegisterService;
import com.example.pdp_meal.telegram.telegramService.TelegramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Objects;


@Component
@RequiredArgsConstructor
public class MessageHandler {

    private final TelegramService service;
    private final RegisterService registerService;
    private final AuthUserService userService;
    private final FeedBackService feedBackService;
    private final MealServiceTelegram mealServiceTelegram;
    private final BotProcess BOT;


    public void handle(Message message) {
        String chatId = message.getChatId().toString();
        AuthUserDto user = userService.getByChatId(chatId);
        String stateMeal = BOT.mealState.get(chatId);
        String text = message.getText();

        if (Objects.isNull(text)) {
            message.setText("ok");
        }
        if (Objects.isNull(stateMeal)) {
            BOT.mealState.put(chatId, State.START.getName());
        } else if (message.hasPhoto() ||
                (message.getText().equals(Emojis.ADD + "Meal add")) ||
                !BOT.mealState.get(chatId).equals(State.MEAL_ADDED.getName()) &&
                        !BOT.mealState.get(chatId).equals(State.START.getName())) {
            mealServiceTelegram.add(message);
        }
        if (Objects.nonNull(user)) {
            BOT.userState.put(chatId, State.START.getName());
        }


        if (message.hasContact() || (message.getText().equals("/start") &&
                Objects.isNull(user)) ||
                !BOT.userState.get(chatId).equals(State.START.getName())) {
            registerService.register(message);
        } else if (message.getText().equals("/start") && Objects.nonNull(user)) {
            SendMessage message1 = new SendMessage(chatId, "Menu");
            message1.setReplyMarkup(MarkupBoards.mainMenu(user.getRole()));
            BOT.executeMessage(message1);
        } else if (message.getText().equals("/help") ||
                message.getText().equals(Emojis.HELP + "Help")) {
            service.help(chatId);
        } else if (message.getText().equals(Emojis.MEAL + "Order")) {
            service.ordering(chatId);
        } else if (message.getText().equals("/support") ||
                message.getText().equals(Emojis.SUPPORT + "Support")) {
            service.support(chatId);
        } else if (message.getText().equals("/about_us") ||
                message.getText().equals(Emojis.ABOUT_US + "About us")) {
            service.aboutUs(chatId);
        } else if (message.getText().equals("/feedback") ||
                message.getText().equals(Emojis.FEEDBACK + "Feedback")) {
            service.feedback(chatId);
        } else if ((message.getText().equals("/feedback") ||
                message.getText().equals(Emojis.FEEDBACK + "Feedbacks")
                        && user.getRole().equals(Role.ADMIN.name()))) {
            service.feedback(chatId);
        }
        // for Admin offers see
        else if (message.getText().equals(Emojis.OFFER + "Offer")
                && user.getRole().equals(Role.ADMIN.name())) {
            SendMessage offers = new SendMessage();
            offers.setChatId(chatId);
            service.getFeedbacksOffer(chatId);
        } else if (message.getText().equals(Emojis.MENU + "Create Menu") &&
                user.getRole().equals(Role.ADMIN.name())) {

            service.createMenu(chatId);
        } else if (message.getText().equals((Emojis.ORDERS + "Orders"))) {
            if (user.getRole().equals("ADMIN")) {
                service.getOrders(chatId);
            }
        }
        // for Admin Disapprovals see
        else if (message.getText().equals(Emojis.DISAPPROVAL + "Disapproval")
                && user.getRole().equals(Role.ADMIN.name())) {
            service.getFeedbacksDisapproval(chatId);
        } else if (message.getText().equals("/profile") ||
                message.getText().equals(Emojis.PROFILE + "Profile")) {
            service.profile(chatId);
        } else if (message.getText().equals(Emojis.MEAL + "Order")) {
            service.ordering(chatId);
        } else if (message.getText().equals(Emojis.GO_BACK + "Back")) {
            service.changeStatus(chatId, State.REGISTERED.getName());
            service.mainMenu(chatId, user.getRole());
        } else if (message.getText().equals(Emojis.OFFER + "Offer")) {
            service.changeStatus(chatId, State.OFFER.getName());
            SendMessage message1 = new SendMessage(chatId, "Your feedBack : ");
            message1.setReplyMarkup(MarkupBoards.back());
            BOT.executeMessage(message1);
        } else if (message.getText().equals(Emojis.DISAPPROVAL + "Disapproval")) {
            service.changeStatus(chatId, State.DISAPPROVAL.getName());
            SendMessage message1 = new SendMessage(chatId, "Your feedBack : ");
            message1.setReplyMarkup(MarkupBoards.back());
            BOT.executeMessage(message1);
        } else if (user.getState().equals(State.OFFER.getName())) {
            feedBackService.create(new FeedBackCreateDto(message.getText(),
                    user.getId(), FeedBackType.POSITIVE.name()));
            service.changeStatus(chatId, State.REGISTERED.getName());
            SendMessage sendMessage = new SendMessage(chatId, "Thank you for your feedback " + Emojis.SMILE);
            BOT.executeMessage(sendMessage);
            service.mainMenu(chatId, user.getRole());
        } else if (user.getState().equals(State.DISAPPROVAL.getName())) {
            feedBackService.create(new FeedBackCreateDto(message.getText(),
                    user.getId(), FeedBackType.NEGATIVE.name()));
            service.changeStatus(chatId, State.REGISTERED.getName());
            SendMessage sendMessage = new SendMessage(chatId, "Thank you for your feedback " + Emojis.SMILE);
            BOT.executeMessage(sendMessage);
            service.mainMenu(chatId, user.getRole());
        }

    }


}
