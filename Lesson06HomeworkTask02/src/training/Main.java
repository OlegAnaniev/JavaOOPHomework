package training;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Main {	
	public static void main(String[] args) {		
		int[] array = getRandomIntArray();	
		Calendar start;
		Calendar end;
		BigInteger sum;	
		
//		System.out.println(Arrays.toString(array));
		
		start = Calendar.getInstance();
		sum = ArraySumCalculator.sumArrayMultithreaded(array);		
		end = Calendar.getInstance();
		System.out.println("Multithreaded results:" + System.lineSeparator()
			+ "Sum: " + sum + System.lineSeparator()
			+ "Time: " + (end.getTimeInMillis() - start.getTimeInMillis()));
		
		System.out.println();
		
		start = Calendar.getInstance();
		sum = ArraySumCalculator.sumArrayMultithreadedPool(array);		
		end = Calendar.getInstance();
		System.out.println(
			"Multithreaded pool results:" + System.lineSeparator()
			+ "Sum: " + sum + System.lineSeparator()
			+ "Time: " + (end.getTimeInMillis() - start.getTimeInMillis()));
		
		System.out.println();
		
		start = Calendar.getInstance();
		sum = ArraySumCalculator.sumArraySinglethreaded(array);		
		end = Calendar.getInstance();
		System.out.println("Singlethreaded results:" + System.lineSeparator()
			+ "Sum: " + sum + System.lineSeparator()
			+ "Time: " + (end.getTimeInMillis() - start.getTimeInMillis()));		
	}
	
	/**
	 * Gets a random array of int basing on preset constants
	 * 
	 * @return <code>int[]</code>
	 */
	private static int[] getRandomIntArray() {
		final int ARRAY_LENGTH = 50000000;
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