package cn.edu.buaa.gridWeb.servlet;

import java.io.IOException;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import cn.edu.buaa.gridWeb.service.AccountMapService;
import cn.edu.buaa.gridWeb.service.AccountsQueryService;

public class FailureMapServlet extends BaseServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginUserString = this.getUserName(request);
		String arg = request.getParameter("arg");
		AccountMapService ams = new AccountMapService();
		HashSet<String> failMap = ams.readFailureMap();
		if (arg.equals("num"))
		{
			if (failMap == null)
				response.getWriter().write("0");
			else
				response.getWriter().write(String.valueOf(failMap.size()));
		}
		else if (arg.equals("list")) {
			if (failMap == null)
				response.getWriter().write("");
			else
			{
				JSONArray jsonArray = new JSONArray(failMap);
				response.getWriter().write(jsonArray.toString());
			}
		}
		else if (arg.equals("sub")){
			ams.retryFailureMap(loginUserString);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
