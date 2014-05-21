package cn.edu.buaa.gridWeb.service;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.buaa.gridWeb.model.UserApplyInfo;
import cn.edu.buaa.gridWeb.utility.MethodPrivacyMapping;
import cn.edu.buaa.gridWeb.utility.PrivacyManager;

public class UserPermissionManageService {
	public String getPermissionList() {
		ArrayList<String> permissionList = new ArrayList<String>();
		MethodPrivacyMapping pm = new MethodPrivacyMapping();
		Class clazz=MethodPrivacyMapping.class;
		Field[] fields=clazz.getDeclaredFields();
		for(Field f:fields)
		{
			if(f.getName().endsWith("CString"))
			{
				try {
					permissionList.add(f.get(clazz.newInstance()).toString());
				} catch (IllegalArgumentException | IllegalAccessException
						| InstantiationException e) {
					e.printStackTrace();
				}
			}
		}
		String resultString = "";
		for(String s:permissionList)
			resultString += s+"\n";
		return resultString;
	}
	
	public void setUserPrivacy(String jsonString) {
		String userString = null;
		String privacyString = null;
		try {
			JSONObject userJson = new JSONObject(jsonString);
			userString = userJson.getString("user");
			privacyString = userJson.getString("privacy");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		String[] privacyStrings = privacyString.split("\n");
		long privacyValue = 0l;
		MethodPrivacyMapping pm = new MethodPrivacyMapping();
		Class clazz=MethodPrivacyMapping.class;
		Field[] fields=clazz.getDeclaredFields();
		for (String s : privacyStrings) {
			for(Field f:fields)
			{
				try {
					if(f.get(clazz.newInstance()).toString().equals(s))
					{
						privacyValue += getPrivacyValue(f.getName());
					}
				} catch (IllegalArgumentException | IllegalAccessException
						| InstantiationException e) {
					e.printStackTrace();
				}
			}	
		}
		if(PrivacyManager.privacyMap.containsKey(userString))
		{
			PrivacyManager.privacyMap.remove(userString);
			PrivacyManager.privacyMap.put(userString, privacyValue);
		}
		else {
			PrivacyManager.privacyMap.put(userString, privacyValue);
		}
		PrivacyManager.writePrivacyFile();
		PrivacyManager.buildPrivacyMap();
	}
	
	private long getPrivacyValue(String privacyString) {
		MethodPrivacyMapping pm = new MethodPrivacyMapping();
		Class clazz=MethodPrivacyMapping.class;
		Field[] fields=clazz.getDeclaredFields();
		for(Field f:fields)
		{
			try {
				if((f.getName()+"_CString").equals(privacyString))
				{
					 return (long) f.get(clazz.newInstance());
				}
			} catch (IllegalArgumentException | IllegalAccessException
					| InstantiationException e) {
				e.printStackTrace();
			}
		}
		return 0l;
	}
	public static void main(String[] args) {
		UserPermissionManageService ums = new UserPermissionManageService();
		//System.out.println(ums.getPermissionList());
		System.out.println(ums.getPrivacyValue("AccountServlet_POST_CString"));
	}
}
