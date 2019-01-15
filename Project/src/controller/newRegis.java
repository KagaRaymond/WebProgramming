package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

/**
 * Servlet implementation class newRegis
 */
@WebServlet("/newRegis")
public class newRegis extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public newRegis() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newRegis.jsp");
		dispatcher.forward(request,  response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//リクエストパラメータの文字コードを指定
				request.setCharacterEncoding("UTF-8");

				//リクエストパラメータの入力項目を取得
				String loginId = request.getParameter("loginId");
				String password = request.getParameter("password");
				String name = request.getParameter("name");
				String birth_date = request.getParameter("birth_date");

				//リクエストパラメータの入力項目を引数に渡して、Daoのメソッドを実行
				UserDao userDao = new UserDao();
				userDao.userInsert(loginId, password, name, birth_date);

				/** 既に登録されているログインIDが入力された場合の登録失敗仕様　**/



				/** パスワードとパスワード(確認)の入力内容が異なる場合の登録失敗仕様　**/



				/** 入力項目に一つでも未入力のものがある場合の登録失敗仕様　**/




				// ユーザ一覧のサーブレットにリダイレクト
				// リダイレクトは指定した名前のサーブレットにGETアクセス
				response.sendRedirect("users");

	}
}
