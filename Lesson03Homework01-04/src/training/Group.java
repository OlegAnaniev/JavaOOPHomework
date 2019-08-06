package training;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import training.Human.Gender;
import training.exceptions.TooManyStudentsException;
import training.presistence.FileStorable;

/**
 * Class representing a group
 * 
 * @version 0.3 05.08.2019
 * @author Oleg
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "group")
public class Group implements MilitaryManager, Serializable, FileStorable {
	private static final long serialVersionUID = 1L;
	private final static int GROUP_SIZE = 10;
	private int id;
	private String name;
	@XmlElementWrapper(name = "students")
	@XmlElements({ @XmlElement(name = "student", type = Student.class) })
	private List<Student> students;
	private transient Comparator<Student> comparator;

	/**
	 * Creates default group
	 */
	public Group() {
		super();
		this.name = "Default";
		this.students = new ArrayList<Student>();
	}

	/**
	 * Creates group with given name
	 * 
	 * @param groupName <code>String</code>
	 */
	public Group(String groupName) {
		this();
		this.name = groupName;
	}

	/**
	 * Creates group with given id and name
	 * 
	 * @param id        <code>id</code>
	 * @param groupName <code>String</code>
	 */
	public Group(int id, String groupName) {
		this(groupName);
		this.id = id;
	}

	/**
	 * Gets group storage id
	 * 
	 * @return <code>int</code>
	 */
	@Override
	public int getId() {
		return id;
	}

	/**
	 * Sets group storage id
	 * 
	 * @param id <code>int</code>
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets group name
	 * 
	 * @return <code>String</code>
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets group name
	 * 
	 * @param groupName <code>String</code>
	 */
	public void setName(String groupName) {
		this.name = groupName;

		for (Student student : students) {
			student.setGroupName(groupName);
		}
	}

	/**
	 * Gets the array of students
	 * 
	 * @return <code>List&lt;Student&gt;</code>
	 */
	public List<Student> getStudents() {
		return new ArrayList<Student>(students);
	}

	/**
	 * Sets the array of students
	 * 
	 * @param groupMembers <code>List&lt;Student&gt;</code>
	 */
	public void setStudents(List<Student> students) {
		this.students = new ArrayList<Student>(students);
	}

	@Override
	public String getFileName() {
		return this.name;
	}

	/**
	 * Sets a student to the group
	 * 
	 * @param student <code>Student</code>
	 * @throws TooManyStudentsException
	 */
	public void setStudent(Student student) throws TooManyStudentsException {
		if (student == null) {
			throw new IllegalArgumentException("Student cannot be null");
		}

		if (students.size() == GROUP_SIZE) {
			throw new TooManyStudentsException();
		}

		student.setGroupName(name);
		students.add(student);
	}

	/**
	 * Removes a student from the group
	 * 
	 * @param student <code>Student</code>
	 * @return <code>boolean</code> true if the student was found and removed 
	 * 		   and false otherwise
	 */
	public boolean removeStudent(Student student) {
		for (Student item : students) {
			if (student.equals(item)) {
				students.remove(student);
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets a student by last name
	 * 
	 * @param lastName <code>String</code>
	 * @return <code>Student</code>
	 */
	public Student getStudent(String lastName) {
		for (Student student : students) {
			if (student.getLastName().equalsIgnoreCase(lastName)) {
				return student;
			}
		}

		return null;
	}

	/**
	 * Gets currently set comparator
	 * 
	 * @return <code>Comparator</code>
	 */

	@XmlTransient
	public Comparator<Student> getComparator() {
		return comparator;
	}

	/**
	 * Sets comparator
	 * 
	 * @param comparator <code>Comparator</code>
	 */
	public void setComparator(Comparator<Student> comparator) {
		this.comparator = comparator;
	}

	/**
	 * Gets text representation of the group
	 */
	@Override
	public String toString() {
		StringBuilder text = new StringBuilder("Group " + name + ":");		

		if (comparator != null) {
			Collections.sort(students, comparator);
		} else {
			Student[] array = convertArrayOfObjects(students.toArray());
			quickSort(array, 0, array.length - 1);
			students = new ArrayList<Student>(Arrays.asList(array));
		}

		for (Student student : students) {
			text.append(System.lineSeparator() + student);
		}

		return text.toString();
	}

	/**
	 * Converts array of objects into array of students
	 * 
	 * @param array <code>Object[]</code>
	 * @return <code>Student[]</code>
	 */
	private Student[] convertArrayOfObjects(Object[] array) {
		Student[] result = new Student[array.length];
		
		for (int i = 0; i < result.length; i++) {
			result[i] = (Student) array[i];			
		}
		
		return result;
	}
	
	/**
	 * Sorts array of student by last name ascending
	 * 
	 * @param array <code>Student[]</code>
	 * @param start <code>int</code>
	 * @param end   <code>int</code>
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

	@Override
	public List<Student> getLiableStudents() {
		List<Student> liable = new ArrayList<Student>();

		for (Student student : students) {
			if (student.getGender() == Gender.MALE 
					&& isAdult(student)) {
				liable.add(student);
			}
		}

		return liable;
	}

	/**
	 * Checks if the student is adult
	 * 
	 * @param student <code>Student</code>
	 * @return <code>boolean</code> true if the student is adult and false 
	 * 		   otherwise
	 */
	private boolean isAdult(Student student) {
		Calendar now = Calendar.getInstance();
		Calendar birthdate = student.getBirthdate();

		if (now.get(Calendar.YEAR) - birthdate.get(Calendar.YEAR) > 18) {
			return true;
		}

		if (now.get(Calendar.YEAR) - birthdate.get(Calendar.YEAR) == 18) {
			if (now.get(Calendar.MONTH) > birthdate.get(Calendar.MONTH)) {
				return true;
			}

			if (now.get(Calendar.MONTH) == birthdate.get(Calendar.MONTH)) {
				if (now.get(Calendar.DAY_OF_MONTH) 
						>= birthdate.get(Calendar.DAY_OF_MONTH)) {
					return true;
				}
			}
		}

		return false;
	}
}