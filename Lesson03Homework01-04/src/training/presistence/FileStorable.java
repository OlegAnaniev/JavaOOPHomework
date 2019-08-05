package training.presistence;

/**
 * Interface providing methods necessary to construct filename
 * 
 * @version 0.1 05.08.2019
 * @author Oleg
 */
public interface FileStorable {
	/**
	 * Gets entity id
	 * 
	 * @return <code>int</code>
	 */
	public int getId();
	
	/**
	 * Gets entity name
	 * 
	 * @return <code>String</code>
	 */
	public String getFileName();
}