package training;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Class providing multithreaded folder copying functionality
 * 
 * @version 0.1 19.07.2019
 * @author Oleg
 */
public class FolderCopier {
	private File source;
	private File target;
	private ExecutorService executor;
	private ArrayList<Future<CopyResult>> results;
	
	/**
	 * Default constructor
	 */
	public FolderCopier() {
		super();
	}	
	
	/**
	 * Parameterized constructor accepting folder paths
	 * 
	 * @param source <code>String</code>
	 * @param target <code>String</code>
	 */
	public FolderCopier(String source, String target) {
		super();
		this.source = new File(source);
		this.target = new File(target);
	}	

	/**
	 * Parameterized constructor accepting folders as File class instances
	 * 
	 * @param source <code>File</code>
	 * @param target <code>File</code>
	 */
	public FolderCopier(File source, File target) {
		super();
		this.source = source;
		this.target = target;
	}

	/**
	 * Gets source folder
	 * 
	 * @return <code>File</code>
	 */
	public File getSource() {
		return source;
	}

	/**
	 * Sets source folder
	 * 
	 * @param source <code>File</code>
	 */
	public void setSource(File source) {
		this.source = source;
	}

	/**
	 * Gets target folder
	 * 
	 * @return <code>File</code>
	 */
	public File getTarget() {
		return target;
	}

	/**
	 * Gets target folder
	 * 
	 * @param target <code>File</code>
	 */
	public void setTarget(File target) {
		this.target = target;
	}
	
	/**
	 * Guides copying process
	 * 
	 * @return <code>List&lt;File&gt;</code> list of files failed to copy
	 */
	public List<File> copy() {
		executor = Executors.newFixedThreadPool(
				getProcessorCount());
		results = new ArrayList<Future<CopyResult>>();
		
		copyFolder(source, target);

		executor.shutdown();
		return checkResults();
	}
	
	/**
	 * Gets count of available processors
	 * 
	 * @return <code>int</code>
	 */
	private int getProcessorCount() {
		Runtime runtime = Runtime.getRuntime();
		return runtime.availableProcessors();
	}	
	
	/**
	 * Copies a folder
	 * 
	 * @param source <code>File</code>
	 * @param target <code>File</code>
	 */
	private void copyFolder(File source, File target) {
		File[] files = source.listFiles(file -> file.isFile());		
		
		if (!target.exists()) {
			target.mkdirs();
		}
		
		for (File file : files) {
			results.add(executor.submit(new Worker(file, 
					new File(target + File.separator + file.getName()))));
		}
		
		copySubfolders(source, target);
	}
	
	/**
	 * Starts copying process for subfolders of a given folder
	 * 
	 * @param source <code>File</code>
	 * @param target <code>File</code>
	 */
	private void copySubfolders(File source, File target) {
		File[] folders = source.listFiles(folder -> folder.isDirectory());
		
		for (File folder : folders) {
			copyFolder(folder, new File(target + File.separator 
					+ folder.getName()));
		}		
	}	
	
	/**
	 * Checks results of multithreaded copying process
	 * 
	 * @return <code>List&lt;File&gt;</code> list of files failed to copy
	 */
	private List<File> checkResults() {
		ArrayList<File> fails = new ArrayList<File>();
		CopyResult temp;
	
		for (Future<CopyResult> result : results) {
			try {
				temp = result.get();
				if (!temp.isCopied) {
					fails.add(temp.getFile());
				}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}			
		}	
		
		return fails;
	}
	
	/**
	 * Class representing a thread in a multithreaded folder copying process
	 * 
	 * @version 0.1 19.07.2019
 	 * @author Oleg
	 */
	private class Worker implements Callable<CopyResult> {
		private File source;
		private File target;
		
		/**
		 * Default constructor
		 */
		@SuppressWarnings("unused")
		public Worker() {
			super();
		}

		/**
		 * Parameterized constructor
		 * 
		 * @param source <code>File</code>
		 * @param target <code>File</code>
		 */
		public Worker(File source, File target) {
			super();
			this.source = source;
			this.target = target;
		}

		/**
		 * Gets source file
		 * 
		 * @return <code>File</code>
		 */
		@SuppressWarnings("unused")
		public File getSource() {
			return source;
		}

		/**
		 * Sets source file
		 * 
		 * @param source <code>File</code>
		 */
		@SuppressWarnings("unused")
		public void setSource(File source) {
			this.source = source;
		}

		/**
		 * Gets target file
		 * 
		 * @return <code>File</code>
		 */
		@SuppressWarnings("unused")
		public File getTarget() {
			return target;
		}

		/**
		 * Sets target file
		 * 
		 * @param target <code>File</code>
		 */
		@SuppressWarnings("unused")
		public void setTarget(File target) {
			this.target = target;
		}

		/**
		 * Copies a file
		 */
		@Override
		public CopyResult call() throws Exception {
			CopyResult result = new CopyResult(source, true);
			
			try {
				IOService.fileCopy(source, target);
			} catch (IOException e) {
				result.setCopied(false);
			}
			
			return result;			
		}		
	}	
	
	/**
	 * Class representing results of a copying process
	 * 
	 * @version 0.1 20.07.2019
 	 * @author Oleg
	 */
	private class CopyResult {
		private File file;
		private boolean isCopied;
		
		/**
		 * Default constructor
		 */
		@SuppressWarnings("unused")
		public CopyResult() {
			super();
		}
		
		/**
		 * Parameterized constructor
		 * 
		 * @param file <code>File</code>
		 * @param isCopied <code>File</code>
		 */
		public CopyResult(File file, boolean isCopied) {
			super();
			this.file = file;
			this.isCopied = isCopied;
		}
		
		/**
		 * Gets file
		 * 
		 * @return <code>File</code>
		 */
		public File getFile() {
			return file;
		}
		
		/**
		 * Sets file
		 * 
		 * @param file <code>File</code>
		 */
		@SuppressWarnings("unused")
		public void setFile(File file) {
			this.file = file;
		}
		
		/**
		 * Indicates if the file was copied successfully
		 * 
		 * @return <code>boolean</code> true if the file was copied 
		 * successfully and false otherwise
		 */
		@SuppressWarnings("unused")
		public boolean isCopied() {
			return isCopied;
		}
		
		/**
		 * Sets copying process result status
		 * 
		 * @param isCopied <code>boolean</code>
		 */
		public void setCopied(boolean isCopied) {
			this.isCopied = isCopied;
		}		
	}
}