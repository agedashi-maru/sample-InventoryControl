package servletconf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Decision;
import model.History;
import model.ProductJB;
import model.SelectLogic;

/**
 * Servlet implementation class ReceiveConf
 */
@WebServlet("/ReceiveConf")
public class ReceiveConf extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String PUT_IN = "入庫";

	private final String ZERO = "0";

	ProductJB inOutJB = null;
	History history = null;
	SelectLogic selectLogic = new SelectLogic();
	Decision decision = new Decision();
	List<ProductJB> productList = new ArrayList<ProductJB>();
	RequestDispatcher dispatcher = null;
	String firstId = null;
	String firstcount = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReceiveConf() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<ProductJB> inOutJBList = new ArrayList<ProductJB>();
		List<History> historyList = new ArrayList<History>();

		for (int i = 1; i <= 5; i++) {
			firstId = request.getParameter("stockid" + i);
			firstcount = request.getParameter("count" + i);

			if (!(decision.isInt(firstId)) || firstId.equals("")) {
				firstId = ZERO;
			}
			int id = Integer.parseInt(firstId);
			if (id < 0) {
				id = 0;
			}

			if (!(decision.isInt(firstcount)) || firstcount.equals("")) {
				firstcount = ZERO;
			}
			int count = Integer.parseInt(firstcount);
			if (count < 0) {
				count = 0;
			}

			productList = selectLogic.executeFindAll();

			if (count != 0 && id != 0) {
				for (ProductJB productJB : productList) {
					if (productJB.getId() == id) {
						int stock = productJB.getStock() + count;
						inOutJB = new ProductJB(id, productJB.getItem(), stock);
						history = new History(PUT_IN, count, id);
						break;
					}

				}
				inOutJBList.add(inOutJB);
				historyList.add(history);
			}

		}

		boolean flag = false;

		Set<Integer> checkHash = new HashSet<Integer>();
		for (int i = 0; i < inOutJBList.size(); i++) {
			if (checkHash.contains(inOutJBList.get(i).getId())) {
				// 重複があればtrueをセットし終了
				flag = true;
				break;
			} else {
				// 重複しなければハッシュセットへ追加
				checkHash.add(inOutJBList.get(i).getId());
			}
		}

		if (flag) {
			String dupMsg = "※商品IDが重複しています";
			request.setAttribute("dupMsg", dupMsg);
			dispatcher = request.getRequestDispatcher("/jsp/inoutjsp/stockin.jsp");

		} else if (historyList.size() != 0) {
			HttpSession session = request.getSession();
			session.setAttribute("inOutJBList", inOutJBList);
			session.setAttribute("historyList", historyList);
			dispatcher = request.getRequestDispatcher("/jsp/confInOutjsp/confIn.jsp");

		} else {
			String msg = "※項目を正しく入力してください";
			request.setAttribute("msg", msg);
			dispatcher = request.getRequestDispatcher("/jsp/inoutjsp/stockin.jsp");

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
