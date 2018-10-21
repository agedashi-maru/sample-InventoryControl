package model;

import java.util.List;

import dao.DaoExce;
import dao.DeleteDao;

public class DeleteLogic {
	DeleteDao dao;
	public int executeDelete(List<ProductJB> empList) {
		dao = new DeleteDao();
		int count = 0;
		try {
			for (ProductJB productJB : empList) {
				count += dao.deleteProduct(productJB);
			}
		} catch (DaoExce e) {
			e.printStackTrace();
		}

		return count;
	}
}
