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
 * Servlet implementation class userInfo
 */
@WebServlet("/userInfo")
public class userInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public userInfo() {
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


		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userInfo.jsp");
		dispatcher.forward(request,  response);



	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


	}

}
