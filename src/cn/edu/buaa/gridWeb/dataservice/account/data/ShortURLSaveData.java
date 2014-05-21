/**
 * 
 */
package cn.edu.buaa.gridWeb.dataservice.account.data;


/**
 * @author caorongqiang
 * 
 */
public class ShortURLSaveData {
	private long id=0;//自动增长的ID
	private String shortURL=null;
	private String fullURL=null;
	private int status=0;//1表示效状态，0表示无效状态
	
	public ShortURLSaveData() {
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
	 * @return the shortURL
	 */
	public String getShortURL() {
		return shortURL;
	}

	/**
	 * @param shortURL the shortURL to set
	 */
	public void setShortURL(String shortURL) {
		this.shortURL = shortURL;
	}

	/**
	 * @return the fullURL
	 */
	public String getFullURL() {
		return fullURL;
	}

	/**
	 * @param fullURL the fullURL to set
	 */
	public void setFullURL(String fullURL) {
		this.fullURL = fullURL;
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
