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
import model.HistoryLogic;

/**
 * Servlet implementation class HistoryMain
 */
@WebServlet("/log/HistoryMain")
public class HistoryMain extends HttpServlet {

	private static final long serialVersionUID = 1L;

	final private String noHistory = "※照会方法を選択してください";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoryMain() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		List<History> hisList = new ArrayList<History>();
		HistoryLogic historyLogic = new HistoryLogic();
		String history = request.getParameter("history");

		if (history==null) {
			request.setAttribute("noHistory", noHistory);

		}else{
			if (history.equals("his1")) {
			hisList = historyLogic.inLogic();

			}else if(history.equals("his2")){
				hisList = historyLogic.outLogic();

			}else if (history.equals("his3")) {
				hisList = historyLogic.inOutLogic();

			}
			session.setAttribute("hisList", hisList);

		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/menujsp/selectMenu.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
