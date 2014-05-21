package cn.edu.buaa.gridWeb.service;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.buaa.gridWeb.dataservice.restapi.RESTMaster;
import cn.edu.buaa.gridWeb.model.UserInfo;

public class AccountCreateService {

	/**
	 * 创建用户，现在的参数只有uid和userName
	 * 返回arg参数，分别代表
	 * 1	创建成功
	 * 2	创建失败，uid已经被占用
	 * 3	创建失败，uid不存在
	 */
	public String createAccount(String loginUser,  String jsonString){
		RESTMaster instance=RESTMaster.getInstance();
		String uid = null;
		String userName = null;
		String email = null;
		String telphoneString = null;
		int gid = 3000;
		String loginShell = "/bin/bash";
		String homeDirectory = "/bin/bash";
		try {
			JSONObject userUidJson = new JSONObject(jsonString);
			uid = userUidJson.getString("uid");
			userName = userUidJson.getString("user");
			
			UserInfo userInfo = instance.getOneUserInfo(loginUser, userName);
			email = userInfo.userApplyInfo.email;
			telphoneString = userInfo.userApplyInfo.tel;
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		int uid_int = Integer.valueOf(uid);
		
		//judge uid is available
		String uidString = instance.queryUid(loginUser);
		if (isAvailable(uidString, uid_int))
		{	
			boolean result = instance.addLdaUser(loginUser, userName, gid, uid_int, email, homeDirectory, loginShell, telphoneString);
			if (result)
				return "1";
			return "2";
		}
		else {
			return "2";
		}
		/*
		if(!TestDataService.uidMap.containsKey(uid)){
			return "3"; 
		}
		else if(!TestDataService.uidMap.get(uid).equals("")){
			return "2";
		}
		else {
			TestDataService.uidMap.put(uid, userName);
			for(UserInfo userInfo:TestDataService.userInfoList){
				if(userInfo.name.equals(userName)){
					userInfo.userApplyInfo.uid = uid;
				}
			}
			return "1";
		}
		*/
	}
	private boolean isAvailable(String uidString, int uid) {
		String[] itemsStrings = uidString.split(" ");
		for (String string : itemsStrings) {
			int min = Integer.valueOf(string.split(",")[0]);
			int max = Integer.valueOf(string.split(",")[1]);
			if (uid>= min && uid <= max)
				return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AccountCreateService ac = new AccountCreateService();
		//System.out.println(ac.createAccount("{\"user\":\"user4\",\"uid\":\"das\"}"));
//		System.out.println(ac.createAccount("3232", "fsd"));
//		System.out.println(ac.createAccount("3232", "fs"));
	}

}
