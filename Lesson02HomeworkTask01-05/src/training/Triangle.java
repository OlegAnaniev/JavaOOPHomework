package training;

/**
 * Triangle
 * 
 * @version 0.1 07.07.2019
 * @author Oleg
 */
public class Triangle extends StraightLinedShape {
	private Point a;
	private Point b;
	private Point c;

	/**
	 * Creates default triangle with (0, 0) corner points
	 */
	public Triangle() {
		super();
		this.a = new Point(0, 0);
		this.b = new Point(0, 0);
		this.c = new Point(0, 0);
	}

	/**
	 * Creates triangle with given corner points
	 * 
	 * @param a <code>Point</code>
	 * @param b <code>Point</code>
	 * @param c <code>Point</code>
	 */
	public Triangle(Point a, Point b, Point c) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
	}

	/**
	 * Creates triangle with given corner points
	 * 
	 * @param ax <code>double</code> x coordinate for corner a
	 * @param ay <code>double</code> y coordinate for corner a
	 * @param bx <code>double</code> x coordinate for corner b
	 * @param by <code>double</code> y coordinate for corner b
	 * @param cx <code>double</code> x coordinate for corner c
	 * @param cy <code>double</code> y coordinate for corner c
	 */
	public Triangle(double ax, double ay, double bx, double by, double cx, 
			double cy) {
		super();
		this.a = new Point(ax, ay);
		this.b = new Point(bx, by);
		this.c = new Point(cx, cy);
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
	 * @param <code>Point</code>
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
	 * @param <code>Point</code>
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
	 * @param <code>Point</code>
	 */
	public void setC(Point c) {
		this.c = c;
	}

	/**
	 * Calculates perimeter of the triangle
	 */
	@Override
	public double getPerimeter() {				
		return StraightLinedShape.getLineSegmentLength(a, b) 
				+ StraightLinedShape.getLineSegmentLength(b, c) 
				+ StraightLinedShape.getLineSegmentLength(a, c);
	}
	
	/**
	 * Calculates area of the triangle
	 */
	@Override
	public double getArea() {
		return 0.5 * Math.abs((a.getX() - c.getX()) * (b.getY() - c.getY()) 
				- (b.getX() - c.getX()) * (a.getY() - c.getY()));
	}

	/**
	 * Gets text representation of the triangle 
	 */
	@Override
	public String toString() {
		return "Triangle [a=" + a + ", b=" + b + ", c=" + c + "]";
	}
}