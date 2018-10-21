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
 * Servlet implementation class InOutConf
 */
@WebServlet("/log/InOutConf")
public class InOutConf extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InOutConf() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ProductJB inOutJB = null;
		History history = null;
		SelectLogic selectLogic = new SelectLogic();
		Decision decision = new Decision();
		List<ProductJB> productList = new ArrayList<ProductJB>();
		List<ProductJB> inOutJBList = new ArrayList<ProductJB>();
		List<History> historyList = new ArrayList<History>();
		List<String> strout = new ArrayList<String>();
		String inout = request.getParameter("action");
		if (inout.equals("in")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/inoutjsp/stockin.jsp");
			dispatcher.forward(request, response);
		} else if(inout.equals("out")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/inoutjsp/stockout.jsp");
			dispatcher.forward(request, response);
		}else if (inout.equals("stockin")) {

			String firstId;
			String firstcount;
			for (int i = 1; i <= 5; i++) {
				firstId = request.getParameter("stockid" + i);
				firstcount = request.getParameter("count" + i);

				if (!(decision.isInt(firstId)) || firstId.equals("")) {
					firstId = "0";
				}
				int id = Integer.parseInt(firstId);
				if (id < 0) {
					id = 0;
				}

				if (!(decision.isInt(firstcount)) || firstcount.equals("")) {
					firstcount= "0";
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
							inOutJB = new ProductJB(id,productJB.getItem(),stock);
							history = new History("入庫", count,id);
							break;
						}

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
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/inoutjsp/stockin.jsp");
				dispatcher.forward(request, response);
			} else if (historyList.size() != 0) {
				HttpSession session = request.getSession();
				session.setAttribute("inOutJBList", inOutJBList);
				session.setAttribute("historyList", historyList);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/confInOutjsp/confIn.jsp");
				dispatcher.forward(request, response);
			}else {
				String msg = "※項目を正しく入力してください";
				request.setAttribute("msg", msg);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/inoutjsp/stockin.jsp");
				dispatcher.forward(request, response);
			}

		}else if (inout.equals("stockout")) {

			String firstId;
			String firstcount;
			int stock = 0;
			String ms = null;
			for (int i = 1; i <= 5; i++) {
				firstId = request.getParameter("stockid" + i);
				firstcount = request.getParameter("count" + i);

				if (!(decision.isInt(firstId)) || firstId.equals("")) {
					firstId = "0";
				}
				int id = Integer.parseInt(firstId);
				if (id < 0) {
					id = 0;
				}

				if (!(decision.isInt(firstcount)) || firstcount.equals("")) {
					firstcount = "0";
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
							inOutJB = new ProductJB(id,productJB.getItem(),stock);
							history = new History("出庫", count,id);
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/inoutjsp/stockout.jsp");
				dispatcher.forward(request, response);
			} else if (historyList.size() != 0) {
				HttpSession session = request.getSession();
				session.setAttribute("inOutJBList", inOutJBList);
				session.setAttribute("historyList", historyList);
				request.setAttribute("strout", strout);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/confInOutjsp/confOut.jsp");
				dispatcher.forward(request, response);
			}else{
				String msg = "※項目を正しく入力してください";
				request.setAttribute("msg", msg);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/inoutjsp/stockout.jsp");
				dispatcher.forward(request, response);
			}

		}else{

			String firstId = request.getParameter("inoutid");
			String firstStock = request.getParameter("inoutstock");

			if (firstId.equals("") || !(decision.isInt(firstId))) {
				firstId = "0";
			}
			int id = Integer.parseInt(firstId);
			if (id < 0) {
				id = 0;
			}

			String item = request.getParameter("inoutitem");
			String kind = request.getParameter("inoutkind");
			String group = request.getParameter("inoutgroup");
			if (firstStock.equals("") || !(decision.isInt(firstStock))) {
				firstStock = "なし";
			}
			selectLogic = new SelectLogic();

			if (id==0 && (item==null || item.length()==0) && (kind==null || kind.length()==0) && (group==null || group.length()==0) && firstStock.equals("なし")) {
				productList = selectLogic.executeFindAll();
			}else if (firstStock.equals("なし")) {
				inOutJB = new ProductJB(id,item,kind,group);
				productList = selectLogic.executeSomeMatch(inOutJB);
			}else {
				int stock = Integer.parseInt(firstStock);
				inOutJB = new ProductJB(id,item,kind,group,stock);
				productList = selectLogic.executeStockMatch(inOutJB);
			}
			HttpSession session = request.getSession();
			session.setAttribute("productList", productList);

			if (inout.equals("inout1")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/inoutjsp/stockin.jsp");
				dispatcher.forward(request, response);
			}else if(inout.equals("inout2")){
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/inoutjsp/stockout.jsp");
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
