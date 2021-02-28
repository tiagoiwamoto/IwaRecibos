package reciboDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Clientes;
import utils.DbConnect;

public class ClienteDao implements IAbstractDao<Clientes> {

	@Override
	public List<Clientes> listaTodos() throws SQLException {
		Connection conn = null;
		List<Clientes> lista = new ArrayList<>();
		try{
			conn = DbConnect.getConnection();
			ResultSet meuRs = DbConnect.getResultSet(conn, "SELECT * FROM Clientes");
			while(meuRs.next()){
				Clientes p = new Clientes();
				p.setId(meuRs.getString("id"));
				p.setNome(meuRs.getString("nome"));
				p.setCpf(meuRs.getString("cpf"));
				p.setRg(meuRs.getString("rg"));
				p.setDtNasc(meuRs.getString("dtNasc"));
				p.setHabilitacao(meuRs.getString("habilitacao"));
				p.setEndereco(meuRs.getString("endereco"));
				p.setCidade(meuRs.getString("cidade"));
				p.setEstado(meuRs.getString("estado"));
				p.setCep(meuRs.getString("cep"));
				p.setPais(meuRs.getString("pais"));
				p.setObs(meuRs.getString("obs"));
				lista.add(p);
			}
		}catch(Exception ex){
			String msgEx = "Erro: " + ex;
		}finally{
			if(conn != null){
				conn.close();
			}
		}
		return lista;
	}

	@Override
	public String cadastraClientes(Clientes gerenciador) {
		String msg = null;
		Connection conn = null;
		
		try{
			conn = DbConnect.getConnection();
		
		PreparedStatement meuSt = DbConnect.getPreparedStatement(conn, "INSERT INTO Clientes (id, nome, cpf, rg, dtNasc, habilitacao, endereco, cidade, estado, cep, pais, obs) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		meuSt.setString(1, gerenciador.getId());
		meuSt.setString(2, gerenciador.getNome());
		meuSt.setString(3, gerenciador.getCpf());
		meuSt.setString(4, gerenciador.getRg());
		meuSt.setString(5, gerenciador.getDtNasc());
		meuSt.setString(6, gerenciador.getHabilitacao());
		meuSt.setString(7, gerenciador.getEndereco());
		meuSt.setString(8, gerenciador.getCidade());
		meuSt.setString(9, gerenciador.getEstado());
		meuSt.setString(10, gerenciador.getCep());
		meuSt.setString(11, gerenciador.getPais());
		meuSt.setString(12, gerenciador.getObs());
		if(meuSt.executeUpdate() >= 1){
			msg = "Cliente adicionado com sucesso!";
		}else{
			msg = "Erro ao adicionar";
		}
		}catch(Exception ex){
			String msgEx = "Erro: " + ex;
		}finally{
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return msg;
	}

	@Override
	public String alteraClientes(Clientes gerenciador) {
		String msg = null;
		Connection conn = null;
		
		try{
			conn = DbConnect.getConnection();
		
		PreparedStatement meuSt = DbConnect.getPreparedStatement(conn, "UPDATE Clientes SET nome = ?, cpf = ?, rg = ?, dtNasc = ?, habilitacao = ?, endereco = ?, cidade = ?, estado = ?, cep = ?, pais = ?, obs = ? WHERE id = ?");
		meuSt.setString(1, gerenciador.getNome());
		meuSt.setString(2, gerenciador.getCpf());
		meuSt.setString(3, gerenciador.getRg());
		meuSt.setString(4, gerenciador.getDtNasc());
		meuSt.setString(5, gerenciador.getHabilitacao());
		meuSt.setString(6, gerenciador.getEndereco());
		meuSt.setString(7, gerenciador.getCidade());
		meuSt.setString(8, gerenciador.getEstado());
		meuSt.setString(9, gerenciador.getCep());
		meuSt.setString(10, gerenciador.getPais());
		meuSt.setString(11, gerenciador.getObs());
		meuSt.setString(12, gerenciador.getId());
		if(meuSt.executeUpdate() >= 1){
			msg = "Cliente ATUALIZADO com sucesso!";
		}else{
			msg = "Erro ao ATUALIZAR";
		}
		}catch(Exception ex){
			String msgEx = "Erro: " + ex;
		}finally{
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return msg;
	}

	@Override
	public String excluiClientes(Clientes gerenciador) {
		String msg = null;
		Connection conn = null;
		
		try{
			conn = DbConnect.getConnection();
		
		PreparedStatement meuSt = DbConnect.getPreparedStatement(conn, "DELETE FROM Clientes WHERE id = ?");
		meuSt.setString(1, gerenciador.getId());
		if(meuSt.executeUpdate() >= 1){
			msg = "Cliente EXCLUIDO com sucesso!";
		}else{
			msg = "Erro ao EXCLUIR";
		}
		}catch(Exception ex){
			String msgEx = "Erro: " + ex;
		}finally{
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return msg;
	}

	@Override
	public List<Clientes> procuraClientes(String nome) {
		Connection conn = null;
		List<Clientes> todos = new ArrayList<>();
		String msg = null;
		try{
			conn = DbConnect.getConnection();
			//PreparedStatement meuSt = DbConnect.getPreparedStatement(conn, "SELECT * FROM Clientes WHERE nome LIKE '%" + "?" + "%'");
			
			ResultSet meuRs = DbConnect.getResultSet(conn, "SELECT * FROM Clientes WHERE nome LIKE '%" + nome + "%' OR cpf = " + "'" + nome + "'");
			
			while(meuRs.next()){
				Clientes p = new Clientes();
				p.setId(meuRs.getString("id"));
				p.setNome(meuRs.getString("nome"));
				p.setCpf(meuRs.getString("cpf"));
				p.setRg(meuRs.getString("rg"));
				p.setDtNasc(meuRs.getString("dtNasc"));
				p.setHabilitacao(meuRs.getString("habilitacao"));
				p.setEndereco(meuRs.getString("endereco"));
				p.setCidade(meuRs.getString("cidade"));
				p.setEstado(meuRs.getString("estado"));
				p.setCep(meuRs.getString("cep"));
				p.setPais(meuRs.getString("pais"));
				p.setObs(meuRs.getString("obs"));
				todos.add(p);
			}
		}catch(Exception ex){
			msg = "Erro: " + ex;
		}finally{
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return todos;
	}
	
	public static int totalClientes(){
		Connection conn = null;
		int total = 0;
		try{
			conn = DbConnect.getConnection();
			ResultSet meuRs = DbConnect.getResultSet(conn, "SELECT (id) from Clientes");
			while(meuRs.next()){
				total++;
			}
		}catch(Exception ex){
			String errEx = "Erro: " + ex;
		}finally{
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return total;
	}

}
