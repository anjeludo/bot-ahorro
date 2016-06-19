package es.tecnova.telegram.bots.commands;

import java.util.List;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;

import es.tecnova.telegram.Configuration;
import es.tecnova.telegram.ConfigurationHandler;
import es.tecnova.telegram.MessageResponses;

public class Command {
	private String type;
	private String file;
	private String to;
	private boolean button;
	private List<String> alias;

	public Command() {
		button = true;
	}

	public Command(Command c) {
		type = c.getType();
		file = c.getFile();
		to = c.getTo();
		button = c.isButton();
		alias = c.getAlias();
	}

	public void run(final Message message, SendMessage replyMessage, List<SendMessage> othersMsg) {
		replyMessage.setText("Not implemented yet - " + getType());
	};

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public List<String> getAlias() {
		return alias;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public void setAlias(List<String> alias) {
		this.alias = alias;
	}

	public void setCommand(Command c) {
		setType(c.getType());
	}

	public boolean isButton() {
		return button;
	}

	public void setButton(boolean button) {
		this.button = button;
	}
	
	public Configuration getConf(){
		return ConfigurationHandler.get().getConf();
	}
	
	public String buildMessage(String msg, String... args){
		int i=0;
		if(args!=null && args.length>0){
			for (String arg : args) {
				msg=msg.replace("{"+i+"}", arg);
				i++;
			}
		}
		return msg;
	}
	
	public String getErrorMsg(String... args){
		String msg=ConfigurationHandler.get().getMessage(getType(), MessageResponses.ERROR_MESSAGE);
		return buildMessage(msg,args);
	}

	public String getSuccessMsg(String... args){
		String msg=ConfigurationHandler.get().getMessage(getType(), MessageResponses.SUCCESS_MESSAGE);
		return buildMessage(msg,args);
	}
	
	public String getNotificationMsg(String... args){
		String msg=ConfigurationHandler.get().getMessage(getType(), MessageResponses.NOTIFICATION_MESSAGE);
		return buildMessage(msg,args);
	}
}
