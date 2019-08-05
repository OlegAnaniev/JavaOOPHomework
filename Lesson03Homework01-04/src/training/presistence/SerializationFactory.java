package training.presistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import training.Group;
import training.Student;

/**
 * University data serializer
 * 
 * @version 0.1 05.08.2019
 * @author Oleg
 */
public class SerializationFactory implements UniversityDAO {
	private static final String BASE_STORAGE_PATH = "datastorage" 
			+ File.separator + "serialization";
	private static final String GROUP_STORAGE_PATH = "groups";
	private static final String STUDENT_STORAGE_PATH = "students";
	private static final String GROUP_FILE_EXTENSION = ".group";
	private static final String STUDENT_FILE_EXTENSION = ".student";
	
	private File studentStorage;
	private File groupStorage;

	/**
	 * Default constructor
	 */
	public SerializationFactory() {
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
		
		try (ObjectInputStream input = 
				new ObjectInputStream(new FileInputStream(file))) {
			return (Group) input.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
		
		return null;
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
				GROUP_FILE_EXTENSION);
		
		try (ObjectOutputStream output = 
				new ObjectOutputStream(new FileOutputStream(file))) {
			output.writeObject(group);				
		} catch (IOException e) {
			return -1;
		}
		
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
		
		try (ObjectInputStream input = 
				new ObjectInputStream(new FileInputStream(file))) {
			return (Student) input.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
		
		return null;
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
				STUDENT_FILE_EXTENSION);
		
		try (ObjectOutputStream output = 
				new ObjectOutputStream(new FileOutputStream(file))) {
			output.writeObject(student);				
		} catch (IOException e) {
			return -1;
		}
		
		return student.getId();
	}

	@Override
	public boolean deleteStudent(Student student) {
		FileFactoryHelper.validateFolder(studentStorage);
		
		File studentFile = FileFactoryHelper.getFile(studentStorage, 
				student.getId());			
		return studentFile.delete();
	}	
}