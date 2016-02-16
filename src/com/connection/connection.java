package com.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class connection {
	public Connection conn = null;
	 
	public connection() {
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("class.forename executed");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("InstantiationException");
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("IllegalAccessException");
		}
			String url = "jdbc:mysql://localhost/ssl_sec";
			String user="root";
			String password="akhil";
			try{
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("conn built");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("conn cannot be built");
		}
	}
 
	public ResultSet runSql(String sql) throws SQLException {
	
		Statement sta=conn.createStatement();
		
		//Statement sta = conn.createStatement();
		return sta.executeQuery(sql);
	}
 
	public boolean runSql2(String sql) throws SQLException {
		Statement sta = conn.createStatement();
		return sta.execute(sql);
	}
 
	@Override
	protected void finalize() throws Throwable {
		if (conn != null || !conn.isClosed()) {
			conn.close();
		}
	}

}
