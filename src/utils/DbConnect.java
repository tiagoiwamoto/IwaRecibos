package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnect {
	public static Connection getConnection() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		Class.forName("org.sqlite.JDBC").newInstance();
		String dbPath = "jdbc:sqlite:database.db";
		Connection conexao = DriverManager.getConnection(dbPath);
		return conexao;
	}
	
	public static ResultSet getResultSet(Connection conn, String sql) throws SQLException{
		Statement meuSt = conn.createStatement();
		return meuSt.executeQuery(sql);
	}
	
	public static PreparedStatement getPreparedStatement(Connection conn, String sql) throws SQLException{
		return conn.prepareStatement(sql);
	}
}
