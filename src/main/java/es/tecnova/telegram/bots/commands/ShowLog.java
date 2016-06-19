package es.tecnova.telegram.bots.commands;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.input.ReversedLinesFileReader;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.logging.BotLogger;

public class ShowLog extends Command {
	private static final String LOGTAG = "ShowLog";
	private static final int N_LINES = 10;

	public ShowLog(Command c) {
		super(c);
	}

	@Override
	public void run(Message message, SendMessage replyMessage, List<SendMessage> othersMsg) {
		StringBuffer sb = new StringBuffer();
		String line = null;
		try {
			ReversedLinesFileReader fr = new ReversedLinesFileReader(new File(getFile()));
			int i = 0;
			while (((line = fr.readLine()) != null) && i < N_LINES) {
				sb.append(line).append("\n");
				i++;
			}
			fr.close();
			replyMessage.setText(sb.toString());
		} catch (IOException e) {
			replyMessage.setText("Error reading log file");
			BotLogger.error(LOGTAG, e);
		}
	}
}
