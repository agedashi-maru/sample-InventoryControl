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
 * Servlet implementation class InsertConf
 */
@WebServlet("/log/InsertConf")
public class InsertConf extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final String ENCODE_UTF8 = "UTF-8";

	private final String NONE_CORRESPOND = "該当なし";

	private final String ZERO = "0";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertConf() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(ENCODE_UTF8);
		String hidden = request.getParameter("hidden");
		SelectLogic selectLogic = new SelectLogic();
		Decision decision = new Decision();
		ProductJB jb = null;
		List<ProductJB> insertjbList = new ArrayList<ProductJB>();
		List<ProductJB> empList = new ArrayList<ProductJB>();

		if (hidden == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/menujsp/insertMenu.jsp");
			dispatcher.forward(request, response);

		} else if (hidden.equals("done")) {
			for (int i = 1; i <= 5; i++) {
				String item = request.getParameter("insertitem" + i);
				if (item.length() == 0 || item == null) {
					item = NONE_CORRESPOND;
				}
				String kind = request.getParameter("insertkind" + i);
				if (kind.length() == 0 || kind == null) {
					kind = NONE_CORRESPOND;
				}
				String group = request.getParameter("insertgroup" + i);
				if (group.length() == 0 || group == null) {
					group = NONE_CORRESPOND;
				}
				String firststock = request.getParameter("insertstock" + i);

				if (firststock.length() == 0 || firststock == null || !(decision.isInt(firststock))) {
					firststock = ZERO;
				}
				int stock = Integer.parseInt(firststock);
				if (stock < 0) {
					stock = 0;
				}

				if (item.equals(NONE_CORRESPOND) && kind.equals(NONE_CORRESPOND) && group.equals(NONE_CORRESPOND) && firststock.equals(ZERO)) {
					continue;
				}

				jb = new ProductJB(item, kind, group, stock);
				insertjbList.add(jb);
			}

			List<String> items = new ArrayList<String>();
			boolean flag = false;
			empList = selectLogic.executeFindAll();
			for (ProductJB proJB : empList) {
				for (int j = 0; j < insertjbList.size(); j++) {
					if (proJB.getItem().equals(insertjbList.get(j).getItem())) {
						flag = true;
						break;
					}
				}
			}

			for (ProductJB prodJB : insertjbList) {
				items.add(prodJB.getItem());
			}

			Set<String> checkHash = new HashSet<String>();
			for(String str : items) {
				if(checkHash.contains(str)) {
					// 重複があればtrueをセットし終了
					flag = true;
					break;
				} else {
					// 重複しなければハッシュセットへ追加
					checkHash.add(str);
				}
			}

			if (flag) {
				String dupMsg = "※商品名が重複しているか、既に登録されています";
				request.setAttribute("dupMsg", dupMsg);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/menujsp/insertMenu.jsp");
				dispatcher.forward(request, response);
			}else if (insertjbList.size() != 0) {
				HttpSession session = request.getSession();
				session.setAttribute("insertjbList", insertjbList);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/confjsp/confInsert.jsp");
				dispatcher.forward(request, response);
			} else {
				String insMsg = "※項目を正しく入力してください";
				request.setAttribute("insMsg", insMsg);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/menujsp/insertMenu.jsp");
				dispatcher.forward(request, response);
			}

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
