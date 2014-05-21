package cn.edu.buaa.gridWeb.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.apache.catalina.Cluster;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sun.org.apache.bcel.internal.generic.IADD;

import cn.edu.buaa.gridWeb.dataservice.restapi.RESTMaster;
import cn.edu.buaa.gridWeb.model.ClusterListInfo;
import cn.edu.buaa.gridWeb.model.UserInfo;

public class AccountMapService {

	/**
	 * @param args
	 * 用户映射主要用来处理用户映射的查看和增加（页面逻辑中没有涉及用户映射的改变和删除）
	 */
	
	private String failureMapFile = "E:\\java_workspace\\webDemo\\WebContent\\WEB-INF\\classes\\failureMap\\";
	
	public JSONArray getAccountMap(String loginuser, String userName){
		RESTMaster instance=RESTMaster.getInstance();
		JSONArray mapResult = instance.getMapArray(loginuser, userName);
		JSONArray mapJsonArray = new JSONArray();
		for (int i = 0; i < mapResult.length(); i++) {
			try {
				JSONObject jo = mapResult.getJSONObject(i);
				JSONObject oneMapJsonObject = new JSONObject();
				oneMapJsonObject.put("cluster", jo.getString("hpc"));
				oneMapJsonObject.put("mapAccount", jo.getString("localuser"));
				oneMapJsonObject.put("status", "成功");
				mapJsonArray.put(oneMapJsonObject);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return mapJsonArray;
	}
	
	public JSONArray getClusterList(String loginuser){
		RESTMaster instance=RESTMaster.getInstance();
		String[] cluserListStrings = instance.getHPCNames(loginuser);
		JSONArray clusterJsonArray = new JSONArray();
		for(String clusterString:cluserListStrings)
		{
			JSONObject oneCluster = new JSONObject();
			try {
				oneCluster.put("name", clusterString);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			clusterJsonArray.put(oneCluster);
		}
		return clusterJsonArray;
	}
	
	/**
	 * @jsonString 
	 * {
	 * 	name:username
	 *  mapArray:[{{cluster:"", mapAccount:""}},{},{}]
	 * }
	 * 
	 */
	public String addAccountMap(String loginuser, String jsonString){
		RESTMaster instance=RESTMaster.getInstance();
		
		String userName = null;
		String mapArrayString = null;
		try {
			JSONObject userMapJson = new JSONObject(jsonString);
			userName = userMapJson.getString("name");
			mapArrayString = userMapJson.getString("mapArray");
			JSONArray mapArray = new JSONArray(mapArrayString);
			List<String> hpcnames = new ArrayList<String>();
			for(int i=0;i<mapArray.length();i++){
				hpcnames.add(mapArray.getJSONObject(i).getString("cluster"));
			}
			String localuser = mapArray.getJSONObject(0).getString("mapAccount");
			String[] okHPCStrings = instance.addMapUser(loginuser, userName, hpcnames, localuser);
			
			List<String> tempList = Arrays.asList(okHPCStrings);
			HashSet<String> failureHashSet = new HashSet<String>();
			for(int i=0;i<mapArray.length();i++){
				if (tempList.contains(mapArray.getJSONObject(i).getString("cluster")) == false)
				{
					String temp = userName+" "+ mapArray.getJSONObject(i).getString("cluster")
							+" "+ mapArray.getJSONObject(i).getString("mapAccount");
					if (failureHashSet.contains(temp) == false)
						failureHashSet.add(temp);
				}	
			}
			this.writeFailureMap(failureHashSet, true);
			// TODO: 返回信息告诉用户连接了多少个
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return "1";
	}
	
	public void retryFailureMap(String loginuser) {
		RESTMaster instance=RESTMaster.getInstance();
		HashSet<String> mapHashSet = readFailureMap();
		HashSet<String> newMapHashSet = new HashSet<String>();
		if (mapHashSet == null)
			return;
		for (String string : mapHashSet) {
			String[] items = string.split(" ");
			List<String> hpcnames = new ArrayList<String>();
			hpcnames.add(items[1]);
			String[] okHPCStrings = instance.addMapUser(loginuser, items[0], hpcnames, items[2]);
			if (okHPCStrings == null)
				continue;
			List<String> tempList = Arrays.asList(okHPCStrings);
			if (tempList == null || !tempList.contains(items[1]))
				newMapHashSet.add(string);
		}
		this.writeFailureMap(newMapHashSet, false);
	}
	
	public HashSet<String> readFailureMap() {
		ObjectInputStream ois;
		try {	
			FileInputStream fis = new FileInputStream(new File(this.failureMapFile));
			ois = new ObjectInputStream(fis);
			HashSet<String> mapHashSet = (HashSet<String>)ois.readObject();
			fis.close();
			return mapHashSet;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void writeFailureMap(HashSet<String> mapHashSet, boolean append) {
		try {
			
			HashSet<String> oldMapHashSet = readFailureMap();
			FileOutputStream fos = new FileOutputStream(new File(failureMapFile));
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			if (append == false)
			{
				oos.writeObject(mapHashSet);
				return;
			}
			for (String string : mapHashSet) {
				if (oldMapHashSet == null)
				{
					oldMapHashSet = mapHashSet;
					break;
				}
				else if (oldMapHashSet.contains(string) == false)
					oldMapHashSet.add(string);
			}
			oos.writeObject(oldMapHashSet);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		AccountMapService am = new AccountMapService();
		//System.out.println(am.getClusterList().toString());
		String jsonString = "{'name':'user1','mapArray':[{'cluster':'nnn','mapAccount':'user1004'},{'cluster':'nnn','mapAccount':'user1004'}]}";
		//am.addAccountMap(jsonString);
		//System.out.print(am.getAccountMap("user1"));
		HashSet<String> tempHashSet = new HashSet<String>();
		tempHashSet.add("hsh_7 deepcomp7000 hsh_7");
		//tempHashSet.add("hsh_7|watermelon|hsh_6");
		am.writeFailureMap(tempHashSet, false);
		//am.readFailureMap();
	}

}
