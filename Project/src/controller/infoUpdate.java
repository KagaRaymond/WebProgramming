package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.User;

/**
 * Servlet implementation class infoUpdate
 */
@WebServlet("/infoUpdate")
public class infoUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public infoUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//リクエストパラメータの文字コードを指定
		request.setCharacterEncoding("UTF-8");

		String id = request.getParameter("id");

		UserDao userDao = new UserDao();
		User userInfo = userDao.findUserById(id);

		request.setAttribute("user", userInfo);


		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/infoUpdate.jsp");
		dispatcher.forward(request,  response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//リクエストパラメータの文字コードを指定
		request.setCharacterEncoding("UTF-8");

		//リクエストパラメータの入力項目を取得
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String birth_date = request.getParameter("birth_date");
		String loginId = request.getParameter("loginId");

		//リクエストパラメータの入力項目を引数に渡して、Daoのメソッドを実行
		UserDao userDao = new UserDao();
		userDao.userUpdate(password, name, birth_date, loginId);

		// ユーザ一覧のサーブレットにリダイレクト
		// リダイレクトは指定した名前のサーブレットにGETアクセス
		response.sendRedirect("users");

	}

}
