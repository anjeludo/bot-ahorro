package es.tecnova.telegram.bots;

import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.logging.BotLogger;

import es.tecnova.telegram.Configuration;
import es.tecnova.telegram.ConfigurationHandler;

public abstract class PrivateBot  extends TelegramLongPollingBot {
	private static final String LOGTAG = "PRIVATE_BOT";
	
	public PrivateBot() {}
	
	@Override
	public void onUpdateReceived(Update update) {
		Configuration conf=ConfigurationHandler.get().getConf();
		if (update.hasMessage()) {
			Message message = update.getMessage();
			String username = message.getFrom().getUserName();
			if(conf.isAuth(username)){
				onAuthUpdateReceived(update);
			}else{
				onUnAuthUpdateReceived(update);
		    }
		}
	}

	public Configuration getConf() {
		return ConfigurationHandler.get().getConf();
	}

	public void onUnAuthUpdateReceived(Update update){
		System.out.println("unauth msj");
		BotLogger.info(LOGTAG,"["+update.getMessage().getDate()+"]UnAuth msj("+update.getMessage().getText()+") reseived from "+update.getMessage().getFrom()+" with chatId: "+update.getMessage().getChatId());
	};
	
	
	public abstract void onAuthUpdateReceived(Update update);
}
