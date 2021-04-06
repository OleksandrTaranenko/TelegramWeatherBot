import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

public class TelegramBot extends TelegramLongPollingBot {

    private static String TOKEN;
    private static String USERNAME;

    public TelegramBot() {
        Properties props = new Properties();
        File propFile = new File("src/main/resources/myapp.properties");

        try(Reader reader = new FileReader(propFile)) {
            props.load(reader);
            TOKEN = props.getProperty("token.telegram");
            USERNAME = props.getProperty("username.telegram");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            switch (message.getText()) {
                case "/description": {
                    sendMsg(message, "Enter the name of the city in any language and get the weather forecast");
                    break;
                }
                default:
                    try {
                        Model model = Weather.getWeather(message.getText());
                        sendMsg(message, model.getAllData());
                        sendPhoto(message, model.getIcon(), model.getMain());
                    } catch (IOException e) {
                        sendMsg(message, "Error in the name of the city");
                    }
            }


        }

    }

    public void sendPhoto(Message message, String URL, String caption) {
        SendPhoto sendPhotoRequest = new SendPhoto();
        sendPhotoRequest.setChatId(message.getChatId().toString());
        sendPhotoRequest.setPhoto(URL);
        sendPhotoRequest.setCaption(caption);
        try {
            execute(sendPhotoRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);
        sendMessage.setReplyToMessageId(message.getMessageId());
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return USERNAME;
    }

    public String getBotToken() {
        return TOKEN;
    }
}
