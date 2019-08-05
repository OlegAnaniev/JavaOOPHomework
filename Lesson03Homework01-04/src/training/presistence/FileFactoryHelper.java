package training.presistence;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
 * Helper class providing common file storage functionality
 * 
 * @version 0.1 05.08.2019
 * @author Oleg
 */
public class FileFactoryHelper {
	public static final String NON_EXISTANT_GROUP_MESSAGE = "Group does not "
			+ "exist";
	public static final String NON_EXISTANT_STUDENT_MESSAGE = "Student does "
			+ "not exist";
	public static final String INVALID_DATA_FORMAT_MESSAGE = "Storage "
			+ "conatins data in invalid format";
	public static final SimpleDateFormat dateFormat = 
			new SimpleDateFormat("dd/MM/y");
	private static final String INVALID_PATHS_MESSAGE = "Given path does not"
			+ " exist or is invalid";	
	
	/**
	 * Private constructor to make instance creation impossible
	 */
	private FileFactoryHelper() {
		super();
	}

	/**
	 * Validates folders. Throws IllegalArgumentException if folder does not
	 * exist or is not a folder
	 * 
	 * @param folders <code>File...</code>
	 */
	public static void validateFolder(File... folders) {
		for (File folder : folders) {
			if (!folder.exists() || !folder.isDirectory()) {
				throw new IllegalArgumentException(INVALID_PATHS_MESSAGE);
			}	
		}				
	}
	
	/**
	 * Gets next free id depending on given path
	 * 
	 * @param path <code>File</code>
	 * @return <code>int</code>
	 */
	public static int getNextId(File path) {		
		String[] files = path.list();
		
		if (files.length == 0) {
			return 1;
		}
		
		Arrays.sort(files);
		int underscorePosition = files[files.length - 1].indexOf("_");
		
		int lastId;
		try {
			lastId = Integer.parseInt(files[files.length - 1]
					.substring(0, underscorePosition));
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(INVALID_DATA_FORMAT_MESSAGE);
		}			
		
		return lastId + 1;
	}		
	
	/**
	 * Gets file by path and id
	 * 
	 * @param path <code>File</code>
	 * @param id <code>int</code>
	 * @return <code>File</code>
	 */
	public static File getFile(File path, int id) {
		File[] files = path.listFiles();
		int underscorePosition;
		int currentId;
		
		for (File file : files) {
			underscorePosition = file.getName().indexOf("_");			
			
			try {
				currentId = Integer.parseInt(file.getName()
						.substring(0, underscorePosition));
				
				if (currentId == id) {
					return file;
				}				
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException(
						INVALID_DATA_FORMAT_MESSAGE);
			}
		}
		
		return null;			
	}	
	
	/**
	 * Gets entity file
	 * 
	 * @param entity <code>FileStorable</code>
	 * @param storage <code>File</code>
	 * @param extention <code>String</code>
	 * @return <code>File</code>
	 */
	public static File getFile(FileStorable entity, File storage, String extention) {
		File existingFile = getFile(storage, entity.getId());
		File newFile = new File(storage.getPath() + File.separator
				+ constructFileName(entity.getId(), entity.getFileName(), 
						extention));
		
		if (existingFile == null) {
			return newFile;
		} else if (!existingFile.equals(newFile)) {
			existingFile.renameTo(newFile);
		}
		
		return existingFile;
	}
	
	/**
	 * Constructs file name
	 * 
	 * @param id <code>int</code>
	 * @param name <code>String</code>
	 * @param extention <code>String</code>
	 * @return <code>String</code>
	 */
	public static String constructFileName(int id, String name, String extention) {
		StringBuilder sb = new StringBuilder();
		sb.append(id);
		sb.append("_");
		sb.append(name);
		sb.append(extention);
		
		return sb.toString();
	}
}