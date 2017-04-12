package kr.or.dgit.sw_project.initsetting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCon {
	private static Connection con;
	
	private DBCon(String url, String user, String pwd) {
		try {
			Class.forName(Config.DRIVER);
			con = DriverManager.getConnection(url, user, pwd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection(String url, String user, String pwd) {
		if (con == null) {
			new DBCon(url, user, pwd);
		}
		return DBCon.con;
	}

	public static void closeConnection() {
		if (con != null) {
			try {
				con.close();
				con = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
