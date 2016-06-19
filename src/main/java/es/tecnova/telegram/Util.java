package es.tecnova.telegram;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ThreadLocalRandom;

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
		String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
		// Cadena de caracteres ASCII que reemplazarÃ¡n los originales.
		String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
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

	@SuppressWarnings("unchecked")
	public static Object readYaml(String filePath, Class objectClass) {
		Object o = null;
		try {
			InputStream input = BotAhorro.class.getResourceAsStream(filePath);
			Yaml yaml = new Yaml();
			o = yaml.loadAs(input, objectClass);
		} catch (Exception e) {
			BotLogger.error(LOGTAG, e);
			e.printStackTrace();
		}
		return o;
	}
	
	public static MessageResponses readYamlMsg(String filePath) {
		return (MessageResponses) Util.readYaml(filePath, MessageResponses.class);
	}
	
	public static Configuration readYamlConf(String filePath) {
		return (Configuration) Util.readYaml(filePath, Configuration.class);
	}

	public static boolean writeYamlConf(String filePath, Configuration config) {
		boolean result = false;

		try {
			Yaml yaml = new Yaml();
			File file = new File(filePath);
			FileWriter writer = new FileWriter(file);
			yaml.dump(config, writer);
			writer.flush();
			writer.close();

			System.out.println("conf file content(writing): " + config.toString()+"  "+file.getAbsolutePath());
			result = true;
		} catch (Exception e) {
			BotLogger.error(LOGTAG, e);
		}
		return result;
	}

	// inclusive
	public static int generateRandomInt(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}

	public static boolean execute(String file) {
		boolean result = false;
		try {
			ProcessBuilder pb = new ProcessBuilder("cmd", "/c", file);
			Process process = pb.start();
			result = true;
		} catch (IOException e) {
			BotLogger.error(LOGTAG, e);
		}
		return result;
	}

	public static String getLastWord(String sentence) {
		return sentence.substring(sentence.lastIndexOf(" ") + 1);
	}

	public static int countWords(String sentence) {
		String trim = sentence.trim();
		if (trim.isEmpty())
			return 0;
		return trim.split("\\s+").length;
	}

	// /**
	// * Scans all classes accessible from the context class loader which belong
	// to the given package and subpackages.
	// *
	// * @param packageName The base package
	// * @return The classes
	// * @throws ClassNotFoundException
	// * @throws IOException
	// */
	// @SuppressWarnings({ "rawtypes", "unused" })
	// private static Class[] getClasses(String packageName) throws
	// ClassNotFoundException, IOException {
	// ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	// assert classLoader != null;
	// String path = packageName.replace('.', '/');
	// Enumeration<URL> resources = classLoader.getResources(path);
	// List<File> dirs = new ArrayList<File>();
	// while (resources.hasMoreElements()) {
	// URL resource = resources.nextElement();
	// dirs.add(new File(resource.getFile()));
	// }
	// ArrayList<Class> classes = new ArrayList<Class>();
	// for (File directory : dirs) {
	// classes.addAll(findClasses(directory, packageName));
	// }
	// return classes.toArray(new Class[classes.size()]);
	// }
	//
	//
	//
	// /**
	// * Recursive method used to find all classes in a given directory and
	// subdirs.
	// *
	// * @param directory The base directory
	// * @param packageName The package name for classes found inside the base
	// directory
	// * @return The classes
	// * @throws ClassNotFoundException
	// */
	// @SuppressWarnings("rawtypes")
	// private static List<Class> findClasses(File directory, String
	// packageName) throws ClassNotFoundException {
	// List<Class> classes = new ArrayList<Class>();
	// if (!directory.exists()) {
	// return classes;
	// }
	// File[] files = directory.listFiles();
	// for (File file : files) {
	// if (file.isDirectory()) {
	// assert !file.getName().contains(".");
	// classes.addAll(findClasses(file, packageName + "." + file.getName()));
	// } else if (file.getName().endsWith(".class")) {
	// classes.add(Class.forName(packageName + '.' + file.getName().substring(0,
	// file.getName().length() - 6)));
	// }
	// }
	// return classes;
	// }
}
