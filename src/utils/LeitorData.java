package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LeitorData {
	public static String getData(){
		// PEGO AQUI A DATA ATUAL
	    Date dataAtual = new Date();

	    // CRIO AQUI UM FORMATADOR
	    // PASSANDO UM ESTILO DE FORMATAÇÃO : DateFormat.FULL
	    // E PASSANDO UM LOCAL DA DATA : new Locale("pt", "BR")
	    DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL, new Locale("pt", "BR"));

	    // FORMATO A DATA, O FORMATADOR ME RETORNA 
	    // A STRING DA DATA FORMATADA 
	    String dataExtenso = formatador.format(dataAtual);


	    // MOSTRA A DATA
	    String msg = dataExtenso;


	    // AQUI É CASO VOCÊ QUEIRA TIRAR
	    // O DIA DA SEMANA QUE APARECE NO 
	    // PRIMEIRO EXEMPLO
	    int index  = dataExtenso.indexOf(",");
	    int lenght = dataExtenso.length();


	    // MOSTRA A DATA
	    //System.out.println("Data sem o dia da semana :  " + dataExtenso.substring(++index, lenght));
	    return msg;
	}
	
	public static String getDataNumero(){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String diaHoraAtual = dateFormat.format(date).toString();
		/*
		Calendar calendario = Calendar.getInstance();
		calendario.getTime();
		String diaHoraAtual = calendario.getTime().toString();
		*/
		return diaHoraAtual;
	}
	
}
