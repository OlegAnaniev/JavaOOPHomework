package training;

/**
 * Circle in 2d coordinate system
 * 
 * @version 0.1 07.07.2019
 * @author Oleg
 */
public class Circle extends Shape {
	private Point centre;
	private double radius;

	/**
	 * Creates default circle with zero radius and centre in (0, 0)
	 */
	public Circle() {
		super();
		centre = new Point(0, 0);
	}

	/**
	 * Creates circle with given centre and radius
	 * 
	 * @param centre <code>Point</code>
	 * @param radius <code>double</code>
	 */
	public Circle(Point centre, double radius) {
		super();
		this.centre = centre;
		this.radius = radius;
	}	
	
	/**
	 * Creates circle with given centre and radius
	 * 
	 * @param x <code>double</code> centre coordinate
	 * @param y <code>double</code> centre coordinate
	 * @param radius
	 */
	public Circle(double x, double y, double radius) {
		super();
		this.centre = new Point(x, y);
		this.radius = radius;
	}

	/**
	 * Gets circle's centre
	 * 
	 * @return <code>Point</code>
	 */
	public Point getCentre() {
		return centre;
	}

	/**
	 * Sets circle's centre
	 * 
	 * @param centre <code>Point</code>
	 */
	public void setCentre(Point centre) {
		this.centre = centre;
	}

	/**
	 * Gets circle's radius
	 * 
	 * @return <code>double</code>
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * Sets circle's radius
	 * 
	 * @param radius <code>double</code>
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}

	/**
	 * Calculates length of circumference
	 */
	@Override
	public double getPerimeter() {		
		return 2 * Math.PI * radius;
	}

	/**
	 * Calculates area of the circle
	 */
	@Override
	public double getArea() {		
		return Math.PI * radius * radius;
	}

	/**
	 * Gets text representation of the circle 
	 */
	@Override
	public String toString() {
		return "Cricle [centre=" + centre + ", radius=" + radius + "]";
	}	
}