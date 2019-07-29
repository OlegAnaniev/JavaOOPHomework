package training;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Multithreaded  file seeker
 * 
 * @version 0.1 29.07.2019
 * @author Oleg
 */
public class Seeker {
	private static final String INVALID_HAYSTACK_MESSAGE = 
			"Haystack must exist and be a folder";
	
	private File haystack;
	private String needle;
	private ExecutorService executor;
	private AtomicInteger activeTasks;

	/**
	 * Default constructor
	 */
	public Seeker() {
		super();
	}

	/**
	 * Parameterized constructor 
	 * 
	 * @param haystack <code>String</code>
	 * @param needle <code>String</code>
	 */
	public Seeker(String haystack, String needle) {
		super();
		this.haystack = new File(haystack);
		this.needle = needle;
	}

	/**
	 * Get haystack
	 * 
	 * @return <code>File</code>
	 */
	public File getHaystack() {
		return haystack;
	}

	/**
	 * Set haystack
	 * 
	 * @param haystack <code>File</code>
	 */
	public void setHaystack(File haystack) {
		this.haystack = haystack;
	}

	/**
	 * Get needle
	 * 
	 * @return <code>String</code>
	 */
	public String getNeedle() {
		return needle;
	}

	/**
	 * Set needle
	 * 
	 * @param needle <code>String</code>
	 */
	public void setNeedle(String needle) {
		this.needle = needle;
	}
	
	/**
	 * Seeks for files
	 */
	public void seek() {
		validateHaystack();
		
		executor = Executors.newFixedThreadPool(getAvaliableProcessors());
		activeTasks = new AtomicInteger();
		activeTasks.incrementAndGet();
		executor.submit(new Worker(haystack, needle));		
		
		while (activeTasks.get() > 0) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		executor.shutdown();
	}
	
	/**
	 * Gets available processor count
	 * 
	 * @return <code>int</code>
	 */
	private int getAvaliableProcessors() {
		Runtime runtime = Runtime.getRuntime();
		return runtime.availableProcessors();
	}
	
	/**
	 * Validates haystack path
	 */
	private void validateHaystack() {
		if (!haystack.exists() || !haystack.isDirectory()) {
			throw new IllegalArgumentException(INVALID_HAYSTACK_MESSAGE);
		}
	}

	/**
	 * A thread of the file seeker
	 * 
	 * @version 0.1 29.07.2019
	 * @author Oleg
	 */
	private class Worker implements Runnable {
		private File haystack;
		private String needle;

		/**
		 * Default constructor
		 */
		public Worker() {
			super();
		}

		/**
		 * Parameterized constructor
		 * 
		 * @param haystack <code>File</code>
		 * @param needle <code>File</code>
		 */
		public Worker(File haystack, String needle) {
			super();
			this.haystack = haystack;
			this.needle = needle;
		}

		/**
		 * Gets haystack
		 * 
		 * @return <code>File</code>
		 */
		public File getHaystack() {
			return haystack;
		}

		/**
		 * Sets haystack
		 * 
		 * @param haystack <code>File</code>
		 */
		public void setHaystack(File haystack) {
			this.haystack = haystack;
		}

		/**
		 * Gets needle
		 * 
		 * @return <code>String</code>
		 */
		public String getNeedle() {
			return needle;
		}

		/**
		 * Sets needle
		 * 
		 * @param needle <code>String</code>
		 */
		public void setNeedle(String needle) {
			this.needle = needle;
		}		
		
		/**
		 * Text representation of the worker
		 */
		@Override
		public String toString() {
			return "Worker [haystack=" + haystack + ", needle=" + needle + "]";
		}

		/**
		 * Luke Filewalker =)
		 */
		@Override
		public void run() {
			File[] files = haystack.listFiles();

			for (File file : files) {
				if (file.isFile()) {
					if (file.getName().equalsIgnoreCase(needle)) {
						System.out.println(file);
					}					
				} else {
					activeTasks.incrementAndGet();
					executor.submit(new Worker(file, needle));
				}
			}
			
			activeTasks.decrementAndGet();
		}
	}
}