package cn.edu.buaa.gridWeb.service;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.buaa.gridWeb.dataservice.restapi.RESTMaster;

public class EmailService {

	/**
	 * @param args
	 */
	public boolean sendEmail(String loginuser, String jsonString) {
		try {
			JSONObject jo = new JSONObject(jsonString);
			String userName = jo.getString("user");
			String content = jo.getString("email");
			RESTMaster instance=RESTMaster.getInstance();
			boolean result = instance.sendEmail(loginuser, userName, content);
			return result;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
