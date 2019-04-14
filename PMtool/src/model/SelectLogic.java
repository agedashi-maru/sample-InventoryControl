package model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import dao.DaoExce;
import dao.SelectDao;

public class SelectLogic {

	private SelectDao dao = new SelectDao();;

	public List<ProductJB> executeFindAll() {

		List<ProductJB> empList = new ArrayList<ProductJB>();
		String sql = "SELECT * FROM m_product;";
		try {
			empList = dao.findProducts(sql);
		} catch (DaoExce e) {
			e.printStackTrace();
		}

		return empList;
	}

	public List<ProductJB> executeFindId() {

		List<ProductJB> empList = new ArrayList<ProductJB>();
		String sql = "SELECT id FROM m_product;";
		try {
			empList = dao.findId(sql);
		} catch (DaoExce e) {
			e.printStackTrace();
		}

		return empList;
	}

	public List<ProductJB> executeAllMatch(ProductJB jb) {
		List<ProductJB> empList = new ArrayList<ProductJB>();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM m_product where 1 ");

		if (jb.getId() != 0) {
			sb.append(" AND ");
			sb.append("id =");
			sb.append(jb.getId());
		}

		if (!StringUtils.isEmpty(jb.getItem())) {
			sb.append(" AND ");
			sb.append("items = '");
			sb.append(jb.getItem());
			sb.append("' ");
		}

		if (!StringUtils.isEmpty(jb.getKind())) {
			sb.append(" AND ");
			sb.append("kinds = '");
			sb.append(jb.getKind());
			sb.append("' ");
		}

		if (!StringUtils.isEmpty(jb.getGroup())) {
			sb.append(" AND ");
			sb.append("groups = '");
			sb.append(jb.getGroup());
			sb.append("'");
		}

		if (jb.getStock() != -1) {
			sb.append(" AND ");
			sb.append("stocks = ");
			sb.append(jb.getStock());
		}

		sb.append(";");

		try {
			empList = dao.findProducts(sb.toString());
		} catch (DaoExce e) {
			e.printStackTrace();
		}

		return empList;
	}

	public List<ProductJB> executeStockMatch(ProductJB jb) {
		List<ProductJB> empList = new ArrayList<ProductJB>();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM m_product where 1 ");
		if (jb.getId() != 0) {
			sb.append(" AND ");
			sb.append("id = ");
			sb.append(jb.getId());
		}

		if (!StringUtils.isEmpty(jb.getItem())) {
			sb.append(" OR ");
			sb.append(" items like '%");
			sb.append(jb.getItem());
			sb.append("%' ");
		}

		if (!StringUtils.isEmpty(jb.getKind())) {
			sb.append(" OR ");
			sb.append(" kinds like '%");
			sb.append(jb.getKind());
			sb.append("%' ");
		}

		if (!StringUtils.isEmpty(jb.getGroup())) {
			sb.append(" OR ");
			sb.append(" groups like '%");
			sb.append(jb.getGroup());
			sb.append("%' ");
		}

		if (jb.getStock() != -1) {
			sb.append(" OR ");
			sb.append("stocks = ");
			sb.append(jb.getStock());
		}
		sb.append(";");

		try {
			empList = dao.findProducts(sb.toString());
		} catch (DaoExce e) {
			e.printStackTrace();
		}

		return empList;
	}

	public List<ProductJB> executeSomeMatch(ProductJB jb) {
		List<ProductJB> empList = new ArrayList<ProductJB>();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM m_product where 1 ");
		if (jb.getId() != 0) {
			sb.append(" AND ");
			sb.append("id = ");
			sb.append(jb.getId());
		}

		if (!StringUtils.isEmpty(jb.getItem())) {
			sb.append(" OR ");
			sb.append(" items like '%");
			sb.append(jb.getItem());
			sb.append("%' ");
		}

		if (!StringUtils.isEmpty(jb.getKind())) {
			sb.append(" OR ");
			sb.append(" kinds like '%");
			sb.append(jb.getKind());
			sb.append("%' ");
		}

		if (!StringUtils.isEmpty(jb.getGroup())) {
			sb.append(" OR ");
			sb.append(" groups like '%");
			sb.append(jb.getGroup());
			sb.append("%' ");
		}

		sb.append(";");

		try {
			empList = dao.findProducts(sb.toString());
		} catch (DaoExce e) {
			e.printStackTrace();
		}

		return empList;
	}

	public List<ProductJB> executeSelectById(List<ProductJB> deleteIdList) {
		List<ProductJB> selectByIdList = new ArrayList<ProductJB>();
		ProductJB deleJB = new ProductJB();
		for (ProductJB productJB : deleteIdList) {
			String sql = "select * from m_product where id = " + productJB.getId() + ";";
			try {
				deleJB = dao.selectById(sql);
			} catch (DaoExce e) {
				e.printStackTrace();
			}
			selectByIdList.add(deleJB);

		}
		return selectByIdList;
	}
}
