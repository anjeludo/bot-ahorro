package es.tecnova.bots;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.logging.BotLogger;

import es.tecnova.Ahorrador;
import es.tecnova.Configuration;

public class BotRedir extends TelegramLongPollingBot {
	private static final String LOGTAG = "BotRedir";

    private static final int STARTSTATE = 0;
    private static final int MAINMENU = 1;
    
    
    Configuration config;
    
	public BotRedir(Configuration config) {
		super();
		this.config=config;
	}

	@Override
	public String getBotUsername() {
		return "tecnovaAhorroBot";
	}

	@Override
	public void onUpdateReceived(Update update) {
		try {
			if (update.hasMessage()) {
				SendMessage replyMessage = new SendMessage();
	            Message message = update.getMessage();
	            replyMessage.enableMarkdown(true);
	            replyMessage.setChatId(message.getChatId().toString());
	            replyMessage.setReplayMarkup(getKeyboard(null));
	            if (message.hasText()){
	            	handleTextMessage(message,replyMessage);
	            }else{
	            	handleOtherMessage(message,replyMessage);
	            }
	            if(replyMessage.getText()!=null){
	            	sendMessage(replyMessage);	
	            }
			}
		} catch (TelegramApiException e) {
			BotLogger.error(LOGTAG, e);
			e.printStackTrace();
		}

	}
	
	 private void handleOtherMessage(Message message, SendMessage replyMessage) throws TelegramApiException {
		 //sendMessage(messageOnMainMenu(message));
		 BotLogger.info(LOGTAG, message.toString());
	 }
		
    private ReplyKeyboardMarkup getKeyboard(String language) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(false);
        //replyKeyboardMarkup.setOneTimeKeyboad(true);

        List<KeyboardRow> keyboard = new ArrayList<>();
        
        Map<String, Ahorrador> ahorradores=config.getAhorradores();
        
        for(String key:ahorradores.keySet()) {
        	KeyboardRow row = new KeyboardRow();
        	row.add("/redir "+ahorradores.get(key).getAliasTelegram());
        	keyboard.add(row);
        }

        replyKeyboardMarkup.setKeyboard(keyboard);

        return replyKeyboardMarkup;
    }

	@Override
	public String getBotToken() {
		return config.getToken();
	}

	
	
	
	
    // This handler gets called whenever a user sends /start or /help
    /*@CommandHandler({"start", "help"})
    public void handleHelp(Message message) {
    	
        replyTo(message, "Hi there! I am here to echo all your kind words back to you!");
    }*/

    public void handleTextMessage(Message message, SendMessage replyMessage) {
        System.out.println(String.format("%s: %s", message.getChat().getId(), message.getText()));
        String respuesta = "";
        String username = message.getFrom().getUserName();
        System.out.println(message.getFrom().getUserName());
        if(config.isAuth(username)) {
	        if(message.getText().toLowerCase().contains("/redir")) {
	        	String [] msg = message.getText().toLowerCase().split("/redir ");
	        	String ahorrador = msg[1];
	        	 if( config.isAhorrador( ahorrador ) ) {	        		
	        		 respuesta = "Redireccionado a " + ahorrador;
	        		 try {
	        			 String lnk = config.findAhorrador(ahorrador).getLink();
	        			 ProcessBuilder pb = 
		        				 new ProcessBuilder("cmd", "/c", lnk);
						Process process = pb.start();
	        		 } catch (Exception e) {
	        			 e.printStackTrace();
						respuesta = "Ha ocurrido un error al intentar redireccionar";
					}
	        	 }
	        	 else {
	        		 respuesta = ahorrador + " no esta en la lista";
	        	 }	 
	        }
	        else {
	        	respuesta = "No entiendo lo qué quieres decir";
	        }
	        replyMessage.setText(respuesta);
        }
	    else {
	    	// No autorizado: sin respuesta
	    }
    }

    // This is the default handler, called when the other two handlers don't apply.
    /*@DefaultHandler
    public void handleDefault(Message message) {
        replyTo(message, "Say what?");
    }*/
	
	
	
	
	
	
	
}
