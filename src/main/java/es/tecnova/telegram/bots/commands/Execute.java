package es.tecnova.telegram.bots.commands;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;

import es.tecnova.telegram.Util;

public class Execute extends Command {
	private static final String SUCCESS = "Éxito en la redirección.";
	private static final String FAIL = "No se pudo realidar la redirección.";
	
	public Execute(Command c) {
		super(c);
	}

	@Override
	public void run(Message message, SendMessage replyMessage) {
		if(Util.execute(getFile())){
			replyMessage.setText(SUCCESS);
		}else{
			replyMessage.setText(FAIL);
		}
	}


}
