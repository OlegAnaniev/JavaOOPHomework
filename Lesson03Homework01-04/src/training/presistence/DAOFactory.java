package training.presistence;

/**
 * Class providing functionality to save and load university data
 * 
 * @version 0.2 05.08.2019
 * @author Oleg
 */
public class DAOFactory {
	public enum Type {
		FILESYSTEM, SERIALIZATION, XML, JAXB, GSON
	}
	
	/*
	 * Private constructor to make instance creation impossible
	 */
	private DAOFactory() {
		super();
	}

	/**
	 * Returns requested saver/loader instance
	 * 
	 * @param factory <code>int</code> type
	 * @return <code>UniversityDAO</code>
	 */
	public static UniversityDAO getFactory(Type factory) {
		switch (factory) {
		case FILESYSTEM:
			return new FilesystemFactory();
		case SERIALIZATION:
			return new SerializationFactory();
		case XML:
			return new XMLFactory();
		case JAXB:
			return new JAXBFactory();
		case GSON:
			return new GSONFactory();
		default:
			return null;
		}		
	}	
}