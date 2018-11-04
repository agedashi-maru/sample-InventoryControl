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

import org.apache.commons.lang3.StringUtils;

import model.Decision;
import model.ProductJB;
import model.SelectLogic;

/**
 * Servlet implementation class SelectMain
 */
@WebServlet("/log/SelectMain")
public class SelectMain extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final String NO_SELECT_ERROR_MSG = "※照会方法を選択してください";

	private final String EMPTY_ERROR_MSG = "※項目を入力してください";

	private final String ENCODE_UTF8 = "UTF-8";

	private final String NO_MSG = "noMsg";

	private final String ERRORMSG = "errorMsg";

	private final String ALL = "all";

	private final String DONE = "done";

	private final String HIDDEN = "hidden";

	private final String SELECT = "select";

	private final String SELECT_1 = "select1";

	private final String SELECT_2 = "select2";

	private final String PRO_LIST = "proList";

	private final String SELECT_ID = SELECT + "id";

	private final String SELECT_STOCK = SELECT + "stock";

	private final String SELECT_ITEM = SELECT + "item";

	private final String SELECT_KIND = SELECT + "kind";

	private final String SELECT_GROUP = SELECT + "group";

	private final String ZERO = "0";

	private final String MINUS_1 = "-1";


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

		request.setCharacterEncoding(ENCODE_UTF8);
		List<ProductJB> proList = new ArrayList<ProductJB>();
		String hidden = request.getParameter(HIDDEN);
		ProductJB jb = null;
		Decision decision = new Decision();
		SelectLogic sLogic = new SelectLogic();
		HttpSession session = request.getSession();

		if (hidden == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/menujsp/selectMenu.jsp");
			dispatcher.forward(request, response);

		} else if (hidden.equals(DONE)) {

			String select = request.getParameter(SELECT);

			if (select == null) {
				request.setAttribute(NO_MSG, NO_SELECT_ERROR_MSG);

			} else if (select.equals(ALL)) {

				proList = sLogic.executeFindAll();
				session.setAttribute(PRO_LIST, proList);

			} else {
				String firstId = request.getParameter(SELECT_ID);
				String firststock = request.getParameter(SELECT_STOCK);
				String item = request.getParameter(SELECT_ITEM);
				String kind = request.getParameter(SELECT_KIND);
				String group = request.getParameter(SELECT_GROUP);

				if (StringUtils.isEmpty(firstId) && firststock.equals("") && item.equals("") && kind.equals("") && group.equals("")) {
					request.setAttribute(ERRORMSG, EMPTY_ERROR_MSG);

				} else {
					if (!(decision.isInt(firstId)) || firstId.equals("")) {
						firstId = ZERO;
					}
					int id = Integer.parseInt(firstId);
					if (id < 0) {
						id = 0;
					}

					if (!(decision.isInt(firststock)) || firststock.equals("") || (firststock == null)) {
						firststock = MINUS_1;
					}
					int stock = Integer.parseInt(firststock);
					jb = new ProductJB(id, item, kind, group, stock);
					if (select.equals(SELECT_1)) {
						proList = sLogic.executeAllMatch(jb);
					} else if (select.equals(SELECT_2)) {
						proList = sLogic.executeStockMatch(jb);
					}
					session.setAttribute(PRO_LIST, proList);
				}

			}

			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/menujsp/selectMenu.jsp");
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
