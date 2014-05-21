package cn.edu.buaa.gridWeb.utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map.Entry;

import cn.edu.buaa.gridWeb.servlet.BaseServlet;
import cn.edu.buaa.gridWeb.servlet.EmailServlet;
import cn.edu.buaa.gridWeb.servlet.PasswordServlet;

public class PrivacyManager {
	
	public static HashMap<String, Long> privacyMap = new HashMap<>();
	static String filePath = "E:/java_workspace/webDemo/WebContent/WEB-INF/classes/userPrivacy/userPrivacy.txt";
	static
	{
		buildPrivacyMap();
	}
	private String privacyCode(long value,String methodName){
		return null;
		
	}
	
	HashMap<String, String> actionMap=new HashMap<String, String>();
	//key=usename value= XXXServlet.post


	public static boolean checkPrivacy(String name,
			String action, Class<? extends BaseServlet> class1) {
		String keyString=class1.getSimpleName()+"_"+action.toUpperCase();
		Class clazz=MethodPrivacyMapping.class;
		MethodPrivacyMapping instance=new MethodPrivacyMapping();
		for(Method m:clazz.getMethods()){
			if(m.getName().equalsIgnoreCase("get"+keyString)){
				try {
					Long value=(Long)m.invoke(instance,null);
					Long p=getPrivacyFromMap(name);
					if((p&value)!=0)
						return true;
					else
						return false;
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
		
	}
	
	public static void buildPrivacyMap() {
		try {
			File f = new File(filePath);
			InputStreamReader read = new InputStreamReader (new FileInputStream(f),"UTF-8");
			BufferedReader br = new BufferedReader(read); 
	        String str = "";
	        while ((str = br.readLine()) != null) {
	        	privacyMap.put(str.split("\t")[0], Long.valueOf(str.split("\t")[1]));
	        }
	        br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ;
	}
	
	public static void writePrivacyFile() {
	    try { 
	        FileOutputStream fos = new FileOutputStream(filePath); 
	        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8"); 
	        for (Entry<String, Long> item : privacyMap.entrySet()) {
	        	osw.write(item.getKey()+"\t"+item.getValue().toString()+"\n");
			}
	        osw.flush(); 
	        osw.close();
	    } catch (Exception e) { 
	        e.printStackTrace(); 
	    }
		return ;
	}
	
	public static Long getPrivacyFromMap(String username){
		if (privacyMap.containsKey(username))
			return privacyMap.get(username);
		else {
			return 0l;
		}
	}
	
	public static void main(String[] args) {
		PrivacyManager pm=new PrivacyManager();
		pm.privacyMap.put("2143", 123l);
		System.out.println(pm.getPrivacyFromMap("2143"));
		System.out.println(pm.getPrivacyFromMap("admin"));
		pm.writePrivacyFile();
		
	}
}
