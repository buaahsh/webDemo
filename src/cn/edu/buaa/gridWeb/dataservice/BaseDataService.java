package cn.edu.buaa.gridWeb.dataservice;

import cn.edu.buaa.gridWeb.dataservice.client.ApiHttpClient4Account;


public class BaseDataService {
	public static ApiHttpClient4Account client;

	public boolean isconnected() {
		if (client == null || !client.isLogin())
			return false;
		return true;
	}

	public boolean connect() {
		client = new ApiHttpClient4Account("api.scgrid.cn", "v1", 80, 443, "",
				true);
		if (client.isLogin())
			return true;
		return false;
	}

	public boolean close() {
		try {
			client.logout();
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		client = null;
		return true;
	}

}
