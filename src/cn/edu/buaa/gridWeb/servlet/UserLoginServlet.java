package cn.edu.buaa.gridWeb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.buaa.gridWeb.service.UserLogInOutService;
import cn.edu.buaa.gridWeb.utility.PrivacyManager;

public class UserLoginServlet extends BaseServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		System.out.println(request.getParameter("data"));
		UserLogInOutService uio = new UserLogInOutService();
		
		String re = uio.userLogin(request.getParameter("data"));
		response.getWriter().write(re);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
