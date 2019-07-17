package training;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

/**
 * Class copying files and folders from one folder to another 
 * 
 * @version 0.1 17.07.2019
 * @author Oleg
 */
public class Copier {
	private final static String NULL_FILE_EXCEPTION_MESSAGE = "Source and "
			+ "target may not be null";
	private final static String NOT_FOLDERS_EXCEPTION_MESSAGE = "Source and "
			+ "target must be folders";
	private final static String NULL_FILTER_EXCEPTION_MESSAGE = "FileFilter "
			+ "may not be null";
	private File source;
	private File target;
	private FileFilter filter;
	
	/**
	 * Default constructor
	 */
	public Copier() {
		super();
	}

	/**
	 * Parameterized constructor
	 * 
	 * @param source <code>File</code>
	 * @param target <code>File</code>
	 * @param filter <code>FileFilter</code>
	 */
	public Copier(File source, File target, FileFilter filter) {
		super();
		this.source = source;
		this.target = target;
		this.filter = filter;
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
	 * Sets target folder
	 * 
	 * @param target <code>File</code>
	 */
	public void setTarget(File target) {
		this.target = target;
	}

	/**
	 * Gets filter
	 * 
	 * @return <code>FileFilter</code>
	 */
	public FileFilter getFilter() {
		return filter;
	}

	/**
	 * Sets filter
	 * 
	 * @param filter <code>FileFilter</code>
	 */
	public void setFilter(FileFilter filter) {
		this.filter = filter;
	}

	/**
	 * Copies files and folders from source folder to target folder
	 * 
	 * @throws IOException
	 */
	public void copy() throws IOException {
		if (source == null || target == null) {
			throw new IllegalArgumentException(NULL_FILE_EXCEPTION_MESSAGE);
		}
		
		if (!source.isDirectory() || target.isFile()) {
			throw new IllegalArgumentException(NOT_FOLDERS_EXCEPTION_MESSAGE);
		}
		
		if (filter == null) {
			throw new IllegalArgumentException(NULL_FILTER_EXCEPTION_MESSAGE);			
		}
		
		copyFolder(source, target);
	}
	
	/**
	 * Recursively copies folder contents 
	 * 
	 * @param source <code>File</code>
	 * @param target <code>File</code>
	 * @throws IOException
	 */
	private void copyFolder(File source, File target) throws IOException {
		File[] files = source.listFiles(filter);
		
		if (!target.exists()) {
			target.mkdirs();
		}		
		
		for (File file : files) {			
			IOService.fileCopy(file, new File(target.getPath() + File.separator 
					+ file.getName()));
		}
		
		files = source.listFiles();
		
		for (File file : files) {
			if (file.isDirectory()) {
				copyFolder(file, new File(target.getPath() + File.separator 
						+ file.getName()));
			}
		}
	}	
}