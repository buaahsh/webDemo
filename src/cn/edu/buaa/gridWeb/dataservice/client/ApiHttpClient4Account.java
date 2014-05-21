/**
 * 
 */
package cn.edu.buaa.gridWeb.dataservice.client;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
//import javax.ws.rs.core.MediaType;

import org.apache.tomcat.dbcp.jocl.JOCLContentHandler;
import org.json.*;
import com.google.gson.Gson;

import cn.edu.buaa.gridWeb.dataservice.account.data.*;

import org.json.JSONException;
import org.json.XMLTokener;
import org.json.XML;


/**
 * @author HuangShaohan 用户API的HTTP client 本对象仅能登录一个用户实例。 同一个实例若登录多个用户，以最后一次登录为准
 */
public class ApiHttpClient4Account {

	private HttpClientUtils client = new HttpClientUtils();
	private Md5Gen md5 = null;
	//private JsonParser parser = null;
	private Gson gson = null;
	private HttpParaComparator hcom = null;

	private String md5secret = null;
	private boolean loginFlag = false;

	private boolean logHttpFlag = false;
	private String baseURL = null;
	private String versionStr = null;
	private int portText = 8080;
	private int portTLS = 8443;
	private String username = null;
	private String appkey = null;
	private String webdir = null;
	
	private String charset = "utf-8";
	//private MediaType accept_type = MediaType.APPLICATION_JSON_TYPE;

	public ApiHttpClient4Account(String hostName, String webdir, int portText,
			int portTLS, String versionStr, boolean logHttpFlag) {
		super();
		this.baseURL = hostName;
		this.webdir = webdir;
		this.portText = portText;
		this.portTLS = portTLS;
		this.versionStr = versionStr;
		this.logHttpFlag = logHttpFlag;

		hcom = new HttpParaComparator();
		md5 = new Md5Gen();
		gson = new Gson();
		
		init();
	}

	private void init()
	{
		System.setProperty("javax.net.ssl.trustStore", "E:\\java_workspace\\webDemo\\WebContent\\WEB-INF\\classes\\jssecacerts");
	}
	/**
	 * 输入一个URL，返回一个带md5sum的url
	 * 
	 * @param urlstr
	 * @return
	 */
	private String getUrlWithMd5(String urlstr, String httpMethod) {
		/* 计算md5接要 */
		long timelong = System.currentTimeMillis();
		int index = urlstr.indexOf('?');
		String urlpath = null;
		if (index < 0) {/* 增加一个问号 */
			urlpath = new String(urlstr.getBytes());
			urlstr += "?";
		} else {
			urlpath = urlstr.substring(0, index);
		}
		int len = urlstr.length();
		String clearStr = String.format("timestamp=%d", timelong);
		if (index > 0 && (index + 1) < len) {
			clearStr = clearStr + "&" + urlstr.substring(index + 1);
		}
		String[] paras = clearStr.split("&");
		Arrays.sort(paras, hcom);
		StringBuffer cbuf = new StringBuffer();
		for (String str : paras) {
			cbuf.append(str);
		}
		cbuf.append(md5secret);
		String cstr = cbuf.toString();
		cstr = httpMethod.toUpperCase() + urlpath + cstr;
		String md5sum = md5.getMd5Sum(cstr);
		String returl = null;
		if (index > 0 && (index + 1) < len) {/* 有参数 */
			returl = String.format("%s&md5sum=%s&timestamp=%d", urlstr, md5sum,
					timelong);
		} else {
			returl = String.format("%smd5sum=%s&timestamp=%d", urlstr, md5sum,
					timelong);
		}
		return returl;
	}

	/**
	 * 登录到系统
	 * 
	 * @param appKey
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean login(String appkey, String username, String password) {

		String urlstr = String.format("https://%s:%d/%s/users/login", baseURL,
				portTLS, webdir, versionStr);

		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", appkey);
		params.put("username", username);
		params.put("password", password);
		
		String resultString = client.getDoPostResponseDataByURL(urlstr, params, "utf-8", false);
		if (resultString != null) {
			JSONObject jo ;
			try {
				jo =  XML.toJSONObject(resultString);
				if (jo.isNull("login")||!jo.getJSONObject("login").getString("status_code").equals("0"))
					return loginFlag;
				md5secret = jo.getJSONObject("login").getString("md5secret");
				System.out.printf("user=%s login the system.\n", username);
				loginFlag = true;
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			System.out.printf("user=%s can't login the system.\n", username);
		}
		return loginFlag;
	}

	/*
	 * 检查用户是否已经登录过
	 */
	public boolean isLogin() {
		return loginFlag;
	}

	public boolean logout() {

		String urlstr = String.format(
				"https://%s:%d/%s/%s/users/logout?appid=%s", baseURL, portTLS,
				webdir, versionStr, appkey);

		String resultString = client.getDoGetURL(urlstr, charset);
		if (resultString != null){
			try {
				System.out.printf("login out the system.ret=%s\n", XML.toJSONObject(resultString));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		loginFlag = false;
		return loginFlag;
	}

	
	/*
	public boolean online() {

		String urlstr = String.format("https://%s:%d/%s/%s/users/online",
				baseURL, portTLS, webdir, versionStr);
		WebResource resource = client.resource(urlstr);
		ClientResponse response = resource.get(ClientResponse.class);
		int retstatus = processResponseStatus(response);
		if (retstatus == 200) {
			String textEntity = response.getEntity(String.class);
			System.out.printf("login out the system.ret=%s\n", textEntity);
		}
		return true;
	}
	*/
	public boolean saveAccount(String username, UserSaveFullData udata) {
		String urlstr = null;
		if (portTLS == 443) {
			urlstr = String.format("https://%s/%s/account/%s", baseURL, webdir,
					username);
		} else {
			urlstr = String.format("https://%s:%d/%s/account/%s", baseURL,
					portTLS, webdir, username);
		}

		String weburl = getUrlWithMd5(urlstr, "put");
		String input = gson.toJson(udata);
		String resultString = client.getDoPutResponseDataByURL(weburl, input);
		if (resultString != null)
		{
			System.out.printf("modify.msg=%s\n", resultString);
		}
		return true;

	}

	public String getLdapIDArray(int minID, int maxID) {
		String urlstr = null;
		if (portTLS == 443) {
			urlstr = String.format("https://%s/%s/account/util/userid",
					baseURL, webdir);
		} else {
			urlstr = String.format("https://%s:%d/%s/account/util/userid",
					baseURL, portTLS, webdir);
		}
		StringBuffer urlbuf = new StringBuffer();
		urlbuf.append(urlstr);
		urlbuf.append("?minUid=");
		urlbuf.append(minID);
		urlbuf.append("&maxUid=");
		urlbuf.append(maxID);
		String weburl = getUrlWithMd5(urlbuf.toString(), "get");
		String resultString = client.getDoGetURL(weburl, charset);
		if (resultString != null) {
			System.out.printf("modifyAccount.ret=%s\n", resultString);
			return resultString;
		}
		return null;
	}

	public String getLdapInfo(String username) {

		String urlstr = null;
		if (portTLS == 443) {
			urlstr = String.format("https://%s/%s/account/%s/ldap", baseURL,
					webdir, username);
		} else {
			urlstr = String.format("https://%s:%d/%s/account/%s/ldap", baseURL,
					portTLS, webdir, username);
		}
		String weburl = getUrlWithMd5(urlstr, "get");
		String resultString = client.getDoGetURL(weburl, charset);
		if (resultString != null) {
			System.out.printf("modifyAccount.ret=%s\n", resultString);
			return resultString;
		}
		return null;
	}

	public String addLdapUser(String username, UserLdapData ldapuser) {
		String urlstr = null;
		if (portTLS == 443) {
			urlstr = String.format("https://%s/%s/account/%s/ldap", baseURL,
					webdir, username);
		} else {
			urlstr = String.format("https://%s:%d/%s/account/%s/ldap", baseURL,
					portTLS, webdir, username);
		}
		String weburl = getUrlWithMd5(urlstr, "put");
		String input = gson.toJson(ldapuser);
		String resultString = client.getDoPutResponseDataByURL(weburl, input);
		if (resultString != null)
		{
			System.out.printf("saveAccount.ret=%s\n", resultString);
			return resultString;
		}
		return null;
	}

	public String modifyLdapUser(String username, UserLdapAttribute atrribute) {

		String urlstr = null;
		if (portTLS == 443) {
			urlstr = String.format("https://%s/%s/account/%s/ldap/attribute",
					baseURL, webdir, username);
		} else {
			urlstr = String.format(
					"https://%s:%d/%s/account/%s/ldap/attribute", baseURL,
					portTLS, webdir, username);
		}
		String weburl = getUrlWithMd5(urlstr, "put");
		String input = gson.toJson(atrribute);
		String resultString = client.getDoPutResponseDataByURL(weburl, input);
		if (resultString != null)
		{
			System.out.printf("saveAccount.ret=%s\n", resultString);
			return resultString;
		}
		return null;
	}

	public String deleteLdapUser(String username) {
		String urlstr = null;
		if (portTLS == 443) {
			urlstr = String.format("https://%s/%s/account/%s/ldap", baseURL,
					webdir, username);
		} else {
			urlstr = String.format("https://%s:%d/%s/account/%s/ldap", baseURL,
					portTLS, webdir, username);
		}
		String weburl = getUrlWithMd5(urlstr, "delete");
		String resultString = client.getDoDeleteResponseDataByURL(weburl);
		
		if (resultString != null) {
			System.out.printf("deleteAccount.ret=%s\n", resultString);
			return resultString;
		}
		return null;
	}

	public String getHPCNames() {
		String urlstr = null;
		if (portTLS == 443) {
			urlstr = String.format("https://%s/%s/account/util/hpc", baseURL,
					webdir);
		} else {
			urlstr = String.format("https://%s:%d/%s/account/util/hpc",
					baseURL, portTLS, webdir);
		}
		String weburl = getUrlWithMd5(urlstr, "get");
		String resultString = client.getDoGetURL(weburl, charset);
		if (resultString != null){
			System.out.printf("modifyAccount.ret=%s\n", resultString);
			return resultString;
		}
		return null;
	}

	public String getumap(boolean gridFlag, List<String> usernames,
			List<String> hpcnames) {
		String urlstr = null;
		if (portTLS == 443) {
			urlstr = String
					.format("https://%s/%s/account/map", baseURL, webdir);
		} else {
			urlstr = String.format("https://%s:%d/%s/account/map", baseURL,
					portTLS, webdir);
		}
		StringBuffer urlbuf = new StringBuffer();
		urlbuf.append(urlstr);
		urlbuf.append("?userflag=");
		urlbuf.append(gridFlag);
		urlbuf.append("&usernames=");
		urlbuf.append(usernames.get(0));
		int len = usernames.size();
		for (int i = 1; i < len; i++) {
			urlbuf.append(',');
			urlbuf.append(usernames.get(i));
		}
		urlbuf.append("&hpcnames=");
		urlbuf.append(hpcnames.get(0));
		len = hpcnames.size();
		for (int i = 1; i < len; i++) {
			urlbuf.append(',');
			urlbuf.append(hpcnames.get(i));
		}

		String weburl = getUrlWithMd5(urlbuf.toString(), "get");
		String resultString = client.getDoGetURL(weburl, charset);
		if (resultString != null){
			System.out.printf("modifyAccount.ret=%s\n", resultString);
			return resultString;
		}
		return null;
	}

	public String setumap(MapSetRequestData mdata) {
		String urlstr = null;
		if (portTLS == 443) {
			urlstr = String
					.format("https://%s/%s/account/map", baseURL, webdir);
		} else {
			urlstr = String.format("https://%s:%d/%s/account/map", baseURL,
					portTLS, webdir);
		}
		String weburl = getUrlWithMd5(urlstr, "put");
		String input = gson.toJson(mdata);
		String resultString = client.getDoPutResponseDataByURL(weburl, input);
		if (resultString != null)
		{
			System.out.printf("saveAccount.ret=%s\n", resultString);
			return resultString;
		}
		return null;
	}

	public String setAccountStatus(String username, AccountStatus status) {
		String urlstr = null;
		if (portTLS == 443) {
			urlstr = String.format("https://%s/%s/account/%s/status", baseURL,
					webdir, username);
		} else {
			urlstr = String.format("https://%s:%d/%s/account/%s/status",
					baseURL, portTLS, webdir, username);
		}
		String weburl = getUrlWithMd5(urlstr, "put");
		String input = gson.toJson(status);
		String resultString = client.getDoPutResponseDataByURL(weburl, input);
		if (resultString != null)
		{
			System.out.printf("saveAccount.ret=%s\n", resultString);
			return resultString;
		}
		return null;
	}

	public String getAccountStatus(String username) {
		String urlstr = null;
		if (portTLS == 443) {
			urlstr = String.format("https://%s/%s/account/%s/status", baseURL,
					webdir, username);
		} else {
			urlstr = String.format("https://%s:%d/%s/account/%s/status",
					baseURL, portTLS, webdir, username);
		}
		String weburl = getUrlWithMd5(urlstr, "get");
		String resultString = client.getDoGetURL(weburl, charset);
		if (resultString != null){
			System.out.printf("modifyAccount.ret=%s\n", resultString);
			return resultString;
		}
		return null;
	}

	public String getAccountByName(String username, boolean softwareFlag,
			boolean projectFlag) {
		String urlstr = null;
		if (portTLS == 443) {
			urlstr = String.format("https://%s/%s/account/%s", baseURL, webdir,
					username);
		} else {
			urlstr = String.format("https://%s:%d/%s/account/%s", baseURL,
					portTLS, webdir, username);
		}
		StringBuffer urlbuf = new StringBuffer();
		urlbuf.append(urlstr);
		urlbuf.append("?needsoftwares=");
		urlbuf.append(softwareFlag);
		urlbuf.append("&needprojects=");
		urlbuf.append(projectFlag);
		String weburl = getUrlWithMd5(urlbuf.toString(), "get");
		String resultString = client.getDoGetURL(weburl, "utf-8");
		if (resultString != null) {
			System.out.printf("modifyAccount.ret=%s\n", resultString);
			return resultString;
		}
		return null;
	}

	public String getProjectByAccount(String username) {
		String urlstr = null;
		if (portTLS == 443) {
			urlstr = String.format("https://%s/%s/account/%s/projects",
					baseURL, webdir, username);
		} else {
			urlstr = String.format("https://%s:%d/%s/account/%s/projects",
					baseURL, portTLS, webdir, username);
		}
		String weburl = getUrlWithMd5(urlstr, "get");
		String resultString = client.getDoGetURL(weburl, "utf-8");
		if (resultString != null) {
			System.out.printf("modifyAccount.ret=%s\n", resultString);
			return resultString;
		}
		return null;
	}

	public String getSoftwareByAccount(String username) {
		String urlstr = null;
		if (portTLS == 443) {
			urlstr = String.format("https://%s/%s/account/%s/softwares",
					baseURL, webdir, username);
		} else {
			urlstr = String.format("https://%s:%d/%s/account/%s/softwares",
					baseURL, portTLS, webdir, username);
		}
		String weburl = getUrlWithMd5(urlstr, "get");
		String resultString = client.getDoGetURL(weburl, "utf-8");
		if (resultString != null) {
			System.out.printf("modifyAccount.ret=%s\n", resultString);
			return resultString;
		}
		return null;
	}

	/*
	 * public boolean queryAccounts( String account, String applicant, String
	 * interest, String tel, String email, int user_status, int lock_status, int
	 * map_status, int userid, String timefor, String starttime, String endtime,
	 * String order, int startrow, int length ){
	 */

	public String queryAccounts() {
		String urlstr = null;
		if (portTLS == 443) {
			urlstr = String.format("https://%s/%s/account", baseURL, webdir,
					username);
		} else {
			urlstr = String.format("https://%s:%d/%s/account", baseURL,
					portTLS, webdir, username);
		}
		StringBuffer urlbuf = new StringBuffer();
		urlbuf.append(urlstr);
		urlbuf.append("?startrow=0&length=100");
		urlbuf.append("&user_status=0");
		String weburl = getUrlWithMd5(urlbuf.toString(), "get");
		String resultString = client.getDoGetURL(weburl, "utf-8");
		if (resultString != null) {
			System.out.printf("modifyAccount.ret=%s\n", resultString);
			return resultString;
		}
		return null;
	}
	
	public String queryAccountsByArgs(String nameString, String applicant, String emailString, String status) {
		String urlstr = null;
		if (portTLS == 443) {
			urlstr = String.format("https://%s/%s/account", baseURL, webdir,
					username);
		} else {
			urlstr = String.format("https://%s:%d/%s/account", baseURL,
					portTLS, webdir, username);
		}
		StringBuffer urlbuf = new StringBuffer();
		urlbuf.append(urlstr);
		urlbuf.append("?startrow=0&length=100");
		
		if (status != null  && status.equals("") == false)
			urlbuf.append("&user_status="+status);
		if (nameString != null && nameString.equals("") == false)
			urlbuf.append("&account="+nameString);
		if (applicant != null && applicant.equals("") == false)
			urlbuf.append("&applicant="+applicant);
		if (emailString != null && emailString.equals("") == false)
			urlbuf.append("&email="+emailString);
		String weburl = getUrlWithMd5(urlbuf.toString(), "get");
		String resultString = client.getDoGetURL(weburl, "utf-8");
		if (resultString != null) {
			System.out.printf("modifyAccount.ret=%s\n", resultString);
			return resultString;
		}
		return null;
	}
	public String queryProjects() {
		String urlstr = null;
		if (portTLS == 443) {
			urlstr = String.format("https://%s/%s/account/projects", baseURL,
					webdir, username);
		} else {
			urlstr = String.format("https://%s:%d/%s/account/projects",
					baseURL, portTLS, webdir, username);
		}
		StringBuffer urlbuf = new StringBuffer();
		urlbuf.append(urlstr);
		urlbuf.append("?startrow=0&length=100");
		urlbuf.append("&account=caorqt5");
		String weburl = getUrlWithMd5(urlbuf.toString(), "get");
		String resultString = client.getDoGetURL(weburl, charset);
		if (resultString != null){
			System.out.printf("modifyAccount.ret=%s\n", resultString);
			return resultString;
		}
		return null;
	}

	public String querySoftware() {
		String urlstr = null;
		if (portTLS == 443) {
			urlstr = String.format("https://%s/%s/account/softwares", baseURL,
					webdir, username);
		} else {
			urlstr = String.format("https://%s:%d/%s/account/softwares",
					baseURL, portTLS, webdir, username);
		}
		StringBuffer urlbuf = new StringBuffer();
		urlbuf.append(urlstr);
		urlbuf.append("?startrow=0&length=100");
		urlbuf.append("&account=caorqt5");
		String weburl = getUrlWithMd5(urlbuf.toString(), "get");
		String resultString = client.getDoGetURL(weburl, "utf-8");
		if (resultString != null) {
			System.out.printf("modifyAccount.ret=%s\n", resultString);
			return resultString;
		}
		return null;
	}

	public String setRemark(String username, RemarkSaveData remark) {
		String urlstr = null;
		if (portTLS == 443)
		{
			urlstr = String.format("https://%s/%s/account/%s/remark",
					baseURL, webdir, username);
		}
		else {
			urlstr = String.format("https://%s:%d/%s/account/%s/remark",
					baseURL, portTLS, webdir, username);
		}
		
		String weburl = getUrlWithMd5(urlstr, "put");
		String input = gson.toJson(remark);
		String resultString = client.getDoPutResponseDataByURL(weburl, input);
		if (resultString != null)
		{
			System.out.printf("modifyAccount.ret=%s\n", resultString);
			return resultString;
		}
		return null;
	}

	public String getRemarkByAccount(String username) {
		String urlstr = null;
		if (portTLS == 443)
		{
			urlstr = String.format("https://%s/%s/account/%s/remark",
					baseURL, webdir, username);
		}
		else {
			urlstr = String.format("https://%s:%d/%s/account/%s/remark",
					baseURL, portTLS, webdir, username);
		}
		String weburl = getUrlWithMd5(urlstr, "get");
		String resultString = client.getDoGetURL(weburl, "utf-8");
		if (resultString != null) {
			System.out.printf("modifyAccount.ret=%s\n", resultString);
			return resultString;
		}
		return null;
	}

	public String queryRemarks() {
		String urlstr = null;
		if (portTLS == 443)
		{
			urlstr = String.format("https://%s/%s/account/remarks",
					baseURL, webdir, username);
		}
		else {
			urlstr = String.format("https://%s:%d/%s/account/remarks",
					baseURL, portTLS, webdir, username);
		}
		StringBuffer urlbuf = new StringBuffer();
		urlbuf.append(urlstr);
		urlbuf.append("?startrow=0&length=100");
		urlbuf.append("&account=caorq&status=1");
		String weburl = getUrlWithMd5(urlbuf.toString(), "get");
		String resultString = client.getDoGetURL(weburl, charset);
		if (resultString != null){
			System.out.printf("modifyAccount.ret=%s\n", resultString);
			return resultString;
		}
		return null;
	}

	public String getActionLogByAccount(String username) {
		String urlstr = null;
		if (portTLS == 443) {
			urlstr = String.format("https://%s/%s/account/%s/actionlog",
					baseURL, webdir, username);
		} else {
			urlstr = String.format("https://%s:%d/%s/account/%s/actionlog",
					baseURL, portTLS, webdir, username);
		}
		String weburl = getUrlWithMd5(urlstr, "get");
		String resultString = client.getDoGetURL(weburl, charset);
		if (resultString != null){
			System.out.printf("modifyAccount.ret=%s\n", resultString);
			return resultString;
		}
		return null;
	}

	public String queryActionLog() {
		String urlstr = null;
		if (portTLS == 443) {
			urlstr = String.format("https://%s/%s/account/actionlogs", baseURL,
					webdir, username);
		} else {
			urlstr = String.format("https://%s:%d/%s/account/actionlogs",
					baseURL, portTLS, webdir, username);
		}
		StringBuffer urlbuf = new StringBuffer();
		urlbuf.append(urlstr);
		urlbuf.append("?startrow=0&length=100");
		urlbuf.append("&action=account_modify");
		String weburl = getUrlWithMd5(urlbuf.toString(), "get");
		String resultString = client.getDoGetURL(weburl, charset);
		if (resultString != null){
			System.out.printf("modifyAccount.ret=%s\n", resultString);
			return resultString;
		}
		return null;
	}

	public String setShortURL(String username, ShortURLSaveData shorturl) {
		String urlstr = null;
		if (portTLS == 443) {
			urlstr = String.format("https://%s/%s/account/%s/shorturl",
					baseURL, webdir, username);
		} else {
			urlstr = String.format("https://%s:%d/%s/account/%s/shorturl",
					baseURL, portTLS, webdir, username);
		}
		String weburl = getUrlWithMd5(urlstr, "put");
		String input = gson.toJson(shorturl);
		String resultString = client.getDoPutResponseDataByURL(weburl, input);
		if (resultString != null)
		{
			System.out.printf("saveAccount.ret=%s\n", resultString);
			return resultString;
		}
		return null;
	}

	public String getShortURLByAccount(String username) {
		String urlstr = null;
		if (portTLS == 443) {
			urlstr = String.format("https://%s/%s/account/%s/shorturl",
					baseURL, webdir, username);
		} else {
			urlstr = String.format("https://%s:%d/%s/account/%s/shorturl",
					baseURL, portTLS, webdir, username);
		}
		String weburl = getUrlWithMd5(urlstr, "get");
		String resultString = client.getDoGetURL(weburl, charset);
		if (resultString != null){
			System.out.printf("modifyAccount.ret=%s\n", resultString);
			return resultString;
		}
		return null;
	}

	public String queryShortURL() {
		String urlstr = null;
		if (portTLS == 443) {
			urlstr = String.format("https://%s/%s/account/shorturls",
					baseURL, webdir, username);
		} else {
			urlstr = String.format("https://%s:%d/%s/account/shorturls",
					baseURL, portTLS, webdir, username);
		}
		StringBuffer urlbuf = new StringBuffer();
		urlbuf.append(urlstr);
		urlbuf.append("?startrow=0&length=100");
		urlbuf.append("&action=account_modify");
		String weburl = getUrlWithMd5(urlbuf.toString(), "get");
		String resultString = client.getDoGetURL(weburl, charset);
		if (resultString != null){
			System.out.printf("modifyAccount.ret=%s\n", resultString);
			return resultString;
		}
		return null;
	}

	public String sendEmail(String accountName, EmailData email) {
		String urlstr = null;
		if (portTLS == 443) {
			urlstr = String.format("https://%s/%s/account/util/email/%s",
					baseURL, webdir, accountName);
		} else {
			urlstr = String.format("https://%s:%d/%s/account/util/email/%s",
					baseURL, portTLS, webdir, accountName);
		}
		String weburl = getUrlWithMd5(urlstr, "put");
		String input = gson.toJson(email);
		String resultString = client.getDoPutResponseDataByURL(weburl, input);
		if (resultString != null)
		{
			System.out.printf("saveAccount.ret=%s\n", resultString);
			return resultString;
		}
		return null;
	}

	public String getEmailByAccount(String username) {
		String urlstr =null;
		if (portTLS == 443) {
			urlstr = String.format("https://%s/%s/account/util/email/%s",
					baseURL, webdir, username);
		} else {
			urlstr = String.format("https://%s:%d/%s/account/util/email/%s",
					baseURL, portTLS, webdir, username);
		}
		String weburl = getUrlWithMd5(urlstr, "get");
		String resultString = client.getDoGetURL(weburl, charset);
		if (resultString != null){
			System.out.printf("modifyAccount.ret=%s\n", resultString);
			return resultString;
		}
		return null;
	}

	public String getEmailByID(long id) {
		String urlstr = null;
		if (portTLS == 443)
		{
			urlstr = String.format("https://%s/%s/account/util/email",
					baseURL, webdir, username);
		}
		else {
			urlstr = String.format("https://%s:%d/%s/account/util/email",
					baseURL, portTLS, webdir, username);
		}
		StringBuffer urlbuf = new StringBuffer();
		urlbuf.append(urlstr);
		urlbuf.append("?startrow=0&length=100");
		urlbuf.append("&emailID=" + id);
		String weburl = getUrlWithMd5(urlbuf.toString(), "get");
		String resultString = client.getDoGetURL(weburl, charset);
		if (resultString != null){
			System.out.printf("modifyAccount.ret=%s\n", resultString);
			return resultString;
		}
		return null;
	}

	public String getAccountProfile() {
		String urlstr = null;
		if (portTLS == 443)
		{
			urlstr = String.format("https://%s/%s/account/profile",
					baseURL, webdir);
		}
		else {
			urlstr = String.format("https://%s:%d/%s/account/profile",
					baseURL, portTLS, webdir);
		}
		
		String weburl = getUrlWithMd5(urlstr, "get");
		String resultString = client.getDoGetURL(weburl, charset);
		if (resultString != null){
			System.out.printf("modifyAccount.ret=%s\n", resultString);
			return resultString;
		}
		return null;
	}

	public void close() {
		if (client != null) {
			client = null;
		}
	}
}
