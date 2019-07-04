package training;

/**
 * 3D vector
 * 
 * @version 0.1 04.07.2019
 * @author Oleg
 */
public class Vector3d {
	private int x;
	private int y;
	private int z;
	
	/**
	 * Creates zero vector
	 */
	public Vector3d() {
		super();		
	}

	/**
	 * Creates vector with given coordinates
	 * 
	 * @param x <code>int</code>
	 * @param y <code>int</code>
	 * @param z <code>int</code>
	 */
	public Vector3d(int x, int y, int z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Gets x coordinate
	 * 
	 * @return <code>int</code>
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets x coordinate
	 * 
	 * @param x <code>int</code>
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Gets y coordinate
	 * 
	 * @return <code>int</code>
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets y coordinate
	 * 
	 * @param y <code>int</code>
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Gets z coordinate
	 * 
	 * @return <code>int</code>
	 */
	public int getZ() {
		return z;
	}

	/**
	 * Sets z coordinate
	 * 
	 * @param z <code>int</code>
	 */
	public void setZ(int z) {
		this.z = z;
	}

	@Override
	public String toString() {
		return "Vector3d [x=" + x + ", y=" + y + ", z=" + z + "]";
	}	
	
	/**
	 * Calculates sum of two vectors
	 * 
	 * @param a <code>Vector3d</code>
	 * @param b <code>Vector3d</code>
	 * @return <code>Vector3d</code>
	 */
	public static Vector3d add(Vector3d a, Vector3d b) {
		return new Vector3d(a.x + b.x, a.y + b.y, a.z + b.z);
	}
	
	/**
	 * Calculates scalar product of two vectors
	 * 
	 * @param a <code>Vector3d</code>
	 * @param b <code>Vector3d</code>
	 * @return <code>int</code>
	 */
	public static int getScalarProduct(Vector3d a, Vector3d b) {
		return a.x * b.x + a.y * b.y + a.z * b.z;
	}
	
	/**
	 * Calculates vector product of two vectors
	 * 
	 * @param a <code>Vector3d</code>
	 * @param b <code>Vector3d</code>
	 * @return <code>Vector3d</code>
	 */
	public static Vector3d getVectorProduct(Vector3d a, Vector3d b) {
		int x = a.y * b.z - a.z * b.y;
		int y = a.z * b.x - a.x * b.z;
		int z = a.x * b.y - a.y * b.x;
		
		return new Vector3d(x, y, z);
	}
}