package training;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Class translating text from one language to another basing on given 
 * dictionary
 * 
 * @version 0.1 07.08.2019
 * @author Oleg
 */
public class Translator {
	private static final String WORD_INPUT_MESSAGE = "Enter a word: ";
	private static final String TRANSLATION_INPUT_MESSAGE = 
			"Enter translation: ";
	private static final String UNKNOWN_WORD = "невідоме_слово";
	private static final String SAVE_FORMAT = "%s,%s";

	Map<String, String> dictionary;

	/**
	 * Default constructor
	 */
	public Translator() {
		super();
	}

	/**
	 * Constructor accepting dictionary
	 * 
	 * @param dictionary <code>Map&lt;String, String&gt;</code>
	 */
	public Translator(Map<String, String> dictionary) {
		super();
		this.dictionary = dictionary;
	}
	
	/**
	 * Constructor loading dictionary from a given file  
	 * 
	 * @param dictionaryPath <code>String</code>
	 */
	public Translator(String dictionaryPath) {
		try {
			dictionary = Files.lines(Paths.get(dictionaryPath), 
						Charset.forName("Cp1251"))
					.map(n -> n.split("[,]"))
					.collect(Collectors.toMap(n -> n[0], n -> n[1], 
							(n1, n2) -> n1, TreeMap::new));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets dictionary
	 * 
	 * @return <code>Map&lt;String, String&gt;</code>
	 */
	public Map<String, String> getDictionary() {
		return dictionary;
	}

	/**
	 * Sets dictionary
	 * 
	 * @param dictionary <code>Map&lt;String, String&gt;</code>
	 */
	public void setDictionary(Map<String, String> dictionary) {
		this.dictionary = dictionary;
	}

	/**
	 * Text representation of translator
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("Translator dictionary:");		
		
		dictionary.forEach((key, value) -> builder.append(
				System.lineSeparator() + key + " " + value));
		
		return builder.toString();
	}

	/**
	 * Translates text from file and saves results to another file
	 * 
	 * @param sourcePath <code>String</code>
	 * @param targetPath <code>String</code>
	 * @throws IOException
	 */
	public void translate(String sourcePath, String targetPath) 
			throws IOException {
		
		List<String> in = getSource(sourcePath);
		List<String> out = new ArrayList<>();

		in.forEach(n -> out.add(dictionary.getOrDefault(n, UNKNOWN_WORD)));

		writeToTarget(out, targetPath);
	}

	/**
	 * Loads source file content
	 * 
	 * @param sourcePath <code>String</code>
	 * @return <code>List&lt;String&gt;</code>
	 * @throws IOException
	 */
	private List<String> getSource(String sourcePath) throws IOException {
		return Files.lines(Paths.get(sourcePath))
				.flatMap(n -> Arrays.stream(n.split("[ ]")))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	/**
	 * Saves translation to file
	 * 
	 * @param out <code>List&lt;String&gt;</code>
	 * @param targetPath <code>String</code>
	 * @throws FileNotFoundException
	 */
	private void writeToTarget(List<String> out, String targetPath) 
			throws FileNotFoundException {
		
		try (PrintWriter writer = new PrintWriter(targetPath)) {
			out.forEach(n -> writer.print(n + " "));
		}
	}

	/**
	 * Adds word to dictionary from console
	 */
	public void addWord() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		System.out.print(WORD_INPUT_MESSAGE);
		String key = scanner.nextLine();

		System.out.print(TRANSLATION_INPUT_MESSAGE);
		String value = scanner.nextLine();

		dictionary.put(key, value);
	}

	/**
	 * Saves dictionary to file
	 * 
	 * @param path <code>String</code>
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public void saveDictionary(String path) throws FileNotFoundException, 
		UnsupportedEncodingException {
		
		try (PrintWriter writer = new PrintWriter(path)) {
			dictionary.forEach((key, value) -> writer.println(
					String.format(SAVE_FORMAT, key,value))); 
		}
	}
}