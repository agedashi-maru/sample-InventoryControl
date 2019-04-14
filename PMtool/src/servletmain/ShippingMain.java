package servletmain;

import java.io.IOException;
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
 * Servlet implementation class ShippingMain
 */
@WebServlet("/ShippingMain")
public class ShippingMain extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String COUNT = "count";

	private final String INOUT_JBLIST = "inOutJBList";

	private final String HISTORY_LIST = "historyList";

	UpdateLogic uLogic = new UpdateLogic();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShippingMain() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		List<ProductJB> stockList = (List<ProductJB>) session.getAttribute(INOUT_JBLIST);
		List<History> histories = (List<History>) session.getAttribute(HISTORY_LIST);

		Integer count = uLogic.stockInOut(stockList, histories);
		request.setAttribute(COUNT, count);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/resInOutjsp/resOut.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
