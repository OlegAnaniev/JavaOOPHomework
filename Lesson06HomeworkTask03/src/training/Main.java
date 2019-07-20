package training;

import java.util.Arrays;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		int[] array = getRandomIntArray();
		
		System.out.println(Arrays.toString(array));		
		ShellSorter.sort(array);
		System.out.println(Arrays.toString(array));
	}
	
	/**
	 * Gets a random array of int basing on preset constants
	 * 
	 * @return <code>int[]</code>
	 */
	private static int[] getRandomIntArray() {
		final int ARRAY_LENGTH = 10;
		final int MIN_VALUE = -50;
		final int MAX_VALUE = 50;
		int[] array = new int[ARRAY_LENGTH];
		Random random = new Random();
		
		for (int i = 0; i < array.length; i++) {
			array[i] = random.nextInt(MAX_VALUE + 1 - MIN_VALUE) + MIN_VALUE;
		}
		
		return array;
	}
}