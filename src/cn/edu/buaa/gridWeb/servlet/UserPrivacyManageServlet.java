package cn.edu.buaa.gridWeb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import cn.edu.buaa.gridWeb.service.AccountsQueryService;
import cn.edu.buaa.gridWeb.service.UserPermissionManageService;

public class UserPrivacyManageServlet extends BaseServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserPermissionManageService ums = new UserPermissionManageService();
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(ums.getPermissionList());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 设置权限
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserPermissionManageService ums = new UserPermissionManageService();
		ums.setUserPrivacy(request.getParameter("data"));
		response.getWriter().write("1");
	}
}
