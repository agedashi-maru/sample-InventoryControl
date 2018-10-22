package servletmain;

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
 * Servlet implementation class SelectMain
 */
@WebServlet("/log/SelectMain")
public class SelectMain extends HttpServlet {

	private static final long serialVersionUID = 1L;

	final private String noMsg = "※照会方法を選択してください";

	final private String errorMsg = "※項目を入力してください";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SelectMain() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		List<ProductJB> proList = new ArrayList<ProductJB>();
		String hidden = request.getParameter("hidden");
		ProductJB jb = null;
		Decision decision = new Decision();
		SelectLogic sLogic = new SelectLogic();
		HttpSession session = request.getSession();

		if (hidden == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/menujsp/selectMenu.jsp");
			dispatcher.forward(request, response);

		} else if (hidden.equals("done")) {

			String select = request.getParameter("select");

			if (select == null) {
				request.setAttribute("noMsg", noMsg);

			} else if (select.equals("all")) {

				proList = sLogic.executeFindAll();
				session.setAttribute("proList", proList);

			} else {
				String firstId = request.getParameter("selectid");
				String firststock = request.getParameter("selectstock");
				String item = request.getParameter("selectitem");
				String kind = request.getParameter("selectkind");
				String group = request.getParameter("selectgroup");

				if (firstId.equals("") && firststock.equals("") && item.equals("") && kind.equals("") && group.equals("")) {
					request.setAttribute("errorMsg", errorMsg);

				} else {
					if (!(decision.isInt(firstId)) || firstId.equals("")) {
						firstId = "0";
					}
					int id = Integer.parseInt(firstId);
					if (id < 0) {
						id = 0;
					}

					if (!(decision.isInt(firststock)) || firststock.equals("") || (firststock == null)) {
						firststock = "-1";
					}
					int stock = Integer.parseInt(firststock);
					jb = new ProductJB(id, item, kind, group, stock);
					if (select.equals("select1")) {
						proList = sLogic.executeAllMatch(jb);
					} else if (select.equals("select2")) {
						proList = sLogic.executeStockMatch(jb);
					}
					session.setAttribute("proList", proList);
				}

			}

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/menujsp/selectMenu.jsp");
			dispatcher.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
