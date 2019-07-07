package training;

public class Main {

	public static void main(String[] args) {

		Circle circle = new Circle(0, 0, 4);
		Triangle triangle = new Triangle(3, -3, 7, -3, 5, 5);
		Square square = new Square(0, 0, 0, 5, 5, 5, 5, 0);
		Board board = new Board();
		Shape[] shapes;
		
		board.setShape(circle, 0);
		board.setShape(triangle, 1);
		board.setShape(square, 2);
		
		System.out.println(board);
		System.out.println("Total area: " + board.getTotalArea());
		
		
		shapes = board.getShapes();
		for (int i = 0; i < shapes.length; i++) {
			System.out.println(shapes[i]);
			if (shapes[i] != null) {
				System.out.println("Perimeter: " + shapes[i].getPerimeter());
				System.out.println("Area: " + shapes[i].getArea());
			}
		}
	}
}