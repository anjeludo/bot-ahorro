package es.tecnova.telegram.bots.commands;

import java.util.List;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;

import es.tecnova.telegram.Util;

public class Execute extends Command {
	public Execute(Command c) {
		super(c);
	}

	@Override
	public void run(Message message, SendMessage replyMessage, List<SendMessage> othersMsg) {
		if (Util.execute(getFile())) {
			replyMessage.setText(getSuccessMsg());
		} else {
			replyMessage.setText(getErrorMsg());
		}
	}

}
