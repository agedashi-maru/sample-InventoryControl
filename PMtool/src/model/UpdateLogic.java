package model;

import java.util.ArrayList;
import java.util.List;

import dao.DaoExce;
import dao.UpdateDao;

public class UpdateLogic {
	UpdateDao dao;

	public int executeUpdate(List<ProductJB> empList) {
		dao = new UpdateDao();
		int count = 0;
		try {
			for (ProductJB jb : empList) {
				StringBuilder sb = new StringBuilder();
				sb.append("update m_product set ");

				if (!(jb.getItem().equals(""))) {

					sb.append("items = '");
					sb.append(jb.getItem());
					sb.append("' ");
				}

				if (!(jb.getKind().equals(""))) {
					if (!(jb.getItem().equals(""))) {
						sb.append(", ");
					}
					sb.append("kinds = '");
					sb.append(jb.getKind());
					sb.append("' ");
				}

				if (!(jb.getGroup().equals(""))) {
					if ((!(jb.getItem().equals(""))) || (!(jb.getKind().equals("")))) {
						sb.append(", ");
					}
					sb.append("groups = '");
					sb.append(jb.getGroup());
					sb.append("'");
				}

				if (jb.getStock() != -1) {
					if ((!(jb.getItem().equals(""))) || (!(jb.getKind().equals(""))) || (!(jb.getGroup().equals("")))) {
						sb.append(", ");
					}
					sb.append("stocks = ");
					sb.append(jb.getStock());
				}

				sb.append(" where id = ");
				sb.append(jb.getId());
				sb.append(";");

				String sql = new String(sb);

				count += dao.updateProduct(sql);
			}
		} catch (DaoExce e) {
			e.printStackTrace();
		}

		return count;
	}

	public int stockInOut(List<ProductJB> stockList, List<History> histories) {
		int s = 0;
		int count = 0;
		List<ProductJB> stocks = new ArrayList<ProductJB>();
		stocks = stockList;
		dao = new UpdateDao();
		for (History history : histories) {
			try {
				count += dao.updateStockInOut(stocks.get(s), history);
				s++;
			} catch (DaoExce e) {
				e.printStackTrace();
			}
		}

		return count;

	}

}
