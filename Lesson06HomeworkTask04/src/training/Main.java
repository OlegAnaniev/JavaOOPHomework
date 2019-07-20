package training;

import java.io.File;
import java.util.Iterator;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		FolderCopier copier = new FolderCopier("from", "to");
		List<File> result = copier.copy();
		
		if (result.size() > 0) {
			Iterator<File> iterator = result.iterator();
			File copyResult;
			
			while (iterator.hasNext()) {
				copyResult = (File) iterator.next();
				System.out.println(copyResult + " copying failed");
			}			
		} else {
			System.out.println("All files copied successfully");
		}		
	}
}