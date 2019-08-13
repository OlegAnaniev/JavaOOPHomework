package training;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Class sending system information to remote user
 * 
 * @version 0.1 09.08.2019
 * @author Oleg
 */
public class Client implements Runnable {
	private static int requestCounter = 1;
	private static final String RESPONSE = "HTTP/1.1 200 OK\r\n" 
			+ "Server: My_Server\r\n" 
			+ "Content-Type: text/html\r\n"
			+ "Content-Length: \r\n" 
			+ "Connection: close\r\n\r\n";
	private static final String NEXT_LINE = "<br>";
	private Socket socket;	
	
	/**
	 * Default constructor
	 */
	public Client() {
		super();
	}

	/**
	 * Constructor accepting socket and starting new thread
	 * 
	 * @param socket <code>Socket</code>
	 */
	public Client(Socket socket) {
		super();
		this.socket = socket;
		new Thread(this).start();
	}

	/**
	 * Gets socket
	 * 
	 * @return <code>Socket</code>
	 */
	public Socket getSocket() {
		return socket;
	}

	/**
	 * Sets socket
	 * 
	 * @param socket <code>Socket</code>
	 */
	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try (InputStream in = socket.getInputStream();
			 OutputStream out = socket.getOutputStream();
			 PrintWriter writer = new PrintWriter(out);	
				) {
			
			in.read(new byte[in.available()]);
			writer.write(RESPONSE + getSystemInformation()); 
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * Gets system information
	 * 
	 * @return <code>String</code>
	 */
	private String getSystemInformation() {
		StringBuilder builder = new StringBuilder();
		Runtime runtime = Runtime.getRuntime();
		
		builder.append("Request no.: " + requestCounter++ + NEXT_LINE);
		builder.append("Processors: " + runtime.availableProcessors() 
			+ NEXT_LINE);
		builder.append("Free memory: " + runtime.freeMemory() + NEXT_LINE);
		builder.append("Max memory: " + runtime.maxMemory() + NEXT_LINE);
		builder.append("Total memory: " + runtime.totalMemory() + NEXT_LINE);		
		
		return builder.toString();
	}
}