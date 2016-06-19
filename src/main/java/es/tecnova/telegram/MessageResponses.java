package es.tecnova.telegram;

import java.util.Map;

public class MessageResponses {
	private Map<String, String> messages;
	public static String ERROR_MESSAGE="error";
	public static String SUCCESS_MESSAGE="success";
	public static String NOTIFICATION_MESSAGE="notification";
	
	public MessageResponses(){
	}

	public Map<String, String> getMessages() {
		return messages;
	}

	public void setMessages(Map<String, String> messages) {
		this.messages = messages;
	}
}
