package training;

import java.util.Arrays;

/**
 * Class representing a blacklist of classes
 * 
 * @version 0.1 30.07.2019
 * @author Oleg
 */
public class Blacklist {
	private static final int SIZE_STEP = 10;
	private Class<?>[] array;
	private int length;
	
	/**
	 * Default constructor
	 */
	public Blacklist() {
		super();
		array = new Class<?>[SIZE_STEP];
	}

	/**
	 * Constructor accepting stack blacklist size
	 * 
	 * @param size <code>int</code>
	 */
	public Blacklist(int size) {
		super();
		array = new Class<?>[size];
	}

	/**
	 * Gets current blackilist length
	 * 
	 * @return <code>int</code>
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Text representation of the blacklist
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("Blacklist [array=[");
		
		if (length > 0) {
			result.append(array[0]);
		}
		
		for (int i = 1; i < length; i++) {
			result.append(", " + array[i]);
		}
		
		result.append("], length=" + length + "]");
		
		return result.toString();
	}	
	
	/**
	 * Adds a class to the blacklist
	 * 
	 * @param cls <code>Class&lt;?&lg;</code>
	 */
	public void add(Class<?> cls) {
		if (isOnList(cls)) {
			return;
		}
		
		if (length == array.length) {
			resize();
		}
		
		array[length] = cls;
		length++;
	}
	
	/**
	 * Extends blacklist
	 */
	private void resize() {
		array = Arrays.copyOf(array, array.length + SIZE_STEP);
	}

	/**
	 * Checks if a class is already on the blacklist
	 * 
	 * @param cls <code>Class&lt;?&lg;</code>
	 * @return <code>boolean</code>
	 */
	public boolean isOnList(Class<?> cls) {
		for (int i = 0; i < length; i++) {
			if (array[i].equals(cls)) {
				return true;
			}
		}
		
		return false;
	}	
}