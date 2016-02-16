package com.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.connection.connection;

public class DAO {
	
	public connection db = new connection();
	public void addKeys(String public_key, String private_key, String modulo, String p, String q, String dmp1, String dmq1, String coeff)
	{
		//INSERT INTO  `ssl_sec`.`key_pair` (`public_key`,`private_key`) VALUES('ahshsavchvascsahvc','masgdhsadhsagjhsadhg');
		
		try {
			String sql = "INSERT INTO  `ssl_sec`.`key_pair` (`public_key`,`private_key`,`modulo`,`p`, `q`, `dmp1`, `dmq1`, `coeff`) VALUES "+ "(?,?,?,?,?,?,?,?);";
			PreparedStatement stmt = db.conn.prepareStatement(sql);
			stmt.setString(1, public_key);
			stmt.setString(2, private_key);
			stmt.setString(3, modulo);
			stmt.setString(4, p);
			stmt.setString(5, q);
			stmt.setString(6, dmp1);
			stmt.setString(7, dmq1);
			stmt.setString(8, coeff);
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<String> getPrivateKey(String public_key, String modulo)
	{
		ArrayList<String> serverInfo = new ArrayList<String>();
		
		ResultSet rs;
		String private_key =null;
		String sql = "SELECT `private_key`, `p`,`q`,`dmp1`,`dmq1`,`coeff`  FROM `ssl_sec`.`key_pair` where public_key = ? AND modulo = ?;";
		try {
			//PreparedStatement stmt = db.conn.prepareStatement(sql);
			PreparedStatement pst = db.conn.prepareStatement(sql);
			pst.setString(1, public_key);
			pst.setString(2, modulo);
			rs = pst.executeQuery();
			
			
			//int i = 1;
			while(rs.next())
			{
//				System.out.println("1 " + rs.getString(1));
//				System.out.println(rs.getString(2));
//				System.out.println(rs.getString(3));
//				System.out.println(rs.getString(4));
//				System.out.println(rs.getString(5));
//				System.out.println(rs.getString(6));
				//serverInfo.add(rs.getString(0));
				serverInfo.add(rs.getString(1));
				serverInfo.add(rs.getString(2));
				serverInfo.add(rs.getString(3));
				serverInfo.add(rs.getString(4));
				serverInfo.add(rs.getString(5));
				serverInfo.add(rs.getString(6));
				
				//i++;
				//private_key = rs.getString(1);
			}
			//System.out.println("length of serverInfo "+ serverInfo.size());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return serverInfo;
	}

}
