package es.tecnova;

import static java.lang.String.format;

import java.util.List;
import java.util.Map;

public class Configuration {
    private String token;
    private Map<String, Ahorrador> ahorradores;
    
 
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public boolean isAhorrador(String ahorrador)
	{
		boolean encontrado = false;
		for(String key:ahorradores.keySet()) {
			if(Util.normalizarTexto(key).equals(Util.normalizarTexto(ahorrador))) {
				encontrado = true;
				break;
			}
		}
		
		return encontrado;
	}
	
	public Ahorrador findAhorrador(String ahorrador) {
		Ahorrador ahorradorBuscado = null;
		for(String key:ahorradores.keySet()) {
			if(Util.normalizarTexto(key).equals(Util.normalizarTexto(ahorrador))) {
				ahorradorBuscado = ahorradores.get(key);
				break;
			}
		}
		
		return ahorradorBuscado;
	}
	
	public boolean isAuth(String username) {
		boolean auth = false;
		for(String key:ahorradores.keySet()) {
			if(ahorradores.get(key).getAliasTelegram()!=null && ahorradores.get(key).getAliasTelegram().equals(username)) {
				auth = true;
				break;
			}
		}
		
		return auth;
	}
	
	@Override
    public String toString() {
        return new StringBuilder()
            .append( format( "token: %s\n", token ) )
            .append( format( "ahorradores: %s\n", ahorradores ) ) 
            .toString();
    }
	
	public Map<String, Ahorrador> getAhorradores() {
		return ahorradores;
	}
	public void setAhorradores(Map<String, Ahorrador> ahorradores) {
		this.ahorradores = ahorradores;
	}

	
}
