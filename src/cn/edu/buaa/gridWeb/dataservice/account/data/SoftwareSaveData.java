/**
 * 
 */
package cn.edu.buaa.gridWeb.dataservice.account.data;



/**
 * @author caorongqiang
 * 用户保存的软件信息
 */
public class SoftwareSaveData {
	private long id=0;//自动增长的ID
	private String appid=null;
	private String app_name=null;
	private int status=0;//1表示效状态，0表示无效状态
	
	
	public SoftwareSaveData() {
		super();
	}


	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}


	/**
	 * @return the appid
	 */
	public String getAppid() {
		return appid;
	}


	/**
	 * @param appid the appid to set
	 */
	public void setAppid(String appid) {
		this.appid = appid;
	}


	/**
	 * @return the app_name
	 */
	public String getApp_name() {
		return app_name;
	}


	/**
	 * @param app_name the app_name to set
	 */
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}


	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
