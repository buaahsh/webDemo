/**
 * 
 */
package cn.edu.buaa.gridWeb.dataservice;

import cn.edu.buaa.gridWeb.dataservice.client.ApiHttpClient4Account;




/**
 * @author caorongqiang
 * 测试登录和退出
 */
public class TestActionLog {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*ApiHttpClient4Account client=new ApiHttpClient4Account("localhost","restapi3", 8080,
				8443, "", true);*/
		ApiHttpClient4Account client=new ApiHttpClient4Account("rest.scgrid.cn","v1", 80,
				443, "", true);
		client.login("test", "caorq", "api2013july");		
		client.getActionLogByAccount("hsh_2");
		//client.queryActionLog();
		client.logout();
		client.close();
	}

}
