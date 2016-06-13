package es.tecnova.telegram;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.telegram.telegrambots.logging.BotLogger;
import org.yaml.snakeyaml.Yaml;

public class Util {
	private static final String LOGTAG = "UTIL";
	/**
	 * Funcion que elimina acentos y caracteres especiales de una cadena de
	 * texto.
	 * 
	 * @param input
	 * @return cadena de texto limpia de acentos y caracteres especiales.
	 */
	private static String quitarTildes(String input) {
		if (input == null)
			return null;
		// Cadena de caracteres original a sustituir.
		String original =	"áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
		// Cadena de caracteres ASCII que reemplazarÃ¡n los originales.
		String ascii = 		"aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
		String output = input;
		for (int i = 0; i < original.length(); i++) {
			// Reemplazamos los caracteres especiales.
			output = output.replace(original.charAt(i), ascii.charAt(i));
		}
		return output;
	}

	public static String normalizarTexto(String texto) {
		return (texto == null ? null : quitarTildes(texto).toLowerCase());
	}

	public static Configuration readYamlConf(String filePath) {
		Configuration config = null;
		try {
			InputStream input = BotAhorro.class.getResourceAsStream(filePath);
			Yaml yaml = new Yaml();
			config = yaml.loadAs(input, Configuration.class);
			System.out.println("conf file content(reading): " + config.toString());
		} catch (Exception e) {
			BotLogger.error(LOGTAG, e);
			e.printStackTrace();
		}
		return config;
	}

	public static boolean writeYamlConf(String filePath, Configuration config) {
		boolean result=false;

		try {
			Yaml yaml = new Yaml();
			FileWriter writer = new FileWriter(filePath);
			yaml.dump(config, writer);
			System.out.println("conf file content(writing): " + config.toString());
			result=true;
		} catch (Exception e) {
			BotLogger.error(LOGTAG, e);
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	
	 /**
	 * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
	 *
	 * @param packageName The base package
	 * @return The classes
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private static Class[] getClasses(String packageName) throws ClassNotFoundException, IOException {
	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	    assert classLoader != null;
	    String path = packageName.replace('.', '/');
	    Enumeration<URL> resources = classLoader.getResources(path);
	    List<File> dirs = new ArrayList<File>();
	    while (resources.hasMoreElements()) {
	        URL resource = resources.nextElement();
	        dirs.add(new File(resource.getFile()));
	    }
	    ArrayList<Class> classes = new ArrayList<Class>();
	    for (File directory : dirs) {
	        classes.addAll(findClasses(directory, packageName));
	    }
	    return classes.toArray(new Class[classes.size()]);
	}
	
	

	/**
	 * Recursive method used to find all classes in a given directory and subdirs.
	 *
	 * @param directory   The base directory
	 * @param packageName The package name for classes found inside the base directory
	 * @return The classes
	 * @throws ClassNotFoundException
	 */
	private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
	    List<Class> classes = new ArrayList<Class>();
	    if (!directory.exists()) {
	        return classes;
	    }
	    File[] files = directory.listFiles();
	    for (File file : files) {
	        if (file.isDirectory()) {
	            assert !file.getName().contains(".");
	            classes.addAll(findClasses(file, packageName + "." + file.getName()));
	        } else if (file.getName().endsWith(".class")) {
	            classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
	        }
	    }
	    return classes;
	}
}
