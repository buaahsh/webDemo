package cn.edu.buaa.gridWeb.dataservice.restapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonArray;

import cn.edu.buaa.gridWeb.dataservice.account.data.AccountStatus;
import cn.edu.buaa.gridWeb.dataservice.account.data.EmailData;
import cn.edu.buaa.gridWeb.dataservice.account.data.MapSetRequestData;
import cn.edu.buaa.gridWeb.dataservice.account.data.RemarkSaveData;
import cn.edu.buaa.gridWeb.dataservice.account.data.UserLdapData;
import cn.edu.buaa.gridWeb.dataservice.account.data.UserSaveFullData;
import cn.edu.buaa.gridWeb.dataservice.client.ApiHttpClient4Account;
import cn.edu.buaa.gridWeb.model.UserApplyInfo;
import cn.edu.buaa.gridWeb.model.UserInfo;

public class RESTMaster {
	private static RESTMaster instance;
	private RESTMaster(){
	}
	public static RESTMaster getInstance(){
		if (instance==null)
			instance=new RESTMaster();
		return instance;
	}
	private HashMap<String,RESTConnnectionImpl> connectionMap=new HashMap<String,RESTConnnectionImpl>();
	public void init(){
		
	}
	
	/**
	 * 用户进行登录
	 * @param loginUser
	 * @param password
	 * @return boolean
	 */
	public boolean userLogin(String appKey,String loginUser, String password) {
		ApiHttpClient4Account client=new ApiHttpClient4Account("rest.scgrid.cn","v1", 80,
				443, "", true);
		//client.login("test", "caorq", "api2013july");	
		boolean result = client.login(appKey, loginUser, password);
		if (result)
		{
			RESTConnnectionImpl clientImpl=new RESTConnnectionImpl(client);
			this.connectionMap.put(loginUser, clientImpl);
			return true;
		}
		return false;
	}
	
	/**
	 * 用户注销
	 * @param loginUser
	 */
	public boolean userLogout(String loginUser) {
		if (connectionMap.containsKey(loginUser) == false)
			return true;
		ApiHttpClient4Account curentClient=connectionMap.get(loginUser).client;
		curentClient.logout();
		curentClient.close();
		return true;
	}
	
	/**
	 * 得到不同状态的用户个数
	 */
	public int[] getAccountProfile(String loginUser) {
		int[] result = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		ApiHttpClient4Account curentClient=connectionMap.get(loginUser).client;
		String resultString = curentClient.getAccountProfile();
		try {
			JSONObject jo = new JSONObject(resultString);
			JSONObject params = jo.getJSONObject("response_params");
			/*
			 * 待确认（USER_APPLY ）
			 * 待审批（APPLY_CONFIRM+APPLY_REVISED）
			 * 待建立（APPLY_APPROVED）
			 * 待映射（ACCOUNT_CREATE）
			 * 待创建vpn（MAP_SUCCESS+MAP_PART）
			 * 待设置为试用账号（VPN_CREATE）
			 * 待设置为正式账号 （ACCOUNT_TEST）
			 */
			result[0] = params.getInt("USER_APPLY");
			result[1] = params.getInt("APPLY_CONFIRM") + params.getInt("APPLY_REVISED") + params.getInt("APPLY_REVISE");
			result[2] = params.getInt("APPLY_APPROVED");
			result[4] = params.getInt("MAP_SUCCESS");
			result[3] = params.getInt("ACCOUNT_CREATE") - result[4];
			result[5] = params.getInt("VPN_CREATE");
			result[6] = params.getInt("ACCOUNT_TEST");
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public ArrayList<UserInfo> getAccounts(String loginuser, int status, String user, String email,
			String applicant)
	{
		ArrayList<UserInfo> userInfoList = new ArrayList<UserInfo>();
		ApiHttpClient4Account curentClient=connectionMap.get(loginuser).client;
		if (transferRESTStatus(status) == null)
			return userInfoList;
		int restStatus = transferRESTStatus(status)[0];
		int restMapStatus = transferRESTStatus(status)[1];
		String resultString = curentClient.queryAccountsByArgs(user, applicant, email, String.valueOf(restStatus));
		
		try {
			JSONObject jo = new JSONObject(resultString);
			JSONArray jsonArray = jo.getJSONArray("response_params");
			for (int i=0; i < jsonArray.length(); i++) {
				JSONObject item = jsonArray.getJSONObject(i);
				int mapStatus = Integer.parseInt(item.getString("map_status"));
				if (restMapStatus == 2 && mapStatus != 2)
					continue;
				if (restMapStatus == 0 && mapStatus == 2)
					continue;
				
				int userStatus = transferUserStatus(Integer.parseInt(item.getString("user_status")), Integer.parseInt(item.getString("map_status")));
				UserInfo userInfo = new UserInfo(item.getString("account"), userStatus);
				UserApplyInfo userApplyInfo = new UserApplyInfo(item.getString("account"));
				userApplyInfo.setApplicant(item.getString("applicant"));
				userApplyInfo.setEmail(item.getString("email"));
				userInfo.userApplyInfo = userApplyInfo;
				userInfoList.add(userInfo);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return userInfoList;
	}
	
	public UserInfo getOneUserInfo(String loginuser, String userString) {
		ApiHttpClient4Account curentClient=connectionMap.get(loginuser).client;
		String resultString = curentClient.getAccountByName(userString, true, true);
		try {
			JSONObject jo = new JSONObject(resultString);
			JSONObject userJsonInfo = jo.getJSONObject("response_params").getJSONObject("accunt");
			//JSONArray projectJsonObject = jo.getJSONArray("projects");
			//JSONArray softwaresJsonObject = jo.getJSONArray("softwares");
			
			int userStatus = transferUserStatus(Integer.parseInt(userJsonInfo.getString("user_status")), Integer.parseInt(userJsonInfo.getString("map_status")));
			UserInfo userInfo = new UserInfo(userJsonInfo.getString("account"), userStatus);
			UserApplyInfo userApplyInfo = new UserApplyInfo(userJsonInfo.getString("account"));
			userApplyInfo.setApplicant(userJsonInfo.getString("applicant"));
			userApplyInfo.setEmail(userJsonInfo.getString("email"));
			userApplyInfo.setAdress(userJsonInfo.getString("address"));
			userApplyInfo.setApplyCompany(userJsonInfo.getString("company"));
			userApplyInfo.setHard(userJsonInfo.getString("disk"));
			userApplyInfo.setMachineHours(userJsonInfo.getString("walltime"));
			userApplyInfo.setMemory(userJsonInfo.getString("mem"));
			//userApplyInfo.setProjectInfo(projectInfo);
			//userApplyInfo.setSoft(soft);
			userApplyInfo.setTel(userJsonInfo.getString("tel"));
			userInfo.userApplyInfo = userApplyInfo;
			return userInfo;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * 
	 * @param loginuser 
	 * @param userString
	 * @param lockStatus TATUS_UNLOCKED=1;//解锁状态 STATUS_LOCKED=2;//锁定状态 
	 * @param mapStatus MAP_SUCCESS=1;//映射成功 MAP_FAILURE=2;//映射失败 MAP_PART=3;//部分成功
	 * @param userStatus
	 * @return
	 */
	public boolean editUserStatus(String loginuser, String userString,
			int lockStatus, int mapStatus, int userStatus) {
		AccountStatus status=new AccountStatus();
		status.setLock_status(lockStatus);
		status.setMap_status(mapStatus);
		status.setUser_status(userStatus);
		ApiHttpClient4Account curentClient=connectionMap.get(loginuser).client;
		String result = curentClient.setAccountStatus(userString, status);
		if (result != null)
			return true;
		return false;
	}
	
	public boolean addLdaUser(String loginuser, String userString ,int gid, int uid,
			String emailString, String homeDirString, String loginShell,
			String telPhone) {
		ApiHttpClient4Account curentClient=connectionMap.get(loginuser).client;
		UserLdapData data=new UserLdapData();
		data.setEmail(emailString);
		data.setGidNumber(gid);
		data.setHomeDirectory(homeDirString);
		data.setLoginShell(loginShell);
		data.setTelPhone(telPhone);
		data.setUidNumber(uid);
		String result = curentClient.addLdapUser(userString, data);
		if (result != null)
			return true;
		return false;
	}
	
	public String queryUid(String loginuser) {
		ApiHttpClient4Account curentClient=connectionMap.get(loginuser).client;
		String resultString = curentClient.getLdapIDArray(0, 5000);
		JSONObject jo;
		String result = null;
		try {
			jo = new JSONObject(resultString);
			result = jo.getString("response_params");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public JSONArray getOneAccountLog(String loginuser, String userName) {
		JSONArray logArray = null;
		ApiHttpClient4Account curentClient=connectionMap.get(loginuser).client;
		String resultString = curentClient.getActionLogByAccount(userName);
		if (resultString != null)
		{
			try {
				JSONObject jo = new JSONObject(resultString);
				logArray = jo.getJSONArray("response_params");
				return logArray;
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return logArray;
	}
	
	public boolean sendEmail(String loginuser, String userName, String content) {
		ApiHttpClient4Account curentClient=connectionMap.get(loginuser).client;
		EmailData email=new EmailData();
		email.setApplyTableFlag(true);
		UserInfo userInfo = this.getOneUserInfo(loginuser, userName);
		//System.err.print(userInfo.userApplyInfo.email);
		email.setTomails(userInfo.userApplyInfo.email);
		email.setSubject(userName + ",你好");
		email.setContent(content);
		
		String result = curentClient.sendEmail(userName, email);
		if (result != null)
			return true;
		return false;
	}
	
	public boolean addRemark(String loginuser, String userName, String content) {
		ApiHttpClient4Account curentClient=connectionMap.get(loginuser).client;
		RemarkSaveData remark=new RemarkSaveData();
		remark.setNote("");
		remark.setRemark(content);
		String result = curentClient.setRemark(userName, remark);
		if (result != null)
			return true;
		return false;
	}
	
	public JSONArray getOneAccountRemark(String loginuser, String userName) {
		ApiHttpClient4Account curentClient=connectionMap.get(loginuser).client;
		String result = curentClient.getRemarkByAccount(userName);
		JSONObject jo;
		try {
			jo = new JSONObject(result);
			JSONArray remarkArray = jo.getJSONArray("response_params");
			return remarkArray;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean ModifyUserFullData(String loginuser, String userName, UserSaveFullData uData) {
		ApiHttpClient4Account curentClient=connectionMap.get(loginuser).client;
		boolean result = curentClient.saveAccount(userName, uData);
		return result;
	}
	
	public String[] getHPCNames(String loginuser) {
		ApiHttpClient4Account curentClient=connectionMap.get(loginuser).client;
		String result = curentClient.getHPCNames();
		try {
			JSONObject jo = new JSONObject(result);
			String paramsString = jo.getString("response_params");
			String[] hpc = paramsString.split(" ");
			return hpc;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public JSONArray getMapArray(String loginuser, String username) {
		ApiHttpClient4Account curentClient=connectionMap.get(loginuser).client;
		List<String> usernames=new ArrayList<String>();
		usernames.add(username);
		List<String> hpcnames=new ArrayList<String>();
		for (String string : this.getHPCNames(loginuser)) {
			hpcnames.add(string);
		}
		String result = curentClient.getumap(true, usernames, hpcnames);
		JSONObject jo;
		try {
			jo = new JSONObject(result);
			JSONArray remarkArray = jo.getJSONObject("response_params").getJSONArray("mapping");
			return remarkArray;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String[] addMapUser(String loginuser, String username, List<String> hpcnames, String localuser) {
		ApiHttpClient4Account curentClient=connectionMap.get(loginuser).client;
		MapSetRequestData mdata=new MapSetRequestData();
		mdata.setGridUser(username);
		mdata.setLocalUser(localuser);
		mdata.setHpcList(hpcnames);
		String resultString = curentClient.setumap(mdata);
		JSONObject jo;
		try {
			jo = new JSONObject(resultString);
			String HPCList = jo.getJSONObject("response_params").getString("okHPCListString");
			String[]  HPCStrings = HPCList.split(",");
			return HPCStrings;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * add user
	 * 
	 * @param username
	 * @return
	 */
	public boolean addUser(String loginuser,UserInfo userInfo){
		
		ApiHttpClient4Account curentClient=connectionMap.get(loginuser).client;
		UserLdapData data=userInfo.toRESTModel();
		curentClient.addLdapUser("caorq_test", data);
		//change to toRESTModel();
		{
		curentClient.getLdapIDArray(1,5000);
		//新建用户
		data=new UserLdapData();
		data.setEmail("caorq@sccas.cn");
		data.setGidNumber(3000);
		data.setHomeDirectory("/bin/bash");
		data.setLoginShell("/bin/bash");
		data.setTelPhone("010-58812152");
		data.setUidNumber(6001);
		curentClient.addLdapUser("caorq_test", data);
		curentClient.addLdapUser("caorq_test3", data);
		}
		return false;
	}

	
	private int transferUserStatus(int restUserStatus, int mapStatus) {
		/*
		 * 	 USER_APPLY=1;//新申请的用户
			 APPLY_CONFIRM=2;//确认 
			 CONFIRM_OVERTIME=3;//确认超时
			 APPLY_APPROVED=4;//批准申请
			 APPLY_REVISE=5;//申请退修
			 APPLY_REVISED=6;//申请修回
			 APPLY_REJECT=7;//拒绝申请
			 ACCOUNT_CREATE=8;//创建 LDAP 账号
			 VPN_CREATE=9;//创建 VPN
			 ACCOUNT_TEST=10;//测试账号
			 ACCOUNT_FORMAL=11;//正式账号
			 ACCOUNT_DELETE=12;//删除账号
			 
			 MAP_SUCCESS=1;//映射成功
			 MAP_FAILURE=2;//映射失败
			 MAP_PART=3;//部分成功
		 */
		/*
		 * {"", "待确认" 1,"待审批" 2, "退修" 3, "修回" 4,"待建立" 5, 
		 * "待映射" 6, "创建VPN" 7, "待设试用" 8, "待转正" 9, "正式" 10, "无效" 11};
		 */
		switch (restUserStatus) {
		case 1:
			return 1;
		case 2:
			return 2;
		case 5:
			return 3;
		case 6:
			return 4;
		case 4:
			return 5;
		case 8:
			if (mapStatus==2)
				return 6;
			else
				return 7;
		case 9:
			return 8;
		case 10:
			return 9;
		case 11:
			return 10;
		default:
			return 0;
		}
	}

	/**
	 * restStatus = {user_status, map_status}
	 * map_status = -1 : any map_status
	 * map_status = 0 :  map_status != 2
	 * 
	 */
	private int[] transferRESTStatus(int userStatus)
	{
		int[] restStatus = null;
		switch (userStatus) {
		case 1:
			restStatus = new int[]{1,-1};break;
		case 2:
			restStatus = new int[]{2,-1};break;
		case 3:
			restStatus = new int[]{5,-1};break;
		case 4:
			restStatus = new int[]{6,-1};break;
		case 5:
			restStatus = new int[]{4,-1};break;
		case 6:
			restStatus = new int[]{8,2};break;
		case 7:
			restStatus = new int[]{8,0};break;
		case 8:
			restStatus = new int[]{9,-1};break;
		case 9:
			restStatus = new int[]{10,-1};break;
		case 10:
			restStatus = new int[]{11,-1};break;
		default:
			return new int[]{0,-1};
		}
		return restStatus;
	}
}
