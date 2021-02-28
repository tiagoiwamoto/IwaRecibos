package reciboDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Recibo;
import utils.DbConnect;

public class ReciboDao implements IAbstractReciboDao<Recibo> {
	@Override
	public List<Recibo> listaTodos() throws SQLException {
		Connection conn = null;
		List<Recibo> todos = new ArrayList<>();
		String msg = null;
		try{
			conn = DbConnect.getConnection();
			ResultSet meuRs = DbConnect.getResultSet(conn, "SELECT * FROM Recibo");
			while(meuRs.next()){
				Recibo p = new Recibo();
				p.setId(meuRs.getString("id"));
				p.setTipoRecibo(meuRs.getString("tipoRecibo"));
				p.setValor(meuRs.getDouble("valor"));
				p.setqFp(meuRs.getString("qfp"));
				p.setCpf(meuRs.getString("cpf"));
				p.setValorEscrito(meuRs.getString("valorEscrito"));
				p.setReferente(meuRs.getString("referente"));
				p.setObs(meuRs.getString("obs"));
				p.setDataEmissao(meuRs.getString("dataEmissao"));
				p.setLocal(meuRs.getString("local"));
				p.setDataEscrita(meuRs.getString("dataEscrita"));
				p.setQuemRecebe(meuRs.getString("quemRecebe"));
				p.setCpfQuemRecebe(meuRs.getString("cpfQuemRecebe"));
				todos.add(p);
			}
		}catch(Exception ex){
			msg = "Erro: " + ex;
		}finally{
			if(conn != null){
				conn.close();
			}
		}
		return todos;
	}

	@Override
	public String cadastraRecibo(Recibo gerenciador) throws SQLException {
		Connection conn = null;
		String msg = null;
		try{
			conn = DbConnect.getConnection();
			PreparedStatement meuSt = DbConnect.getPreparedStatement(conn, "INSERT INTO Recibo (id, tipoRecibo, valor, qfp, cpf, valorEscrito, referente, obs, dataEmissao, local, dataEscrita, quemRecebe, cpfQuemRecebe) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
			meuSt.setString(1, gerenciador.getId());
			meuSt.setString(2, gerenciador.getTipoRecibo());
			meuSt.setDouble(3, gerenciador.getValor());
			meuSt.setString(4, gerenciador.getqFp());
			meuSt.setString(5, gerenciador.getCpf());
			meuSt.setString(6, gerenciador.getValorEscrito());
			meuSt.setString(7, gerenciador.getReferente());
			meuSt.setString(8, gerenciador.getObs());
			meuSt.setString(9, gerenciador.getDataEmissao());
			meuSt.setString(10, gerenciador.getLocal());
			meuSt.setString(11, gerenciador.getDataEscrita());
			meuSt.setString(12, gerenciador.getQuemRecebe());
			meuSt.setString(13, gerenciador.getCpfQuemRecebe());
			if(meuSt.executeUpdate() >= 1){
				msg = "Recibo adicionado com sucesso";
			}else{
				msg = "Erro ao adicionar recibo";
			}
		}catch(Exception ex){
			String errEx = "Erro: " + ex;
		}finally{
			if(conn != null){
				conn.close();
			}
		}
		return msg;
	}

	@Override
	public String alteraRecibo(Recibo gerenciador) {
		Connection conn = null;
		String msg = null;
		try{
			conn = DbConnect.getConnection();
			PreparedStatement meuSt = DbConnect.getPreparedStatement(conn, "UPDATE Recibo SET tipoRecibo = ?, valor = ?, qfp = ?, cpf = ?, valorEscrito = ?, referente = ?, obs = ?, dataEmissao = ?, local = ?, dataEscrita = ?, quemRecebe = ?, cpfQuemRecebe = ? WHERE id = ?");
			meuSt.setString(1, gerenciador.getTipoRecibo());
			meuSt.setDouble(2, gerenciador.getValor());
			meuSt.setString(3, gerenciador.getqFp());
			meuSt.setString(4, gerenciador.getCpf());
			meuSt.setString(5, gerenciador.getValorEscrito());
			meuSt.setString(6, gerenciador.getReferente());
			meuSt.setString(7, gerenciador.getObs());
			meuSt.setString(8, gerenciador.getDataEmissao());
			meuSt.setString(9, gerenciador.getLocal());
			meuSt.setString(10, gerenciador.getDataEscrita());
			meuSt.setString(11, gerenciador.getQuemRecebe());
			meuSt.setString(12, gerenciador.getCpfQuemRecebe());
			meuSt.setString(13, gerenciador.getId());
			if(meuSt.executeUpdate() >= 1){
				msg = "Recibo ATUALIZADO com sucesso";
			}else{
				msg = "Erro ao ATUALIZAR recibo";
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
		return msg;
	}

	@Override
	public String excluiRecibo(Recibo gerenciador) {
		Connection conn = null;
		String msg = null;
		try{
			conn = DbConnect.getConnection();
			PreparedStatement meuSt = DbConnect.getPreparedStatement(conn, "DELETE FROM Recibo WHERE id = ?");
			meuSt.setString(1, gerenciador.getId());
			if(meuSt.executeUpdate() >= 1){
				msg = "Recibo EXCLUIDO com sucesso";
			}else{
				msg = "Erro ao EXCLUIR recibo";
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
		return msg;
	}

	@Override
	public List<Recibo> procuraRecibo(String nome) {
		Connection conn = null;
		List<Recibo> todos = new ArrayList<>();
		String msg = null;
		try{
			conn = DbConnect.getConnection();
			ResultSet meuRs = DbConnect.getResultSet(conn, "SELECT * FROM Recibo WHERE qfp OR quemRecebe LIKE '%" + nome + "%' OR cpf = " + "'" + nome + "' OR cpfQuemRecebe = " + "'" + nome + "'");

			while(meuRs.next()){
				Recibo p = new Recibo();
				p.setId(meuRs.getString("id"));
				p.setTipoRecibo(meuRs.getString("tipoRecibo"));
				p.setValor(meuRs.getDouble("valor"));
				p.setqFp(meuRs.getString("qfp"));
				p.setCpf(meuRs.getString("cpf"));
				p.setValorEscrito(meuRs.getString("valorEscrito"));
				p.setReferente(meuRs.getString("referente"));
				p.setObs(meuRs.getString("obs"));
				p.setDataEmissao(meuRs.getString("dataEmissao"));
				p.setLocal(meuRs.getString("local"));
				p.setDataEscrita(meuRs.getString("dataEscrita"));
				p.setQuemRecebe(meuRs.getString("quemRecebe"));
				p.setCpfQuemRecebe(meuRs.getString("cpfQuemRecebe"));
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
	
	public List<String> getConfig(){
		Connection conn = null;
		List<String> lista = new ArrayList<>();
		try{
			conn = DbConnect.getConnection();
			ResultSet meuRs = DbConnect.getResultSet(conn, "SELECT * FROM Config");
			lista.add(0, meuRs.getString("emitente"));
			lista.add(1, meuRs.getString("cpf"));
			lista.add(2, meuRs.getString("cidade"));
			lista.add(3, meuRs.getString("img"));
			lista.add(4, meuRs.getString("skin"));
			
		}catch(Exception e){
			
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
		return lista;
	}
	
	public static int totalRecibo(){
		Connection conn = null;
		int total = 0;
		try{
			conn = DbConnect.getConnection();
			ResultSet meuRs = DbConnect.getResultSet(conn, "SELECT (id) from Recibo");
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
