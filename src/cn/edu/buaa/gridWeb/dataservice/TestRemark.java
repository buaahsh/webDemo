/**
 * 
 */
package cn.edu.buaa.gridWeb.dataservice;

import cn.edu.buaa.gridWeb.dataservice.account.data.RemarkSaveData;
import cn.edu.buaa.gridWeb.dataservice.client.ApiHttpClient4Account;



/**
 * @author shaohan
 * 测试登录和退出
 */
public class TestRemark {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//ApiHttpClient4Account client=new ApiHttpClient4Account("localhost","restapi3", 8080,
		//		8443, "", true);
		ApiHttpClient4Account client=new ApiHttpClient4Account("rest.scgrid.cn","v1", 80,
				443, "", true);
		client.login("test", "caorq", "api2013july");	
//		client.get
		RemarkSaveData remark=new RemarkSaveData();
		remark.setNote("这是一个备注信息");
		remark.setRemark("这是备注信息的备注");
		client.setRemark("caorq1", remark);
		remark.setStatus(1);
		client.getRemarkByAccount("hsh_8");
		//client.queryRemarks();
	
		client.logout();
		client.close();
	}

}
