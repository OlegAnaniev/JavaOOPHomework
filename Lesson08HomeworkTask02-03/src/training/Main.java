package training;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		Blacklist blacklist = new Blacklist();
		blacklist.add(String.class);
		blacklist.add(Random.class);
		blacklist.add(Random.class);
		
		Stack stack = new Stack(blacklist);
		System.out.println(stack);
		
		Integer number1 = new Integer(25);
		stack.push(number1);		
		System.out.println(stack);
		
		Double number2 = new Double(101.11);
		stack.push(number2);
		System.out.println(stack);
		
		System.out.println(stack.peek());
		System.out.println(stack);
		
		System.out.println(stack.pop());
		System.out.println(stack);
		
		try {
			stack.push("Test");
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			stack.push(new Random());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
}