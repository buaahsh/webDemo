package cn.edu.buaa.gridWeb.service;


import java.lang.reflect.Field;
import java.lang.reflect.Method;


import cn.edu.buaa.gridWeb.model.EmailTemplateInfo;
import cn.edu.buaa.gridWeb.model.UserApplyInfo;

public class RelectDemoService {

	public void demo(){
		UserApplyInfo info=new UserApplyInfo("test");
		Class clazz=UserApplyInfo.class;
		Field[] fields=clazz.getDeclaredFields();
		for(Field f:fields){
			String name=f.getName();
			Method[] ms=clazz.getMethods();
//			String value=editJson.getString(name);
			for(Method m:ms){
//				if(m.getName().equalsIgnoreCase("get"+name))
//					m.invoke(value);
			}
		}
		EmailTemplateInfo e=new EmailTemplateInfo();
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
