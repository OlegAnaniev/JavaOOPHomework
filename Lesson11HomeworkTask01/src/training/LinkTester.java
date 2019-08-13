package training;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class testing links availability
 * 
 * @version 0.1 09.08.2019
 * @author Oleg
 */
public class LinkTester {
	private Set<String> links;	
	
	/**
	 * Default constructor
	 */
	public LinkTester() {
		super();
	}

	/**
	 * Constructor accepting set of links
	 * 
	 * @param links <code>Set&lt;String&gt;</code>
	 */
	public LinkTester(Set<String> links) {
		super();
		this.links = links;
	}
	
	/**
	 * Gets the set of links
	 * 
	 * @return <code>Set&lt;String&gt;</code>
	 */
	public Set<String> getLinks() {
		return links;
	}

	/**
	 * Sets the set of links
	 * 
	 * @param links <code>Set&lt;String&gt;</code>
	 */
	public void setLinks(Set<String> links) {
		this.links = links;
	}
	
	/**
	 * Gets links from file
	 * 
	 * @param path <code>String</code>
	 * @throws IOException
	 */
	public void setLinksFromFile(String path) throws IOException {
		this.links = Files.lines(Paths.get(path))
				.collect(Collectors.toCollection(HashSet::new));
	}

	/**
	 * Text representation of linktester
	 */
	@Override
	public String toString() {
		return "LinkTester [links=" + links + "]";
	}
	
	/**
	 * Checks links availability
	 * 
	 * @return <code>Map&lt;String, String&gt;</code>
	 */
	public Map<String, String> check() {
		Map<String, String> result = new HashMap<>();
		
		if (links != null) {
			URL url;
			HttpURLConnection connection;
			
			for (String link : links) {
				try {
					url = new URL(link);
					connection = (HttpURLConnection) url.openConnection();
					result.put(link, 
							Integer.toString(connection.getResponseCode()));
				} catch (IOException e) {
					result.put(link, e.getMessage());
				}
			}
		}		
		
		return result;
	}	
}