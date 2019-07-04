package training;

import java.util.Arrays;

/**
 * Simple cell network simulator
 * 
 * @version 0.1 04.07.2019
 * @author Oleg
 */
public class Network {
	private final int ARRAY_EXTENSION_STEP = 10;
	private Phone[] phones;
	private String[] numbers;
	private int nextUnusedSlot;
	private int nextUnusedNumber;
	
	/**
	 * Creates network
	 */
	public Network() {
		super();
		this.phones = new Phone[ARRAY_EXTENSION_STEP];
		this.numbers = new String[ARRAY_EXTENSION_STEP];
		this.nextUnusedSlot = 0;
		this.nextUnusedNumber = 1;
	}
	
	/**
	 * Gets next unused phone number
	 * 
	 * @return <code>int</code>
	 */
	public int getNextUnusedNumber() {
		return nextUnusedNumber;
	}

	/**
	 * Sets next unused phone number
	 * 
	 * @param nextUnusedNumber <code>int</code>
	 */
	public void setNextUnusedNumber(int nextUnusedNumber) {
		this.nextUnusedNumber = nextUnusedNumber;
	}

	/**
	 * Gets phone array
	 * 
	 * @return <code>Phone[]</code>
	 */
	public Phone[] getPhones() {
		return phones;
	}

	/**
	 * Gets phone number array
	 * 
	 * @return <code>String[]</code>
	 */
	public String[] getNumbers() {
		return numbers;
	}

	/**
	 * Registers given phone in the network
	 * 
	 * @param phone <code>Phone</code>
	 * @return phone number <code>String</code>
	 */
	public String register(Phone phone) {
		String newNumber;
		
		if (nextUnusedSlot >= phones.length) {
			extendArrays();
		}
		
		phones[nextUnusedSlot] = phone;
		
		newNumber = formatNumber(nextUnusedNumber);
		numbers[nextUnusedSlot] = newNumber;
		
		nextUnusedSlot++;
		nextUnusedNumber++;
		
		return newNumber;
	}
	
	/**
	 * Extends phone and number arrays
	 */
	private void extendArrays() {
		phones = Arrays.copyOf(phones, phones.length + ARRAY_EXTENSION_STEP);
		numbers = Arrays.copyOf(numbers, numbers.length 
				+ ARRAY_EXTENSION_STEP);
	}
	
	/**
	 * Converts int phone number into 7-symbol String
	 * 
	 * @param number <code>int</code>
	 * @return <code>String</code>
	 */
	private String formatNumber(int number) {
		return String.format("%07d", number);
	}
	
	/**
	 * Routes a call to given phone number
	 * 
	 * @param number <code>String</code>
	 * @return <code>true</code> if the number is found and <code>false</code> 
	 * otherwise 
	 */
	public boolean routeCall(String number) {		
		for (int i = 0; i < nextUnusedSlot; i++) {
			if (numbers[i].equals(number)) {
				phones[i].receiveCall();
				return true;
			}
		}
		
		return false;
	}

	@Override
	public String toString() {
		return "Network [subscribers=" + (nextUnusedSlot) 
				+ ", nextUnusedNumber=" + formatNumber(nextUnusedNumber) + "]";
	}		
}