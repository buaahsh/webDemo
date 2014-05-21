package cn.edu.buaa.gridWeb.service;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.buaa.gridWeb.model.EmailTemplateInfo;
import cn.edu.buaa.gridWeb.model.UserInfo;

public class AccountEmailTemplateService {

	/**
	 * @param args
	 * @throws JSONException 
	 */
	public String getEmailTemplate(String userName, String emailArg)
	{
		String templateString = "";
		switch (emailArg) {
		case "tuixiu":
			templateString = EmailTemplateInfo.EmailTemplateDict.get("tuixiu").replace("$account$", userName);
			break;
		case "shiyong":
			templateString = EmailTemplateInfo.EmailTemplateDict.get("shiyong").replace("$account$", userName);
			break;
		case "zhengshi":
			templateString = EmailTemplateInfo.EmailTemplateDict.get("zhengshi").replace("$account$", userName);
			break;
		default:
			break;
		}
		return templateString;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AccountEmailTemplateService ae = new AccountEmailTemplateService();
		//System.out.print(ae.getEmailTemplate("user1"));
	}

}
