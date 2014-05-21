package cn.edu.buaa.gridWeb.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.buaa.gridWeb.utility.PrivacyManager;

/**
 * Servlet implementation class Password
 */
public class PasswordServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userString = getUserName(request);
		boolean canAct=PrivacyManager.checkPrivacy(userString,"post",this.getClass());
		if(!canAct){
			response.getWriter().write("#ERROR#");
		}
				
		System.err.println(request.getParameter("data"));
		response.getWriter().write("1");
	}

}
