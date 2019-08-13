package training;

import java.io.IOException;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		LinkTester tester = new LinkTester();
		System.out.println(tester.check());
		
		try {
			tester.setLinksFromFile("address.txt");
			Map<String, String> result = tester.check();
			result.forEach((key, value) -> System.out.println(key + ": " 
					+ value));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}