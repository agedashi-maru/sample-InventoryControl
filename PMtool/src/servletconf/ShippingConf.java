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
 * Servlet implementation class ShippingConf
 */
@WebServlet("/ShippingConf")
public class ShippingConf extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String PUT_OUT = "出庫";

	private final String ZERO = "0";

	String firstId = null;
	String firstcount = null;
	int stock = 0;
	String ms = null;
	ProductJB inOutJB = null;
	History history = null;
	SelectLogic selectLogic = new SelectLogic();
	Decision decision = new Decision();
	List<ProductJB> productList = new ArrayList<ProductJB>();
	List<ProductJB> inOutJBList = new ArrayList<ProductJB>();
	List<History> historyList = new ArrayList<History>();
	List<String> strout = new ArrayList<String>();
	RequestDispatcher dispatcher = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShippingConf() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
						stock = productJB.getStock() - count;
						inOutJB = new ProductJB(id, productJB.getItem(), stock);
						history = new History(PUT_OUT, count, id);
						break;
					}
				}
				if (stock < 0) {
					ms = "商品コード " + i + " は出庫超過により処理できませんでした";
					strout.add(ms);
					continue;
				}
				inOutJBList.add(inOutJB);
				historyList.add(history);
			}

		}

		List<Integer> ids = new ArrayList<Integer>();
		boolean flag = false;

		for (ProductJB prodJB : inOutJBList) {
			ids.add(prodJB.getId());
		}

		Set<Integer> checkHash = new HashSet<Integer>();
		for (Integer str : ids) {
			if (checkHash.contains(str)) {
				// 重複があればtrueをセットし終了
				flag = true;
				break;
			} else {
				// 重複しなければハッシュセットへ追加
				checkHash.add(str);
			}
		}

		if (flag) {
			String dupMsg = "※商品IDが重複しています";
			request.setAttribute("dupMsg", dupMsg);
			dispatcher = request.getRequestDispatcher("/jsp/inoutjsp/stockout.jsp");

		} else if (historyList.size() != 0) {
			HttpSession session = request.getSession();
			session.setAttribute("inOutJBList", inOutJBList);
			session.setAttribute("historyList", historyList);
			request.setAttribute("strout", strout);
			dispatcher = request.getRequestDispatcher("/jsp/confInOutjsp/confOut.jsp");

		} else {
			String msg = "※項目を正しく入力してください";
			request.setAttribute("msg", msg);
			dispatcher = request.getRequestDispatcher("/jsp/inoutjsp/stockout.jsp");

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
