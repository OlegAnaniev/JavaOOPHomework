package training;

import java.math.BigInteger;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class ArraySumCalculator {	
	
	/**
	 * Private constructor to make instance creation impossible
	 */
	private ArraySumCalculator() {
		super();
	}
	
	/**
	 * Gets count of available processors
	 * 
	 * @return <code>int</code>
	 */
	private static int getProcessorCount() {
		Runtime runtime = Runtime.getRuntime();
		return runtime.availableProcessors();
	}
	
	/**
	 * Gets starting position of a range to be processed by a certain thread
	 * 
	 * @param array <code>int[]</code>
	 * @param threadNo <code>int</code>
	 * @param threadCount <code>int</code>
	 * @return <code>int</code>
	 */
	private static int getThreadStartingPosition(int[] array, int threadNo,
			int threadCount) {
		return (int) Math.ceil((double) array.length / threadCount) * threadNo;
	}
	
	/**
	 * Gets ending position of a range to be processed by a certain thread
	 * 
	 * @param array <code>int[]</code>
	 * @param threadNo <code>int</code>
	 * @param threadCount <code>int</code>
	 * @return <code>int</code>
	 */
	private static int getThreadEndingPosition(int[] array, int threadNo,
			int threadCount) {
		int temp = (int) Math.ceil((double) array.length / threadCount) 
				* (threadNo + 1);
		return temp > array.length ? array.length : temp;
	}
	
	/**
	 * Calculates total of thread results
	 * 
	 * @param tasks <code>Future<BigInteger>[]</code>
	 * @return <code>BigInteger</code>
	 */
	private static BigInteger getTotal(Future<BigInteger>[] tasks) {
		BigInteger sum = new BigInteger("0");
		
		for (Future<BigInteger> result : tasks) {
			try {
				sum = sum.add(result.get());				
			} catch (InterruptedException | ExecutionException e) {				
				e.printStackTrace();
			}
		}
		
		return sum;
	}
	
	/**
	 * Calculates sum of int array in multithreaded mode
	 * 
	 * @param array <code>int[]</code>
	 * @return <code>BigInteger</code>
	 */
	public static BigInteger sumArrayMultithreaded(int[] array) {
		int threadCount = getProcessorCount();
		Future<BigInteger>[] tasks = new Future[threadCount];
		FutureTask<BigInteger> task;
		Thread thread;
		
		for (int i = 0; i < tasks.length; i++) {			
			task = new FutureTask<BigInteger>(new ArrayPartSumCalculator(array, 
					getThreadStartingPosition(array, i, threadCount), 
					getThreadEndingPosition(array, i, threadCount)));
			tasks[i] = task;
			thread = new Thread(task);
			thread.start();			
		}
		
		return getTotal(tasks);		
	}

	/**
	 * Calculates sum of int array in multithreaded mode using thread pool
	 * 
	 * @param array <code>int[]</code>
	 * @return <code>BigInteger</code>
	 */
	public static BigInteger sumArrayMultithreadedPool(int[] array) {
		int threadCount = getProcessorCount();
		Future<BigInteger>[] tasks = new Future[threadCount];
		ExecutorService executor = Executors.newFixedThreadPool(threadCount);
		
		for (int i = 0; i < tasks.length; i++) {
			tasks[i] = executor.submit(new ArrayPartSumCalculator(array, 
					getThreadStartingPosition(array, i, threadCount), 
					getThreadEndingPosition(array, i, threadCount)));
		}
		
		executor.shutdown();
		
		return getTotal(tasks);
	}
	
	/**
	 * Calculates sum of int array in singlethreaded mode
	 * 
	 * @param array <code>int[]</code>
	 * @return <code>BigInteger</code>
	 */
	public static BigInteger sumArraySinglethreaded(int[] array) {
		BigInteger sum = new BigInteger("0");
		
		for (int item : array) {
			sum = sum.add(new BigInteger(Integer.toString(item)));
		}
		
		return sum;
	}

	/**
	 * Multithread array sum calculator
	 * 
	 * @version 0.1 18.07.2019
	 * @author Oleg
	 */
	private static class ArrayPartSumCalculator implements Callable<BigInteger> {
		private int[] array;
		private int from;
		private int to;
		
		/**
		 * Default constructor
		 */
		public ArrayPartSumCalculator() {
			super();
		}
		
		/**
		 * Parameterized constructor
		 * 
		 * @param array <code>int[]</code>
		 * @param from <code>int</code> inclusively
		 * @param to <code>int</code> exclusively
		 */
		public ArrayPartSumCalculator(int[] array, int from, int to) {
			super();
			this.array = array;
			this.from = from;
			this.to = to;
		}

		/**
		 * Get array
		 * 
		 * @return <code>int[]</code> 
		 */
		public int[] getArray() {
			return array;
		}

		/**
		 * Set array
		 * 
		 * @param array <code>int[]</code>
		 */
		public void setArray(int[] array) {
			this.array = array;
		}

		/**
		 * Get calculation starting position (inclusively)
		 * 
		 * @return <code>int</code>
		 */
		public int getFrom() {
			return from;
		}

		/**
		 * Set calculation starting position (inclusively)
		 * 
		 * @param from <code>int</code>
		 */
		public void setFrom(int from) {
			this.from = from;
		}

		/**
		 * Get calculation ending position (exclusively)
		 * 
		 * @return <code>int</code>
		 */
		public int getTo() {
			return to;
		}

		/**
		 * Set calculation ending position (exclusively)
		 * 
		 * @param to <code>int</code>
		 */
		public void setTo(int to) {
			this.to = to;
		}

		/**
		 * Calculate sum of array segment values
		 */
		@Override
		public BigInteger call() throws Exception {
			BigInteger sum = new BigInteger("0");
			
			for (int i = from; i < to; i++) {
				sum = sum.add(new BigInteger(Integer.toString(array[i])));
			}
			
			return sum;
		}		
	}	
}