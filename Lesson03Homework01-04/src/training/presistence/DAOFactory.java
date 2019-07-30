package training.presistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import training.Faculty.FacultyName;
import training.Group;
import training.Human.Gender;
import training.Student.TeachingMethod;
import training.Student;
import training.exceptions.TooManyStudentsException;

/**
 * Class providing functionality to save and load university data
 * 
 * @version 0.1 18.07.2019
 * @author Oleg
 */
public class DAOFactory {
	public static final int FILESYSTEM = 1;	
	
	/*
	 * Private constructor to make instance creation impossible
	 */
	private DAOFactory() {
		super();
	}

	/**
	 * Returns requested saver/loader instance
	 * 
	 * @param factory <code>int</code> type
	 * @return <code>UniversityDAO</code>
	 */
	public static UniversityDAO getFactory(int factory) {
		switch (factory) {
		case FILESYSTEM:
			return new FilesystemFactory();
		default:
			break;
		}
		
		return null;
	}
	
	/**
	 * University data saver/loader interface
	 * 
	 * @version 0.1 18.07.2019
	 * @author Oleg
	 */
	public interface UniversityDAO {
		/**
		 * Gets group from storage by id
		 * 
		 * @param id <code>int</code>
		 * @return <code>Group</code>
		 */
		public Group getGroup(int id);
		
		/**
		 * Inserts group into storage
		 * 
		 * @param group <code>Group</code>
		 * @return <code>int</code> id
		 */
		public int insertGroup(Group group);
		
		/**
		 * Updates existing group in storage
		 * 
		 * @param group <code>Group</code>
		 * @return <code>int</code> id
		 */
		public int updateGroup(Group group);
		
		/**
		 * Deletes group from storage
		 * 
		 * @param group <code>Group</code>
		 * @return <code>boolean</code> true if deletion successful and false 
		 * otherwise
		 */
		public boolean deleteGroup(Group group);
		
		/**
		 * Gets student from storage by id
		 * 
		 * @param id <code>int</code>
		 * @return <code>Student</code>
		 */
		public Student getStudent(int id);
		
		/**
		 * Inserts student into storage
		 * 
		 * @param student <code>Student</code>
		 * @return <code>int</code> id
		 */
		public int insertStudent(Student student);
		
		/**
		 * Updates existing student in storage
		 * 
		 * @param student <code>Student</code>
		 * @return <code>int</code> id
		 */
		public int updateStudent(Student student);
		
		/**
		 * Deletes student from storage
		 * 
		 * @param student <code>Student</code>
		 * @return <code>boolean</code> true if deletion successful and false 
		 * otherwise
		 */
		public boolean deleteStudent(Student student);
	}
	
	private static class FilesystemFactory implements UniversityDAO {		
		private static final String BASE_STORAGE_PATH = "datastorage";
		private static final String GROUP_STORAGE_PATH = "groups";
		private static final String STUDENT_STORAGE_PATH = "students";
		private static final String INVALID_PATHS_MESSAGE = "Given paths do "
				+ "not exist or invalid";
		private static final String INVALID_DATA_FORMAT_MESSAGE = "Storage "
				+ "conatins data in invalid format";
		private static final String GROUP_FILE_EXTENSION = ".group";
		private static final String STUDENT_FILE_EXTENSION = ".student";
		private static final SimpleDateFormat dateFormat = 
				new SimpleDateFormat("dd/MM/y");
		
		/**
		 * Default constructor
		 */
		public FilesystemFactory() {
			super();
		}

		/**
		 * Validates paths. Throws IllegalArgumentException if paths are invalid
		 */
		private void validatePaths() {
			File storage = new File(BASE_STORAGE_PATH);
			File groups = new File(BASE_STORAGE_PATH + File.separator
					+ GROUP_STORAGE_PATH);
			File students = new File(BASE_STORAGE_PATH + File.separator
					+ STUDENT_STORAGE_PATH);
			
			if (!storage.exists() || !storage.isDirectory()
					|| !groups.exists() || !groups.isDirectory()
					|| !students.exists() || !students.isDirectory()) {
				throw new IllegalArgumentException(INVALID_PATHS_MESSAGE);
			}			
		}
		
		/**
		 * Gets next free group id
		 * 
		 * @return <code>int</code>
		 */
		private int getNextGroupId() {
			File groups = new File(BASE_STORAGE_PATH + File.separator
					+ GROUP_STORAGE_PATH);
			return getNextId(groups);
		}
		
		/**
		 * Gets next free student id
		 * 
		 * @return <code>int</code>
		 */
		private int getNextStudentId() {
			File students = new File(BASE_STORAGE_PATH + File.separator
					+ STUDENT_STORAGE_PATH);
			return getNextId(students);
		}
		
		/**
		 * Gets next free id depending on given path
		 * 
		 * @param path <code>File</code>
		 * @return <code>int</code>
		 */
		private int getNextId(File path) {
			validatePaths();
			
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
		private File getFileByID(File path, int id) {
			validatePaths();
			
			File[] groups = path.listFiles();
			
			for (File group : groups) {
				int underscorePosition = group.getName().indexOf("_");
				
				int currentId;
				try {
					currentId = Integer.parseInt(group.getName()
							.substring(0, underscorePosition));
				} catch (NumberFormatException e) {
					throw new IllegalArgumentException(
							INVALID_DATA_FORMAT_MESSAGE);
				}				
				
				if (currentId == id) {
					return group;
				}
			}
			
			return null;			
		}
		
		@Override
		public Group getGroup(int id) {
			validatePaths();
			
			File path = new File(BASE_STORAGE_PATH + File.separator 
					+ GROUP_STORAGE_PATH);
			File groupFile = getFileByID(path, id);
			String groupName = groupFile.getName().substring(
					groupFile.getName().indexOf("_") + 1,
					groupFile.getName().lastIndexOf(GROUP_FILE_EXTENSION));			
			Group group = new Group(groupName);
			String text; 
			int studentId;
			Student student;
			
			try (BufferedReader reader = new BufferedReader(
					new FileReader(groupFile))) {
				
				while ((text = reader.readLine()) != null) {
					studentId = Integer.parseInt(text);
					student = getStudent(studentId);
					group.setStudent(student);	
				}				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (TooManyStudentsException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException(
						INVALID_DATA_FORMAT_MESSAGE);
			}			
			
			return group;
		}

		@Override
		public int insertGroup(Group group) {
			validatePaths();
			
			int groupId;			
			if (group.getId() == 0) {
				groupId = getNextGroupId();				
				group.setId(groupId);
			} else {
				groupId = group.getId();
			}			
			
			File groupFile = new File(BASE_STORAGE_PATH + File.separator 
					+ GROUP_STORAGE_PATH + File.separator 
					+ groupId + "_" + group.getGroupName() 
					+ GROUP_FILE_EXTENSION);
			
			try (PrintWriter writer = new PrintWriter(groupFile)) {
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
			
			return groupId;
		}		
		
		@Override
		public int updateGroup(Group group) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public boolean deleteGroup(Group group) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Student getStudent(int id) {
			validatePaths();
			
			File path = new File(BASE_STORAGE_PATH + File.separator 
					+ STUDENT_STORAGE_PATH);
			File studentFile = getFileByID(path, id);						
			Student student = null;
			
			try (BufferedReader reader = new BufferedReader(
					new FileReader(studentFile))){
				
				String text = reader.readLine();
				String[] params = text.split("[,]");
				Calendar calendar = Calendar.getInstance();
				
				calendar.setTime(dateFormat.parse(params[3]));
				student = new Student(
						params[0],
						params[1],
						Gender.valueOf(params[2]),
						calendar,
						FacultyName.valueOf(params[4]),
						TeachingMethod.valueOf(params[5]),
						params[6],
						params[7]);							
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				throw new IllegalArgumentException(INVALID_DATA_FORMAT_MESSAGE);
			}			
			return student;
		}

		@Override
		public int insertStudent(Student student) {
			validatePaths();
			
			int studentId;			
			if (student.getId() == 0) {
				studentId = getNextStudentId();				
				student.setId(studentId);
			} else {
				studentId = student.getId();
			}
			
			File studentFile = new File(BASE_STORAGE_PATH + File.separator 
					+ STUDENT_STORAGE_PATH + File.separator 
					+ studentId + "_" + student.getLastName() 
					+ STUDENT_FILE_EXTENSION);
			
			try (PrintWriter writer = new PrintWriter(studentFile)) {				
				writer.println(student.getFirstName() + ","
						+ student.getLastName() + ","
						+ student.getGender() + ","
						+ dateFormat.format(student.getBirthdate().getTime()) + ","
						+ student.getFacultyName() + ","
						+ student.getTeachingMethod() + ","
						+ student.getGroupName() + ","
						+ student.getIdNumber() + ","
						+ student.getRecordBookNumber());			
			} catch (IOException e) {
				return -1;
			}
			
			return studentId;
		}		
		
		@Override
		public int updateStudent(Student student) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public boolean deleteStudent(Student student) {
			// TODO Auto-generated method stub
			return false;
		}		
	}
}