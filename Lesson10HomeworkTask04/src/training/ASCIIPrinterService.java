package training;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Service class providing methods to print text as ASCII-art
 * 
 * @version 0.1 09.08.2019
 * @author Oleg
 */
public class ASCIIPrinterService {	
	private static final int DEFAULT_WIDTH = 144;
	private static final int DEFAULT_HEIGHT = 32;
	private static final Font DEFAULT_FONT = 
			new Font("Times New Roman", Font.PLAIN, 24);
	private static final String DEFAULT_TEXT = "Test";

	/**
	 * Private constructor to make instance creation impossible 
	 */
	private ASCIIPrinterService() {
		super();
	}
	
	/**
	 * Prints default text with default settings
	 */
	public static void print() {
		print(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_FONT, DEFAULT_TEXT);
	}
	
	/**
	 * Prints text with default settings
	 * 
	 * @param text <code>String</code>
	 */
	public static void print(String text) {
		print(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_FONT, text);
	}
	
	/**
	 * Prints given text in given font using default space
	 * 
	 * @param font <code>String</code>
	 * @param text <code>Font</code>
	 */
	public static void print(Font font, String text) {
		print(DEFAULT_WIDTH, DEFAULT_HEIGHT, font, text);
	}
	
	/**
	 * Prints fully cusomized text
	 * 
	 * @param widht <code>int</code>
	 * @param height <code>int</code>
	 * @param font <code>Font</code>
	 * @param text <code>String</code>
	 */
	public static void print(int widht, int height, Font font, String text) {
		BufferedImage image = new BufferedImage(widht, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = (Graphics2D) image.getGraphics();
		
		graphics.setFont(font);		
		graphics.drawString(text, 0, font.getSize());

		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < height; i++) {
		    sb.delete(0, sb.length());
		    
		    for (int j = 0; j < widht; j++) {
		    	sb.append(image.getRGB(j, i) == -16777216 ? " " : "*");
		    }
		        
		    if (sb.toString().trim().isEmpty()) { 
		    	continue;
		    }
		    
		    System.out.println(sb);
		}
	}	
}