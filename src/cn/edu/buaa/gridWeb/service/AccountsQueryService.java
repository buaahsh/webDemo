package cn.edu.buaa.gridWeb.service;

import java.util.ArrayList;

import org.json.*;

import cn.edu.buaa.gridWeb.dataservice.restapi.RESTMaster;
import cn.edu.buaa.gridWeb.model.UserInfo;
import cn.edu.buaa.gridWeb.service.TestDataService;

public class AccountsQueryService {

	/**
	 * 从testdata中统计各个状态的个数，返回json. 格式如下： { "status_1":"3", "status_2":"5",
	 * ... }
	 */
	public JSONObject getAccountProfile(String userString) {
		/*
		JSONObject accountsProfile = new JSONObject();
		int[] result = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		// TestData testData = new TestData();
		for (UserInfo userInfo : TestDataService.userInfoList) {
			if (userInfo.status == 1)
				result[0] += 1;
			else if (userInfo.status >= 2 && userInfo.status <= 4)
				result[1] += 1;
			else
				result[userInfo.status - 3] += 1;
		}

		for (int i = 0; i < result.length; i++) {
			try {
				accountsProfile.put("status_" + String.valueOf(i),
						String.valueOf(result[i]));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return accountsProfile;
		*/
		JSONObject accountsProfile = new JSONObject();
		RESTMaster instance=RESTMaster.getInstance();
		int[] result = instance.getAccountProfile(userString);

		for (int i = 0; i < result.length; i++) {
			try {
				accountsProfile.put("status_" + String.valueOf(i),
						String.valueOf(result[i]));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return accountsProfile;
	}
	
	/**
	 * 参数意义应该可以再修改 status=0 任意状态 status 列表为： user、email、applicant
	 * 如果为null不做要求，不为空即组合查询。
	 * 
	 */
	public JSONObject getAccounts(String loginuser, int status, String user, String email,
			String applicant) {
		
		/*
		ArrayList<UserInfo> userInfoList = new ArrayList<UserInfo>(
				TestDataService.userInfoList);

		//根据条件进行过滤
		if (status > 0) {
			for (int i = 0; i < userInfoList.size(); i++) {
				if (userInfoList.get(i).status != status) {
					userInfoList.remove(i);
					i -= 1;
				}
			}
		}
		if (user != null) {
			if (user.length() > 0)
				for (int i = 0; i < userInfoList.size(); i++) {
					if (!userInfoList.get(i).name.equals(user)) {
						userInfoList.remove(i);
						i -= 1;
					}
				}
		}
		if (email != null) {
			if (email.length() > 0)
				for (int i = 0; i < userInfoList.size(); i++) {
					if (!userInfoList.get(i).userApplyInfo.email.equals(email)) {
						userInfoList.remove(i);
						i -= 1;
					}
				}
		}
		if (applicant != null) {
			if (applicant.length() > 0)
				for (int i = 0; i < userInfoList.size(); i++) {
					if (!userInfoList.get(i).userApplyInfo.applicant
							.equals(applicant)) {
						userInfoList.remove(i);
						i -= 1;
					}
				}
		}
		*/
		RESTMaster instance=RESTMaster.getInstance();
		
		ArrayList<UserInfo> userInfoList = instance.getAccounts(loginuser, status, user, email, applicant);
		
		JSONObject jsonResult = new JSONObject();
		JSONArray jsonUsers = new JSONArray();
		
		/*
		 * 待确认 1 待审批 2 退修 3 修回 4 待建立 5 待映射 6 创建VPN 7 待设试用 8 待转正 9
		 */
		String[] statusTableStrings = TestDataService.statusTableStrings;
		for (UserInfo userInfo : userInfoList) {
			JSONObject userJson = new JSONObject();
			try {
				userJson.put("name", userInfo.name);
				userJson.put("email", userInfo.userApplyInfo.email == null ? ""
						: userInfo.userApplyInfo.email);
				userJson.put("applicant",
						userInfo.userApplyInfo.applicant == null ? ""
								: userInfo.userApplyInfo.applicant);
				userJson.put("status", statusTableStrings[userInfo.status]);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			jsonUsers.put(userJson);
		}
		try {
			jsonResult.put("users", jsonUsers);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonResult;
	}

	public static void main(String[] args) {
		AccountsQueryService aq = new AccountsQueryService();
		// System.out.print(aq.getAccountProfile());
		// JSONObject jo = aq.getAccounts(-1, "user1", null, null);
	//	System.out.println(aq.getAccounts(2, null, null, null).toString());
	//	System.out.println(aq.getAccounts(0, "user1", null, null).toString());
	}

}
