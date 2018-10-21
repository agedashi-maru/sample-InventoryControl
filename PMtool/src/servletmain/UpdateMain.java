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
		String action = request.getParameter("action");
		if (action.equals("up1")) {
			empList = (List<ProductJB>) session.getAttribute("updateList");
			Integer count = uLogic.executeUpdate(empList);
			session.setAttribute("count", count);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/resultjsp/resUpdate.jsp");
			dispatcher.forward(request, response);
		} else {
			stockList = new ArrayList<ProductJB>();
			stockList = (List<ProductJB>) session.getAttribute("inOutJBList");

			histories = new ArrayList<History>();
			histories = (List<History>) session.getAttribute("historyList");

			Integer count = uLogic.stockInOut(stockList, histories);
			request.setAttribute("count", count);

			if (action.equals("up2")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/resInOutjsp/resIn.jsp");
				dispatcher.forward(request, response);
			} else if(action.equals("up3")){
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
