package training.presistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Calendar;

import training.Faculty.FacultyName;
import training.Group;
import training.Human.Gender;
import training.Student;
import training.Student.TeachingMethod;
import training.exceptions.TooManyStudentsException;

/**
 * University data file saver/loader
 * 
 * @version 0.2 05.08.2019
 * @author Oleg
 */
public class FilesystemFactory implements UniversityDAO {		
	private static final String BASE_STORAGE_PATH = "datastorage" 
			+ File.separator + "filesystem";
	private static final String GROUP_STORAGE_PATH = "groups";
	private static final String STUDENT_STORAGE_PATH = "students";
	private static final String GROUP_FILE_EXTENSION = ".group";
	private static final String STUDENT_FILE_EXTENSION = ".student";	
	
	private File studentStorage;
	private File groupStorage;
	
	/**
	 * Default constructor
	 */
	public FilesystemFactory() {
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
		
		if (file == null) {
			throw new IllegalArgumentException(
					FileFactoryHelper.NON_EXISTANT_GROUP_MESSAGE);
		}
		
		String groupName = file.getName().substring(
				file.getName().indexOf("_") + 1,
				file.getName().lastIndexOf(GROUP_FILE_EXTENSION));			
		Group group = new Group(id, groupName);
		String text;
		int studentId;
		Student student;
		
		try (BufferedReader reader = new BufferedReader(
				new FileReader(file))) {
			
			while ((text = reader.readLine()) != null) {
				studentId = Integer.parseInt(text);
				student = getStudent(studentId);					
				group.setStudent(student);
			}				
		} catch (IOException | TooManyStudentsException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(
					FileFactoryHelper.INVALID_DATA_FORMAT_MESSAGE);
		}			
		
		return group;
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
		
		try (PrintWriter writer = new PrintWriter(file)) {
			Student[] students = group.getStudents();
			for (Student student : students) {
				if (student != null) {						
					if (insertStudent(student) == -1) {
						return -1;
					}
					writer.println(student.getId());
				}
			}				
		} catch (IOException e) {
			return -1;
		}		
		
		return group.getId();
	}

	@Override
	public boolean deleteGroup(Group group) {
		FileFactoryHelper.validateFolder(groupStorage);
		
		Student[] students = group.getStudents();
		boolean result;
		
		for (Student student : students) {
			if (!(student == null)) {
				result = deleteStudent(student);
				if (!result) {
					return false;
				}
			}				
		}
		
		File groupFile = FileFactoryHelper.getFile(groupStorage, 
				group.getId());			
		return groupFile.delete();
	}

	@Override
	public Student getStudent(int id) {
		FileFactoryHelper.validateFolder(studentStorage);

		File studentFile = FileFactoryHelper.getFile(studentStorage, id);			
		
		if (studentFile == null) {
			throw new IllegalArgumentException(
					FileFactoryHelper.NON_EXISTANT_STUDENT_MESSAGE);
		}		
		
		Student student = null;
		
		try (BufferedReader reader = new BufferedReader(
				new FileReader(studentFile))){
			
			String text = reader.readLine();
			String[] params = text.split("[,]");
			Calendar calendar = Calendar.getInstance();
			
			calendar.setTime(FileFactoryHelper.dateFormat.parse(params[3]));
			student = new Student(id, params[0], params[1], 
					Gender.valueOf(params[2]), calendar, 
					FacultyName.valueOf(params[4]), 
					TeachingMethod.valueOf(params[5]), params[7], params[8]);				
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			throw new IllegalArgumentException(
					FileFactoryHelper.INVALID_DATA_FORMAT_MESSAGE);
		}			
		
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
		
		File studentFile = FileFactoryHelper.getFile(student, studentStorage, 
				STUDENT_FILE_EXTENSION);
		
		try (PrintWriter writer = new PrintWriter(studentFile)) {				
			writer.println(student.getFirstName() + ","
					+ student.getLastName() + ","
					+ student.getGender() + ","
					+ FileFactoryHelper.dateFormat.format(student.
							getBirthdate().getTime()) + ","
					+ student.getFacultyName() + ","
					+ student.getTeachingMethod() + ","
					+ student.getGroupName() + ","
					+ student.getIdNumber() + ","
					+ student.getRecordBookNumber());			
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