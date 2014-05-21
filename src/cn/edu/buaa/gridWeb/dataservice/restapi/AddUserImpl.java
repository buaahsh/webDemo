package cn.edu.buaa.gridWeb.dataservice.restapi;

import cn.edu.buaa.gridWeb.dataservice.account.data.UserLdapAttribute;
import cn.edu.buaa.gridWeb.dataservice.account.data.UserLdapData;
import cn.edu.buaa.gridWeb.dataservice.client.ApiHttpClient4Account;

public class AddUserImpl {
	ApiHttpClient4Account client;
	public void init(){
		client=new ApiHttpClient4Account("rest.scgrid.cn","v1", 80,
				443, "", true);
		client.login("test", "caorq", "api2013july");	
	}
	

	public String restAddInfo(){
		
		
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
		
		
		//删除用户
		//client.deleteLdapUser("caorq_test");
		
		client.logout();
		client.close();
		return "";
	}
	public void restChangeInfo(){
		UserLdapAttribute attr=new UserLdapAttribute();
		attr.setMtype(2);//替换参数
		attr.setName("uidNumber");
		attr.setValue("6002");
		client.modifyLdapUser("caorq_test", attr);
		client.getLdapInfo("caorq_test");
		
	}
}
