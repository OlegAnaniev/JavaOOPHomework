package training;

/**
 * Declares methods mandatory for a shape with straight sides 
 * 
 * @version 0.1 07.07.2019
 * @author Oleg
 */
public abstract class StraightLinedShape extends Shape {
	
	/**
	 * Calculates line segment length
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static double getLineSegmentLength(Point a, Point b) {
		return Math.sqrt(Math.pow(b.getX() - a.getX(), 2) 
				+ Math.pow(b.getY() - a.getY(), 2));
	}
	
	@Override
	public abstract double getPerimeter();

	@Override
	public abstract double getArea();
}