package rrs.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnector {
	
	private final static String DB_URL = "jdbc:mysql://localhost:3306/reservations_db";
	private final static String DB_USER = "root";
	private final static String DB_PASSWORD = "";
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded");
		} catch (ClassNotFoundException e) {
			System.err.println("error loading driver. " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static Connection connectToDB(){
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			System.out.println("Connection successful");
		} catch (SQLException e) {
			System.err.println("Connection Error: " + e.getMessage());
			e.printStackTrace();
		}
		
		return con;
	}
	
	public static void closeResources (PreparedStatement ps, ResultSet rs, Connection con) {
		try {
			if(ps != null)
			{
				ps.close();
			}
			
			if(rs != null)
			{
				rs.close();
			}
			
			if(con != null)
			{
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
