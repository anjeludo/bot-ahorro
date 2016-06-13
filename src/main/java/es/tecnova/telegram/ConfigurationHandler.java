package es.tecnova.telegram;

public class ConfigurationHandler {
    private static final String CONFIG_FILE = "/config.yaml";
	private static ConfigurationHandler instance;
	private Configuration conf;
	
	private ConfigurationHandler(Configuration conf) {
		this.conf=conf;
	}

	
    public static ConfigurationHandler get() {
    	if(instance==null){
    		instance=new ConfigurationHandler(Util.readYamlConf(CONFIG_FILE));
    	}
        return instance;
    }


	public Configuration getConf() {
		return conf;
	}
	
	public void save(){
		Util.writeYamlConf(CONFIG_FILE, getConf());
	}
}
