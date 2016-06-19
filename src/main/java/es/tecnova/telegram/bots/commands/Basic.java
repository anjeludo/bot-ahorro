package es.tecnova.telegram.bots.commands;

import java.util.List;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.logging.BotLogger;

import es.tecnova.telegram.ConfigurationHandler;

public class Basic extends Command {

	public Basic() {
		// TODO Auto-generated constructor stub
	}

	public Basic(Command c) {
		super(c);
	}
	
	@Override
	public void run(final Message message, SendMessage replyMessage, List<SendMessage> otherMessages) {
		replyMessage.setText("Not today, tomorrow");
	}

}
