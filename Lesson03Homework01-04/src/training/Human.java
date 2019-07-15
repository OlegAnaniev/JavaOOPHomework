package training;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Class representing a human
 * 
 * @version 0.1 10.07.2019
 * @author Oleg
 */
public class Human implements Cloneable {
	public enum Gender {
		MALE, FEMALE, UNKNOWN
	}
	private static final SimpleDateFormat dateFormat = 
			new SimpleDateFormat("G dd/MM/y");
	private String firstName;
	private String lastName;
	private Gender gender;
	private Calendar birthdate;
	
	/**
	 * Creates default human of unknown gender born today;
	 */
	public Human() {
		super();
		this.firstName = "Default";
		this.lastName = "Default";
		this.gender = Gender.UNKNOWN;
		this.birthdate = Calendar.getInstance();		
	}

	/**
	 * Auxiliary private constructor called by public constructors 
	 * 
	 * @param firstName <code>String</code>
	 * @param lastName <code>String</code>
	 * @param gender <code>Human.Gender</code>
	 */
	private Human(String firstName, String lastName, Gender gender) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
	}
	
	/**
	 * Creates parameterized human
	 * 
	 * @param firstName <code>String</code>
	 * @param lastName <code>String</code>
	 * @param gender <code>Human.Gender</code>
	 * @param birthdate <code>Calendar</code>
	 */
	public Human(String firstName, String lastName, Gender gender, 
			Calendar birthdate) {
		this(firstName, lastName, gender);		
		this.birthdate = (Calendar) birthdate.clone();
	}

	/**
	 * Creates parameterized human
	 * 
	 * @param firstName <code>String</code>
	 * @param lastName <code>String</code>
	 * @param gender <code>Human.Gender</code>
	 * @param day <code>int</code> of birth
	 * @param month <code>int</code> of birth
	 * @param year <code>int</code> of birth
	 */
	public Human(String firstName, String lastName, Gender gender, 
			int day, int month, int year) {
		this(firstName, lastName, gender);
		this.birthdate = Calendar.getInstance();
		birthdate.set(Calendar.DATE, day);
		birthdate.set(Calendar.MONTH, month - 1);
		birthdate.set(Calendar.YEAR, year);
	}

	/**
	 * Gets first name
	 * 
	 * @return <code>String</code>
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets first name
	 * 
	 * @param firstName <code>String</code>
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets last name
	 * 
	 * @return <code>String</code>
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets last name
	 * 
	 * @param lastName <code>String</code>
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets gender
	 * 
	 * @return <code>Human.Gender</code>
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * Sets gender
	 * 
	 * @param gender <code>Human.Gender</code>
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * Gets date of birth
	 * 
	 * @return <code>Calendar</code>
	 */
	public Calendar getBirthdate() {
		return (Calendar) birthdate.clone();
	}
	
	public String getTextBirthdate() {
		return dateFormat.format(birthdate.getTime());
	}

	/**
	 * Sets date of birth
	 * 
	 * @param birthdate <code>Calendar</code>
	 */
	public void setBirthdate(Calendar birthdate) {
		this.birthdate = (Calendar) birthdate.clone();
	}

	/**
	 * Gets text representation of the human
	 */
	@Override
	public String toString() {
		return "Human [firstName=" + firstName + ", lastName=" + lastName + 
				", " + "gender=" + gender + ", birthdate=" + getTextBirthdate() 
				+ "]";
	}

	/**
	 * Gets hash code of the human
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthdate == null) ? 0 
				: birthdate.hashCode());
		result = prime * result + ((firstName == null) ? 0 
				: firstName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((lastName == null) ? 0 
				: lastName.hashCode());
		return result;
	}

	/**
	 * Compares two humans
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Human other = (Human) obj;
		if (birthdate == null) {
			if (other.birthdate != null)
				return false;
		} else if (!birthdate.equals(other.birthdate))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (gender != other.gender)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	/**
	 * Clones the human
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {		
		return new Human(firstName, lastName, gender, birthdate);
	}	
}