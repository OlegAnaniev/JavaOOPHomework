package training;

public class Main {

	public static void main(String[] args) {

		Vector3d a = new Vector3d(1, 2, 3);
		Vector3d b = new Vector3d(3, 2, 1);
		
		System.out.println("Vector a: " + a);
		System.out.println("Vector b: " + b);
		
		Vector3d sum = Vector3d.add(a, b);
		System.out.println("Sum: " + sum);
		
		int scalarProduct = Vector3d.getScalarProduct(a, b);
		System.out.println("Scalar product: " + scalarProduct);
		
		Vector3d vectorProduct = Vector3d.getVectorProduct(a, b);
		System.out.println("Vector product: " + vectorProduct);
	}
}