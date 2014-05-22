package cn.edu.buaa.gridWeb.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.buaa.gridWeb.dataservice.account.data.AccountSaveData;
import cn.edu.buaa.gridWeb.dataservice.account.data.UserSaveFullData;
import cn.edu.buaa.gridWeb.dataservice.restapi.RESTMaster;
import cn.edu.buaa.gridWeb.model.*;
import cn.edu.buaa.gridWeb.service.TestDataService;

public class AccountOperateService {

	/**
	 * @param args
	 * 主要涉及到单个账号信息的查询和单个账号信息的编辑
	 * 
	 */
	
	public JSONObject getOneAccountInfo(String loginuser, String userName){
		/**
		 * 得到单个用户的个人信息
		 */
		JSONObject accountInfo = new JSONObject();
		String[] statusTableStrings = TestDataService.statusTableStrings;
		RESTMaster instance=RESTMaster.getInstance();
		UserInfo userInfo = instance.getOneUserInfo(loginuser, userName);
		try {
			accountInfo.put("name", userInfo.name);
			accountInfo.put("status", statusTableStrings[userInfo.status]);
			accountInfo.put("email", userInfo.userApplyInfo.email==null?"":userInfo.userApplyInfo.email);
			accountInfo.put("applicant", userInfo.userApplyInfo.applicant==null?"":userInfo.userApplyInfo.applicant);	
			accountInfo.put("adress", userInfo.userApplyInfo.adress==null?"":userInfo.userApplyInfo.adress);
			accountInfo.put("tel", userInfo.userApplyInfo.tel==null?"":userInfo.userApplyInfo.tel);
			accountInfo.put("applyDoc", userInfo.userApplyInfo.applyDoc==null?"":userInfo.userApplyInfo.applyDoc);
			accountInfo.put("applyCompany", userInfo.userApplyInfo.applyCompany==null?"":userInfo.userApplyInfo.applyCompany);
			accountInfo.put("projectInfo", userInfo.userApplyInfo.projectInfo==null?"":userInfo.userApplyInfo.projectInfo);
			accountInfo.put("memory", userInfo.userApplyInfo.memory==null?"":userInfo.userApplyInfo.memory);
			accountInfo.put("soft", userInfo.userApplyInfo.soft==null?"":userInfo.userApplyInfo.soft);
			accountInfo.put("hard", userInfo.userApplyInfo.hard==null?"":userInfo.userApplyInfo.hard);
			accountInfo.put("VPN", userInfo.userApplyInfo.VPN==null?"":userInfo.userApplyInfo.VPN);
			accountInfo.put("machineHours", userInfo.userApplyInfo.machineHours==null?"":userInfo.userApplyInfo.machineHours);
			accountInfo.put("other", userInfo.userApplyInfo.other==null?"":userInfo.userApplyInfo.other);
			accountInfo.put("uid", userInfo.userApplyInfo.uid==null?"":userInfo.userApplyInfo.uid);

			accountInfo.put("remarks", getOneRemark(loginuser, userName));
		
			accountInfo.put("applyProcess", getOneAccountApplyProcess(loginuser, userName));
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}

		return accountInfo;
	}
	
	public String getOneRemark(String loginuser, String userName) {
		String remarkString = "";
		RESTMaster instance=RESTMaster.getInstance();
		JSONArray jsonArray = instance.getOneAccountRemark(loginuser, userName);
		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				JSONObject jo = jsonArray.getJSONObject(i);
				remarkString += jo.get("remark") + "\n";
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return remarkString;
	}
	public JSONArray getOneAccountApplyProcess(String loginuser, String userName){
		JSONArray applyProcess = new JSONArray();
		RESTMaster instance=RESTMaster.getInstance();
		JSONArray jsonArray = instance.getOneAccountLog(loginuser, userName);
		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				applyProcess.put(i, this.transferApplyProcessInfo(jsonArray.getJSONObject(i)));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return applyProcess;
	}
	
	private String transferApplyProcessInfo(JSONObject jsonObject) {
		String processInfo = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String date = sdf.format(new Date(Long.valueOf(jsonObject.getString("create_time"))));
			processInfo = "操作时间:" + date
					+ "  操作类型:" + jsonObject.getString("action") 
					+ "  操作人：" + jsonObject.getString("operator");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return processInfo;
	}
	/**
	 * 对单个账号的的状态进行修改
	 * arg参数如果为0，代表状态进入下一个状态
	 * 如果是 大于0的数就是跳转的状态ID号（超级管理员的权限以及在退修、修回问题上的跳转）
	 * 
	 */
	public void editAccountStatus(String loginuser, String userName, String argString){
		RESTMaster instance=RESTMaster.getInstance();
		int arg = Integer.parseInt(argString);
		if(arg==0)
		{
			UserInfo userInfo = instance.getOneUserInfo(loginuser, userName);
			if(userInfo.status>=2&&userInfo.status<=4)
				arg = 5;
			else
				arg = userInfo.status + 1;
		}
		int[] restStatus = transferRESTStatus(arg);
		boolean result = instance.editUserStatus(loginuser, userName, restStatus[0], restStatus[2], restStatus[1]);
		
	}
	
	/**
	 * 
	 * @param userStatus
	 * @return lockStatus,userStatus,mapStatus
	 */
	private int[] transferRESTStatus(int userStatus)
	{
		int[] restStatus = null;
		switch (userStatus) {
		case 1:
			restStatus = new int[]{1,1,2};break;
		case 2:
			restStatus = new int[]{1,2,2};break;
		case 3:
			restStatus = new int[]{1,5,2};break;
		case 4:
			restStatus = new int[]{1,6,2};break;
		case 5:
			restStatus = new int[]{1,4,2};break;
		case 6:
			restStatus = new int[]{1,8,2};break;
		case 7:
			restStatus = new int[]{1,8,1};break;
		case 8:
			restStatus = new int[]{1,9,2};break;
		case 9:
			restStatus = new int[]{1,10,2};break;
		case 10:
			restStatus = new int[]{1,11,2};break;
		default:
			return new int[]{1,0,2};
		}
		return restStatus;
	}
	
	public void addAccountRemark(String loginuser, String remarkString){
		String remark = null;
		String userName = null;
		try {
			JSONObject remarkJson = new JSONObject(remarkString);
			remark = remarkJson.getString("remark");
			userName = remarkJson.getString("user");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		RESTMaster instance=RESTMaster.getInstance();
		instance.addRemark(loginuser, userName, remark);
	}
	
	/**
	 * editJson
	 * {
	 * 	name:username
	 * 	adress:adress
	 *  tel:tel
	 *  applicant:applicant
	 *  applyCampany:applyCampany
	 *  memory:memory
	 *  hard:hard
	 *  machineHours:machineHours
	 * }
	 */
	public void editAccountApplyInfo(String loginuser, String editString){
		JSONObject editJson;
		try {
			editJson = new JSONObject(editString);	
			
			String userName = editJson.getString("name");
			UserSaveFullData udata=new UserSaveFullData();
			AccountSaveData account=new AccountSaveData();
			account.setAddress(editJson.getString("adress"));
			account.setApplicant(editJson.getString("applicant"));
			account.setCompany(editJson.getString("applyCompany"));
			account.setDisk(editJson.getString("hard"));
			account.setMem(editJson.getString("memory"));
			account.setTel(editJson.getString("tel"));
			account.setWalltime(editJson.getString("machineHours"));
			
			account.setEmail(editJson.getString("email"));
			account.setInterest("");
			account.setSecurity("VPN");
			
			udata.setAccount(account);
			
			RESTMaster instance=RESTMaster.getInstance();
			instance.ModifyUserFullData(loginuser, userName, udata);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AccountOperateService ao = new AccountOperateService();
//		System.out.println(ao.getOneAccountInfo("user1"));
//		ao.editAccountStatus("user1", "0");
//		System.out.println(ao.getOneAccountInfo("user1"));
		//System.out.println(ao.getOneAccountInfo("user1").toString());
		//ao.getOneAccountInfo("user1");
		
	}

}
