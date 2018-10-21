package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.History;

public class HistoryDao {
	// フィールド変数
	private Connection con = null; // コネクションオブジェクト
	private Statement stmt = null; // ステートメントオブジェクト

	// Statementの取得
	private void createStmt() throws DaoExce {
		if (this.stmt != null) {
			return;
		} // 既にStatement取得済みならばメソッドを抜ける
		try {
			stmt = con.createStatement(); // ステートメント取得
			// SQLに関する例外処理
		} catch (SQLException e) {
			throw new DaoExce("[createStmt]異常", e);
		}
	}

	public List<History> getHistory(String sql) throws DaoExce {
		List<History> hisList = new ArrayList<History>();
		History his = null;
		// 事前準備
		try {
			con = ConectDB.getConnection(); // Connectionの取得
			createStmt(); // Statementの取得
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int historyid = rs.getInt("historyid");
				String historytime = rs.getString("historytime");
				String category = rs.getString("category");
				int num = rs.getInt("num");
				int id = rs.getInt("id");
				his = new History(historyid, historytime, category, num, id);
				hisList.add(his);
			}
		} catch (SQLException e) {
			throw new DaoExce("[getHistory]異常", e);
		} catch (Exception e) {
			throw new DaoExce("[getHistory]異常", e);
		} finally {
			close(); // クローズ処理
		}
		return hisList;
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
