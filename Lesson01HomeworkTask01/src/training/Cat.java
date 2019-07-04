package training;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Simple cat simulator
 * 
 * @version 0.1 04.07.2019
 * @author Oleg
 */
public class Cat {
	private String name;
	private String gender;
	private Calendar birthday;
	private String color;
	private double weight;
	
	/**
	 * Creates default male black/white cat born today 
	 */
	public Cat() {
		super();		
		this.name = "Default";
		this.gender = "male";
		this.birthday = Calendar.getInstance();
		this.color = "black/white";
	}

	/**
	 * Creates parameterized cat
	 * 
	 * @param name <code>String</code>
	 * @param gender <code>String</code>
	 * @param birthday <code>Calendar</code>
	 * @param color <code>String</code>
	 * @param weight <code>double</code>
	 */
	public Cat(String name, String gender, Calendar birthday, String color, 
			double weight) {
		super();
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.color = color;
		this.weight = weight;
	}

	/**
	 * Gets cat's name
	 * 
	 * @return <code>String</code>
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets cat's name
	 * 
	 * @param name <code>String</code>
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets cat's gender
	 * 
	 * @return <code>String</code>
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets cat's gender
	 * 
	 * @param gender <code>String</code>
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Gets cat's birthday
	 * 
	 * @return <code>Calendar</code>
	 */
	public Calendar getBirthday() {
		return birthday;
	}

	/**
	 * Sets cat's birthday
	 * 
	 * @param birthday <code>Calendar</code>
	 */
	public void setBirthday(Calendar birthday) {
		this.birthday = birthday;
	}

	/**
	 * Gets cat's color
	 * 
	 * @return <code>String</code>
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Sets cat's color
	 * 
	 * @param color <code>String</code>
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * Gets cat's weight
	 * 
	 * @return <code>double</code>
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * Sets cat's weight
	 * 
	 * @param weight <code>double</code>
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	/**
	 * Forces cat to meow
	 */
	public void meow() {
		System.out.println(this.name + " says \"Meow!\"");
	}
	
	/**
	 * Forces cat to eat
	 */
	public void eat() {
		System.out.println(this.name + " eats Whiskas");
	}
	
	/**
	 * Forces cat to drink
	 */
	public void drink() {
		System.out.println(this.name + " drinks milk");
	}
	
	/**
	 * Forces cat to play
	 */
	public void play() {
		System.out.println(this.name + " plays with ball");
	}
	
	/**
	 * Forces cat to sleep
	 */
	public void sleep() {
		System.out.println(this.name + " sleeps");
	}
	
	/**
	 * Calculates cat's age in years
	 * 
	 * @return <code>int</code>
	 */
	public int getAge() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.YEAR) - this.birthday.get(Calendar.YEAR);
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/y");
		String birthdate = sdf.format(this.birthday.getTime());
		
		return "Cat [name=" + name + ", gender=" + gender + ", birthday=" 
				+ birthdate + ", color=" + color + ", weight=" + weight + "]";
	}
}