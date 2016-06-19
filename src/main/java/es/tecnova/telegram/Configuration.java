package es.tecnova.telegram;

import static java.lang.String.format;

import java.io.Serializable;
import java.util.Map;

import es.tecnova.telegram.bots.commands.Command;

public class Configuration implements Serializable {
	private static final long serialVersionUID = 1L;
	private String token;
	private String botRedirName;
	private Map<String, User> users;
	private Map<String, Command> commands;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Map<String, User> getUsers() {
		return users;
	}

	public void setUsers(Map<String, User> users) {
		this.users = users;
	}

	public Map<String, Command> getCommands() {
		return commands;
	}

	public void setCommands(Map<String, Command> commands) {
		this.commands = commands;
	}

	public User findUser(String username) {
		return users.get(username);
	}

	public boolean isAuth(String username) {
		return users.containsKey(username);
	}

	public Command getCommand(String commandName) {
		if (commandName != null && commandName.length() > 0 && commandName.startsWith("/")) {
			return commands.get(commandName.substring(1));
		}
		return null;
	}

	@Override
	public String toString() {
		return new StringBuilder().append(format("token: %s\n", token)).append(format("usuarios: %s\n", users))
				.append(format("comandos: %s\n", commands)).toString();
	}

	public String getBotRedirName() {
		return botRedirName;
	}

	public void setBotRedirName(String botRedirName) {
		this.botRedirName = botRedirName;
	}
}
