package cn.edu.buaa.gridWeb.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import cn.edu.buaa.gridWeb.service.AccountCreateService;
import cn.edu.buaa.gridWeb.service.AccountOperateService;
import cn.edu.buaa.gridWeb.utility.PrivacyManager;

/**
 * Servlet implementation class Account
 * Account 主要是单个用户，查询用户信息，修改用户信息，添加用户备注等。
 * 
 */
public class AccountServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *
	 *
	 * 如果arg为空表示查询user的个人信息
	 * 如果arg不为空，表示对user状态的修改
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String loginuserString = this.getUserName(request);
		
		String arg = request.getParameter("arg");
		if(arg!=null){
			String user = request.getParameter("user");
			AccountOperateService ao = new AccountOperateService();
			ao.editAccountStatus(loginuserString, user, arg);
		}
		else{
			String user = request.getParameter("user");
			response.setCharacterEncoding("utf-8");
			AccountOperateService ao = new AccountOperateService();
			JSONObject accountInfo = ao.getOneAccountInfo(loginuserString, user);
			response.getWriter().write(accountInfo.toString());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 *
	 * 添加remark
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginuserString = this.getUserName(request);
		AccountOperateService ao = new AccountOperateService();
		ao.addAccountRemark(loginuserString,request.getParameter("data"));
		response.getWriter().write("successful");
			
	}

}
