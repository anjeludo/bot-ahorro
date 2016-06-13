package es.tecnova.telegram.bots.commands;

import java.io.Serializable;
import java.util.List;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;

public class Command implements Serializable{
	private static final long serialVersionUID = 1L;
	private String type;
	private String file;
	private String to;
	private List<String> alias;// = new LinkedList<String>();

	
	public Command(){}
	
	public Command(Command c){
		type=c.getType();
		file=c.getFile();
		to=c.getTo();
		alias=c.getAlias();
	}
	
	public void run(Message message, SendMessage replyMessage){ 
		System.out.println("Not implemented yet - " + getType());
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

	public void setCommand(Command c){
		setType(c.getType());
	}
}
