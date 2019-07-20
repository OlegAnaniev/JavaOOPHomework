package training;

public class Main {

	public static void main(String[] args) {
		FactorialCalculator calculator;
		Thread thread;
		
		for (int i = 0; i < 100; i++) {
			calculator = new FactorialCalculator(i);
			thread = new Thread(calculator);
			thread.start();
		}
	}
}