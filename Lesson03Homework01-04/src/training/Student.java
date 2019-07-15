package training;

import java.util.Calendar;

import training.Faculty.FacultyName;

/**
 * Class representing a student
 * 
 * @version 0.1 10.07.2019
 * @author Oleg
 */
public class Student extends Human implements Cloneable {
	public enum TeachingMethod {
		IN_PERSON, LONG_DISTANCE, ON_LINE, ON_LINE_AND_IN_PERSON, SELF_STUDY, 
		NONE
	}
	
	private final static String NONE = "none";	
	private Faculty.FacultyName facultyName;
	private TeachingMethod teachingMethod;
	private String groupName = NONE;
	private String idNumber = NONE;
	private String recordBookNumber = NONE;
	
	/**
	 * Creates default student
	 */
	public Student() {
		super();
		this.facultyName = Faculty.FacultyName.NONE;
		this.teachingMethod = TeachingMethod.NONE;
	}
	
	/**
	 * Creates parameterized student
	 * 
	 * @param firstName <code>String</code>
	 * @param lastName <code>String</code>
	 * @param gender <code>Human.Gender</code>
	 * @param birthdate <code>Calendar</code>
	 * @param facultyName <code>Faculty.Name</code>
	 * @param teachingMethod <code>TeachingMethod</code>
	 * @param idNumber <code>String</code>
	 * @param recordBookNumber <code>String</code>
	 */
	public Student(String firstName, String lastName, Gender gender, 
			Calendar birthdate, FacultyName facultyName, 
			TeachingMethod teachingMethod, String idNumber, 
			String recordBookNumber) {
		super(firstName, lastName, gender, birthdate);
		initializeClassFields(facultyName, teachingMethod, idNumber, 
				recordBookNumber);
	}

	/**
	 * Creates parameterized student
	 * 
	 * @param firstName <code>String</code>
	 * @param lastName <code>String</code>
	 * @param gender <code>Human.Gender</code>
	 * @param day <code>int</code> of birth
	 * @param month <code>int</code> of birth
	 * @param year <code>int</code> of birth
	 * @param facultyName <code>Faculty.Name</code>
	 * @param teachingMethod <code>TeachingMethod</code>
	 * @param idNumber <code>String</code>
	 * @param recordBookNumber <code>String</code>
	 */
	public Student(String firstName, String lastName, Gender gender, int day, 
			int month, int year, FacultyName facultyName, 
			TeachingMethod teachingMethod, String idNumber,
			String recordBookNumber) {		
		super(firstName, lastName, gender, day, month, year);
		initializeClassFields(facultyName, teachingMethod, idNumber, 
				recordBookNumber);
	}

	/**
	 * Initializes class fields. Called by constructors
	 * 
	 * @param facultyName <code>Faculty.Name</code>
	 * @param teachingMethod <code>TeachingMethod</code>
	 * @param groupName <code>String</code>
	 * @param idNumber <code>String</code>
	 * @param recordBookNumber <code>String</code>
	 */
	private final void initializeClassFields(FacultyName facultyName, 
			TeachingMethod teachingMethod, String idNumber,
			String recordBookNumber) {
		this.facultyName = facultyName;
		this.teachingMethod = teachingMethod;
		this.idNumber = idNumber;
		this.recordBookNumber = recordBookNumber;
	}

	/**
	 * Gets the name of the faculty
	 * 
	 * @return <code>Faculty.Name</code>
	 */
	public Faculty.FacultyName getFacultyName() {
		return facultyName;
	}

	/**
	 * Sets the name of the faculty
	 * 
	 * @param facultyName <code>Faculty.Name</code>
	 */
	public void setFacultyName(Faculty.FacultyName facultyName) {
		this.facultyName = facultyName;
	}

	/**
	 * Gets teaching method
	 * 
	 * @return <code>TeachingMethod</code>
	 */
	public TeachingMethod getTeachingMethod() {
		return teachingMethod;
	}

	
	/**
	 * Sets teaching method
	 * 
	 * @param teachingMethod <code>TeachingMethod</code>
	 */
	public void setTeachingMethod(TeachingMethod teachingMethod) {
		this.teachingMethod = teachingMethod;
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
	 * Gets id number
	 * 
	 * @return <code>String</code>
	 */
	public String getIdNumber() {
		return idNumber;
	}

	/**
	 * Sets id number
	 * 
	 * @param idNumber <code>String</code>
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	/**
	 * Gets record book number
	 * 
	 * @return<code>String</code>
	 */
	public String getRecordBookNumber() {
		return recordBookNumber;
	}

	/**
	 * Sets record book number
	 * 
	 * @param recordBookNumber <code>String</code>
	 */
	public void setRecordBookNumber(String recordBookNumber) {
		this.recordBookNumber = recordBookNumber;
	}

	/**
	 * Gets text representation of the student
	 */
	@Override
	public String toString() {
		return "Student [firstName=" + this.getFirstName() 
			+ ", lastName=" + this.getLastName() 
			+ ", gender=" + this.getGender() 
			+ ", birthdate=" + getTextBirthdate()
			+ ", facultyName=" + facultyName 
			+ ", teachingMethod=" + teachingMethod 
			+ ", groupName=" + groupName
			+ ", idNumber=" + idNumber 
			+ ", recordBookNumber=" + recordBookNumber + "]";
	}

	/**
	 * Gets hash code of the student
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((facultyName == null) ? 0 
				: facultyName.hashCode());
		result = prime * result + ((groupName == null) ? 0 
				: groupName.hashCode());
		result = prime * result + ((idNumber == null) ? 0 
				: idNumber.hashCode());
		result = prime * result + ((recordBookNumber == null) ? 0 
				: recordBookNumber.hashCode());
		result = prime * result + ((teachingMethod == null) ? 0 
				: teachingMethod.hashCode());
		return result;
	}

	/**
	 * Compares two students
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (facultyName != other.facultyName)
			return false;
		if (groupName == null) {
			if (other.groupName != null)
				return false;
		} else if (!groupName.equals(other.groupName))
			return false;
		if (idNumber == null) {
			if (other.idNumber != null)
				return false;
		} else if (!idNumber.equals(other.idNumber))
			return false;
		if (recordBookNumber == null) {
			if (other.recordBookNumber != null)
				return false;
		} else if (!recordBookNumber.equals(other.recordBookNumber))
			return false;
		if (teachingMethod != other.teachingMethod)
			return false;
		return true;
	}

	/**
	 * Clones the student
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {		
		Student student = new Student(this.getFirstName(), this.getLastName(), 
				this.getGender(), this.getBirthdate(), facultyName, 
				teachingMethod, idNumber, recordBookNumber);
		student.setGroupName(this.groupName);
		return student;
	}		
}