package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopiaArquivos {
	public static void copiaArquivo(String source, String dest){
	    InputStream is = null;
	    OutputStream os = null;
	    try {
	        is = new FileInputStream(source);
	        os = new FileOutputStream(dest);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    }catch(Exception ex){
	    	
	    }finally {
	        try {
				is.close();
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       
	    }
	}
}
