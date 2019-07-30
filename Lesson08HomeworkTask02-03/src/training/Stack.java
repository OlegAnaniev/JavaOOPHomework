package training;

import java.util.Arrays;

/**
 * Class representing a stack
 * 
 * @version 0.1 30.07.2019
 * @author Oleg
 */
public class Stack {
	private static final int SIZE_STEP = 10;
	private static final String BLACKLISTED_OBJECT_MESSAGE = 
			"Given object is on the blacklist";
	private Object[] array;
	private int length;
	private Blacklist blacklist;	

	/**
	 * Default constructor;
	 */
	public Stack() {
		super();
		initializeArray();
		initializeBlacklist();
	}

	/**
	 * Constructor accepting stack start size
	 * 
	 * @param size <code>int</code>
	 */
	public Stack(int size) {
		super();
		array = new Object[size];
		initializeBlacklist();
	}	
	
	/**
	 * Constructor accepting existing blacklist
	 * 
	 * @param blacklist <code>Blacklist</code>
	 */
	public Stack(Blacklist blacklist) {
		super();
		initializeArray();
		this.blacklist = blacklist;
	}
	
	/**
	 * Constructor accepting stack start size and existing blacklist
	 * 
	 * @param size <code>int</code>
	 * @param blacklist <code>Blacklist</code>
	 */
	public Stack(int size, Blacklist blacklist) {
		this(size);
		this.blacklist = blacklist;
	}
	
	/**
	 * Default array initialization
	 */
	private final void initializeArray() {
		array = new Object[SIZE_STEP];
	}
	
	/**
	 * Default blacklist initialization
	 */
	private final void initializeBlacklist() {
		blacklist = new Blacklist();
	}
	
	/**
	 * Gets current stack length
	 * 
	 * @return <code>int</code>
	 */
	public int getLength() {
		return length;
	}		

	/**
	 * Gets blacklist
	 * 
	 * @return <code>Blacklist</code>
	 */
	public Blacklist getBlacklist() {
		return blacklist;
	}

	/**
	 * Sets blacklist
	 * 
	 * @param blacklist <code>Blacklist</code>
	 */
	public void setBlacklist(Blacklist blacklist) {
		this.blacklist = blacklist;
	}

	/**
	 * Text representation of the stack
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("Stack [array=[");
		
		if (length > 0) {
			result.append(array[0]);
		}
		
		for (int i = 1; i < length; i++) {
			result.append(", " + array[i]);
		}
		

		
		result.append("], " + blacklist + ", length=" + length + "]");
		return result.toString();
	}

	/**
	 * Adds an object to the stack
	 * 
	 * @param object <code>Object</code>
	 */
	public void push(Object object) {
		if (blacklist.isOnList(object.getClass())) {
			throw new IllegalArgumentException(BLACKLISTED_OBJECT_MESSAGE);
		}
		
		if (length == array.length) {
			resize();
		}

		array[length] = object;
		length++;
	}

	/**
	 * Extends array
	 */
	private void resize() {
		array = Arrays.copyOf(array, array.length + SIZE_STEP);
	}

	/**
	 * Gets the last stack element
	 * 
	 * @return <code>Object</code>
	 */
	public Object peek() {
		return length > 0 ? array[length - 1] : null;
	}
	
	/**
	 * Gets the last stack element and removes it form stack
	 * 
	 * @return <code>Object</code>
	 */
	public Object pop() {
		Object tempObject = peek();
		
		if (tempObject != null) {
			length--;
			array[length] = null;
		}
		
		return tempObject;				
	}
}