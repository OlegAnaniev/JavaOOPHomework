package training.presistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import training.Group;
import training.Student;

/**
 * University data json saver/loader
 * 
 * @version 0.1 05.08.2019
 * @author Oleg
 */
public class GSONFactory implements UniversityDAO {
	private static final String BASE_STORAGE_PATH = "datastorage" 
			+ File.separator + "gson";
	private static final String GROUP_STORAGE_PATH = "groups";
	private static final String STUDENT_STORAGE_PATH = "students";
	private static final String FILE_EXTENSION = ".json";

	private File groupStorage;
	private File studentStorage;
	
	/**
	 * Default constructor
	 */
	public GSONFactory() {
		super();
		studentStorage = new File(BASE_STORAGE_PATH + File.separator 
				+ STUDENT_STORAGE_PATH);
		groupStorage = new File(BASE_STORAGE_PATH + File.separator 
				+ GROUP_STORAGE_PATH);
	}
	
	@Override
	public Group getGroup(int id) {
		FileFactoryHelper.validateFolder(groupStorage);		
		File file = FileFactoryHelper.getFile(groupStorage, id);
		
		return (Group) loadFromFile(file, Group.class);
	}

	@Override
	public int insertGroup(Group group) {
		FileFactoryHelper.validateFolder(groupStorage);
		
		if (group.getId() == 0) {
			group.setId(FileFactoryHelper.getNextId(groupStorage));
		} 		
		
		return updateGroup(group);
	}

	@Override
	public int updateGroup(Group group) {
		FileFactoryHelper.validateFolder(groupStorage);
		
		if (group.getId() == 0) {
			throw new IllegalArgumentException(
					FileFactoryHelper.NON_EXISTANT_GROUP_MESSAGE);
		}
		
		File file = FileFactoryHelper.getFile(group, groupStorage, 
				FILE_EXTENSION);
		
		saveToFile(group, file);
		
		return group.getId();
	}

	@Override
	public boolean deleteGroup(Group group) {
		FileFactoryHelper.validateFolder(groupStorage);
		
		File file = FileFactoryHelper.getFile(groupStorage, group.getId());			
		
		return file.delete();
	}

	@Override
	public Student getStudent(int id) {
		FileFactoryHelper.validateFolder(studentStorage);
		
		File file = FileFactoryHelper.getFile(studentStorage, id);
		
		Student student = (Student) loadFromFile(file, Student.class);
		
		return student;
	}

	@Override
	public int insertStudent(Student student) {
		FileFactoryHelper.validateFolder(studentStorage);
		
		if (student.getId() == 0) {
			student.setId(FileFactoryHelper.getNextId(studentStorage));
		} 

		return updateStudent(student);
	}

	@Override
	public int updateStudent(Student student) {
		FileFactoryHelper.validateFolder(studentStorage);
		
		if (student.getId() == 0) {
			throw new IllegalArgumentException(
					FileFactoryHelper.NON_EXISTANT_STUDENT_MESSAGE);
		}
		
		File file = FileFactoryHelper.getFile(student, studentStorage, 
				FILE_EXTENSION);
		
		saveToFile(student, file);
		
		return student.getId();
	}

	@Override
	public boolean deleteStudent(Student student) {
		FileFactoryHelper.validateFolder(studentStorage);
		
		File file = FileFactoryHelper.getFile(studentStorage, student.getId());			
		return file.delete();
	}
	
	/**
	 * Saves entity to file
	 * 
	 * @param object <code>Object</code>
	 * @param file <code>File</code>
	 */
	private void saveToFile(Object object, File file) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(object);
		
		try (PrintWriter writer = new PrintWriter(file)) {
			writer.println(json);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Loads entity from file
	 * 
	 * @param file <code>File</code>
	 * @param cls <code>Class&lt;?&gt;</code>
	 * @return <code>Object</code>
	 */
	private Object loadFromFile(File file, Class<?> cls) {
		Gson gson = new Gson();
		Object object = null;
		
		try {
			object = gson.fromJson(new FileReader(file), cls);
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return object;
	}
}