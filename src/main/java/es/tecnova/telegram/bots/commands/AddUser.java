package es.tecnova.telegram.bots.commands;

import java.util.List;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.logging.BotLogger;

import es.tecnova.telegram.ConfigurationHandler;
import es.tecnova.telegram.User;
import es.tecnova.telegram.Util;

public class AddUser extends Command {
	private static final String LOGTAG = "AddUser";

	public AddUser(Command c) {
		super(c);
	}

	@Override
	public void run(Message message, SendMessage replyMessage, List<SendMessage> othersMsg) {
		BotLogger.info(LOGTAG, message.getText());
		if (Util.countWords(message.getText()) == 2) {
			String userName = Util.getLastWord(message.getText());
			User u = new User();
			u.setName(userName);
			ConfigurationHandler.get().getConf().getUsers().put(u.getName(), u);
			ConfigurationHandler.get().save();
			ConfigurationHandler.get().reload();
		}
	}

}
