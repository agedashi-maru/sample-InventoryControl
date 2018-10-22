package model;

import java.util.List;

import dao.DaoExce;
import dao.InsertDao;

public class InsertLogic {

	private InsertDao dao;

	public int  executeInsert(List<ProductJB> empList) {
		dao = new InsertDao();
		int count = 0;
		try {
			for (ProductJB productJB : empList) {
				count += dao.insertProduct(productJB);
			}
		} catch (DaoExce e) {
			e.printStackTrace();
		}

		return count;
	}
}
