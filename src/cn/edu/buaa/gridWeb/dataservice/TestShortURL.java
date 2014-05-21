/**
 * 
 */
package cn.edu.buaa.gridWeb.dataservice;

import cn.edu.buaa.gridWeb.dataservice.account.data.ShortURLSaveData;
import cn.edu.buaa.gridWeb.dataservice.client.ApiHttpClient4Account;




/**
 * @author caorongqiang
 * 测试登录和退出
 */
public class TestShortURL {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ApiHttpClient4Account client=new ApiHttpClient4Account("localhost","restapi3", 8080,
//				8443, "", true);
		ApiHttpClient4Account client=new ApiHttpClient4Account("api.scgrid.cn","v1", 80,
				443, "", true);
		client.login("test", "caorq", "api2013july");		
		ShortURLSaveData shorturl=new ShortURLSaveData();
		shorturl.setFullURL("http://www.scgrid.cn/report");
		shorturl.setShortURL("http://uesr.scgrid.cn/s/afafdafsa");
		client.setShortURL("caorq", shorturl);
		client.setShortURL("caorq2", shorturl);
		client.getShortURLByAccount("caorq");
		client.queryShortURL();
		client.logout();
		client.close();
	}

}
