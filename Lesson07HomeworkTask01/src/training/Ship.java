package training;

/**
 * Class representing a ship able to unload cargo in separate thread
 * 
 * @version 0.1 29.07.2019
 * @author Oleg
 */
public class Ship implements Runnable {
	private final static String UNLOAD_MESSAGE_FORMAT = 
			"%s: unloaded a cargo unit. Remaining - %d"; 	
	private String name;
	private int cargoCount;
	private int cargoUnitUnloadTime;
	
	/**
	 * Default constructor
	 */
	public Ship() {
		super();
	}	

	/**
	 * Parameterized constructor
	 * 
	 * @param name <code>String</code>
	 * @param cargoCount <code>int</code>
	 * @param cargoUnitUnloadTime <code>int</code>
	 */
	public Ship(String name, int cargoCount, int cargoUnitUnloadTime) {
		super();
		this.name = name;
		this.cargoCount = cargoCount;
		this.cargoUnitUnloadTime = cargoUnitUnloadTime;
	}	
	
	/**
	 * Gets the name of the ship
	 * 
	 * @return <code>String</code>
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the ship
	 * 
	 * @param name <code>String</code>
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets cargo count
	 * 
	 * @return <code>int</code>
	 */
	public int getCargoCount() {
		return cargoCount;
	}

	/**
	 * Sets cargo count
	 * 
	 * @param cargoCount <code>int</code>
	 */
	public void setCargoCount(int cargoCount) {
		this.cargoCount = cargoCount;
	}

	/**
	 * Gets time (millis) required to unload one cargo unit
	 * 
	 * @return <code>int</code>
	 */
	public int getCargoUnitUnloadTime() {
		return cargoUnitUnloadTime;
	}

	/**
	 * Sets time (millis) required to unload one cargo unit
	 * 
	 * @param cargoUnitUnloadTime <code>int</code>
	 */
	public void setCargoUnitUnloadTime(int cargoUnitUnloadTime) {
		this.cargoUnitUnloadTime = cargoUnitUnloadTime;
	}	
	
	/**
	 * Text representation of the ship
	 */
	@Override
	public String toString() {
		return "Ship [name=" + name + ", cargoCount=" + cargoCount + ", "
				+ "cargoUnitUnloadTime=" + cargoUnitUnloadTime + "]";
	}

	/**
	 * Unloads cargo
	 */
	@Override
	public void run() {
		while (cargoCount > 0) {
			
			cargoCount--;
			System.out.println(String.format(UNLOAD_MESSAGE_FORMAT, name, 
					cargoCount));
			try {
				Thread.sleep(cargoUnitUnloadTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}
}