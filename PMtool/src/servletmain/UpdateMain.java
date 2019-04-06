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

import model.History;
import model.ProductJB;
import model.UpdateLogic;

/**
 * Servlet implementation class UpdateMain
 */
@WebServlet("/log/UpdateMain")
public class UpdateMain extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final String UP_1 = "up1";

	private final String UP_2 = "up2";

	private final String UP_3 = "up3";

	private final String ACTION = "action";

	private final String COUNT = "count";

	private final String INOUT_JBLIST = "inOutJBList";

	private final String UPDATE_LIST = "updateList";

	private final String HISTORY_LIST = "historyList";


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateMain() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		List<ProductJB> empList = new ArrayList<ProductJB>();
		List<ProductJB> stockList = new ArrayList<ProductJB>();;
		List<History> histories = new ArrayList<History>();;
		UpdateLogic uLogic = new UpdateLogic();
		String action = request.getParameter(ACTION);
		RequestDispatcher dispatcher = null;

		if (action.equals(UP_1)) {
			empList = (List<ProductJB>) session.getAttribute(UPDATE_LIST);
			Integer count = uLogic.executeUpdate(empList);
			session.setAttribute(COUNT, count);

			dispatcher = request.getRequestDispatcher("/jsp/resultjsp/resUpdate.jsp");

		} else {
			stockList = (List<ProductJB>) session.getAttribute(INOUT_JBLIST);
			histories = (List<History>) session.getAttribute(HISTORY_LIST);

			Integer count = uLogic.stockInOut(stockList, histories);
			request.setAttribute(COUNT, count);

			if (action.equals(UP_2)) {
				dispatcher = request.getRequestDispatcher("/jsp/resInOutjsp/resIn.jsp");

			} else if(action.equals(UP_3)){
				dispatcher = request.getRequestDispatcher("/jsp/resInOutjsp/resOut.jsp");

			}
		}
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
