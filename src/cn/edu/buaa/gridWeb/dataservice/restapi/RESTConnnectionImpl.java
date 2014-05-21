package cn.edu.buaa.gridWeb.dataservice.restapi;

import cn.edu.buaa.gridWeb.dataservice.client.ApiHttpClient4Account;

public class RESTConnnectionImpl {
	public ApiHttpClient4Account client;
	public RESTConnnectionImpl(ApiHttpClient4Account oneClient){
		this.client = oneClient;
	}
	 

}
