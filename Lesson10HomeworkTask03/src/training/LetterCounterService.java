package training;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Service class providing methods to calculate amount of each letter 
 * in a text file
 * 
 * @version 0.1 08.08.2019
 * @author Oleg
 */
public class LetterCounterService {
	private static final Comparator<Map.Entry<Character, Integer>> 
		defaultComparator = (a, b) -> b.getValue() - a.getValue();
	
	/**
	 * Private constructor to make instance creation impossible 
	 */
	private LetterCounterService() {
		super();
	}
	
	/**
	 * Calculates letters using default comparator
	 * 
	 * @param filePath <code>String</code>
	 * @return <code>Map&lt;Character, Integer&gt;</code>
	 * @throws IOException
	 */
	public static Map<Character, Integer> calculateLetters(String filePath) 
			throws IOException {
		
		return calculateLetters(filePath, defaultComparator);
	}
	
	/**
	 * Calculates letters using given comparator
	 * 
	 * @param filePath filePath <code>String</code>
	 * @param comparator <code>Comparator&lt;Map.Entry&lt;Character, 
	 *        Integer&gt;&gt;</code>
	 * @return <code>Map&lt;Character, Integer&gt;</code>
	 * @throws IOException
	 */
	public static Map<Character, Integer> calculateLetters(String filePath, 
			Comparator<Map.Entry<Character, Integer>> comparator) throws IOException {
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(new File(filePath));
		Map<Character, Integer> map = new HashMap<Character, Integer>();		
		char[] line;
		Integer currentValue;
		
		while (scanner.hasNext()) {
			line = scanner.nextLine().toUpperCase().toCharArray();
			for (char c : line) {
				if (c < 'A' || c > 'Z') {
					continue;
				}
				
				currentValue = map.get(c);
				
				if (currentValue == null) {
					map.put(c, 1);
				} else {
					map.put(c, currentValue + 1);
				}
			}
		}	
		
		return sort(map, comparator);
	}	
	
	/**
	 * Sorts calculation results using given comparator
	 * 
	 * @param map <code>Map&lt;Character, Integer&gt;</code>
	 * @param comparator <code>Comparator&lt;Map.Entry&lt;Character, 
	 *        Integer&gt;&gt;</code>
	 * @return <code>Map&lt;Character, Integer&gt;</code>
	 */
	private static Map<Character, Integer> sort(Map<Character, Integer> map, Comparator<Map.Entry<Character, Integer>> comparator) {
		List<Map.Entry<Character, Integer>> temp = new ArrayList<>(map.entrySet());
		Collections.sort(temp, comparator);
		
		Map<Character, Integer> result = new LinkedHashMap<>();
		temp.forEach(n -> result.put(n.getKey(), n.getValue()));
		
		return result;
	}
}