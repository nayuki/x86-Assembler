package org.p79068.assembler.parser;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;


final class FileReader {
	
	/**
	 * Reads the contents of the specified file, appends a newline character ({@code '\n'}), and returns the result.
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String read(File file) throws IOException {
		Reader in = new InputStreamReader(new BufferedInputStream(new FileInputStream(file)));
		
		StringBuilder sb = new StringBuilder();
		char[] buffer = new char[32 * 1024];
		while (true) {
			int read = in.read(buffer);
			if (read == -1)
				break;
			sb.append(buffer, 0, read);
		}
		
		sb.append('\n');
		
		return sb.toString();
	}
	
	
	
	/**
	 * Not instantiable.
	 */
	private FileReader() {}
	
}
