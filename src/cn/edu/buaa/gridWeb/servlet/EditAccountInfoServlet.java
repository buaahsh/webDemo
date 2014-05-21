package cn.edu.buaa.gridWeb.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.buaa.gridWeb.service.AccountCreateService;
import cn.edu.buaa.gridWeb.service.AccountOperateService;

/**
 * Servlet implementation class EditAccountInfo
 */
public class EditAccountInfoServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditAccountInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginuser = this.getUserName(request);
		AccountOperateService ao = new AccountOperateService();
		System.out.println("~~~~~"+request.getParameter("data"));
		ao.editAccountApplyInfo(loginuser, request.getParameter("data"));
		response.getWriter().write("1");
	}

}
