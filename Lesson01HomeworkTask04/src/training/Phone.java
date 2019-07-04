package training;

/**
 * Simple phone simulator
 * 
 * @version 0.1 04.07.2019
 * @author Oleg
 */
public class Phone {
	private final String NOT_REGISTERED_MESSAGE = "Phone not registered";
	private final String CALLING_MESSAGE = "Calling number %s";
	private final String INVALID_NUMBER_MESSAGE = "Number not found";
	private final String CALL_RECEIVED_MESSAGE = "Number %s received a call";	
	private String model;
	private String owner;
	private String number;
	private Network network;
	
	/**
	 * Creates default phone
	 */
	public Phone() {
		super();
		this.model = "Noname";
		this.owner = "Anonimous";
	}

	/**
	 * Creates parameterized phone
	 * 
	 * @param model <code>String</code>
	 * @param owner <code>String</code>
	 */
	public Phone(String model, String owner) {
		super();
		this.model = model;
		this.owner = owner;
	}

	/**
	 * Gets phone model
	 *  
	 * @return <code>String</code>
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Sets phone model
	 * 
	 * @param model <code>String</code>
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * Gets phone owner
	 * 
	 * @return <code>String</code>
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * Sets phone owner
	 * 
	 * @param owner <code>String</code>
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * Gets phone number
	 * 
	 * @return <code>String</code>
	 */
	public String getNumber() {
		return number;
	}
	
	/**
	 * Gets network the phone is registered in
	 * 
	 * @return <code>Network</code>
	 */
	public Network getNetwork() {
		return network;
	}

	/**
	 * Registers the phone in the given network
	 * 
	 * @param network <code>Network</code>
	 */
	public void register(Network network) {
		this.network = network;
		this.number = network.register(this);
	}
	
	/**
	 * Calls the given number
	 * 
	 * @param number <code>String</code>
	 */
	public void call(String number) {
		if (network == null) {
			System.out.println(NOT_REGISTERED_MESSAGE);
			return;
		}
		
		System.out.println(String.format(CALLING_MESSAGE, number));
		if (!network.routeCall(number)) {
			System.out.println(INVALID_NUMBER_MESSAGE);
		}		
	}
	
	/**
	 * Receives a call
	 */
	public void receiveCall() {
		System.out.println(String.format(CALL_RECEIVED_MESSAGE, number));
	}

	@Override
	public String toString() {
		return "Phone [model=" + model + ", owner=" + owner + ", number=" + number + "]";
	}	
}