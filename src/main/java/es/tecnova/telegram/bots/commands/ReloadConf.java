package es.tecnova.telegram.bots.commands;

import java.util.List;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;

import es.tecnova.telegram.ConfigurationHandler;

public class ReloadConf extends Command {
	private static final String LOGTAG = "RELOAD_CONF";

	public ReloadConf(Command c) {
		super(c);
	}

	@Override
	public void run(Message message, SendMessage replyMessage, List<SendMessage> othersMsg) {
		ConfigurationHandler.get().reload();
		replyMessage.setText(getSuccessMsg());
	}
}
