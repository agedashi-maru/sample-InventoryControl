package servletconf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class DeleteConf
 */
@WebServlet("/log/DeleteConf")
public class DeleteConf extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final String DELMSG = "※項目を正しく入力してください";

	private final String NONE = "なし";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteConf() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		Decision decision = new Decision();
		ProductJB deleteJB;
		SelectLogic selectLogic = new SelectLogic();
		HttpSession session = request.getSession();

		if (action == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/menujsp/deleteMenu.jsp");
			dispatcher.forward(request, response);

		} else if (action.equals("done1")) {
			List<ProductJB> deleteList = new ArrayList<ProductJB>();
			String firstId = request.getParameter("deleteid");
			String firststock = request.getParameter("deletestock");

			if (firstId.equals("") || !(decision.isInt(firstId)) || firstId == null) {
				firstId = "0";
			}

			int id = Integer.parseInt(firstId);
			if (id < 0) {
				id = 0;
			}

			String item = request.getParameter("deleteitem");
			String kind = request.getParameter("deletekind");
			String group = request.getParameter("deletegroup");

			if (firststock.equals("") || !(decision.isInt(firststock)) || firststock == null) {
				firststock = NONE;
				deleteJB = new ProductJB(id, item, kind, group);

			} else {
				int stock = Integer.parseInt(firststock);
				deleteJB = new ProductJB(id, item, kind, group, stock);

			}

			if (id == 0 && (item == null || item.length() == 0) && (kind == null || kind.length() == 0)
					&& (group == null || group.length() == 0) && firststock.equals(NONE)) {
				deleteList = selectLogic.executeFindAll();

			} else {
				deleteList = selectLogic.executeSomeMatch(deleteJB);

			}

			request.setAttribute("deleteList", deleteList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/menujsp/deleteMenu.jsp");
			dispatcher.forward(request, response);

		} else if (action.equals("done2")) {
			List<ProductJB> idList = new ArrayList<ProductJB>();
			List<ProductJB> deleteIdList = new ArrayList<ProductJB>();

			for (int i = 1; i <= 5; i++) {

				String firstId = request.getParameter("deleteid" + i);
				if (firstId.equals("") || firstId == null || !(decision.isInt(firstId))) {
					firstId = "0";
				}
				int id = Integer.parseInt(firstId);
				if (id < 0) {
					id = 0;
				}
				idList = selectLogic.executeFindId();
				for (ProductJB jb : idList) {
					if ((!(id == 0)) && jb.getId() == id) {
						deleteJB = new ProductJB(id);
						deleteIdList.add(deleteJB);

					}
				}

			}
			if (deleteIdList.size() != 0) {
				deleteIdList = selectLogic.executeSelectById(deleteIdList);
				session.setAttribute("deleteIdList", deleteIdList);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/confjsp/confDelete.jsp");
				dispatcher.forward(request, response);

			}else{
				request.setAttribute("delMsg", DELMSG);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/menujsp/deleteMenu.jsp");
				dispatcher.forward(request, response);

			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/confjsp/confDelete.jsp");
		dispatcher.forward(request, response);
	}

}
