package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import loginmodel.Account;

public class AccountDao {
	private Connection con = null; // コネクションオブジェクト
	private Statement stmt = null; // ステートメントオブジェクト

	// Statementの取得
	private void createStmt() throws DaoExce {
		// 既にStatement取得済みならばメソッドを抜ける
		if (this.stmt != null) {
			return;
		}

		try {
			// ステートメント取得
			stmt = con.createStatement();
			// SQLに関する例外処理
		} catch (SQLException e) {
			throw new DaoExce("[createStmt]異常", e);
		}
	}


	public Account findByLogin(Account user) throws DaoExce {

		Account account = null;
		try {
			// 事前準備
			con = ConectDB.getConnection(); // Connectionの取得
			createStmt(); // Statementの取得
			String sql = "SELECT * FROM m_login where userid = '" + user.getUserid() + "' AND pass = '" + user.getPass() + "';";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String userid = rs.getString("userid");
				String pass = rs.getString("pass");
				account = new Account(userid,pass);
			}

		} catch (SQLException e) {
			throw new DaoExce("[findByLogin]異常", e);
		} catch (Exception e) {
			throw new DaoExce("[findByLogin]異常", e);
		} finally {
			close(); // クローズ処理
		}
		return account;
	}


	private void close() throws DaoExce {
		if (this.stmt != null) {
			try {
				this.stmt.close();
			} catch (SQLException e) {
				throw new DaoExce("[close]異常", e);
			}
		}
		this.stmt = null;
		if (con != null) {
			try {
				this.con.close();
			} catch (SQLException e) {
				throw new DaoExce("[close]異常", e);
			}
		}
		this.con = null;
	}
}
