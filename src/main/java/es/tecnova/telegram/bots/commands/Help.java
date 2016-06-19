package es.tecnova.telegram.bots.commands;

import java.util.List;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;

public class Help extends Command {

	public Help(Command c) {
		super(c);
	}

	 @Override
	 public void run(final Message message, SendMessage replyMessage, List<SendMessage> otherMessages) {
		 replyMessage.setText(getSuccessMsg());
	 }
}
