package com.example.pdp_meal.telegram.buttons;

import com.example.pdp_meal.dto.dailyMenu.DailyMenuDto;
import com.example.pdp_meal.dto.meal.MealDto;
import com.example.pdp_meal.telegram.emojis.Emojis;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InlineBoards {

    private static final InlineKeyboardMarkup board = new InlineKeyboardMarkup();

    public static ReplyKeyboard department() {

        InlineKeyboardButton academic = new InlineKeyboardButton(Emojis.ACADEMIC + "Academic");
        academic.setCallbackData("academic");

        InlineKeyboardButton marketing = new InlineKeyboardButton(Emojis.MARKETING + "Marketing");
        marketing.setCallbackData("marketing");

        InlineKeyboardButton economic = new InlineKeyboardButton(Emojis.ECONOMIC + "Economic");
        economic.setCallbackData("economic");

        board.setKeyboard(Arrays.asList(getRow(academic), getRow(marketing), getRow(economic)));
        return board;
    }

    private static List<InlineKeyboardButton> getRow(InlineKeyboardButton... buttons) {
        return Arrays.stream(buttons).toList();
    }

    public static ReplyKeyboard position() {
        InlineKeyboardButton teacher = new InlineKeyboardButton(Emojis.ACADEMIC + "Teacher");
        teacher.setCallbackData("teacher");

        InlineKeyboardButton administrator = new InlineKeyboardButton(Emojis.MARKETING + "Administrator");
        administrator.setCallbackData("administrator");

        InlineKeyboardButton employee = new InlineKeyboardButton(Emojis.ECONOMIC + "Employee");
        employee.setCallbackData("employee");

        InlineKeyboardButton headDepartment = new InlineKeyboardButton(Emojis.ECONOMIC + "Head Department");
        headDepartment.setCallbackData("headDepartment");

        board.setKeyboard(Arrays.asList(getRow(teacher), getRow(administrator), getRow(employee), getRow(headDepartment)));
        return board;
    }

    public static ReplyKeyboard menu(List<DailyMenuDto> all) {
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        List<List<InlineKeyboardButton>> allButtons = new ArrayList<>();
        for (int i = 1; i <= all.size(); i++) {
            InlineKeyboardButton button = new InlineKeyboardButton(i + "");
            button.setCallbackData(i + "");
            buttons.add(button);

        }

        allButtons.add(buttons);

        board.setKeyboard(allButtons);
        return board;
    }

    public static ReplyKeyboard getAllMeals(List<MealDto> meals) {
        InlineKeyboardMarkup boards = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> allButtons = new ArrayList<>();
        int size = meals.size();
        for (int i = 0; i <= meals.size() / 10; i++) {
            List<InlineKeyboardButton> buttons = new ArrayList<>();
            if(size < 10){
                for (int j = i / 10; j < size; j++) {
                    InlineKeyboardButton button = new InlineKeyboardButton(String.valueOf(j + 1));
                    button.setCallbackData(String.valueOf(j + 1));
                    buttons.add(button);
                }
            } else {
                for (int j = i / 10; j < (i + 1) * 10; j++) {
                    InlineKeyboardButton button = new InlineKeyboardButton(String.valueOf(j + 1));
                    button.setCallbackData(String.valueOf(j + 1));
                    buttons.add(button);
                }
            }
            InlineKeyboardButton save = new InlineKeyboardButton( "Save");
            save.setCallbackData("save");
            buttons.add(save);
            size = size - 10;
            allButtons.add(buttons);
        }
        boards.setKeyboard(allButtons);
        return boards;
    }
}
