package model;

import java.util.ArrayList;
import java.util.List;

import dao.DaoExce;
import dao.UpdateDao;

public class UpdateLogic {

	private UpdateDao dao = new UpdateDao();;

	public int executeUpdate(List<ProductJB> empList) {

		int count = 0;
		try {
			for (ProductJB jb : empList) {
				StringBuilder sb = new StringBuilder();
				sb.append("update m_product set ");

				if (!(jb.getItem().equals(""))) {
					sb.append("items = '");
					sb.append(jb.getItem());
					sb.append("' ");
					sb.append(",");
				}

				if (!(jb.getKind().equals(""))) {
					sb.append("kinds = '");
					sb.append(jb.getKind());
					sb.append("' ");
					sb.append(",");
				}

				if (!(jb.getGroup().equals(""))) {
					sb.append("groups = '");
					sb.append(jb.getGroup());
					sb.append("'");
					sb.append(",");
				}

				if (jb.getStock() != -1) {
					sb.append("stocks = ");
					sb.append(jb.getStock());
				}

				if(sb.toString().endsWith(",")) {
					sb.deleteCharAt(sb.length() - 1);
				}
				sb.append(" where id = ");
				sb.append(jb.getId());
				sb.append(";");

				count += dao.updateProduct(sb.toString());
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
