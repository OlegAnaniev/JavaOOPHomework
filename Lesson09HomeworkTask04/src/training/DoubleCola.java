package training;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Class emulating double cola vending machine
 * 
 * @version 0.1 05.08.2019
 * @author Oleg
 */
public class DoubleCola {
	private Deque<String> deque;
	private int iterations;

	/**
	 * Default constructor
	 */
	public DoubleCola() {
		super();
		deque = new ArrayDeque<>();
		deque.add("Sheldon");
		deque.add("Leonard");
		deque.add("Volovitc");
		deque.add("Kutrapalli");
		deque.add("Penny");
	}

	/**
	 * Constructor accepting number of double cola glasses to be sold
	 * 
	 * @param iterations
	 */
	public DoubleCola(int iterations) {
		this();
		this.iterations = iterations;
	}

	/**
	 * Gets number of iterations
	 * 
	 * @return <code>int</code>
	 */
	public int getIterations() {
		return iterations;
	}

	/**
	 * Sets number of iterations
	 * 
	 * @param iterations <code>int</code>
	 */
	public void setIterations(int iterations) {
		this.iterations = iterations;
	}

	/**
	 * Text representation of current queue state
	 */
	@Override
	public String toString() {
		return "DoubleCola [deque=" + deque + "]";
	}
	
	/**
	 * Sell double cola
	 */
	public void iterate() {
		String value;
		
		while (iterations > 0) {
			value = deque.pollFirst();
			deque.add(value);
			deque.add(value);
			
			iterations--;
		}
	}
}