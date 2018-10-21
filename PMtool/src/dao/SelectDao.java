package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.ProductJB;

public class SelectDao {
//フィールド変数
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

	public List<ProductJB> findProducts(String sql) throws DaoExce {

		List<ProductJB> empList = new ArrayList<ProductJB>();
		ProductJB jb = null;

		try {
			// 事前準備
			con = ConectDB.getConnection(); // Connectionの取得
			createStmt(); // Statementの取得
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String item = rs.getString("items");
				String kind = rs.getString("kinds");
				String group = rs.getString("groups");
				int stock = rs.getInt("stocks");
				jb = new ProductJB(id, item, kind, group,stock);
				empList.add(jb);
			}
		} catch (SQLException e) {
			throw new DaoExce("[findProducts]異常", e);
		} catch (Exception e) {
			throw new DaoExce("[findProducts]異常", e);
		} finally {
			close(); // クローズ処理
		}
		return empList;
	}

	public List<ProductJB> findId(String sql) throws DaoExce {

		List<ProductJB> empList = new ArrayList<ProductJB>();

		try {
			// 事前準備
			con = ConectDB.getConnection(); // Connectionの取得
			createStmt(); // Statementの取得
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				ProductJB jb = new ProductJB(id);
				empList.add(jb);
			}
		} catch (SQLException e) {
			throw new DaoExce("[findId]異常", e);
		} catch (Exception e) {
			throw new DaoExce("[findId]異常", e);
		} finally {
			close(); // クローズ処理
		}
		return empList;
	}



	public ProductJB selectById(String sql) throws DaoExce{

		ProductJB jb=null;
		try {
			con = ConectDB.getConnection(); // Connectionの取得
			createStmt();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String item = rs.getString("items");
				String kind = rs.getString("kinds");
				String group = rs.getString("groups");
				int stock = rs.getInt("stocks");
				jb = new ProductJB(id, item, kind, group,stock);
			}
		} catch (SQLException e) {
			throw new DaoExce("[selectById]異常", e);
		} catch (Exception e) {
			throw new DaoExce("[selectById]異常", e);
		} finally {
			close(); // クローズ処理
		}
		return jb;
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
