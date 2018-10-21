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

import model.DeleteLogic;
import model.ProductJB;

/**
 * Servlet implementation class DeleteMain
 */
@WebServlet("/log/DeleteMain")
public class DeleteMain extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMain() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ProductJB> empList = new ArrayList<ProductJB>();

		HttpSession session = request.getSession();
		empList = (List<ProductJB>) session.getAttribute("deleteIdList");
		DeleteLogic dLogic = new DeleteLogic();
		Integer count = dLogic.executeDelete(empList);

		session.setAttribute("count", count);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/resultjsp/resDelete.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
