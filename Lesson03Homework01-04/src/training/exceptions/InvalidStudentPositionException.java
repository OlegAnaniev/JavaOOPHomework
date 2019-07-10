package training.exceptions;

/**
 * Exception thrown on an unsuccessful attempt to add a student to a group
 * 
 * @version 0.1 10.07.2019
 * @author Oleg
 */
public class InvalidStudentPositionException extends Exception {

	public static final String MESSAGE = "Invalid student position";
	
	/**
	 * Creates exception with default message
	 */
	public InvalidStudentPositionException() {
		super(MESSAGE);
	}

	/**
	 * Creates exception with message
	 * 
	 * @param message <code>String message</code>
	 */
	public InvalidStudentPositionException(String message) {
		super(message);
	}
}