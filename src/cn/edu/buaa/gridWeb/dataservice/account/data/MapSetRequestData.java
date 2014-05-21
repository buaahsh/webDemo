/**
 * 
 */
package cn.edu.buaa.gridWeb.dataservice.account.data;

import java.util.List;

/**
 * @author caorongqiang
 *
 */
public class MapSetRequestData {
	private String gridUser=null;
	private String localUser=null;
	private List<String> hpcList=null;
	
	
	public MapSetRequestData() {
		super();
	}

	/**
	 * @return the gridUser
	 */
	public String getGridUser() {
		return gridUser;
	}
	/**
	 * @param gridUser the gridUser to set
	 */
	public void setGridUser(String gridUser) {
		this.gridUser = gridUser;
	}
	/**
	 * @return the localUser
	 */
	public String getLocalUser() {
		return localUser;
	}
	/**
	 * @param localUser the localUser to set
	 */
	public void setLocalUser(String localUser) {
		this.localUser = localUser;
	}
	/**
	 * @return the hpcList
	 */
	public List<String> getHpcList() {
		return hpcList;
	}
	/**
	 * @param hpcList the hpcList to set
	 */
	public void setHpcList(List<String> hpcList) {
		this.hpcList = hpcList;
	}
	
	
	
}
