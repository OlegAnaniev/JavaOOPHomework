package training;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		ScheduledExecutorService executor = 
				Executors.newSingleThreadScheduledExecutor();
		FolderWatcher watcher = new FolderWatcher(new File("observed"), true);
		Scanner scanner = new Scanner(System.in);
		String userInput;
		
		executor.scheduleWithFixedDelay(watcher, 0, 1, TimeUnit.SECONDS);
		
		do {
			userInput = scanner.nextLine();
		} while (!userInput.equalsIgnoreCase("quit"));
		
		executor.shutdown();
	}
}