package search;

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

import org.apache.commons.lang3.StringUtils;

import model.Decision;
import model.ProductJB;
import model.SelectLogic;

/**
 * Servlet implementation class StockReference
 */
@WebServlet("/StockReference")
public class StockReference extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String ZERO = "0";

	Decision decision = new Decision();

	private final String NONE = "なし";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public StockReference() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstId = request.getParameter("inoutid");
		String firstStock = request.getParameter("inoutstock");
		SelectLogic selectLogic = new SelectLogic();
		List<ProductJB> productList = new ArrayList<ProductJB>();
		ProductJB inOutJB = null;
		RequestDispatcher dispatcher = null;
		String inout = request.getParameter("action");

		if (StringUtils.isEmpty(firstId) || !(decision.isInt(firstId))) {
			firstId = ZERO;
		}
		int id = Integer.parseInt(firstId);
		if (id < 0) {
			id = 0;
		}

		String item = request.getParameter("inoutitem");
		String kind = request.getParameter("inoutkind");
		String group = request.getParameter("inoutgroup");
		if (StringUtils.isEmpty(firstStock) || !(decision.isInt(firstStock))) {
			firstStock = NONE;
		}
		selectLogic = new SelectLogic();

		if (id==0 && StringUtils.isEmpty(item) && StringUtils.isEmpty(kind) && StringUtils.isEmpty(group) && firstStock.equals(NONE)) {
			productList = selectLogic.executeFindAll();
		}else if (firstStock.equals(NONE)) {
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
			dispatcher = request.getRequestDispatcher("/jsp/inoutjsp/stockin.jsp");

		}else if(inout.equals("inout2")){
			dispatcher = request.getRequestDispatcher("/jsp/inoutjsp/stockout.jsp");

		}
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
