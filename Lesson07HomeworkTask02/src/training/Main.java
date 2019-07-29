package training;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		FileCopier copier = new FileCopier("hamlet.txt", "z.txt");
		try {
			copier.copy();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}