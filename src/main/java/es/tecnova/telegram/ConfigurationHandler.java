package es.tecnova.telegram;

public class ConfigurationHandler {
	private static final String LOGTAG = "CONFIGURATION_HANDLER";
	private static final String CONFIG_FILE = "/config.yaml";
	private static final String MESSAGE_RESPONSES_FILE = "/messages.yaml";
	private static ConfigurationHandler instance;
	private Configuration conf;
	private MessageResponses messages;

	private ConfigurationHandler(Configuration conf, MessageResponses messages) {
		this.conf = conf;
		this.messages = messages;
	}

	public static ConfigurationHandler get() {
		if (instance == null) {
			instance = new ConfigurationHandler(Util.readYamlConf(CONFIG_FILE),
					Util.readYamlMsg(MESSAGE_RESPONSES_FILE));
		}
		return instance;
	}

	public Configuration getConf() {
		return conf;
	}

	public synchronized void save() {
		Util.writeYamlConf(CONFIG_FILE, getConf());
	}

	public synchronized void reload() {
		instance = null;
		ConfigurationHandler.get().getConf();
	}

	private MessageResponses getMessageResponses() {
		return messages;
	}
	
	public String getMessage(String key, String subkey){
		String completeKey=key+"_"+subkey;
		
		return getMessageResponses().getMessages().get(completeKey);		
	}

}
