package cn.edu.buaa.gridWeb.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import cn.edu.buaa.gridWeb.service.AccountCreateService;
import cn.edu.buaa.gridWeb.service.AccountMapService;
import cn.edu.buaa.gridWeb.service.AccountOperateService;

/**
 * Servlet implementation class AccountMap
 */
public class AccountMapServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountMapServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginuser = this.getUserName(request);
		if(request.getParameter("cluster")!=null){
			response.setCharacterEncoding("utf-8");
			AccountMapService am = new AccountMapService();
			JSONArray clusterList = am.getClusterList(loginuser);
			response.getWriter().write(clusterList.toString());
		}
		else{
			String user = request.getParameter("user");
			response.setCharacterEncoding("utf-8");
			AccountMapService am = new AccountMapService();
			JSONArray mapInfo = am.getAccountMap(loginuser, user);
			response.getWriter().write(mapInfo.toString());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginuser = this.getUserName(request);
		AccountMapService am = new AccountMapService();
		
		String result = am.addAccountMap(loginuser, request.getParameter("data"));
		response.getWriter().write(result);

	}

}
