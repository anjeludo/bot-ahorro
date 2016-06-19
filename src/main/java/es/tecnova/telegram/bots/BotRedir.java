package es.tecnova.telegram.bots;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.logging.BotLogger;

import es.tecnova.telegram.bots.commands.Command;

public class BotRedir extends PrivateBot {
	private static final String LOGTAG = "BOT_REDIR";

	private static final String COMMAND_NOT_FOUND_MSJ = "Comando no encontrado. Use /ayuda para saber más.";

	public BotRedir() {
		super();
	}

	@Override
	public String getBotUsername() {
		return getConf().getBotRedirName();
	}

	@Override
	public String getBotToken() {
		return getConf().getToken();
	}

	@Override
	public void onAuthUpdateReceived(Update update) {
		try {
			SendMessage replyMessage = new SendMessage();
			Message message = update.getMessage();
			BotLogger.info(LOGTAG, message.toString());

			replyMessage.enableMarkdown(true);
			replyMessage.setChatId(message.getChatId().toString());
			replyMessage.setReplayMarkup(getKeyboard(null));

			List<SendMessage> otherMessages = new ArrayList<SendMessage>();

			if (message.hasText()) {
				handleTextMessage(message, replyMessage, otherMessages);
			} else {
				handleOtherMessage(message, replyMessage, otherMessages);
			}
			if (replyMessage.getText() != null) {
				sendMessage(replyMessage);
			}
			if (!otherMessages.isEmpty()) {
				for (SendMessage om : otherMessages) {
					sendMessage(om);
				}
			}
		} catch (TelegramApiException e) {
			BotLogger.error(LOGTAG, e);
			e.printStackTrace();
		}

	}

	private void handleOtherMessage(Message message, SendMessage replyMessage, List<SendMessage> otherMessages)
			throws TelegramApiException {
		replyMessage.setText(COMMAND_NOT_FOUND_MSJ);
	}

	private ReplyKeyboardMarkup getKeyboard(String language) {
		ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
		replyKeyboardMarkup.setSelective(true);
		replyKeyboardMarkup.setResizeKeyboard(false);

		List<KeyboardRow> keyboard = new ArrayList<>();

		List<String> commandNames = new ArrayList<String>(getConf().getCommands().keySet());

		for (String cn : commandNames) {
			if (getConf().getCommands().get(cn).isButton()) {
				KeyboardRow row = new KeyboardRow();
				row.add("/" + cn);
				keyboard.add(row);
			}
		}

		replyKeyboardMarkup.setKeyboard(keyboard);

		return replyKeyboardMarkup;
	}

	public void handleTextMessage(Message message, SendMessage replyMessage, List<SendMessage> otherMessages) {
		System.out.println(String.format("%s: %s", message.getChat().getId(), message.getText()));
		Command c = getConf().getCommand(message.getText());
		if (c != null) {
			try {
				Class<? extends Command> commandType = Class.forName("es.tecnova.telegram.bots.commands." + c.getType())
						.asSubclass(Command.class);
				Object cc = commandType.getDeclaredConstructor(Command.class).newInstance(c);
				commandType.cast(cc).run(message, replyMessage, otherMessages);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				BotLogger.info(LOGTAG, e);
			}
		} else {
			BotLogger.info(LOGTAG, "Command not found " + message.getText() + " from " + message.getFrom() + " at "
					+ message.getDate());
			replyMessage.setText("Command not found");
		}

	}
}
