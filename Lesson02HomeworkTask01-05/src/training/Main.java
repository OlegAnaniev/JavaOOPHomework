package training;

public class Main {

	public static void main(String[] args) {

		Circle circle = new Circle(0, 0, 4);
		Triangle triangle = new Triangle(3, -3, 7, -3, 5, 5);
		Square square = new Square(0, 0, 0, 5, 5, 5, 5, 0);
		Board board = new Board();
		Shape[] shapes;
		
		setShape(board, circle, -1);
		setShape(board, circle, 4);
		setShape(board, circle, 0);
		setShape(board, triangle, 1);
		setShape(board, square, 2);
		
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
	
	private static void setShape(Board board, Shape shape, int position) {
		if (board.setShape(shape, position)) {
			System.out.println("Shape set");
		} else {
			System.out.println("Invalid position");
		}
	}
}