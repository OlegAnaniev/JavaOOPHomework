package training;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class comparing contents of two text files
 * 
 * @version 0.1 17.07.2019
 * @author Oleg
 */
public class FileContentsComparer {
	private final static int MAX_FILE_SIZE = 1024 * 1024;
	private final static String FILES_DO_NOT_EXIST_MESSAGE = "Input files must "
			+ "exist";
	private final static String NOT_FILES_MESSAGE = "Input files must be files";
	private final static String FILES_ARE_TOO_BIG_MESSAGE = 
			String.format("Input files must smaller then %d bytes", 
					MAX_FILE_SIZE);
	private File inputOne;
	private File inputTwo;
	private File output;
	
	/**
	 * Default constructor
	 */
	public FileContentsComparer() {
		super();
	}
	
	/**
	 * Parameterized constructor
	 * 
	 * @param inputOne <code>File</code>
	 * @param inputTwo <code>File</code>
	 * @param output <code>File</code>
	 */
	public FileContentsComparer(File inputOne, File inputTwo, File output) {
		super();
		this.inputOne = inputOne;
		this.inputTwo = inputTwo;
		this.output = output;
	}

	/**
	 * Gets the first input file
	 * 
	 * @return <code>File</code>
	 */
	public File getInputOne() {
		return inputOne;
	}

	/**
	 * Sets the first input file
	 * 
	 * @param inputOne <code>File</code>
	 */
	public void setInputOne(File inputOne) {
		this.inputOne = inputOne;
	}

	/**
	 * Gets the second input file
	 * 
	 * @return <code>File</code>
	 */
	public File getInputTwo() {
		return inputTwo;
	}

	/**
	 * Sets the second input file
	 * 
	 * @param inputTwo <code>File</code>
	 */
	public void setInputTwo(File inputTwo) {
		this.inputTwo = inputTwo;
	}

	/**
	 * Gets the output file
	 * 
	 * @return <code>File</code>
	 */
	public File getOutput() {
		return output;
	}

	/**
	 * Sets the output file
	 * 
	 * @param output <code>File</code>
	 */
	public void setOutput(File output) {
		this.output = output;
	}
	
	/**
	 * Compares input files and puts matching parts to the output file
	 * @throws IOException 
	 */
	public void compare() throws IOException {
		if (!inputOne.exists() || !inputTwo.exists()) {
			throw new IllegalArgumentException(FILES_DO_NOT_EXIST_MESSAGE);
		}
		
		if (!inputOne.isFile() || !inputTwo.isFile()) {
			throw new IllegalArgumentException(NOT_FILES_MESSAGE);
		}
		
		if (inputOne.length() > MAX_FILE_SIZE 
				|| inputTwo.length() > MAX_FILE_SIZE) {
			throw new IllegalArgumentException(FILES_ARE_TOO_BIG_MESSAGE);
		}
		
		String[] fileOne = readFile(inputOne);
		String[] fileTwo = readFile(inputTwo);		
		
		try (PrintWriter writer = new PrintWriter(output)) {
			for (int i = 0; i < fileOne.length; i++) {
				for (int j = 0; j < fileTwo.length; j++) {
					if (fileOne[i].equalsIgnoreCase(fileTwo[j])) {
						writer.println(fileOne[i]);
						break;
					}
				}
			}
		} catch (IOException e) {
			throw e;
		}				
	}
	
	/**
	 * Reads data from a file and converts it to array of words
	 * 
	 * @param file <code>File</code>
	 * @return <code>String[]</code>
	 * @throws IOException
	 */
	private String[] readFile(File file) throws IOException {
		String line;
		StringBuilder text = new StringBuilder();
		
		try (BufferedReader reader = 
				new BufferedReader(new FileReader(file))) {
			while ((line = reader.readLine()) != null) {				
				text.append(line);
			}
		} catch (IOException e) {
			throw e;
		}		
		
		return text.toString().split("[ ,.!?]");
	}
}