package es.tecnova;

public class Util {
	
	/**
	 * Función que elimina acentos y caracteres especiales de
	 * una cadena de texto.
	 * @param input
	 * @return cadena de texto limpia de acentos y caracteres especiales.
	 */
	private static String quitarTildes(String input) {
		if(input == null)
			return null;
	    // Cadena de caracteres original a sustituir.
	    String original = "áàäéèëíìïóòöúùuñ�?ÀÄÉÈË�?Ì�?ÓÒÖÚÙÜÑçÇ";
	    // Cadena de caracteres ASCII que reemplazarán los originales.
	    String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcCXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
	    String output = input;
	    for (int i=0; i<original.length(); i++) {
	        // Reemplazamos los caracteres especiales.
	        output = output.replace(original.charAt(i), ascii.charAt(i));
	    }//for i
	    return output;
	}//reemplazarCaracteresRaros
	
	public static String normalizarTexto(String texto)
	{
		return (texto == null? null: quitarTildes(texto).toLowerCase());
	}

}
