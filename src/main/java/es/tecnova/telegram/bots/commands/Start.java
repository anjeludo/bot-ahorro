package es.tecnova.telegram.bots.commands;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.logging.BotLogger;

import es.tecnova.telegram.ConfigurationHandler;

public class Start extends Command {
	private static final String LOGTAG = "Start";
	
	public Start(Command c) {
		super(c);
	}
	
	@Override
	public void run(Message message, SendMessage replyMessage) {
		replyMessage.setText("Who you gonna call?\nUse /help and learn my young padawan.");
		BotLogger.info(LOGTAG, "Command start used by "+message.getFrom()+" with chatid: "+message.getChatId());
		ConfigurationHandler.get().getConf().getUsers().get(message.getFrom().getUserName()).setChatId(Long.toString(message.getChatId()));
		ConfigurationHandler.get().save();
	}

}
