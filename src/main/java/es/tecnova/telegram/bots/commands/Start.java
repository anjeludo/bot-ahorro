package es.tecnova.telegram.bots.commands;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;

public class Start extends Command {

	public Start(Command c) {
		super(c);
	}
	
	@Override
	public void run(Message message, SendMessage replyMessage) {
		replyMessage.setText("Who you gonna call?\nUse /help and learn my young padawan.");
	}

}
