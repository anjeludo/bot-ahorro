package es.tecnova.telegram.bots.commands;

import java.io.IOException;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.logging.BotLogger;

import es.tecnova.telegram.ConfigurationHandler;

public class ReloadConf extends Command {
	private static final String LOGTAG = "ReloadConf";
	private static final String REPPLY_MSJ = "Reloaded";
	public ReloadConf(Command c) {
		super(c);
	}
	
	@Override
	public void run(Message message, SendMessage replyMessage) {
		ConfigurationHandler.get().reload();
		replyMessage.setText(REPPLY_MSJ);
	}
}
