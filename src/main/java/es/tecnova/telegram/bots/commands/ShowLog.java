package es.tecnova.telegram.bots.commands;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;

public class ShowLog extends Command {
	
	public ShowLog(Command c) {
		super(c);
	}

//	@Override
//	public void run(Message message, SendMessage replyMessage) {
//		replyMessage.setReplayToMessageId(message.getMessageId());
//		replyMessage.setText("Not implemented yet");
//	}
}