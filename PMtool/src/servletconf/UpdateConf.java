package servletconf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Decision;
import model.ProductJB;
import model.SelectLogic;

/**
 * Servlet implementation class UpdateConf
 */
@WebServlet("/log/UpdateConf")
public class UpdateConf extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateConf() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		Decision decision = new Decision();
		ProductJB updateJB;
		List<ProductJB> empList = new ArrayList<ProductJB>();
		RequestDispatcher dispatcher = null;

		if (action == null) {
			dispatcher = request.getRequestDispatcher("/jsp/menujsp/updateMenu.jsp");

		} else if (action.equals("done1")) {
			List<ProductJB> updateList = new ArrayList<ProductJB>();
			String firstId = request.getParameter("updateid");
			String firstStock = request.getParameter("updatestock");

			if (firstId.equals("") || !(decision.isInt(firstId))) {
				firstId = "0";
			}
			int id = Integer.parseInt(firstId);
			if (id < 0) {
				id = 0;
			}

			String item = request.getParameter("updateitem");
			String kind = request.getParameter("updatekind");
			String group = request.getParameter("updategroup");
			if (firstStock.equals("") || !(decision.isInt(firstStock))) {
				firstStock = "なし";
				updateJB = new ProductJB(id, item, kind, group);
			} else {
				int stock = Integer.parseInt(firstStock);
				updateJB = new ProductJB(id, item, kind, group, stock);
			}

			SelectLogic selectLogic = new SelectLogic();
			if (id == 0 && (item == null || item.length() == 0) && (kind == null || kind.length() == 0)
					&& (group == null || group.length() == 0) && firstStock.equals("なし")) {
				updateList = selectLogic.executeFindAll();
			} else {
				updateList = selectLogic.executeSomeMatch(updateJB);
			}

			request.setAttribute("updateList", updateList);

			dispatcher = request.getRequestDispatcher("/jsp/menujsp/updateMenu.jsp");

		} else if (action.equals("done2")) {
			List<ProductJB> updateList = new ArrayList<ProductJB>();
			ProductJB jb = null;
			boolean flag = false;

			for (int i = 1; i <= 5; i++) {
				String firstId = request.getParameter("upid" + i);

				if (firstId.equals("") || !(decision.isInt(firstId))) {
					firstId = "0";
				}
				int id = Integer.parseInt(firstId);
				if (id < 0) {
					id = 0;
				}

				String item = request.getParameter("upitem" + i);
				if (item.length() == 0 || item == null) {
					item = "";
				}
				String kind = request.getParameter("upkind" + i);
				if (kind.length() == 0 || kind == null) {
					kind = "";
				}
				String group = request.getParameter("upgroup" + i);
				if (group.length() == 0 || group == null) {
					group = "";
				}
				String firststock = request.getParameter("upstock" + i);
				if (firststock.length() == 0 || firststock == null || !(decision.isInt(firststock))) {
					firststock = "-1";
				}

				int stock = Integer.parseInt(firststock);
				jb = new ProductJB(id, item, kind, group, stock);

				if ((id == 0) || (item.equals("") && kind.equals("") && group.equals("") && firststock.equals("-1"))) {
					continue;
				}
				updateList.add(jb);

				List<String> items = new ArrayList<String>();
				SelectLogic selectL = new SelectLogic();

				empList = selectL.executeFindAll();
				for (ProductJB proJB : empList) {
					for (int j = 0; j < updateList.size(); j++) {
						if (proJB.getItem().equals(updateList.get(j).getItem())) {
							flag = true;
							break;
						}
					}
				}

				for (ProductJB prodJB : updateList) {
					items.add(prodJB.getItem());
				}

				Set<String> checkHash = new HashSet<String>();
				for (String str : items) {
					if (checkHash.contains(str)) {
						// 重複があればtrueをセットし終了
						flag = true;
						break;
					} else {
						// 重複しなければハッシュセットへ追加
						checkHash.add(str);
					}
				}
			}

			if (flag) {
				String dupMsg = "※商品名が重複しているか、既に登録されています";
				request.setAttribute("dupMsg", dupMsg);
				dispatcher = request.getRequestDispatcher("/jsp/menujsp/updateMenu.jsp");

			} else if (updateList.size() != 0) {
				session.setAttribute("updateList", updateList);
				dispatcher = request.getRequestDispatcher("/jsp/confjsp/confUpdate.jsp");

			} else {
				String upMsg = "※項目を正しく入力してください";
				request.setAttribute("upMsg", upMsg);
				dispatcher = request.getRequestDispatcher("/jsp/menujsp/updateMenu.jsp");

			}
		}
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
