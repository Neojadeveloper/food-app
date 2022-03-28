package com.example.pdp_meal.telegram.telegramService;

import com.example.pdp_meal.dto.auth.AuthUserCreateDto;
import com.example.pdp_meal.enums.State;
import com.example.pdp_meal.telegram.BotProcess;
import com.example.pdp_meal.telegram.buttons.InlineBoards;
import com.example.pdp_meal.telegram.buttons.MarkupBoards;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;

import java.util.Objects;


@Service
@RequiredArgsConstructor
public class RegisterService {

    private final BotProcess BOT;
    private final PasswordEncoder encoder;


    public void register(Message message) {
        String chatID = message.getChatId().toString();
        String state = BOT.userState.get(chatID);
        if (Objects.isNull(BOT.userHashMap.get(chatID))) {
            BOT.userHashMap.put(chatID, new AuthUserCreateDto());
        }


        if (Objects.isNull(state)) {
            SendMessage sendMessage = new SendMessage(chatID, "Enter your full name please");
            sendMessage.setReplyMarkup(new ForceReplyKeyboard());
            BOT.executeMessage(sendMessage);
            BOT.userState.put(chatID, State.USER_NAME.getName());

        } else if (State.USER_NAME.getName().equals(state)) {
            String fullName = message.getText();

            AuthUserCreateDto userCreateDto = BOT.userHashMap.get(chatID);
            userCreateDto.setFullName(fullName);
            BOT.userHashMap.put(chatID, userCreateDto);

            SendMessage sendMessage = new SendMessage(chatID, "Enter your username please");
            sendMessage.setReplyMarkup(new ForceReplyKeyboard());
            BOT.executeMessage(sendMessage);
            BOT.userState.put(chatID, State.PASSWORD.getName());
        } else if (State.PASSWORD.getName().equals(state)) {
            String username = message.getText();

            AuthUserCreateDto userCreateDto = BOT.userHashMap.get(chatID);
            userCreateDto.setUsername(username);
            BOT.userHashMap.put(chatID, userCreateDto);

            SendMessage sendMessage = new SendMessage(chatID, "Enter your password please");
            Integer id = sendMessage.getReplyToMessageId();
            sendMessage.setReplyMarkup(new ForceReplyKeyboard());
            BOT.executeMessage(sendMessage);
            BOT.userState.put(chatID, State.PHONE_NUMBER.getName());
        } else if (State.PHONE_NUMBER.getName().equals(state)) {
            String password = message.getText();
            AuthUserCreateDto userCreateDto = BOT.userHashMap.get(chatID);
            userCreateDto.setPassword(encoder.encode(password));
            BOT.userHashMap.put(chatID, userCreateDto);
            SendMessage delete = new SendMessage(chatID, "Your password has been accepted");
            BOT.executeMessage(delete);
            DeleteMessage deleteMessage = new DeleteMessage(chatID, message.getMessageId());
            BOT.executeMessage(deleteMessage);
            SendMessage phoneMessage = new SendMessage();
            phoneMessage.setReplyMarkup(MarkupBoards.sharePhoneNumber());
            phoneMessage.setText("Share your phone Number");
            phoneMessage.setChatId(chatID);
            BOT.executeMessage(phoneMessage);
            BOT.userState.put(chatID, State.DEPARTMENT.getName());
        } else if (BOT.userState.get(chatID).equals(State.DEPARTMENT.getName())) {
            if (message.hasContact()) {
                Contact contact = message.getContact();
                AuthUserCreateDto userCreateDto = BOT.userHashMap.get(chatID);
                userCreateDto.setPhone(contact.getPhoneNumber());
                userCreateDto.setChatId(chatID);
                BOT.userHashMap.put(chatID, userCreateDto);

                SendMessage message1 = new SendMessage(chatID, "Choose your department");
                message1.setReplyMarkup(InlineBoards.department());
                BOT.executeMessage(message1);
                BOT.userState.put(chatID, State.POSITION.getName());
            }
        } else if (BOT.userState.get(chatID).equals(State.POSITION.getName())) {
            BOT.userState.put(chatID, State.REGISTERED.getName());
        }
    }
}

