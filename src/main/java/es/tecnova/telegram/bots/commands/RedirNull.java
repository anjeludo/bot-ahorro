package es.tecnova.telegram.bots.commands;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;

public class RedirNull extends Execute {
	private static final String SUCCESS = "Redirección anulada con éxito.";
	private static final String FAIL = "No se pudo anular la redirección.";

	public RedirNull(Command c) {
		super(c);
	}

	
//	@Override
//	public void run(Message message, SendMessage replyMessage) {
//		
//		
//	}
}
