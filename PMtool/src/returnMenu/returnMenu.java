package returnMenu;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class returnMenu
 */
@WebServlet("/log/returnMenu")
public class returnMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public returnMenu() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("hisList");
		session.removeAttribute("proList");
		session.removeAttribute("count");
		session.removeAttribute("insertjbList");
		session.removeAttribute("updateList");
		session.removeAttribute("deleteIdList");
		session.removeAttribute("inOutJBList");
		session.removeAttribute("historyList");
		session.removeAttribute("productList");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/menujsp/firstMenu.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
