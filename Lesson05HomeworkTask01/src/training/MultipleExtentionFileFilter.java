package training;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

/**
 * FileFilter implementation accepting multiple file extensions
 * 
 * @version 0.1 17.07.2019
 * @author Oleg
 */
public class MultipleExtentionFileFilter implements FileFilter {
	private String[] extensions;

	/**
	 * Default constructor. Filter is set to accept all files
	 */
	public MultipleExtentionFileFilter() {
		super();
		this.extensions = new String[0];
	}
	
	/**
	 * Constructor accepting file extensions
	 * 
	 * @param extentions <code>String[]</code>
	 */
	public MultipleExtentionFileFilter(String... extentions) {
		super();
		this.extensions = Arrays.copyOf(extentions, extentions.length);
	}
	
	/**
	 * Gets the array of extensions
	 * 
	 * @return <code>String[]</code>
	 */
	public String[] getExtentions() {
		return Arrays.copyOf(extensions, extensions.length);
	}

	/**
	 * Sets the array of extensions
	 * 
	 * @param extentions <code>String[]</code>
	 */
	public void setExtentions(String[] extentions) {
		this.extensions = Arrays.copyOf(extentions, extentions.length);
	}

	/**
	 * Checks if the file extension is acceptable
	 */
	@Override
	public boolean accept(File pathname) {		
		if (!pathname.isFile()) {
			return false;
		}
		
		if (extensions.length == 0) {
			return true;
		}
		
		int pointIndex = pathname.getName().lastIndexOf(".");
		
		if (pointIndex == -1) {
			return isAcceptable("");
		}
		
		String currentExtention = pathname.getName().substring(pointIndex + 1);		
		return isAcceptable(currentExtention);
	}
	
	/**
	 * Compares current extension with the list of acceptable extensions
	 * 
	 * @param currentExtention <code>String</code>
	 * @return <code>boolean</code>
	 */
	private boolean isAcceptable(String currentExtention) {
		for (String extension : extensions) {
			if (currentExtention.equalsIgnoreCase(extension)) {
				return true;
			}
		}
		
		return false;
	}	
}