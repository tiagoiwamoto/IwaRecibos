package reciboDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public interface IAbstractDao<T> {
	List<T>	listaTodos() throws SQLException;
	String cadastraClientes(T gerenciador) throws SQLException;
	String alteraClientes(T gerenciador);
	String excluiClientes(T gerenciador);
	List<T> procuraClientes(String nome);
}
