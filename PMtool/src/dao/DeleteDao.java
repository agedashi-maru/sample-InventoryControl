package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import model.ProductJB;

public class DeleteDao {

	// コネクションオブジェクト
	private Connection con = null;
	// ステートメントオブジェクト
	private Statement stmt = null;

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
			throw new DaoExce("[DeleteDao : createStmt]異常", e);
		}
	}

	public int deleteProduct(ProductJB jb) throws DaoExce {

		int count = 0;

		try {
			// 事前準備
			con = ConectDB.getConnection(); // Connectionの取得
			createStmt();
			count = stmt.executeUpdate("delete from m_product where id =" + jb.getId() + ";");
		} catch (SQLException e) {
			throw new DaoExce("[DeleteDao : deleteProduct]異常", e);
		} catch (Exception e) {
			throw new DaoExce("[DeleteDao : deleteProduct]異常", e);
		} finally {
			close();
		}

		return count;
	}

	// クローズ処理
	private void close() throws DaoExce {
		if (this.stmt != null) {
			try {
				this.stmt.close();
			} catch (SQLException e) {
				throw new DaoExce("[DeleteDao : close]異常", e);
			}
		}
		this.stmt = null;
		if (con != null) {
			try {
				this.con.close();
			} catch (SQLException e) {
				throw new DaoExce("[DeleteDao : close]異常", e);
			}
		}
		this.con = null;
	}
}
