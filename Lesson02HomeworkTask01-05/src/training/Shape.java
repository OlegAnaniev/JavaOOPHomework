package training;

/**
 * Declares methods mandatory for any shape 
 * 
 * @version 0.1 07.07.2019
 * @author Oleg
 */
public abstract class Shape {
	/**
	 * Calculates shape's perimeter
	 * 
	 * @return <code>double</code>
	 */
	public abstract double getPerimeter();
	
	/**
	 * Calculates shape's area
	 * 
	 * @return <code>double</code>
	 */
	public abstract double getArea();
}