/**
 * 
 */
package cn.edu.buaa.gridWeb.dataservice.account.data;

import java.util.List;





/**
 * @author caorongqiang
 * 用户的全部信息，包括账号、软件和项目其三项
 */
public class UserSaveFullData {
	
	private AccountSaveData account=null;
	private List<SoftwareSaveData>  softwares=null;
	private List<ProjectSaveData> projects=null;
	
	public UserSaveFullData() {
		super();
	}
	/**
	 * @return the accunt
	 */
	public AccountSaveData getAccount() {
		return account;
	}
	/**
	 * @param accunt the accunt to set
	 */
	public void setAccount(AccountSaveData accunt) {
		this.account = accunt;
	}
	/**
	 * @return the softwares
	 */
	public List<SoftwareSaveData> getSoftwares() {
		return softwares;
	}
	/**
	 * @param softwares the softwares to set
	 */
	public void setSoftwares(List<SoftwareSaveData> softwares) {
		this.softwares = softwares;
	}
	/**
	 * @return the projects
	 */
	public List<ProjectSaveData> getProjects() {
		return projects;
	}
	/**
	 * @param projects the projects to set
	 */
	public void setProjects(List<ProjectSaveData> projects) {
		this.projects = projects;
	}
	
	

}
