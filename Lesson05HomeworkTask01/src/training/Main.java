package training;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		FileFilter filter = new MultipleExtentionFileFilter("txt", "");
		Copier copier = new Copier(new File("copy_from"), new File("copy_to"), filter);
		try {
			copier.copy();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}