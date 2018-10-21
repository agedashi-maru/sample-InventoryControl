package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import model.ProductJB;

public class InsertDao {
//フィールド変数
	private Connection con = null; // コネクションオブジェクト
	private Statement stmt = null; // ステートメントオブジェクト

	public int insertProduct(ProductJB productJB) throws DaoExce {

		int count = 0;

		try {
			con = ConectDB.getConnection(); // Connectionの取得
			PreparedStatement prestmt = con.prepareStatement("insert into m_product (items,kinds,groups,stocks) values(?,?,?,?)");
			prestmt.setString(1,productJB.getItem());
			prestmt.setString(2,productJB.getKind());
			prestmt.setString(3,productJB.getGroup());
			prestmt.setInt(4,productJB.getStock());
			count = prestmt.executeUpdate();

		} catch (SQLException e) {
			throw new DaoExce("[insertProduct]異常", e);
		} catch (Exception e) {
			throw new DaoExce("[insertProduct]異常", e);
		}finally{
			close();
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
