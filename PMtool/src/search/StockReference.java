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

	private final String NONE = "なし";

	Decision decision = new Decision();

	String returnViewName = null;

	SelectLogic selectLogic = new SelectLogic();

	List<ProductJB> stockReferenceList = new ArrayList<ProductJB>();


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

		String firstId = request.getParameter("stockReferenceId");
		String item = request.getParameter("stockReferenceItem");
		String kind = request.getParameter("stockReferenceKind");
		String group = request.getParameter("stockReferenceGroup");
		String firstStock = request.getParameter("stockReferenceStock");
		String returnViewKey = request.getParameter("action");

		if (StringUtils.isEmpty(firstId) || !(decision.isInt(firstId))) {
			firstId = ZERO;
		}
		int id = Integer.parseInt(firstId);
		if (id < 0) {
			id = 0;
		}

		if (StringUtils.isEmpty(firstStock) || !(decision.isInt(firstStock))) {
			firstStock = NONE;
		}

		if (id==0 && StringUtils.isEmpty(item) && StringUtils.isEmpty(kind) && StringUtils.isEmpty(group) && firstStock.equals(NONE)) {
			stockReferenceList = selectLogic.executeFindAll();
		}else if (firstStock.equals(NONE)) {
			stockReferenceList = selectLogic.executeSomeMatch(new ProductJB(id,item,kind,group));

		}else {
			int stock = Integer.parseInt(firstStock);
			stockReferenceList = selectLogic.executeStockMatch(new ProductJB(id,item,kind,group,stock));
		}

		if (returnViewKey.equals("stockin")) {
			returnViewName = "/jsp/inoutjsp/stockin.jsp";

		}else if(returnViewKey.equals("stockout")){
			returnViewName = "/jsp/inoutjsp/stockout.jsp";

		}else if(returnViewKey.equals("updateMenu")){
			returnViewName = "/jsp/menujsp/updateMenu.jsp";

		}else if(returnViewKey.equals("deleteMenu")){
			returnViewName = "/jsp/menujsp/deleteMenu.jsp";

		}
		HttpSession session = request.getSession();
		session.setAttribute("stockReferenceList", stockReferenceList);

		RequestDispatcher dispatcher = request.getRequestDispatcher(returnViewName);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
