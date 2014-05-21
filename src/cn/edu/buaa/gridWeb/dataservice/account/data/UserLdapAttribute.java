/**
 * 
 */
package cn.edu.buaa.gridWeb.dataservice.account.data;

/**
 * @author caorongqiang
 * 修改属性的列表
 */
public class UserLdapAttribute {
	private int mtype=0;
	private String name=null;
	private String value=null;
	
	public UserLdapAttribute() {
		super();
	}

	/**
	 * @return the mtype
	 */
	public int getMtype() {
		return mtype;
	}

	/**
	 * @param mtype the mtype to set
	 */
	public void setMtype(int mtype) {
		this.mtype = mtype;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
