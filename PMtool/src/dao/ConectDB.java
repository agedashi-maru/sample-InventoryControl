package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConectDB {
	static Connection getConnection() throws Exception {
		// JDBCドライバのロード
		Class.forName("com.mysql.jdbc.Driver");
		// 各設定
		String url = "jdbc:mysql://localhost:3306/pmtooldb?autoReconnect=true&useSSL=false";
		String user = "root";
		String pass = "root";
		// データベースに接続
		Connection con = DriverManager.getConnection(url, user, pass);
		return con;
	}
}
