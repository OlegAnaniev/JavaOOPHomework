package training;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {
	final static int SIZE = 10;
	final static int RANGE_FROM = -10;
	final static int RANGE_TO = 10;

	public static void main(String[] args) {
		int[] array = getRandomIntArray();
		System.out.println(Arrays.toString(array));
		
		Optional<Integer> absMin = Arrays.stream(array)
				.mapToObj(n -> n)
				.collect(Collectors.minBy(
						(a, b) -> Math.abs(a) - Math.abs(b) == 0 
						? b - a : Math.abs(a) - Math.abs(b)));
		System.out.println(absMin.get());		
	}
	
	private static int[] getRandomIntArray() {
		int[] array = new int[SIZE];
		Random random = new Random();
		
		for (int i = 0; i < array.length; i++) {
			array[i] = random.nextInt(RANGE_TO + 1 - RANGE_FROM) + RANGE_FROM;			
		}
		
		return array;
	}
}