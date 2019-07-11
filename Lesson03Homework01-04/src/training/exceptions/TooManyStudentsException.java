package training.exceptions;

/**
 * Exception thrown on an unsuccessful attempt to add a student to a group
 * 
 * @version 0.1 10.07.2019
 * @author Oleg
 */
public class TooManyStudentsException extends Exception {

	public static final String MESSAGE = "Too many students";
	
	/**
	 * Creates exception with default message
	 */
	public TooManyStudentsException() {
		super(MESSAGE);
	}

	/**
	 * Creates exception with message
	 * 
	 * @param message <code>String message</code>
	 */
	public TooManyStudentsException(String message) {
		super(message);
	}
}