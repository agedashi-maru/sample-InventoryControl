package servletconf;

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

import model.Decision;
import model.ProductJB;
import model.SelectLogic;

/**
 * Servlet implementation class DeleteConf
 */
@WebServlet("/log/DeleteConf")
public class DeleteConf extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final String DELMSG = "※項目を正しく入力してください";

	Decision decision = new Decision();
	ProductJB deleteJB = null;
	SelectLogic selectLogic = new SelectLogic();
	RequestDispatcher dispatcher = null;
	List<ProductJB> idList = new ArrayList<ProductJB>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteConf() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		List<ProductJB> deleteIdList = new ArrayList<ProductJB>();

		for (int i = 1; i <= 5; i++) {

			String firstId = request.getParameter("deleteid" + i);
			if (firstId.equals("") || firstId == null || !(decision.isInt(firstId))) {
				firstId = "0";
			}
			int id = Integer.parseInt(firstId);
			if (id < 0) {
				id = 0;
			}
			idList = selectLogic.executeFindId();
			for (ProductJB jb : idList) {
				if ((!(id == 0)) && jb.getId() == id) {
					deleteJB = new ProductJB(id);
					deleteIdList.add(deleteJB);

				}
			}

		}
		if (deleteIdList.size() != 0) {
			deleteIdList = selectLogic.executeSelectById(deleteIdList);
			session.setAttribute("deleteIdList", deleteIdList);
			dispatcher = request.getRequestDispatcher("/jsp/confjsp/confDelete.jsp");

		} else {
			request.setAttribute("delMsg", DELMSG);
			dispatcher = request.getRequestDispatcher("/jsp/menujsp/deleteMenu.jsp");

		}
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
