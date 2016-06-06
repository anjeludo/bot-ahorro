package org.example;


import java.io.InputStream;

import org.yaml.snakeyaml.Yaml;


import co.vandenham.telegram.botapi.MessageHandler;
import co.vandenham.telegram.botapi.TelegramBot;
import co.vandenham.telegram.botapi.types.Message;

public class EchoBot extends TelegramBot {
	
	private static Configuration config = null;

    public EchoBot() {
    	super(config.getToken());
    }

    // This handler gets called whenever a user sends /start or /help
    /*@CommandHandler({"start", "help"})
    public void handleHelp(Message message) {
    	
        replyTo(message, "Hi there! I am here to echo all your kind words back to you!");
    }*/

    // This handler gets called whenever a user sends a general text message.
    @MessageHandler(contentTypes = Message.Type.TEXT)
    public void handleTextMessage(Message message) {
        System.out.println(String.format("%s: %s", message.getChat().getId(), message.getText()));
        String respuesta = "";
        String username = message.getFrom().getUsername();
        System.out.println(message.getFrom().getUsername());
        if(config.isAuth(username)) {
	        if(message.getText().toLowerCase().contains("redireccionar")) {
	        	String [] msg = message.getText().toLowerCase().split("redireccionar ");
	        	String ahorrador = msg[1];
	        	 if( config.isAhorrador( ahorrador ) ) {
	        		 //ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "C:\\temp\\file.lnk");
	        		
	        		 respuesta = "Redireccionado a " + ahorrador;
	        		 try {
	        			 String lnk = config.findAhorrador(ahorrador).getLink();
	        			 ProcessBuilder pb = 
		        				 new ProcessBuilder("cmd", "/c", lnk);
						Process process = pb.start();
	        		 } catch (Exception e) {
	        			 e.printStackTrace();
						respuesta = "Ha ocurrido un error al intentar redireccionar";
					}
	        	 }
	        	 else {
	        		 respuesta = ahorrador + " no esta en la lista";
	        	 }	 
	        }
	        else {
	        	respuesta = "No entiendo lo qué quieres decir";
	        }
	        replyTo(message, respuesta);
        }
	    else {
	    	// No autorizado: sin respuesta
	    }
    }

    // This is the default handler, called when the other two handlers don't apply.
    /*@DefaultHandler
    public void handleDefault(Message message) {
        replyTo(message, "Say what?");
    }*/

    public static void main(String[] args) {
    	try{
    		leerYaml();
    		TelegramBot bot = new EchoBot();
    		bot.start();
    	} catch (Exception e) {
    		System.out.println("Error: " + e);
    	}
    }
    
    private static void leerYaml() {
    	 try {
			InputStream input = EchoBot.class.getResourceAsStream("/config.yaml");
			//InputStream input = new FileInputStream(new File("src/main/resources/config.yaml"));
			 Yaml yaml = new Yaml();
			 config = yaml.loadAs( input, Configuration.class );
			 System.out.println(  config.toString() );
			 /*int counter = 0;
			 for (Object data : yaml.loadAll(input)) {
			     System.out.println(data);
			     counter++;
			 }
			 System.out.println(counter);*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
