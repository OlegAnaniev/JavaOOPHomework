package training;

public class Main {

	public static void main(String[] args) {

		Phone a = new Phone();
		Phone b = new Phone("Samsung", "Vasiliy");
		Network network = new Network();
		
		System.out.println(a);
		System.out.println(b);
		
		a.register(network);

		System.out.println(a);
		System.out.println(b);
		
		a.call("0000002");
		b.call("0000001");
		
		b.register(network);
		
		System.out.println(a);
		System.out.println(b);
		
		a.call("0000002");
		b.call("0000001");
		
		System.out.println(network);
	}
}