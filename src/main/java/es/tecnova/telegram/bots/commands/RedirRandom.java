package es.tecnova.telegram.bots.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;

import es.tecnova.telegram.ConfigurationHandler;
import es.tecnova.telegram.Util;

public class RedirRandom extends Command {

	public RedirRandom(Command c) {
		super(c);
	}

	@Override
	public void run(Message message, SendMessage replyMessage, List<SendMessage> othersMsg) {
		Map<String, Command> Conf = ConfigurationHandler.get().getConf().getCommands();
		
		ArrayList<Command> redirCommands = new ArrayList<Command>();

		for (Entry<String, Command> entry : Conf.entrySet()) {
			if (entry.getValue() != null && "Redir".equalsIgnoreCase(entry.getValue().getType())) {
				redirCommands.add(entry.getValue());
			}
		}

		int luckyIndex = Util.generateRandomInt(0, redirCommands.size() - 1);
		if (redirCommands.size() > 0) {
			Redir r = new Redir(redirCommands.get(luckyIndex));
			r.run(message, replyMessage, othersMsg);
			replyMessage.setText(getSuccessMsg(getFile()));
		}

	}
}
