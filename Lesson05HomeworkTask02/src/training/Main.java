package training;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		FileContentsComparer comparer = 
				new FileContentsComparer(new File("input_a.txt"), 
						new File("input_b.txt"),
						new File("output.txt"));
		
		try {
			comparer.compare();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}