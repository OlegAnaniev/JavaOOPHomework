package training;

import java.awt.Font;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		ASCIIPrinterService.print();		
		System.out.println();
		System.out.println();
		
		ASCIIPrinterService.print("Hello world!");		
		System.out.println();
		System.out.println();
		
		ASCIIPrinterService.print(new Font("Arial", Font.BOLD, 16), "Troll");		
		System.out.println();
		System.out.println();
		
		ASCIIPrinterService.print(144, 32, new Font("Comic Sans", Font.ITALIC, 10), "Prog.Kiev.Ua");
	}
}