package cn.edu.buaa.gridWeb.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.buaa.gridWeb.service.AccountEmailTemplateService;
import cn.edu.buaa.gridWeb.service.EmailService;

/**
 * Servlet implementation class Email
 * 主要负责email发送功能，针对退修、设为试用账户，转正的email发送
 */
public class EmailServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmailServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
 	 * 根据服务器中的模板文件，设置模板内容
	 * $account$, $applicant$
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("user");
		String emailArg = request.getParameter("emailArg");
		AccountEmailTemplateService ae = new AccountEmailTemplateService();
		String emailTempalte = ae.getEmailTemplate(userName, emailArg);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(emailTempalte);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginuser = this.getUserName(request);
		String jsonString = request.getParameter("data");
		EmailService emailService = new EmailService();
		boolean result = emailService.sendEmail(loginuser, jsonString);
		if (result)
			response.getWriter().write("1");
		else {
			response.getWriter().write("0");
		}
	}

}
