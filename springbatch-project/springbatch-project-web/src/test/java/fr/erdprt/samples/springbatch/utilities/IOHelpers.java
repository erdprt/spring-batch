package fr.erdprt.samples.springbatch.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class IOHelpers {

	
	/**
	 * Read the file content and return the string representation 
	 * @param file
	 * @param bufferSize
	 * @param encoding
	 * @return String
	 * @throws IOException
	 */
	public String readContent(File file, int bufferSize, String encoding) throws IOException {
		
		char[] buffer			=	new char[bufferSize];
		
		InputStream inputStream	=	null;
		Writer writer			=	null;
		Reader reader			=	null;
		
		try {
			writer		=	new StringWriter();
			inputStream	=	new FileInputStream(file);
			reader		=	new BufferedReader(new InputStreamReader(inputStream, encoding));
			int n;
			
			while ((n = reader.read(buffer)) != -1) {
				writer.write(buffer, 0, n);
			}
			
		} finally {
			if (reader!=null) {
				reader.close();
			}
			if (inputStream!=null) {
				inputStream.close();
			}
		}
		return writer.toString(); 
		
	}

}
