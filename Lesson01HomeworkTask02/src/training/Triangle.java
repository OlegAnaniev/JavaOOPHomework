package training;

/**
 * Simple triangle
 * 
 * @version 0.1 04.07.2019
 * @author Oleg
 */
public class Triangle {
	private double a;
	private double b;
	private double c;
	private boolean isValid;
	
	/**
	 * Creates default triangle with zero side lengths
	 */
	public Triangle() {
		super();
	}

	/**
	 * Creates triangle with given side lengths
	 * 
	 * @param a <code>double</code>
	 * @param b <code>double</code>
	 * @param c <code>double</code>
	 */
	public Triangle(double a, double b, double c) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		
		validate();
	}
	
	/**
	 * Validates triangle
	 */
	private final void validate() {
		if ((a > 0 && b > 0 && c > 0) 
				&& (a < b + c && b < a + c && c < a + b)) {
			isValid = true;
		}
	}

	/**
	 * Gets side a length
	 * 
	 * @return <code>double</code>
	 */
	public double getA() {
		return a;
	}

	/**
	 * Sets side a length
	 * 
	 * @param a <code>double</code>
	 */
	public void setA(double a) {
		this.a = a;
	}

	/**
	 * Gets side b length
	 * 
	 * @return <code>double</code>
	 */
	public double getB() {
		return b;
	}

	/**
	 * Sets side b length
	 * 
	 * @param a <code>double</code>
	 */
	public void setB(double b) {
		this.b = b;
	}

	/**
	 * Gets side c length
	 * 
	 * @return <code>double</code>
	 */
	public double getC() {
		return c;
	}

	/**
	 * Sets side c length
	 * 
	 * @param a <code>double</code>
	 */
	public void setC(double c) {
		this.c = c;
	}
	
	/**
	 * Gets triangle validity
	 * 
	 * @return <code>boolean</code>
	 */
	public boolean isValid() {
		return this.isValid;
	}

	/**
	 * Calculates area of the triangle
	 * 
	 * @return <code>double</code>
	 */
	public double getArea() {
		double halfPeremeter = (this.a + this.b + this.c) / 2;
		double area = Math.sqrt(halfPeremeter * (halfPeremeter - a)
				* (halfPeremeter - b) * (halfPeremeter - c));
		
		return area;
	}
	
	@Override
	public String toString() {
		return "Triangle [a=" + a + ", b=" + b + ", c=" + c + "]";
	}	
}