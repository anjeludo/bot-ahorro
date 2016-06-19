package es.tecnova.telegram.bots.commands;

import java.util.List;

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
	public void run(final Message message, SendMessage replyMessage, List<SendMessage> otherMessages) {
		replyMessage.setText(getSuccessMsg(null));
		BotLogger.info(LOGTAG, "Command start used by " + message.getFrom() + " with chatid: " + message.getChatId());
		getConf().getUsers().get(message.getFrom().getUserName())
				.setChatId(Long.toString(message.getChatId()));
		ConfigurationHandler.get().save();
	}

}
