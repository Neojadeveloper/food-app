package com.example.pdp_meal.telegram.buttons;

import com.example.pdp_meal.enums.Role;
import com.example.pdp_meal.telegram.emojis.Emojis;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

@Component
public class MarkupBoards {

    private static final ReplyKeyboardMarkup board = new ReplyKeyboardMarkup();


    public static ReplyKeyboardMarkup sharePhoneNumber() {
        KeyboardButton phoneContact = new KeyboardButton(Emojis.PHONE + "Share phone number please ");
        phoneContact.setRequestContact(true);
        KeyboardRow row = new KeyboardRow();
        row.add(phoneContact);
        board.setKeyboard(List.of(row));
        board.setResizeKeyboard(true);
        board.setSelective(true);
        return board;
    }

    public static ReplyKeyboardMarkup mainMenu(String role) {
        if (role.equals(Role.ADMIN.name())){
            KeyboardRow row1 = new KeyboardRow();
            row1.add(new KeyboardButton(Emojis.ADD + "Meal add"));
            row1.add(new KeyboardButton(Emojis.MENU + "Create Menu"));

            KeyboardRow row2 = new KeyboardRow();
            row2.add(new KeyboardButton(Emojis.FEEDBACK + "Feedbacks"));
            row2.add(new KeyboardButton(Emojis.ORDERS + "Orders"));
            KeyboardRow row3 = new KeyboardRow();
            row3.add(new KeyboardButton(Emojis.PROFILE + "Profile"));
            row3.add(new KeyboardButton(Emojis.ABOUT_US + "About us"));

            board.setKeyboard(List.of(row1, row2, row3));
        }
        //row1
       if (role.equals(Role.USER.name())) {
            KeyboardRow row1 = new KeyboardRow();
            row1.add(new KeyboardButton(Emojis.FEEDBACK + "Feedback"));
            //row2
            KeyboardRow row2 = new KeyboardRow();
            row2.add(new KeyboardButton(Emojis.ABOUT_US + "About us"));
            row2.add(new KeyboardButton(Emojis.PROFILE + "Profile"));

            //row2
            KeyboardRow row3 = new KeyboardRow();
            row3.add(new KeyboardButton(Emojis.HELP + "Help"));
            row3.add(new KeyboardButton(Emojis.SUPPORT + "Support"));
           board.setKeyboard(List.of(row1, row2, row3));
       }
        //row3

        board.setResizeKeyboard(true);
        board.setSelective(true);
        return board;
    }

    public static ReplyKeyboardMarkup feedBackMenu(){
        //row1
        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton(Emojis.OFFER+"Offer"));
        row1.add(new KeyboardButton(Emojis.DISAPPROVAL+"Disapproval"));
        //row2
        KeyboardRow row2 = new KeyboardRow();
        row2.add(new KeyboardButton(Emojis.GO_BACK+"Back"));

        board.setKeyboard(List.of(row1, row2));
        board.setResizeKeyboard(true);
        board.setSelective(true);
        return board;
    }
    public static ReplyKeyboardMarkup back(){
        //row1
        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton(Emojis.GO_BACK+"Back"));
        board.setKeyboard(List.of(row1));
        board.setResizeKeyboard(true);
        board.setSelective(true);
        return board;
    }

}
