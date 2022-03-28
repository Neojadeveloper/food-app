package com.example.pdp_meal.telegram.handlers;


import com.example.pdp_meal.dto.auth.AuthUserCreateDto;
import com.example.pdp_meal.dto.auth.AuthUserDto;
import com.example.pdp_meal.enums.Role;
import com.example.pdp_meal.enums.State;
import com.example.pdp_meal.service.auth.AuthUserService;
import com.example.pdp_meal.telegram.BotProcess;
import com.example.pdp_meal.telegram.buttons.InlineBoards;
import com.example.pdp_meal.telegram.buttons.MarkupBoards;
import com.example.pdp_meal.telegram.telegramService.TelegramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@RequiredArgsConstructor
public class CallbackHandler {
    private final BotProcess BOT;
    private final AuthUserService userService;
    private final TelegramService telegramService;



    public void handle(CallbackQuery callbackQuery) {
        Message message = callbackQuery.getMessage();
        String data = callbackQuery.getData();
        String chatID = message.getChatId().toString();
        AuthUserDto user = userService.getByChatId(chatID);

        switch (data) {
            case "academic", "marketing", "economic" -> {
                DeleteMessage deleteMessage = new DeleteMessage(chatID, message.getMessageId());
                BOT.executeMessage(deleteMessage);
                AuthUserCreateDto userCreateDto = BOT.userHashMap.get(chatID);
                userCreateDto.setDepartment(data);
                BOT.userHashMap.put(chatID, userCreateDto);
                SendMessage message1 = new SendMessage(chatID, "Choose your position");
                message1.setReplyMarkup(InlineBoards.position());
                BOT.executeMessage(message1);
                BOT.userState.put(chatID, State.POSITION.getName());
            }

            case "teacher", "administrator", "headDepartment" -> {
                DeleteMessage deleteMessage = new DeleteMessage(chatID, message.getMessageId());
                BOT.executeMessage(deleteMessage);
                AuthUserCreateDto userCreateDto = BOT.userHashMap.get(chatID);
                userCreateDto.setChatId(chatID);
                userCreateDto.setState(State.REGISTERED.getName());
                userCreateDto.setRole(Role.USER.name());
                userCreateDto.setPosition(data);
                userCreateDto.setActive(true);
                userCreateDto.setState(State.REGISTERED.getName());
                BOT.userHashMap.put(chatID, userCreateDto);
                userService.create(userCreateDto);
                BOT.userState.put(chatID, State.REGISTERED.getName());
                SendMessage message1 = new SendMessage(chatID, "Successfully registered");
                message1.setReplyMarkup(MarkupBoards.mainMenu(userCreateDto.getRole()));
                BOT.executeMessage(message1);
            }

            case "1", "2", "3", "4", "5","6","7","8","9","10" -> {
                telegramService.orderMeal(chatID, data);
//                DeleteMessage deleteMessage = new DeleteMessage(chatID, message.getMessageId());
//                BOT.executeMessage(deleteMessage);
            }
            case "save" ->{
                SendMessage message1 = new SendMessage(chatID, "Successfully added");
                message1.setReplyMarkup(MarkupBoards.mainMenu(user.getRole()));
                BOT.executeMessage(message1);
            }



        }

    }

}
