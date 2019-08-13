package training.exceptions;

/**
 * Exception thrown on html response code different from 200
 * 
 * @version 0.1 09.08.2019
 * @author Oleg
 */
public class NegativeResponseCodeException extends Exception {
	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_MESSAGE = "Response code differs from "
			+ "200";
	private static final String CODE_MESSAGE = "Response code is ";

	/**
	 * Default constructor
	 */
	public NegativeResponseCodeException() {
		super(DEFAULT_MESSAGE);
	}

	/**
	 * Constructor accepting message
	 * 
	 * @param message <code>String</code>
	 */
	public NegativeResponseCodeException(String message) {
		super(message);
	}	
	
	/**
	 * Constructor accepting response code
	 * 
	 * @param responseCode <code>int</code>
	 */
	public NegativeResponseCodeException(int responseCode) {
		super(CODE_MESSAGE + responseCode);
	}
	
}