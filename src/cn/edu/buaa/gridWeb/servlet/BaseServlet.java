package cn.edu.buaa.gridWeb.servlet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.Cookies;

public class BaseServlet extends HttpServlet{
	public String getUserName(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("user"))
			{
				return cookie.getValue();
			}
		}
		return "";
	}
}
