package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import model.Recibo;

public class GeraHtml {
//	public void gerador() throws IOException{
//		Path path = Paths.get("recibo/modelo.html");
//		Charset charset = StandardCharsets.UTF_8;
//
//		String content = new String(Files.readAllBytes(path), charset);
//		content = content.replaceAll("&nbsp;&lt;#V1#&gt;", "10");
//		Files.write(path, content.getBytes(charset));
//	}
	
	
	
	public static void printRecibo(Recibo p, String skin, String img){
		String cpfNovo;
		String cpfAss;
		try
        {
        File file = new File("modelos/" + skin + ".html");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = "", oldtext = "";
        while((line = reader.readLine()) != null)
            {
            oldtext += line + "\r\n";
        }
        reader.close();
        String replacedtext  = oldtext.replaceAll("&lt;#V1#&gt;", p.getId());
        MoedaFormat m = new MoedaFormat();
        
        
		
        replacedtext = replacedtext.replaceAll("&lt;#V2#&gt;", m.mascaraDinheiro(p.getValor(), MoedaFormat.DINHEIRO_REAL));
        replacedtext = replacedtext.replaceAll("&lt;#V3#&gt;", p.getqFp());
        
        if(p.getCpf().length() > 12){
			cpfNovo = CpfFormat.formatString("##.###.###/####-##", p.getCpf());
		}else{
			cpfNovo = CpfFormat.formatString("###-###-###-##", p.getCpf());
		}
        
        replacedtext = replacedtext.replaceAll("&lt;#V4#&gt;", cpfNovo);
        replacedtext = replacedtext.replaceAll("&lt;#V5#&gt;", p.getValorEscrito());
        replacedtext = replacedtext.replaceAll("&lt;#V6#&gt;", p.getReferente());
        replacedtext = replacedtext.replaceAll("&lt;#V7#&gt;", p.getObs());
        replacedtext = replacedtext.replaceAll("&lt;#V8#&gt;", p.getLocal());
        replacedtext = replacedtext.replaceAll("&lt;#V9#&gt;", p.getDataEscrita());
        replacedtext = replacedtext.replaceAll("&lt;#V10#&gt;", p.getQuemRecebe());
        
        if(p.getCpfQuemRecebe().length() > 12){
        	cpfAss = CpfFormat.formatString("##.###.###/####-##", p.getCpfQuemRecebe());
			
		}else{
			cpfAss = CpfFormat.formatString("###-###-###-##", p.getCpfQuemRecebe());
			
		}
        
        replacedtext = replacedtext.replaceAll("&lt;#V11#&gt;", cpfAss);
        replacedtext = replacedtext.replaceAll("&lt;#V12#&gt;", "../" + img);

        FileWriter writer = new FileWriter("recibos/" + p.getId() + ".html");
        writer.write(replacedtext);


        writer.close();

    }
    catch (IOException ioe)
        {
        ioe.printStackTrace();
    }
	}
}
