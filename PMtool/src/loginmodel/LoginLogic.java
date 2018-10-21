package loginmodel;

import dao.AccountDao;
import dao.DaoExce;

public class LoginLogic {

	public boolean execute(Account user) {
		boolean isLogin;
		try {
			AccountDao dao = new AccountDao();

			if (dao.findByLogin(user) != null) {
				isLogin = true;
			} else {
				isLogin = false;
			}
		} catch (DaoExce e) {
			e.printStackTrace();
			isLogin = false;
		}

		return isLogin;
	}
}
