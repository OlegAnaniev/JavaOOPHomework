package training;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

	public static void main(String[] args) {
		try (ServerSocket socket = new ServerSocket(8080)) {
			Socket clinetSocket;			
			while (true) {
				clinetSocket = socket.accept();
				new Client(clinetSocket);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}