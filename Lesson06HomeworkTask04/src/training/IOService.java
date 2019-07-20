package training;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOService {

	private IOService() {
		super();
	}

	public static void streamCopy(InputStream is, OutputStream os) throws IOException {
		byte[] buffer = new byte[1024 * 1024];
		int readByte = 0;

		for (; (readByte = is.read(buffer)) > 0;) {
			os.write(buffer, 0, readByte);
		}
	}
	
	public static void fileCopy(File in, File out) throws IOException {
		if (in == null || out == null) {
			throw new IllegalArgumentException("Null file pointer");
		}
		
		try(InputStream is = new FileInputStream(in); 
				OutputStream os = new FileOutputStream(out)) {
			streamCopy(is, os);
		} catch (IOException e) {
			throw e;
		}
	}
}