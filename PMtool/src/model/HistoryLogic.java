package model;

import java.util.List;

import dao.DaoExce;
import dao.HistoryDao;

public class HistoryLogic {
	List<History> hisList;
	HistoryDao dao = new HistoryDao();

	public List<History> inLogic() {
		String sql = "select * from m_history where category = '入庫' order by historytime desc ,historyid desc;";
		try {
			hisList = dao.getHistory(sql);
		} catch (DaoExce e) {
			e.printStackTrace();
		}

		return hisList;
	}

	public List<History> outLogic() {
		String sql = "select * from m_history where category = '出庫' order by historytime desc,historyid desc;";
		try {
			hisList = dao.getHistory(sql);
		} catch (DaoExce e) {
			e.printStackTrace();
		}
		return hisList;
	}

	public List<History> inOutLogic() {
		String sql = "select * from m_history order by historytime desc,historyid desc;";
		try {
			hisList = dao.getHistory(sql);
		} catch (DaoExce e) {
			e.printStackTrace();
		}
		return hisList;
	}
}
