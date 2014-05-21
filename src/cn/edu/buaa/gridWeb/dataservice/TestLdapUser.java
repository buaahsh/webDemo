/**
 * 
 */
package cn.edu.buaa.gridWeb.dataservice;

import cn.edu.buaa.gridWeb.dataservice.account.data.UserLdapAttribute;
import cn.edu.buaa.gridWeb.dataservice.account.data.UserLdapData;
import cn.edu.buaa.gridWeb.dataservice.client.ApiHttpClient4Account;




/**
 * @author caorongqiang
 * 测试登录和退出
 */
public class TestLdapUser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		ApiHttpClient4Account client=new ApiHttpClient4Account("localhost","restapi3", 8080,
//				8443, "", true);
		ApiHttpClient4Account client=new ApiHttpClient4Account("api.scgrid.cn","v1", 80,
				443, "", true);
		client.login("test", "caorq", "api2013july");	
		
		client.getLdapIDArray(1,5000);
		
		//新建用户
		UserLdapData data=new UserLdapData();
		data.setEmail("caorq@sccas.cn");
		data.setGidNumber(3000);
		data.setHomeDirectory("/bin/bash");
		data.setLoginShell("/bin/bash");
		data.setTelPhone("010-58812152");
		data.setUidNumber(6001);
		client.addLdapUser("caorq_test", data);
		client.addLdapUser("caorq_test3", data);
		
		//修改属性
		UserLdapAttribute attr=new UserLdapAttribute();
		attr.setMtype(2);//替换参数
		attr.setName("uidNumber");
		attr.setValue("6002");
		client.modifyLdapUser("caorq_test", attr);
		
		client.getLdapInfo("caorq_test");
		
		//删除用户
		//client.deleteLdapUser("caorq_test");
		
		client.logout();
		client.close();
	}

}
