/**
 * 
 */
package cn.edu.buaa.gridWeb.dataservice.account.data;

/**
 * @author caorongqiang
 *
 */
public class UserModifyStatus {
	private int account=0;
	private int softwares=0;
	private int projects=0;
	
	
	public UserModifyStatus() {
		super();
	}
	/**
	 * @return the account
	 */
	public int getAccount() {
		return account;
	}
	/**
	 * @param account the account to set
	 */
	public void setAccount(int account) {
		this.account = account;
	}
	/**
	 * @return the softwares
	 */
	public int getSoftwares() {
		return softwares;
	}
	/**
	 * @param softwares the softwares to set
	 */
	public void setSoftwares(int softwares) {
		this.softwares = softwares;
	}
	/**
	 * @return the projects
	 */
	public int getProjects() {
		return projects;
	}
	/**
	 * @param projects the projects to set
	 */
	public void setProjects(int projects) {
		this.projects = projects;
	}
	
	
}
