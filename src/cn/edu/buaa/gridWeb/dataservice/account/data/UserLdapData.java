/**
 * 
 */
package cn.edu.buaa.gridWeb.dataservice.account.data;

/**
 * @author caorongqiang
 *
 */
public class UserLdapData {
	private String email=null;
	private String  telPhone=null;
	private String  loginShell=null;
	private int  uidNumber=0;
	private int  gidNumber=0;
	private String  homeDirectory=null;
	
	public UserLdapData() {
		super();
	}

	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the telPhone
	 */
	public String getTelPhone() {
		return telPhone;
	}

	/**
	 * @param telPhone the telPhone to set
	 */
	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	/**
	 * @return the loginShell
	 */
	public String getLoginShell() {
		return loginShell;
	}

	/**
	 * @param loginShell the loginShell to set
	 */
	public void setLoginShell(String loginShell) {
		this.loginShell = loginShell;
	}

	/**
	 * @return the uidNumber
	 */
	public int getUidNumber() {
		return uidNumber;
	}

	/**
	 * @param uidNumber the uidNumber to set
	 */
	public void setUidNumber(int uidNumber) {
		this.uidNumber = uidNumber;
	}

	/**
	 * @return the gidNumber
	 */
	public int getGidNumber() {
		return gidNumber;
	}

	/**
	 * @param gidNumber the gidNumber to set
	 */
	public void setGidNumber(int gidNumber) {
		this.gidNumber = gidNumber;
	}

	/**
	 * @return the homeDirectory
	 */
	public String getHomeDirectory() {
		return homeDirectory;
	}

	/**
	 * @param homeDirectory the homeDirectory to set
	 */
	public void setHomeDirectory(String homeDirectory) {
		this.homeDirectory = homeDirectory;
	}
}
