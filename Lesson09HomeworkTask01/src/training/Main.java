package training;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		final int ELEMENT_COUNT = 10;
		final int POLL_FIRST_COUNT = 2;
		final int POLL_LAST_COUNT = 1;
		
		testDeque(ELEMENT_COUNT, POLL_FIRST_COUNT, POLL_LAST_COUNT);
	}
	
	/**
	 * Creates a deque, fills it with elements and removes some elements from
	 * start and end 
	 */
	private static void testDeque(int count, int countPollFirst, 
			int countPollLast) {
		
		Deque<Integer> deque = new ArrayDeque<>();
		
		fillDeque(deque, count);		
		System.out.println(deque);
		
		pollFirst(deque, countPollFirst);
		pollLast(deque, countPollLast);
		System.out.println(deque);
	}
	
	/**
	 * Fills deque with given number of elements
	 * 
	 * @param deque <code>Deque&lt;Integer&gt;</code>
	 * @param count <code>int</code>
	 */
	private static void fillDeque(Deque<Integer> deque, int count) {
		final int MAX_VALUE = 10;
		Random random = new Random();
		
		for (int i = 0; i < count; i++) {
			deque.add(random.nextInt(MAX_VALUE));
		}
	}
	
	/**
	 * Removes given number of elements from deque start
	 *  
	 * @param deque <code>Deque&lt;Integer&gt;</code>
	 * @param count <code>int</code>
	 */
	private static void pollFirst(Deque<Integer> deque, int count) {
		for (int i = 0; i < count; i++) {
			deque.pollFirst();			
		}
	}
	
	/**
	 * Removes given number of elements from deque end
	 * 
	 * @param deque <code>Deque&lt;Integer&gt;</code>
	 * @param count <code>int</code>
	 */
	private static void pollLast(Deque<Integer> deque, int count) {
		for (int i = 0; i < count; i++) {
			deque.pollLast();			
		}
	}
}