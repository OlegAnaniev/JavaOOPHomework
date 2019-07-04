package training;

public class Main {

	public static void main(String[] args) {

		Triangle[] triangles = new Triangle[] { new Triangle(), 
				new Triangle(2, 3, 4), new Triangle(3, 4, 5), 
				new Triangle(1, 2, 3), new Triangle(-1, -2, -3) };

		for (Triangle triangle : triangles) {
			System.out.println(triangle);
			System.out.println("Is valid: " + triangle.isValid());
			if (triangle.isValid()) {
				System.out.println(triangle.getArea());
			}			
		}
	}
}