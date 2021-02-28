package utils;

import java.io.File;
import java.net.URL;
import javax.print.PrintServiceLookup;
import javax.swing.JEditorPane;


public class ImprimirArquivo {
    
		 
	public static void imprimir(String caminho){
		try{
		JEditorPane editorPane = new JEditorPane();
	    editorPane.setEditable(false);
	    URL urlToPage = new File(caminho).toURI().toURL();
	    editorPane.setPage(urlToPage);
	    editorPane.print(null, null, true, PrintServiceLookup.lookupDefaultPrintService(), null, true);
		}catch(Exception e){
			
		}
	}
}
