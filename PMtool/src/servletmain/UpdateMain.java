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

	private final String COUNT = "COUNT";

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
		List<ProductJB> stockList;
		List<History> histories;
		UpdateLogic uLogic = new UpdateLogic();
		String action = request.getParameter(ACTION);

		if (action.equals(UP_1)) {
			empList = (List<ProductJB>) session.getAttribute(UPDATE_LIST);
			Integer count = uLogic.executeUpdate(empList);
			session.setAttribute(COUNT, count);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/resultjsp/resUpdate.jsp");
			dispatcher.forward(request, response);

		} else {
			stockList = new ArrayList<ProductJB>();
			stockList = (List<ProductJB>) session.getAttribute(INOUT_JBLIST);

			histories = new ArrayList<History>();
			histories = (List<History>) session.getAttribute(HISTORY_LIST);

			Integer count = uLogic.stockInOut(stockList, histories);
			request.setAttribute(COUNT, count);

			if (action.equals(UP_2)) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/resInOutjsp/resIn.jsp");
				dispatcher.forward(request, response);

			} else if(action.equals(UP_3)){
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/resInOutjsp/resOut.jsp");
				dispatcher.forward(request, response);

			}

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
