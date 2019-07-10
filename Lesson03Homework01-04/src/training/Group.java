package training;

import java.util.Arrays;

import training.exceptions.InvalidStudentPositionException;

/**
 * Class representing a group
 * 
 * @version 0.1 10.07.2019
 * @author Oleg
 */
public class Group {
	private final static int GROUP_SIZE = 10;
	private String groupName;
	private Student[] students;
	
	/**
	 * Creates default group
	 */
	public Group() {
		super();
		this.groupName = "Default";
		this.students = new Student[GROUP_SIZE];
	}
	
	/**
	 * Creates group with given name
	 * 
	 * @param groupName <code>String</code>
	 */
	public Group(String groupName) {
		this();
		this.groupName = groupName;
	}

	/**
	 * Gets group name
	 * 
	 * @return <code>String</code>
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * Sets group name
	 * 
	 * @param groupName <code>String</code>
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * Gets the array of students
	 * 
	 * @return <code>Student[]</code>
	 */
	public Student[] getStudents() {
		return Arrays.copyOf(students, students.length);
	}

	/**
	 * Sets the array of students
	 * 
	 * @param groupMembers <code>Student[]</code>
	 */
	public void setStudents(Student[] students) {
		this.students = Arrays.copyOf(students, students.length);
	}	
	
	/**
	 * Check if given position in array of students is valid
	 * 
	 * @param position <code>int</code>
	 * @throws InvalidStudentPositionException
	 */
	public void checkPosition(int position) throws InvalidStudentPositionException {
		if (position < 0 || position > students.length) {
			throw new InvalidStudentPositionException();
		}
	}
	
	/**
	 * Sets a student to a given position
	 * 
	 * @param student <code>Student</code>
	 * @param position <code>int</code>
	 * @throws InvalidStudentPositionException 
	 */
	public void setStudent(Student student, int position) throws InvalidStudentPositionException {		
		checkPosition(position);
		students[position] = student;
	}
	
	/**
	 * Removes a student from a given position
	 * 
	 * @param position <code>int</code>
	 * @throws InvalidStudentPositionException
	 */
	public void removeStudent(int position) throws InvalidStudentPositionException {
		checkPosition(position);		
		students[position] = null;
	}
	
	/**
	 * Gets a student by last name
	 * 
	 * @param lastName <code>String</code>
	 * @return <code>Student</code>
	 */
	public Student getStudent(String lastName) {
		for (Student student : students) {
			if (student.getLastName().equals(lastName)) {
				return student;
			}			
		}
		
		return null;
	}

	/**
	 * Gets text representation of the group
	 */
	@Override
	public String toString() {
		StringBuilder text = new StringBuilder("Group " + groupName 
				+ ":" + System.lineSeparator());
		Student[] array;
		int counter = 0;
		
		for (Student student : students) {
			if (student != null) {
				counter++;
			}
		}		
		
		array = new Student[counter];
		counter = 0;
		for (Student student : students) {
			if (student != null) {
				array[counter] = student;
				counter++;
			}
		}	
		
		quickSort(array, 0, array.length - 1);
		
		
		for (Student student : array) {
			text.append(student + System.lineSeparator());
		}
				
		return text.toString();
	}
	
	/**
	 * Sorts array of student by last name ascending
	 * 
	 * @param array <code>Student[]</code>
	 * @param start <code>int</code>
	 * @param end <code>int</code>
	 */
	private void quickSort(Student[] array, int start, int end) {
		int baseIndex = (start + end) / 2;
		String base = array[baseIndex].getLastName();
		int i = start;
		int j = end;
		Student temp;
		
		while (i <= j) {			
			while (array[i].getLastName().compareTo(base) < 0) {				
				i++;
			}			
			while (array[j].getLastName().compareTo(base) > 0) {
				j--;			
			}
			
			if (i <= j) {
				temp = array[i];
				array[i] = array[j];
				array[j] = temp;				
				i++;
				j--;
			}
		}
		
		if (start < j) {
			quickSort(array, start, j);
		}		
		if (end > i) {
			quickSort(array, i, end);
		}		
	}
}