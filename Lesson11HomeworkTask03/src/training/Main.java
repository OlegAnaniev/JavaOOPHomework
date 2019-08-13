package training;

import java.io.IOException;
import java.util.Set;

import training.exceptions.NegativeResponseCodeException;

public class Main {

	public static void main(String[] args) {		
		Set<String> links;
		LinkParser parser = new LinkParser("https://prog.kiev.ua");
		
		System.out.println(parser);
		
		try {
			links = parser.getLinks();
			links.forEach(n -> System.out.println(n));
		} catch (IOException | NegativeResponseCodeException e) {
			e.printStackTrace();
		}		
	}
}