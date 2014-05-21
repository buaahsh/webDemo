package cn.edu.buaa.gridWeb.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.servlet.ServletContext;

import org.apache.catalina.tribes.membership.StaticMember;

import com.sun.xml.internal.ws.api.policy.PolicyResolver.ServerContext;

/**
 * 退修：tuixiu
 * 试用：shiyong
 * 正式：zhengshi
 *
 */
public class EmailTemplateInfo {

	/**
	 * @param args
	 */
	
	
	public static Dictionary<String, String> EmailTemplateDict = new Hashtable<String, String>();
	
	static {
		String filePathString = "E:\\java_workspace\\webDemo\\WebContent\\WEB-INF\\classes\\emailTemplate\\";
		String tuixui = "$applicant$,你好\n你的用户账号$account$的申请资料没有通过审批，请进行修改\n谢谢";
		String shiyong = "$applicant$,你好\n你的用户账号$account$,已经通过审核，变为试用账号。\n恭喜";
		String zhengshi = "$applicant$,你好\n你的用户账号$account$,已经通过审核，变为正式账号。\n恭喜!!!!!!!";
		EmailTemplateDict.put("tuixiu", getEmailTemplate(filePathString+"tuixiu"));
		EmailTemplateDict.put("shiyong", getEmailTemplate(filePathString+"shiyong"));
		EmailTemplateDict.put("zhengshi", getEmailTemplate(filePathString+"zhengshi"));

	}
	private static String getEmailTemplate(String filePath)
	{
		String emailTemplate = "";
		try {
			File f = new File(filePath);
			InputStreamReader read = new InputStreamReader (new FileInputStream(f),"UTF-8");
			BufferedReader br = new BufferedReader(read); 
	        String str = "";
	        while ((str = br.readLine()) != null) {
	        	emailTemplate += str + "\n";
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
		return emailTemplate;
	}

	public static void main(String[] args) throws FileNotFoundException {
		//getEmailTemplate("/WebContent/WEB-INF/classes/emailTemplate/tuixiu");
		
        
	}

}
