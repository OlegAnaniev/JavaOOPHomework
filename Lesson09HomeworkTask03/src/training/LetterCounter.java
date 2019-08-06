package training;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Class calculating letter occurrences in a text file 
 * 
 * @version 0.1 06.08.2019
 * @author Oleg
 */
public class LetterCounter {
	private static final int LETTER_COUNT = 'z' - 'a' + 1;
	private static final String OUTPUT_FORMAT = "%s: %.2f";
	private File file;
	private List<Letter> list;	
	private int totalLetters;
	
	
	/**
	 * Default constructor
	 */
	public LetterCounter() {
		super();			
	}
	
	/**
	 * Parameterized constructor
	 * 
	 * @param path <code>String</code>
	 */
	public LetterCounter(String path) {
		this();
		this.file = new File(path);
	}

	/**
	 * Calculates letter occurrences and displays results
	 * 
	 * @return <code>List&lt;Student&gt;</code>
	 */
	public void calculate() {
		initialize();
		readFile();		
		sortList();
		calculateTotalLetters();
		displayResuls();
	}
	
	/**
	 * Initializes list with letters
	 */
	private void initialize() {		
		list = new ArrayList<>(LETTER_COUNT);
		for (int i = 0; i < LETTER_COUNT; i++) {
			list.add(new Letter((char)('a' + i), 0));			
		}
	}
	
	/**
	 * Reads text from file
	 */
	private void readFile() {
		StringBuilder text = new StringBuilder();
		
		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNext()) {
				text.append(scanner.nextLine());
				countLine(text);
				text.delete(0, text.length());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Counts letter occurrences in a text line
	 * 
	 * @param text <code>StringBuilder</code>
	 */
	private void countLine(StringBuilder text) {
		char symbol;
		Letter item;
		
		for (int i = 0; i < text.length(); i++) {
			symbol = text.charAt(i);
			
			if (symbol >= 'a' && symbol <= 'z') {
				item = list.get(symbol - 'a');
				item.setCount(item.getCount() + 1);
			} else if (symbol >= 'A' && symbol <= 'Z') {
				item = list.get(symbol - 'A');
				item.setCount(item.getCount() + 1);
			}			
		}
	}

	/**
	 * Sorts the list
	 */
	private void sortList() {
		Collections.sort(list, 
				(Letter a, Letter b) -> b.getCount() - a.getCount());
	}
	
	/**
	 * Calculates total number of letters
	 */
	private void calculateTotalLetters() {		
		for (Letter letter : list) {
			if (letter.getCount() == 0) {
				return;
			}
			totalLetters += letter.getCount();
		}
	}
	
	/**
	 * Displays results
	 */
	private void displayResuls() {		
		for (Letter letter : list) {
			if (letter.getCount() == 0) {
				return;
			}
			System.out.println(String.format(OUTPUT_FORMAT, letter.getValue(), 
					(double) letter.getCount() / totalLetters));
		}
	}

	/**
	 * Class holding statistics of a certain letter usage
	 * 
	 * @version 0.1 06.08.2019
	 * @author Oleg
	 */
	private class Letter {
		private char value;
		private int count;
		
		/**
		 * Default constructor
		 */
		private Letter() {
			super();
		}		
		
		/**
		 * Parameterized constructor
		 * 
		 * @param value <code>char</code>
		 * @param count <code>int</code>
		 */
		private Letter(char value, int count) {
			super();
			this.value = value;
			this.count = count;
		}

		/**
		 * Gets letter value
		 * 
		 * @return <code>char</code>
		 */
		public char getValue() {
			return value;
		}

		/**
		 * Sets letter value
		 * 
		 * @param value <code>char</code>
		 */
		@SuppressWarnings("unused")
		public void setValue(char value) {
			this.value = value;
		}

		/**
		 * Gets letter count
		 * 
		 * @return <code>int</code>
		 */
		public int getCount() {
			return count;
		}

		/**
		 * Sets letter count
		 * 
		 * @param count <code>int</code>
		 */
		public void setCount(int count) {
			this.count = count;
		}

		/**
		 * Text representation of a letter
		 */
		@Override
		public String toString() {
			return "Letter [value=" + value + ", count=" + count + "]";
		}		
	}
}