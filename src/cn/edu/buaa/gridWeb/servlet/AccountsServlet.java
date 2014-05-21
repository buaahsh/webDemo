package cn.edu.buaa.gridWeb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.*;

import cn.edu.buaa.gridWeb.service.AccountsQueryService;
import cn.edu.buaa.gridWeb.service.TestDataService;

/**
 * Servlet implementation class Accounts
 * 
 * 用于首页不同状态用户的查询和和符合用户查询条件的用户信息查询
 * 
 */
public class AccountsServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountsServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		/**
		 * status=-1,表示查询不同状态个数
		 * status=0 ,表示任意状态
		 * user
		 * email
		 * applicant
		 */
		String loginUserString = this.getUserName(request);
		
		int status = Integer.parseInt(request.getParameter("status"));
		String user = request.getParameter("user");
		String email = request.getParameter("email");
		String applicant = request.getParameter("applicant");
		response.setCharacterEncoding("utf-8");
		AccountsQueryService aq = new AccountsQueryService();
		if(status==-1){
			JSONObject accountProfile = aq.getAccountProfile(loginUserString);
			response.getWriter().write(accountProfile.toString());
		}
		else {
			JSONObject accountProfile = aq.getAccounts(loginUserString,status, user, email, applicant);
			response.getWriter().write(accountProfile.toString());			
		}
		
//		response.setContentType("application/x-json");  
//		PrintWriter pw = response.getWriter();  
//		pw.print(accountProfile.toString());  
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
