/**
 * 
 */
package cn.edu.buaa.gridWeb.dataservice;

import java.util.ArrayList;
import java.util.List;

import cn.edu.buaa.gridWeb.dataservice.account.data.MapSetRequestData;
import cn.edu.buaa.gridWeb.dataservice.account.data.UserLdapData;
import cn.edu.buaa.gridWeb.dataservice.client.ApiHttpClient4Account;





/**
 * @author caorongqiang
 * 测试登录和退出
 */
public class TestUserMap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*ApiHttpClient4Account client=new ApiHttpClient4Account("localhost","restapi3", 8080,
				8443, "", true);*/
		ApiHttpClient4Account client=new ApiHttpClient4Account("rest.scgrid.cn","v1", 80,
					443, "", true);
		//client.login("test", "caorq", "api2013july");	
		client.login("test", "sce", "r9b6XXc8");

		String username="caorqt5";
		//新建用户
		UserLdapData data=new UserLdapData();
		data.setEmail("caorq@sccas.cn");
		data.setGidNumber(3000);
		data.setHomeDirectory("/bin/bash");
		data.setLoginShell("/bin/bash");
		data.setTelPhone("010-58812152");
		data.setUidNumber(1287);
		client.addLdapUser(username, data);
		
		//列出HPC节点
		//client.getHPCNames();
		
		List<String> usernames=new ArrayList<String>();
		usernames.add("hsh_7");
		List<String> hpcnames=new ArrayList<String>();
		hpcnames.add("watermelon");
		//hpcnames.add("deepcomp7000");
		
		
		MapSetRequestData mdata=new MapSetRequestData();
		mdata.setGridUser(username);
		mdata.setLocalUser("m_test");
		mdata.setHpcList(hpcnames);
		client.setumap(mdata);
//		
		client.getumap(true, usernames, hpcnames);
		
		//删除一个账号
		client.deleteLdapUser(username);
		client.logout();
		client.close();
	}

}
