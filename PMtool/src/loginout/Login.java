package loginout;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import loginmodel.Account;
import loginmodel.LoginLogic;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final String ENCODE_UTF8 = "UTF-8";

	private final String USER_ID = "userid";

	private final String PASS = "pass";

	private final String USER = "user";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(ENCODE_UTF8);
		String userid = request.getParameter(USER_ID);
		String pass = request.getParameter(PASS);
		Account user = new Account(userid,pass);
		LoginLogic logic = new LoginLogic();

		boolean isLogin = logic.execute(user);

		if (isLogin) {
			HttpSession session = request.getSession();
			session.setAttribute(USER, user);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/menujsp/firstMenu.jsp");
			dispatcher.forward(request, response);
		}else {
			response.sendRedirect("/PMtool/Logfail");
		}



	}

}
