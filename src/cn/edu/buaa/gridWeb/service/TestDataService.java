package cn.edu.buaa.gridWeb.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.edu.buaa.gridWeb.model.UserInfo;
import cn.edu.buaa.gridWeb.model.UserApplyInfo;

public class TestDataService {

	/**
	 * @param args
	 * 
	 */
	static String[] statusTableStrings = {"", "待确认","待审批", "退修", "修回",
		"待建立", "待映射", "创建VPN", "待设试用", "待转正", "正式", "无效"};
	static ArrayList<UserInfo> userInfoList = new ArrayList<UserInfo>();
	static Map<String,String> uidMap = new HashMap<String,String>();
	/*
	 * 生成测试数据 
	 */
	static
	{
		/*
		 * 生成用户状态和申请表信息
		 */
		for(int i=1; i<15; i++){
			String userName = "user" + String.valueOf(i);
			// TODO:其中用户状态有10种
			int userStatus = i%10 + 1;
			UserInfo userInfo = new UserInfo(userName, userStatus);
			UserApplyInfo usi = new UserApplyInfo(userName);
			usi.updateApplyInfo("北京市海淀区", userName+"@sccas.cn", "13800138000", userName, "", "北航", "",
					"8G", "gaussian、vasp", "50G", userName, "1万CPU小时", "");
			userInfo.userApplyInfo = usi;
			userInfoList.add(userInfo);
			
		}
		
		/*
		 * 生成申请表流程信息
		 */
		for(UserInfo userInfo:userInfoList){
			String[] oneApplyInfo = {"2014.1.2 13:30", "申请账号", userInfo.name};
			String[] twoApplyInfo = {"2014.1.2 14:00", "确认", userInfo.name};
			ArrayList<String[]> applyProcess = new ArrayList<>();
			applyProcess.add(oneApplyInfo);
			applyProcess.add(twoApplyInfo);
			userInfo.applyProcess = applyProcess;
		}
		/*
		 * 
		 */
		/*
		 * 生成 uid Map
		 */
		for(int i=3004; i<=3300; i++){
			String uidString = String.valueOf(i);
			uidMap.put(uidString, "");
		}
			
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestDataService testData  = new TestDataService();
//		for(UserInfo userInfo:testData.userInfoList){
//			System.out.println(userInfo.name);
//			System.out.println(userInfo.status);
//		}
////		testData.userInfoList.get(0).status = 3;
//		for(UserInfo userInfo:testData.userInfoList){
//			System.out.println(userInfo.name);
//			System.out.println(userInfo.status);
//		}
		
		for(UserInfo userInfo:testData.userInfoList){
			System.out.println(userInfo.name);
			System.out.println(userInfo.applyProcess);
		}
	}

}
