package training;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class monitoring changes within a given folder 
 * 
 * @version 0.1 20.07.2019
 * @author Oleg
 */
public class FolderWatcher implements Runnable {
	private final static String ADDED_MESSAGE = "New %s added: %s - %s";
	private final static String REMOVED_MESSAGE = "Deleted %s: %s - %s";
	private final static String FILE = "file";
	private final static String FOLDER = "folder";
	private final static String DATE_FORMAT = "dd/MM/y hh:mm:ss";
	
	private File folder;	
	private boolean withSubfolders;
	
	private SimpleDateFormat dateFormat;	
	private List<File> list;
	private List<File> newList;
	private Date lastCheckTime;
	
	/**
	 * Default constructor
	 */
	public FolderWatcher() {
		super();
	}	
	
	/**
	 * Constructor accepting folder
	 * 
	 * @param folder <code>File</code>
	 */
	public FolderWatcher(File folder) {
		super();
		this.folder = folder;
		this.dateFormat = new SimpleDateFormat(DATE_FORMAT);
	}
	
	/**
	 * Constructor accepting folder path
	 * 
	 * @param path <code>String</code>
	 */
	public FolderWatcher(String path) {
		this(new File(path));
	}

	/**
	 * Constructor accepting folder and subfolders parameter
	 * 
	 * @param folder <code>File</code>
	 * @param withSubfolders <code>boolean</code>
	 */
	public FolderWatcher(File folder, boolean withSubfolders) {
		this(folder);
		this.withSubfolders = withSubfolders;
	}

	/**
	 * Constructor accepting folder path and subfolders parameter
	 * 
	 * @param path <code>String</code>
	 * @param withSubfolders <code>boolean</code>
	 */
	public FolderWatcher(String path, boolean withSubfolders) {
		this(new File(path), withSubfolders);
	}
	
	/**
	 * Gets monitored folder
	 * 
	 * @return <code>File</code>
	 */
	public File getFolder() {
		return folder;
	}

	/**
	 * Sets monitored folder
	 * 
	 * @param folder <code>File</code>
	 */
	public void setFolder(File folder) {
		this.folder = folder;
	}

	/**
	 * Get subfolders parameter
	 * 
	 * @return <code>boolean</code>
	 */
	public boolean isWithSubfolders() {
		return withSubfolders;
	}

	/**
	 * Sets subfolders parameter
	 * 
	 * @param withSubfolders <code>boolean</code>
	 */
	public void setWithSubfolders(boolean withSubfolders) {
		this.withSubfolders = withSubfolders;
	}

	/**
	 * Monitors folder
	 */
	@Override
	public void run() {		
		if (list == null) {
			list = new ArrayList<File>();
			getFolderContents(folder, list);			
			return;
		}		
		
		lastCheckTime = new Date();		
		newList = new ArrayList<File>();
		getFolderContents(folder, newList);		
		
		for (File newFile : newList) {			
			checkAdded(newFile);
		}
		
		for (File file : list) {
			checkRemoved(file);
		}
		
		list = newList;		
	}
	
	/**
	 * Gets contents of a given folder and puts it into a given list
	 * 
	 * @param folder <code>File</code>
	 * @param list <code>List&lt;File&gt;</code>
	 */
	private void getFolderContents(File folder, List<File> list) {
		File[] files = folder.listFiles();
		
		for (File file : files) {			
			list.add(file);
			
			if (withSubfolders && file.isDirectory()) {
				getFolderContents(file, list);
			}
		}
	}
	
	/**
	 * Checks if a file/folder was added
	 * 
	 * @param newFile <code>File</code>
	 */
	private void checkAdded(File newFile) {
		for (File file : list) {
			if (newFile.equals(file)) {
				return;
			}
		}
		
		System.out.println(String.format(ADDED_MESSAGE, 
				newFile.isDirectory() ? FOLDER : FILE,
				newFile.getPath(),
				dateFormat.format(lastCheckTime)
				));
	}
	
	/**
	 * Checks if a file/folder was removed 
	 * 
	 * @param file <code>File</code>
	 */
	private void checkRemoved(File file) {
		for (File newFile : newList) {
			if (file.equals(newFile)) {
				return;
			}
		}
		
		System.out.println(String.format(REMOVED_MESSAGE, 
				file.isDirectory() ? FOLDER : FILE,
				file.getPath(),
				dateFormat.format(lastCheckTime)
				));
	}	
}