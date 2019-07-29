package training;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {

	public static void main(String[] args) {
		Cat cat1 = new Cat("Vaska", 4);
		Cat cat2 = new Cat("Vaska", 4);
		
		System.out.println(cat1);
		
		System.out.println(cat1.hashCode());
		System.out.println(cat2.hashCode());
		
		System.out.println(cat1 == cat2);
		System.out.println(cat1.equals(cat2));
		
		Cat cat3 = null;
		try {
			cat3 = cat2.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		System.out.println(cat3);
		System.out.println(cat3 == cat2);
		System.out.println();

		Class catClass = Cat.class;
		Field[] catFields = catClass.getDeclaredFields();
		for (int i = 0; i < catFields.length; i++) {
			System.out.println(catFields[i]);
		}
		System.out.println();
		
		Method[] catMethods = catClass.getDeclaredMethods();
		for (int i = 0; i < catMethods.length; i++) {
			System.out.println(catMethods[i]);
		}
		System.out.println();
		
		Class[] catInterfaces = catClass.getInterfaces();
		for (int i = 0; i < catInterfaces.length; i++) {
			System.out.println(catInterfaces[i]);
		}
		System.out.println();
		
		Field catAge;
		try {
			catAge = catClass.getDeclaredField("age");
			catAge.setAccessible(true);
			catAge.setInt(cat1, 100500);
		} catch (NoSuchFieldException | SecurityException 
				| IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		System.out.println(cat1);
		System.out.println();
		
		System.out.println(cat1.getClass().equals(Integer.class));
		
		
		
	}

}
