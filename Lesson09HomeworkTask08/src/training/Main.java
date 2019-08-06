package training;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {	
		try {
			Optional<Integer> max = Files.lines(Paths.get("test.txt"))
					.flatMap(n -> Arrays.stream(n.split("[ ]")))		
					.map(n -> Integer.parseInt(n))
					.collect(Collectors.maxBy((a, b) -> a - b));
			
			System.out.println(max.get());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}