package es.tecnova.telegram;


import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;

import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.logging.BotLogger;
import org.telegram.telegrambots.logging.BotsFileHandler;

import es.tecnova.telegram.bots.BotRedir;



public class BotAhorro{
    private static final String LOGTAG = "MAIN";

    public BotAhorro() {}

    public static void main(String[] args) {
        BotLogger.setLevel(Level.ALL);
        BotLogger.registerLogger(new ConsoleHandler());
        try {
            BotLogger.registerLogger(new BotsFileHandler());
        } catch (IOException e) {
            BotLogger.severe(LOGTAG, e);
        }
    	
        try {
            TelegramBotsApi telegramBotsApi = createTelegramBotsApi();
            try {
                // Register long polling bots. They work regardless type of TelegramBotsApi we are creating
                telegramBotsApi.registerBot(new BotRedir());
            } catch (TelegramApiException e) {
                BotLogger.error(LOGTAG, e);
            }
        } catch (Exception e) {
            BotLogger.error(LOGTAG, e);
            e.printStackTrace();
        }
    }
    
    private static TelegramBotsApi createTelegramBotsApi() throws TelegramApiException {
    	TelegramBotsApi telegramBotsApi= createLongPollingTelegramBotsApi();
      	return telegramBotsApi;
    }
    
    private static TelegramBotsApi createLongPollingTelegramBotsApi() {
        return new TelegramBotsApi();
    }    
}