package cn.edu.buaa.gridWeb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.buaa.gridWeb.service.UserLogInOutService;

public class UserLogoutServlet extends BaseServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserLogInOutService uio = new UserLogInOutService();
		String re = uio.userLogout(request.getParameter("user"));
		response.getWriter().write(re);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
