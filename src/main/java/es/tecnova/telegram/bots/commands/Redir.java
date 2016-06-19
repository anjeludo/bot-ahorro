package es.tecnova.telegram.bots.commands;

import java.util.List;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;

import es.tecnova.telegram.ConfigurationHandler;
import es.tecnova.telegram.User;

public class Redir extends Execute {
	public Redir(Command c) {
		super(c);
	}

	@Override
	public void run(Message message, SendMessage replyMessage, List<SendMessage> othersMsg) {
		super.run(message, replyMessage, othersMsg);
		String from = message.getFrom().getUserName();
		if (getTo() != null && !getTo().isEmpty() && !getTo().equals(from)) {
			User u = ConfigurationHandler.get().getConf().getUsers().get(getTo());
			if (u != null && u.getChatId() != null && !u.getChatId().isEmpty()) {
				SendMessage redirNotification = new SendMessage();
				redirNotification.setChatId(u.getChatId());
				redirNotification.setText(getNotificationMsg(from));
				othersMsg.add(redirNotification);
			}
		}
		replyMessage.setText(getSuccessMsg());
	}
}
