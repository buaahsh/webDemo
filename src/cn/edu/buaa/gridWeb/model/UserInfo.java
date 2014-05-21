package cn.edu.buaa.gridWeb.model;

import java.util.ArrayList;

import cn.edu.buaa.gridWeb.dataservice.account.data.UserLdapData;
import cn.edu.buaa.gridWeb.model.UserApplyInfo;
import cn.edu.buaa.gridWeb.service.TestDataService;

public class UserInfo {

	/**
	 * @param args
	 * status{
	 * 	待确认 	1
	 *  待审批 	2
	 *  退修     	3
	 *  修回    	4
	 *  待建立	5
	 *  待映射  	6
	 *  映射失败 	7//映射失败状态划分为特定状态
	 *  创建VPN	8
	 *  待设试用	9
	 *  待转正	10
	 *  正式		11
	 *  无效		12（暂定）
	 * }
	 * mapInfoList {cluster, mapAccount, status}
	 */
	
	public String name;
	public int status;
	public ArrayList<String[]> applyProcess;
	public UserApplyInfo userApplyInfo;
	public String remarks = "";
	public int specialStatus;
	public ArrayList<String[]> mapInfoList;
	
	public UserInfo(String name, int status){
		this.name = name;
		this.status = status;
	}
	public UserLdapData toRESTModel(){
		UserLdapData info=new UserLdapData();
		return info;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserInfo testInfo = new UserInfo("user1", 1);
//		testInfo.name = "huang";
		System.out.print(testInfo.name);
	}
}
