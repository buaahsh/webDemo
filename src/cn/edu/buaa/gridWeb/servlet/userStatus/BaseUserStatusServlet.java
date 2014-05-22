package cn.edu.buaa.gridWeb.servlet.userStatus;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.buaa.gridWeb.service.AccountOperateService;
import cn.edu.buaa.gridWeb.servlet.BaseServlet;
import cn.edu.buaa.gridWeb.utility.PrivacyManager;

public class BaseUserStatusServlet extends BaseServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginuserString = this.getUserName(request);
		PrivacyManager pm=new PrivacyManager();
		String userString = getUserName(request);
		boolean b2=pm.checkPrivacy(userString, "GET", getClass());
		String arg = request.getParameter("arg");
		if (b2){
			if(arg!=null){
				String user = request.getParameter("user");
				AccountOperateService ao = new AccountOperateService();
				ao.editAccountStatus(loginuserString, user, arg);
			}
		}
		else {
			response.getWriter().write("#ERROR#");
		}
	}
}
