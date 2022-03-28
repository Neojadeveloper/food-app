package com.example.pdp_meal.telegram.telegramService;


import com.example.pdp_meal.dto.auth.AuthUserDto;
import com.example.pdp_meal.dto.meal.MealCreateDto;
import com.example.pdp_meal.enums.State;
import com.example.pdp_meal.service.auth.AuthUserService;
import com.example.pdp_meal.service.meal.MealService;
import com.example.pdp_meal.telegram.BotProcess;
import com.example.pdp_meal.telegram.buttons.MarkupBoards;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MealServiceTelegram {

    private final BotProcess BOT;
    private final MealService mealService;
    private final  AuthUserService userService;

    public  void add(Message message) {
        String chatID = message.getChatId().toString();
        String state = BOT.mealState.get(chatID);
        AuthUserDto user = userService.getByChatId(chatID);

        if (Objects.isNull(BOT.mealHashMap.get(chatID))) {
            BOT.mealHashMap.put(chatID, new MealCreateDto());
        }

        if ( State.START.getName().equals(state) ) {
            SendMessage sendMessage = new SendMessage(chatID, "Enter meal name ");
            sendMessage.setReplyMarkup(new ForceReplyKeyboard());
            BOT.executeMessage(sendMessage);
            BOT.mealState.put(chatID, State.MEAL_NAME.getName());
        } else if (State.MEAL_NAME.getName().equals(state)) {
            SendMessage sendMessage = new SendMessage(chatID, "Enter meal ingredient ");
            sendMessage.setReplyMarkup(new ForceReplyKeyboard());
            BOT.executeMessage(sendMessage);
            MealCreateDto mealCreateDto = BOT.mealHashMap.get(chatID);
            mealCreateDto.setName(message.getText());
            BOT.mealHashMap.put(chatID, mealCreateDto);
            BOT.mealState.put(chatID, State.MEAL_INGREDIENT.getName());
        } else if (State.MEAL_INGREDIENT.getName().equals(state)) {
            SendMessage sendMessage = new SendMessage(chatID, "Send meal picture ");
            BOT.executeMessage(sendMessage);
            MealCreateDto mealCreateDto = BOT.mealHashMap.get(chatID);
            mealCreateDto.setIngredient(message.getText());
            BOT.mealHashMap.put(chatID, mealCreateDto);
            BOT.mealState.put(chatID, State.MEAL_PATH.getName());
        } else if (State.MEAL_PATH.getName().equals(state)) {
            if (message.hasPhoto()) {
                List<PhotoSize> photo = message.getPhoto();
                String fileId = photo.stream().findFirst().get().getFileId();

//                Document document = message.getDocument();
//                String mimeType = message.getDocument().getMimeType();
//                if (mimeType.equals("image/jpg") || mimeType.equals("image/png")
//                        || mimeType.equals("image/bmp") || mimeType.equals("image/jpeg")) {
//                    String fileId = document.getFileId();
                    MealCreateDto mealCreateDto = BOT.mealHashMap.get(chatID);
                    mealCreateDto.setFileId(fileId);
                    BOT.mealHashMap.put(chatID, mealCreateDto);
                    mealService.create(mealCreateDto);
                    BOT.mealState.put(chatID, State.START.getName());
                    SendMessage message1 = new SendMessage(chatID, "Successfully added meal");
                    message1.setReplyMarkup(MarkupBoards.mainMenu(user.getRole()));
                    BOT.executeMessage(message1);
//                } else {
//                    SendMessage sendMessage = new SendMessage(chatID, "Send another image is not valid");
//                    BOT.executeMessage(sendMessage);
//                    BOT.mealState.put(chatID, State.MEAL_PATH.getName());
                }
            }
        }

    }

