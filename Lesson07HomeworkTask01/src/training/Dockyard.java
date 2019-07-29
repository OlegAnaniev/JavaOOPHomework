package training;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class representing a dockyard
 * 
 * @version 0.1 29.07.2019
 * @author Oleg
 */
public class Dockyard implements AutoCloseable {
	private int dockCount;
	private ExecutorService executor;
	
	/**
	 * Default constructor
	 */
	public Dockyard() {
		super();
	}

	/**
	 * Parameterized constructor
	 * 
	 * @param dockCount <code>int</code>
	 */
	public Dockyard(int dockCount) {
		super();
		this.dockCount = dockCount;
		setExecutor();
	}
	
	/**
	 * Initializes executor
	 */
	private final void setExecutor() {
		executor = Executors.newFixedThreadPool(dockCount);
	}

	/**
	 * Gets count of docks
	 * 
	 * @return <code>int</code>
	 */
	public int getDockCount() {
		return dockCount;
	}

	/**
	 * Sets count of docks and reinitializes executor
	 * 
	 * @param dockCount
	 */
	public void setDockCount(int dockCount) {
		this.dockCount = dockCount;
		
		if (executor != null) {
			executor.shutdown();
		}
		
		setExecutor();
	}	
	
	/**
	 * Gets text representation of the dockyard
	 */
	@Override
	public String toString() {
		return "Dockyard [dockCount=" + dockCount + "]";
	}
	
	/**
	 * Closes dockyard
	 */
	@Override
	public void close() {
		executor.shutdown();
	}

	/**
	 * Unloads a ship
	 * 
	 * @param ship <code>Ship</code>
	 */
	public void unloadShip(Ship ship) {
		executor.submit(ship);
	}	
}