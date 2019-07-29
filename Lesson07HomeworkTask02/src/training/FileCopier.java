package training;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Multithreaded file copier
 * 
 * @version 0.1 29.07.2019
 * @author Oleg
 */
public class FileCopier {
	private static final String DOES_NOT_EXIST_MESSAGE = 
			"Source does not exist";
	private static final String NOT_A_FILE_MESSAGE = "Source is not a file";
	private static final String ALREADY_EXISTS_MESSAGE = 
			"Target already exists";
	
	private Path source;
	private Path target;

	/**
	 * Default constructor
	 */
	public FileCopier() {
		super();
	}

	/**
	 * Parameterized constructor
	 * 
	 * @param source <code>String</code>
	 * @param target <code>String</code>
	 */
	public FileCopier(String source, String target) {
		super();
		this.source = Paths.get(source);
		this.target = Paths.get(target);
	}

	/**
	 * Gets source file
	 * 
	 * @return <code>Path</code>
	 */
	public Path getSource() {
		return source;
	}

	/**
	 * Sets source file
	 * 
	 * @param source <code>String</code>
	 */
	public void setSource(String source) {
		this.source = Paths.get(source);
	}

	/**
	 * Gets target file
	 * 
	 * @return <code>Path</code>
	 */
	public Path getTarget() {
		return target;
	}

	/**
	 * Sets target file
	 * 
	 * @param target <code>String</code>
	 */
	public void setTarget(String target) {
		this.target = Paths.get(target);
	}

	/**
	 * Text representation of file copier
	 */
	@Override
	public String toString() {
		return "FileCopier [source=" + source + ", target=" + target + "]";
	}

	/**
	 * Validates set paths
	 * 
	 */
	private void validatePaths() {
		if (!Files.exists(source)) {
			throw new IllegalArgumentException(DOES_NOT_EXIST_MESSAGE);
		}
		
		if (Files.isDirectory(source)) {
			throw new IllegalArgumentException(NOT_A_FILE_MESSAGE);
		}

		//commented out for testing reasons
//		if (Files.exists(target)) {
//			throw new IllegalArgumentException(ALREADY_EXISTS_MESSAGE);
//		}
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
	 * Starts copying process
	 * 
	 * @throws IOException
	 */
	public void copy() throws IOException {
		validatePaths();
		
		int threadCount = getAvaliableProcessors();
		ExecutorService executor = Executors.newFixedThreadPool(threadCount);
		long fileSize = Files.size(source);
		CopyProcessObserver observer = new CopyProcessObserver(fileSize);

		long blockSize = (long) Math.ceil((double) fileSize / threadCount);
		long from = 0;		
		long to = blockSize;
		
		for (int i = 0; i < threadCount; i++) {		
			executor.submit(new Worker(observer, source, target, from, to));
			from += blockSize;
			to = (to + blockSize) < fileSize ? to + blockSize : fileSize;
		}

		executor.shutdown();
	}

	/**
	 * Class collecting and reporting information about copy process
	 * 
	 * @version 0.1 29.07.2019
 	 * @author Oleg
	 */
	private class CopyProcessObserver {
		private long total;
		private long copied;

		/**
		 * Default constructor
		 */
		public CopyProcessObserver() {
			super();
		}

		/**
		 * Parameterized constructor 
		 * 
		 * @param total <code>long</code> file length
		 */
		public CopyProcessObserver(long total) {
			super();
			this.total = total;
		}

		/**
		 * Gets total file length
		 * 
		 * @return <code>long</code>
		 */
		public long getTotal() {
			return total;
		}

		/**
		 * Sets total file length
		 *  
		 * @param total <code>long</code>
		 */
		public void setTotal(long total) {
			this.total = total;
		}

		/**
		 * Accepts and reports progress information received from a worker 
		 * 
		 * @param copied
		 */
		public synchronized void reportCopied(long copied) {
			this.copied += copied;
			System.out.println(this);
		}

		/**
		 * Text representation of observer
		 */
		@Override
		public String toString() {
			return "CopyProcessObserver [total=" + total + ", copied=" + copied 
					+ ", progress=" + String.format("%.6f%%", 
							(double) copied / total * 100) + "]";
		}
	}

	/**
	 * Class representing a single thread of multithreaded file copying process
	 * 
	 * @version 0.1 29.07.2019
 	 * @author Oleg
	 */
	private class Worker implements Runnable {
		private static final int BUFFER_SIZE = 1024;
		private CopyProcessObserver observer;
		private Path source;
		private Path target;
		private long from;
		private long to;

		/**
		 * Default constructor
		 */
		public Worker() {
			super();
		}

		/**
		 * Parameterized constructor
		 * 
		 * @param observer <code>CopyProcessObserver</code>
		 * @param source <code>Path</code>
		 * @param target <code>Path</code>
		 * @param from <code>long</code>
		 * @param to <code>long</code>
		 */
		public Worker(CopyProcessObserver observer, Path source, Path target, 
				long from, long to) {
			super();
			this.observer = observer;
			this.source = source;
			this.target = target;
			this.from = from;
			this.to = to;
		}		
		
		/**
		 * Gets observer
		 * 
		 * @return <code>CopyProcessObserver</code>
		 */
		public CopyProcessObserver getObserver() {
			return observer;
		}

		/**
		 * Sets observer
		 * 
		 * @param observer <code>CopyProcessObserver</code>
		 */
		public void setObserver(CopyProcessObserver observer) {
			this.observer = observer;
		}

		/**
		 * Get source
		 * 
		 * @return <code>Path</code>
		 */
		public Path getSource() {
			return source;
		}

		/**
		 * Sets source
		 * 
		 * @param source <code>Path</code>
		 */
		public void setSource(Path source) {
			this.source = source;
		}

		/**
		 * Gets target
		 * 
		 * @return <code>Path</code>
		 */
		public Path getTarget() {
			return target;
		}

		/**
		 * Sets target
		 * 
		 * @param target <code>Path</code>
		 */
		public void setTarget(Path target) {
			this.target = target;
		}

		/**
		 * Gets start position
		 * 
		 * @return <code>long</code>
		 */
		public long getFrom() {
			return from;
		}

		/**
		 * Sets start position
		 * 
		 * @param from <code>long</code>
		 */
		public void setFrom(long from) {
			this.from = from;
		}

		/**
		 * Gets end position
		 * 
		 * @return <code>long</code>
		 */
		public long getTo() {
			return to;
		}

		/**
		 * Sets end position
		 * 
		 * @param to <code>long</code>
		 */
		public void setTo(long to) {
			this.to = to;
		}		
		
		/**
		 * Text representation of worker
		 */
		@Override
		public String toString() {
			return "Worker [observer=" + observer + ", source=" + source 
					+ ", target=" + target + ", from=" + from + ", to=" + to 
					+ "]";
		}

		/**
		 * Copies given file part
		 */
		@Override
		public void run() {
			try (SeekableByteChannel in = 
					Files.newByteChannel(source, StandardOpenOption.READ)) {				
				ByteBuffer inBuffer = ByteBuffer.allocate(BUFFER_SIZE);
				byte[] outBytes = new byte[BUFFER_SIZE];
				int readByteCount;
				long writePosition = from;

				in.position(from);
				
				while ((readByteCount = in.read(inBuffer)) > 0 
						&& writePosition < to) {
					if (writePosition + readByteCount >= to) {
						readByteCount = (int) (to - writePosition);						
					}

					inBuffer.rewind();
					inBuffer.get(outBytes, 0, readByteCount);
					write(outBytes, writePosition, readByteCount);
					inBuffer.rewind();					
					writePosition += readByteCount;
					
					observer.reportCopied(readByteCount);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * Writes data block to file
		 * 
		 * @param array <code>byte[]</code>
		 * @param position <code>long</code>
		 * @param length <code>int</code>
		 * @throws IOException 
		 */
		private void write(byte[] array, long position, int length) 
				throws IOException {
			try (FileChannel out = (FileChannel) Files.newByteChannel(target,
					StandardOpenOption.WRITE,
					StandardOpenOption.CREATE, 
					StandardOpenOption.READ)) {
				
				MappedByteBuffer outBuffer = out.map(FileChannel.MapMode.READ_WRITE, 
						position, length);
				outBuffer.put(array, 0, length);
			}
		}
	}
}