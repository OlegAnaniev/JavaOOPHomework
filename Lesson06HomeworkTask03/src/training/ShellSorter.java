package training;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class providing Shell sort functionality
 * 
 * @version 0.1 19.07.2019
 * @author Oleg
 */
public class ShellSorter {	
	
	/**
	 * Private constructor to make instance creation impossible
	 */
	private ShellSorter() {
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
	 * Sorts array
	 * 
	 * @param array <code>int[]</code>
	 */
	public static void sort(int[] array) {
		ExecutorService executor = Executors.newFixedThreadPool(
				getProcessorCount());		
		ArrayList tasks;
		int step = array.length / 2;
		
		while (step >= 1) {
			tasks = new ArrayList();
			
			for (int i = 0; i < step; i++) {
				tasks.add(Executors.callable(new InsertionSorter(array, i, step)));
			}
			step /= 2;
			
			try {
				executor.invokeAll(tasks);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		executor.shutdown();
	}
	
	/**
	 * Class providing multithreaded insertion sorting functionality
	 *  
	 * @version 0.1 19.07.2019
 	 * @author Oleg
	 */
	private static class InsertionSorter implements Runnable {
		private int[] array;
		private int startIndex;
		private int step;
		
		/**
		 * Default constructor
		 */
		public InsertionSorter() {
			super();
		}

		/**
		 * Parameterized constructor
		 * 
		 * @param array <code>int[]</code>
		 * @param startIndex <code>int</code>
		 * @param step <code>int</code>
		 */
		public InsertionSorter(int[] array, int startIndex, int step) {
			super();
			this.array = array;
			this.startIndex = startIndex;
			this.step = step;
		}

		/**
		 * Gets the array
		 * 
		 * @return <code>int[]</code>
		 */
		public int[] getArray() {
			return array;
		}

		/**
		 * Sets the array
		 * 
		 * @param array <code>int[]</code>
		 */
		public void setArray(int[] array) {
			this.array = array;
		}

		/**
		 * Gets start index 
		 * 
		 * @return <code>int</code>
		 */
		public int getStartIndex() {
			return startIndex;
		}

		/**
		 * Sets start index
		 * 
		 * @param startIndex
		 */
		public void setStartIndex(int startIndex) {
			this.startIndex = startIndex;
		}

		/**
		 * Gets step
		 * 
		 * @return <code>int</code>
		 */
		public int getStep() {
			return step;
		}

		/**
		 * Sets step
		 * 
		 * @param step
		 */
		public void setStep(int step) {
			this.step = step;
		}

		/**
		 * Sorts given array members
		 */
		@Override
		public void run() {
			int temp;
			
			for (int i = startIndex; i < array.length - 1; i = i + step) {
				for (int j = Math.min(i + step, array.length - 1); j - step >= 0; j = j - step) {
					if (array[j - step] > array[j]) {
						temp = array[j];
						array[j] = array[j - step];
						array[j - step] = temp;
					} else {
						break;
					}
				}
			}			
		}		
	}
}