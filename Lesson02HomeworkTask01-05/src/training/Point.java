package training;

/**
 * A point in 2d coordinate system
 * 
 * @version 0.1 07.07.2019
 * @author Oleg
 */
public class Point {
	private double x;
	private double y;
	
	/**
	 * Creates default point with (0, 0) coordinates
	 */
	public Point() {
		super();
	}

	/**
	 * Creates point with given coordinates
	 * 
	 * @param x <code>double</code>
	 * @param y <code>double</code>
	 */
	public Point(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * Gets x coordinate
	 * 
	 * @return <code>double</code>
	 */
	public double getX() {
		return x;
	}

	/**
	 * Sets x coordinate
	 * 
	 * @param x <code>double</code>
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Gets y coordinate
	 * 
	 * @return <code>double</code>
	 */
	public double getY() {
		return y;
	}

	/**
	 * Sets y coordinate
	 * 
	 * @param y <code>double</code>
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Calculates distance between two points
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static double getDistance(Point a, Point b) {
		return Math.sqrt(Math.pow(b.getX() - a.getX(), 2) 
				+ Math.pow(b.getY() - a.getY(), 2));
	}
	
	/**
	 * Gets text representation of the point 
	 */
	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}	
}