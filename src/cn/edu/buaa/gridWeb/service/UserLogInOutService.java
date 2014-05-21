package cn.edu.buaa.gridWeb.service;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.buaa.gridWeb.dataservice.restapi.RESTMaster;

public class UserLogInOutService {

	public String userLogin(String jsonString) {
		JSONObject userJson;
		try {
			userJson = new JSONObject(jsonString);
			String loginUser = userJson.getString("user");
			String password = userJson.getString("password");
			RESTMaster instance=RESTMaster.getInstance();
			boolean result =  instance.userLogin("test", loginUser, password);
			return result?"1":"0";
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return "0";
	}
	
	public String userLogout(String userString) {
		RESTMaster instance=RESTMaster.getInstance();
		System.out.println(userString);
		boolean result = instance.userLogout(userString);
		return result?"1":"0";
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
