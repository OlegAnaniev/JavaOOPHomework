package training;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;

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
 * @version 0.2 05.08.2019
 * @author Oleg
 */
@XmlRootElement(name = "group")
public class Group implements MilitaryManager, Serializable, FileStorable {
	private static final long serialVersionUID = 1L;
	private final static int GROUP_SIZE = 10;
	private int id;
	private String name;
	private Student[] students;
	private int counter;
	private transient Comparator<Student> comparator;

	/**
	 * Creates default group
	 */
	public Group() {
		super();
		this.name = "Default";
		this.students = new Student[GROUP_SIZE];
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

		for (int i = 0; i < counter; i++) {
			students[i].setGroupName(this.name);
		}
	}

	/**
	 * Gets the array of students
	 * 
	 * @return <code>Student[]</code>
	 */
	@XmlElementWrapper(name = "students")
	@XmlElements({ @XmlElement(name = "student", type = Student.class) })
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

		if (counter == GROUP_SIZE) {
			throw new TooManyStudentsException();
		}

		try {
			students[counter] = student.clone();
			students[counter].setGroupName(name);
			counter++;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Removes a student from the group
	 * 
	 * @param student <code>Student</code>
	 * @return <code>boolean</code> true if the student was found and removed 
	 * 		   and false otherwise
	 */
	public boolean removeStudent(Student student) {
		for (int i = 0; i < counter; i++) {
			if (student.equals(students[i])) {
				Student[] tempStudents = new Student[students.length];
				System.arraycopy(students, 0, tempStudents, 0, i);
				System.arraycopy(students, i + 1, tempStudents, i, 
						counter - i);
				students = tempStudents;
				counter--;
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
		for (int i = 0; i < counter; i++) {
			if (students[i].getLastName().equals(lastName)) {
				try {
					return (Student) students[i].clone();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
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
		Student[] array = Arrays.copyOf(students, counter);

		if (comparator != null) {
			Arrays.sort(array, 0, counter, comparator);
		} else {
			quickSort(array, 0, array.length - 1);
		}

		for (Student student : array) {
			text.append(System.lineSeparator() + student);
		}

		return text.toString();
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
	public Student[] getLiableStudents() {
		int liableCount = countLiable();
		Student[] liable = new Student[liableCount];

		if (liableCount > 0) {
			int position = 0;
			for (int i = 0; i < counter; i++) {
				if (students[i].getGender() == Gender.MALE 
						&& isAdult(students[i])) {
					try {
						liable[position] = (Student) students[i].clone();
						position++;
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return liable;
	}

	/**
	 * Count the number of military liable students
	 * 
	 * @return <code>int</code>
	 */
	private int countLiable() {
		int count = 0;

		for (int i = 0; i < counter; i++) {
			if (students[i].getGender() == Gender.MALE 
					&& isAdult(students[i])) {
				count++;
			}
		}

		return count;
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