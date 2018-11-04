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

	private final String ENCODE_UTF8 = "UTF-8";

	private final String NO_HISTORY_ERROR_MSG = "※照会方法を選択してください";

	private final String HISTORY = "history";

	private final String HIS_LIST = "hisList";

	private final String NO_HISTORY = "noHistory";

	private final String HIS_1 = "his1";

	private final String HIS_2 = "his2";

	private final String HIS_3 = "his3";

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

		request.setCharacterEncoding(ENCODE_UTF8);
		HttpSession session = request.getSession();
		List<History> hisList = new ArrayList<History>();
		HistoryLogic historyLogic = new HistoryLogic();
		String history = request.getParameter(HISTORY);

		if (history == null) {
			request.setAttribute(NO_HISTORY, NO_HISTORY_ERROR_MSG);

		}else{
			if (history.equals(HIS_1)) {
			hisList = historyLogic.inLogic();

			}else if(history.equals(HIS_2)){
				hisList = historyLogic.outLogic();

			}else if (history.equals(HIS_3)) {
				hisList = historyLogic.inOutLogic();

			}
			session.setAttribute(HIS_LIST, hisList);

		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/menujsp/selectMenu.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
