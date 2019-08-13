package training;

import java.io.IOException;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		try {
			Map<Character, Integer> letterCount = 
					LetterCounterService.calculateLetters("text.txt");
			letterCount.forEach((key, value) -> 
				System.out.println(key + ": " + value));
			
			System.out.println();
			
			letterCount = LetterCounterService.calculateLetters("text.txt",
					(a, b) -> a.getValue() - b.getValue());
			letterCount.forEach((key, value) -> 
				System.out.println(key + ": " + value));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}