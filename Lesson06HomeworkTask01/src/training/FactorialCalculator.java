package training;

import java.math.BigInteger;

/**
 * Multithread factorial calculator
 * 
 * @version 0.1 18.07.2019
 * @author Oleg
 */
public class FactorialCalculator implements Runnable {
	private int number;

	/**
	 * Default constructor
	 */
	public FactorialCalculator() {
		super();
	}

	/**
	 * Constructor accepting target number
	 * 
	 * @param number <code>int</code>
	 */
	public FactorialCalculator(int number) {
		super();
		this.number = number;
	}

	/**
	 * Gets target number
	 * 
	 * @return <code>int</code>
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * Sets target number
	 * 
	 * @param number <code>int</code>
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * Calculates factorial
	 */
	@Override
	public void run() {
		Thread currentThread = Thread.currentThread();
		BigInteger factorial = new BigInteger("1");

		for (int i = 1; i <= number; i++) {
			factorial = factorial.multiply(new BigInteger(Integer.toString(i)));
		}

		System.out.println(currentThread.getName() + ": " + number + "! = " 
				+ factorial);
	}
}