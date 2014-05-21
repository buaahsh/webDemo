package cn.edu.buaa.gridWeb.dataservice;

import javax.management.Query;

import cn.edu.buaa.gridWeb.dataservice.jsonmodel.QueryInfo;

public class LogActionDataService extends BaseDataService{

	/**
	 * @param args
	 */
	public QueryInfo queryActionLog(){
		if(!isconnected())
			connect();
		String s=client.queryActionLog();
		return getQueryInfoFromString(s);
		
	}

	private QueryInfo getQueryInfoFromString(String s) {
		// TODO Auto-generated method stub
		return null;
	}

}
