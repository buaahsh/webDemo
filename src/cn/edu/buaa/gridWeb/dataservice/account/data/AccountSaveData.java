/**
 * 
 */
package cn.edu.buaa.gridWeb.dataservice.account.data;



/**
 * @author caorongqiang
 * 从客户端上传的用户的信息，保存到数据库
 */
public class AccountSaveData {
	private String applicant=null;
	private String interest=null;
	private String company=null;
	private String address=null;
	private String tel=null;
	private String extra_tel=null;
	private String email=null;
	private String extra_email=null;

	
	private String mem=null;
	private String disk=null;
	private String walltime=null;

	
	private String security=null;


	public AccountSaveData() {
		super();
	}

	/**
	 * @return the applicant
	 */
	public String getApplicant() {
		return applicant;
	}


	/**
	 * @param applicant the applicant to set
	 */
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}


	/**
	 * @return the interest
	 */
	public String getInterest() {
		return interest;
	}


	/**
	 * @param interest the interest to set
	 */
	public void setInterest(String interest) {
		this.interest = interest;
	}


	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}


	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}


	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}


	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}


	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}


	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}


	/**
	 * @return the extra_tel
	 */
	public String getExtra_tel() {
		return extra_tel;
	}


	/**
	 * @param extra_tel the extra_tel to set
	 */
	public void setExtra_tel(String extra_tel) {
		this.extra_tel = extra_tel;
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
	 * @return the extra_email
	 */
	public String getExtra_email() {
		return extra_email;
	}


	/**
	 * @param extra_email the extra_email to set
	 */
	public void setExtra_email(String extra_email) {
		this.extra_email = extra_email;
	}


	/**
	 * @return the mem
	 */
	public String getMem() {
		return mem;
	}


	/**
	 * @param mem the mem to set
	 */
	public void setMem(String mem) {
		this.mem = mem;
	}


	/**
	 * @return the disk
	 */
	public String getDisk() {
		return disk;
	}


	/**
	 * @param disk the disk to set
	 */
	public void setDisk(String disk) {
		this.disk = disk;
	}


	/**
	 * @return the walltime
	 */
	public String getWalltime() {
		return walltime;
	}


	/**
	 * @param walltime the walltime to set
	 */
	public void setWalltime(String walltime) {
		this.walltime = walltime;
	}


	/**
	 * @return the security
	 */
	public String getSecurity() {
		return security;
	}


	/**
	 * @param security the security to set
	 */
	public void setSecurity(String security) {
		this.security = security;
	}
	
	
}
