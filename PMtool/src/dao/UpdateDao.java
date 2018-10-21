package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import model.History;
import model.ProductJB;

public class UpdateDao {

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
			throw new DaoExce("[createStmt]異常", e);
		}
	}

	public int updateProduct(String sql) throws DaoExce {

		int count = 0;

		try {
			con = ConectDB.getConnection();
			createStmt();
			count = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			throw new DaoExce("[updateProduct]異常", e);
		} catch (Exception e) {
			throw new DaoExce("[updateProduct]異常", e);
		} finally {
			close();
		}

		return count;
	}

	public int updateStockInOut(ProductJB jb, History his) throws DaoExce {

		int count = 0;
		try {
			con = ConectDB.getConnection();
			con.setAutoCommit(false);
			PreparedStatement prestmt1 = con.prepareStatement("insert into m_history (category,num,id) values(?,?,?)");

			prestmt1.setString(1, his.getCategory());
			prestmt1.setInt(2, his.getNum());
			prestmt1.setInt(3, his.getId());
			count = prestmt1.executeUpdate();

			PreparedStatement prestmt2 = con.prepareStatement("update m_product set stocks = ? where id = ? ;");
			prestmt2.setInt(1, jb.getStock());
			prestmt2.setInt(2, jb.getId());
			prestmt2.executeUpdate();

			try {
				con.commit();

			} catch (SQLException e) {
				con.rollback();
				e.printStackTrace();
			}
		} catch (SQLException e) {
			throw new DaoExce("[updateStockInOut]異常", e);
		} catch (Exception e) {
			throw new DaoExce("[updateStockInOut]異常", e);
		}


		return count;
	}

	//クローズ処理
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
