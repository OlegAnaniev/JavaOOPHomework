package training;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Class representing a human
 * 
 * @version 0.1 10.07.2019
 * @author Oleg
 */
public class Human {
	public enum Gender {
		MALE, FEMALE, UNKNOWN
	}
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("G dd/MM/y");
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
	 * Auxiliary private constructor call by public constructors 
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
		this.birthdate = birthdate;
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
}