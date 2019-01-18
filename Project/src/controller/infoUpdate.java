package controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

		if(userInfo == null) {
			response.sendRedirect("login");
		}


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
		String password2 = request.getParameter("password2");
		String name = request.getParameter("name");
		String birth_date = request.getParameter("birth_date");
		String loginId = request.getParameter("loginId");

		UserDao userDao = new UserDao();

		/** パスワードとパスワード(確認)の入力内容が異なる場合 **/
		if(!password.equals(password2)) {
			//リクエストスコープにエラーメッセージをセット
			request.setAttribute("errMsg","パスワードが一致しておりません");

			User user = new User();
			user.setLoginId(loginId);
			user.setName(name);
			try {
				user.setBirthDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(birth_date).getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			request.setAttribute("user", user);

			//新規登録jspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/infoUpdate.jsp");
			dispatcher.forward(request, response);
			return;
		}

		/** パスワード以外に未入力の項目がある場合 **/

		if(name.isEmpty() || birth_date.isEmpty()) {
			//リクエストスコープにエラーメッセージをセット
			request.setAttribute("errMsg","入力項目に未入力のものがあります");

			User user = new User();
			user.setLoginId(loginId);
			user.setName(name);
			try {
				user.setBirthDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(birth_date).getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			request.setAttribute("user", user);


			//新規登録jspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/infoUpdate.jsp");
			dispatcher.forward(request, response);
			return;
		}

		if(password.isEmpty() && password2.isEmpty()) {
			//リクエストパラメータの入力項目を引数に渡して、Daoのメソッドを実行
			userDao.updateInsert(name, birth_date, loginId);

		}else {

		//リクエストパラメータの入力項目を引数に渡して、Daoのメソッドを実行
		userDao.userUpdate(password, name, birth_date, loginId);
		}

		// ユーザ一覧のサーブレットにリダイレクト
		// リダイレクトは指定した名前のサーブレットにGETアクセス
		response.sendRedirect("users");

	}

}
