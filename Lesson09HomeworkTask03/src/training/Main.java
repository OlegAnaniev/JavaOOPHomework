package training;

public class Main {

	public static void main(String[] args) {
		LetterCounter counter = new LetterCounter("text.txt");
		counter.calculate();
	}
}