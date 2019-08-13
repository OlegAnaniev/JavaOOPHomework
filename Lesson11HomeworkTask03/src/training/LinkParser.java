package training;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import training.exceptions.NegativeResponseCodeException;

/**
 * Class parsing given web page for links
 * 
 * @version 0.1 14.08.2019
 * @author Oleg
 */
public class LinkParser {
	private static final String HTTP = "http";
	private static final int HTTPS_LENGTH = "https://".length();
	private static final String A = "<a";
	private static final int A_LENGTH = A.length();
	private static final String END_OF_TAG = ">";	
	private static final char SLASH = '/';
	private static final String HREF = "href=\"";
	private static final int HREF_LENGTH = HREF.length();
	private static final String QUOTATION_MARK = "\"";	
	
	private String spec;	
	private String base;
	
	/**
	 * Default constructor
	 */
	public LinkParser() {
		super();
	}	

	/**
	 * Parameterized constructor
	 * 
	 * @param spec <code>String</code>
	 */
	public LinkParser(String spec) {
		super();
		this.spec = spec;
		setBase(this.spec);
	}

	/**
	 * Gets spec
	 * 
	 * @return <code>String</code>
	 */
	public String getSpec() {
		return spec;
	}

	/**
	 * Sets spec
	 * 
	 * @param spec <code>String</code>
	 */
	public void setSpec(String spec) {
		this.spec = spec;
		setBase(this.spec);
	}

	/**
	 * Text representation of link parser
	 */
	@Override
	public String toString() {
		return "LinkParser [spec=" + spec + "]";
	}
	
	/**
	 * Sets base
	 * 
	 * @param url <code>String</code>
	 */
	private void setBase(String url) {
		int startIndex = url.indexOf(HTTP) == 0 ? HTTPS_LENGTH : 0;
		int endIndex = url.indexOf(SLASH, startIndex);
		
		if (endIndex != -1) {
			base = url.substring(0, endIndex);
		} else {
			base = url;
		}		
	}

	/**
	 * Gets set of links
	 * 
	 * @return <code>Set&lt;String&gt;</code>
	 * @throws IOException
	 * @throws NegativeResponseCodeException
	 */
	public Set<String> getLinks() throws IOException, 
		NegativeResponseCodeException {
		
		URL url = new URL(spec);
		HttpURLConnection connection = 
				(HttpURLConnection) url.openConnection();
		int responseCode = connection.getResponseCode();
		
		if (responseCode != 200) {
			throw new NegativeResponseCodeException(responseCode);
		}
		
		return parseLinks(getHTML(connection));
	}
	
	/**
	 * Gets html from connection
	 * 
	 * @param connection <code>HttpURLConnection</code>
	 * @return <code>String</code>
	 * @throws IOException
	 */
	private static String getHTML(HttpURLConnection connection) 
			throws IOException {
		
		StringBuilder html = new StringBuilder();		
		
		try (InputStream in = connection.getInputStream();
			 BufferedReader reader = new BufferedReader(
					 new InputStreamReader(in))) {
			
			String line;
					
			while ((line = reader.readLine()) != null) {		
				if (line.length() != 0) {
					html.append(line.toLowerCase());
				}				
			}			
		}
		
		return html.toString();		
	}
	
	/**
	 * Creates set of links
	 * 
	 * @param html <code>String</code>
	 * @return <code>Set&lt;String&gt;</code>
	 */
	private Set<String> parseLinks(String html) {
		Set<String> links = new HashSet<String>();		
		int startIndex = 0;
		int endIndex;		
		String a;
		String href;
		
		while (true) {
			startIndex  = html.indexOf(A, startIndex);
			endIndex = html.indexOf(END_OF_TAG, startIndex + A_LENGTH);
			
			if (startIndex == -1 || endIndex == -1) {
				break;
			}
			
			a = html.substring(startIndex, endIndex);
			href = getHref(a);			
			
			if (validateHref(href)) {				
				if (!isBased(href)) {
					href = addBase(href);
				}
				
				links.add(href);
			}
			
			startIndex = endIndex;
		}	
		
		return links;
	}	
	
	/**
	 * Gets "href" attribute value from "a" tag
	 * 
	 * @param a <code>String</code>
	 * @return <code>String</code>
	 */
	private static String getHref(String a) {
		int startIndex = a.indexOf(HREF);
		int endIndex = a.indexOf(QUOTATION_MARK, startIndex + HREF_LENGTH);
		
		if (startIndex == -1 || endIndex == -1) {
			return null;
		}
		
		return a.substring(startIndex + HREF_LENGTH, endIndex);
	}
	
	/**
	 * Validates href
	 * 
	 * @param href <code>String</code>
	 * @return <code>boolean</code> true if href is valid and false otherwise
	 */
	private static boolean validateHref(String href) { 
		if (href == null 
				|| href.indexOf("tel:") == 0
				|| href.indexOf("javascript:") == 0
				|| href.indexOf("skype:") == 0
				|| href.indexOf("mailto") == 0
				|| href.indexOf("#") == 0
				|| href.equals("/")
				|| href.equals("")) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Checks if href is a full link
	 * 
	 * @param href <code>String</code>
	 * @return <code>boolean</code> true if href is a full link and false 
	 * otherwise
	 */
	public static boolean isBased(String href) {
		if (href.indexOf(HTTP) == 0) {
			return true;
		}
		
		if (href.matches("^.+[.].+[.].+$")) {
			return true;
		}
		
		if (href.matches("^.+[.].+(?<!(htm|html|php|aspx))$")) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Adds base to href
	 * 
	 * @param href <code>String</code>
	 * @return <code>String</code>
	 */
	private String addBase(String href) {
		return href.indexOf(SLASH) == 0 ? base + href : base + SLASH + href;
	}
}