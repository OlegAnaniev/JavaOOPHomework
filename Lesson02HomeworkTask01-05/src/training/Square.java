package training;

/**
 * Square
 * 
 * @version 0.1 07.07.2019
 * @author Oleg
 */
public class Square extends StraightLinedShape {	
	private Point a;
	private Point b;
	private Point c;
	private Point d;	
	
	/**
	 * Creates default square with (0, 0) corner points
	 */
	public Square() {
		super();
		this.a = new Point(0, 0);
		this.b = new Point(0, 0);
		this.c = new Point(0, 0);
		this.d = new Point(0, 0);
	}	
	
	/**
	 * Creates square with given corner points
	 * 
	 * @param a <code>Point</code>
	 * @param b <code>Point</code>
	 * @param c <code>Point</code>
	 * @param d <code>Point</code>
	 */
	public Square(Point a, Point b, Point c, Point d) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}

	/**
	 * Creates square with given corner points
	 * 
	 * @param ax x coordinate for corner a
	 * @param ay y coordinate for corner a
	 * @param bx x coordinate for corner b
	 * @param by y coordinate for corner b
	 * @param cx x coordinate for corner c
	 * @param cy y coordinate for corner c
	 * @param dx x coordinate for corner d
	 * @param dy y coordinate for corner d
	 */
	public Square(double ax, double ay, double bx, double by, double cx, 
			double cy, double dx, double dy) {
		super();
		this.a = new Point(ax, ay);
		this.b = new Point(bx, by);
		this.c = new Point(cx, cy);
		this.d = new Point(dx, dy);
	}	
	
	/**
	 * Gets corner a
	 * 
	 * @return <code>Point</code>
	 */
	public Point getA() {
		return a;
	}

	/**
	 * Sets corner a
	 * 
	 * @param a <code>Point</code>
	 */
	public void setA(Point a) {
		this.a = a;
	}

	/**
	 * Gets corner b
	 * 
	 * @return <code>Point</code>
	 */
	public Point getB() {
		return b;
	}

	/**
	 * Sets corner b
	 * 
	 * @param b <code>Point</code>
	 */
	public void setB(Point b) {
		this.b = b;
	}

	/**
	 * Gets corner c
	 * 
	 * @return <code>Point</code>
	 */
	public Point getC() {
		return c;
	}

	/**
	 * Sets corner c
	 * 
	 * @param c <code>Point</code>
	 */
	public void setC(Point c) {
		this.c = c;
	}

	/**
	 * Gets corner d
	 * 
	 * @return <code>Point</code>
	 */
	public Point getD() {
		return d;
	}

	/**
	 * Sets corner d
	 * 
	 * @param d <code>Point</code>
	 */
	public void setD(Point d) {
		this.d = d;
	}

	/**
	 * Calculates perimeter of the square
	 */
	@Override
	public double getPerimeter() {		
		return StraightLinedShape.getLineSegmentLength(a, b) * 4;
	}

	/**
	 * Calculates area of the square
	 */
	@Override
	public double getArea() {
		return Math.pow(StraightLinedShape.getLineSegmentLength(a, b), 2);
	}

	/**
	 * Gets text representation of the square 
	 */
	@Override
	public String toString() {
		return "Square [a=" + a + ", b=" + b + ", c=" + c + ", d=" + d + "]";
	}	
}