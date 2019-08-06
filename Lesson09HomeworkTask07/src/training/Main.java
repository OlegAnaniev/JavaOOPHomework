package training;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		String text = "SoMe RaNdOm TeXt";
		
		int[] array = text.chars().toArray(); 

		System.out.println(Arrays.toString(array));
	}
}