package reciboDao;

import java.sql.SQLException;
import java.util.List;

public interface IAbstractReciboDao<T> {
	List<T>	listaTodos() throws SQLException;
	String cadastraRecibo(T gerenciador) throws SQLException;
	String alteraRecibo(T gerenciador);
	String excluiRecibo(T gerenciador);
	List<T> procuraRecibo(String nome);
}
