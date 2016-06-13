package es.tecnova.telegram.bots.commands;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;

public class Echo extends Command {

	public Echo(Command c) {
		super(c);
	}

	@Override
	public void run(Message message, SendMessage replyMessage) {
		System.out.println(" yeah"+ getType());
		replyMessage.setReplayToMessageId(message.getMessageId());
		replyMessage.setText("Echo");
	}


}