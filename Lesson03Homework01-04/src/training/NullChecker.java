package training;

/**
 * Class providing method to compare null objects
 * 
 * @version 0.1 15.07.2019
 * @author Oleg
 */
public class NullChecker {
	public static final int NOT_NULL = 10;

	/**
	 * Private constructor to make instance creation impossible
	 */
	private NullChecker() {
		super();
	}

	/**
	 * Checks if object are null and compares them if at least one of them is 
	 * null
	 * 
	 * @param a <code>Object</code>
	 * @param b <code>Object</code>
	 * @return <code>int</code> NOT_NULL if both objects are not nulls and
	 * values similar to compateTo() methods otherwise
	 */
	public static int check(Object a, Object b) {
		if (a != null && b == null) {
			return -1;
		}

		if (a == null && b != null) {
			return 1;
		}

		if (a == null && b == null) {
			return 0;
		}

		return NOT_NULL;
	}
}
