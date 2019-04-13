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

import model.InsertLogic;
import model.ProductJB;

/**
 * Servlet implementation class InsertMain
 */
@WebServlet("/log/InsertMain")
public class InsertMain extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final String INSERT_JBLIST = "insertjbList";

	private final String COUNT = "count";

	List<ProductJB> empList = new ArrayList<ProductJB>();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertMain() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		empList = (List<ProductJB>)session.getAttribute(INSERT_JBLIST);
		InsertLogic iLogic = new InsertLogic();
		Integer count = iLogic.executeInsert(empList);

		session.setAttribute(COUNT, count);


		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/resultjsp/resInsert.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
