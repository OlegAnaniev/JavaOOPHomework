package training;

import java.util.Calendar;

public class Main {

	public static void main(String[] args) {
		Cat[] cats = new Cat[3];		
		cats[0] = new Cat("Mirzik", "male", getCalendar(2015, 0, 1), "red", 
				2.5);
		cats[1] = new Cat("Barsik", "male", getCalendar(2018, 5, 15), "black", 
				3.2);
		cats[2] = new Cat();
		
		for (Cat cat : cats) {
			System.out.println(cat);
			cat.meow();
			cat.eat();
			cat.drink();
			cat.sleep();
			System.out.println(cat.getAge() + " year(s)");
		}
	}
	
	/**
	 * Gets Calendar with a given date
	 * 
	 * @param year <code>int</code>
	 * @param month <code>int</code>
	 * @param day <code>int</code>
	 * @return <code>Calendar</code>
	 * @author Oleg
	 */
	private static Calendar getCalendar(int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);		
		
		return calendar;
	}
}
