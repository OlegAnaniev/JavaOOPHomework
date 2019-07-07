package training;

/**
 * Board capable of containing shapes
 * 
 * @version 0.1 07.07.2019
 * @author Oleg
 *
 */
public class Board {
	private final int boardSize = 4;
	private final String OUTPUT_MESSAGE_HEAD = "Board:";
	private final String SHAPE_OUTPUT_MESSAGE = "position %d: %s";
	private Shape[] shapes;
	
	/**
	 * Creates default empty board
	 */
	public Board() {
		super();
		shapes = new Shape[boardSize];
	}

	/**
	 * Creates board with given content
	 * 
	 * @param shapes
	 */
	public Board(Shape[] shapes) {
		super();
		this.shapes = shapes;
	}

	/**
	 * Gets the array of shapes
	 * 
	 * @return <code>Shape[]</code>
	 */
	public Shape[] getShapes() {
		return shapes;
	}

	/**
	 * Sets the array of shapes
	 * 
	 * @param shapes <code>Shape[]</code>
	 */
	public void setShapes(Shape[] shapes) {
		this.shapes = shapes;
	}
	
	/**
	 * Gets shape at given position
	 * 
	 * @param position <code>int</code>
	 * @return <code>Shape</code>
	 */
	public Shape getShape(int position) {
		return shapes[position];
	}
	
	/**
	 * Sets given shape to given position
	 * 
	 * @param shape <code>Shape</code>
	 * @param position <code>int</code>
	 */
	public void setShape(Shape shape, int position) {
		shapes[position] = shape;
	}

	/**
	 * Gets text representation of the board 
	 */
	@Override
	public String toString() {
		StringBuilder text = new StringBuilder(OUTPUT_MESSAGE_HEAD);
		
		for (int i = 0; i < shapes.length; i++) {
			text.append(System.lineSeparator() 
					+ String.format(SHAPE_OUTPUT_MESSAGE, i, shapes[i]));
		}
		
		return text.toString();
	}
	
	/**
	 * Calculates total area of all contained shapes
	 * 
	 * @return
	 */
	public double getTotalArea() {
		double area = 0;
		
		for (Shape shape : shapes) {
			if (shape != null) {
				area += shape.getArea();
			}			
		}
		
		return area;
	}	
}