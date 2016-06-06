package es.tecnova;


import java.io.IOException;
import java.io.InputStream;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;

import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.logging.BotLogger;
import org.telegram.telegrambots.logging.BotsFileHandler;
import org.yaml.snakeyaml.Yaml;

import es.tecnova.bots.BotRedir;


//import co.vandenham.telegram.botapi.MessageHandler;
//import co.vandenham.telegram.botapi.TelegramBot;
//import co.vandenham.telegram.botapi.types.Message;

public class BotAhorro{
	
	private static Configuration config = null;
    private static final String LOGTAG = "MAIN";

    
    public BotAhorro() { }



    public static void main(String[] args) {
        BotLogger.setLevel(Level.ALL);
        BotLogger.registerLogger(new ConsoleHandler());
        try {
            BotLogger.registerLogger(new BotsFileHandler());
        } catch (IOException e) {
            BotLogger.severe(LOGTAG, e);
        }
        
        
    	try{
    		leerYaml();
    	} catch (Exception e) {
    		BotLogger.severe(LOGTAG, e);
    		e.printStackTrace();
    	}
    	
    	
        try {
            TelegramBotsApi telegramBotsApi = createTelegramBotsApi();
            try {
                // Register long polling bots. They work regardless type of TelegramBotsApi we are creating
                telegramBotsApi.registerBot(new BotRedir(config));
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
    
    private static void leerYaml() {
    	 try {
			InputStream input = BotAhorro.class.getResourceAsStream("/config.yaml");
			//InputStream input = new FileInputStream(new File("src/main/resources/config.yaml"));
			 Yaml yaml = new Yaml();
			 config = yaml.loadAs( input, Configuration.class );
			 System.out.println(  config.toString() );
			 /*int counter = 0;
			 for (Object data : yaml.loadAll(input)) {
			     System.out.println(data);
			     counter++;
			 }
			 System.out.println(counter);*/
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
