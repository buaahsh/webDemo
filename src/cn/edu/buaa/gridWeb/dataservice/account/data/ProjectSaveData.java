/**
 * 
 */
package cn.edu.buaa.gridWeb.dataservice.account.data;



/**
 * @author caorongqiang
 *
 */
public class ProjectSaveData {
	private long id=0;//自动增长的ID
	private String project_name=null;
	private String fund_type=null;
	private String project_code=null;
	private String start_month=null;
	private String end_month=null;
	private String apply_reason=null;
	private int status=0;//1表示效状态，0表示无效状态
	
	
	
	public ProjectSaveData() {
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
	 * @return the project_name
	 */
	public String getProject_name() {
		return project_name;
	}


	/**
	 * @param project_name the project_name to set
	 */
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}


	/**
	 * @return the fund_type
	 */
	public String getFund_type() {
		return fund_type;
	}


	/**
	 * @param fund_type the fund_type to set
	 */
	public void setFund_type(String fund_type) {
		this.fund_type = fund_type;
	}


	/**
	 * @return the project_code
	 */
	public String getProject_code() {
		return project_code;
	}


	/**
	 * @param project_code the project_code to set
	 */
	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}


	/**
	 * @return the start_month
	 */
	public String getStart_month() {
		return start_month;
	}


	/**
	 * @param start_month the start_month to set
	 */
	public void setStart_month(String start_month) {
		this.start_month = start_month;
	}


	/**
	 * @return the end_month
	 */
	public String getEnd_month() {
		return end_month;
	}


	/**
	 * @param end_month the end_month to set
	 */
	public void setEnd_month(String end_month) {
		this.end_month = end_month;
	}


	/**
	 * @return the apply_reason
	 */
	public String getApply_reason() {
		return apply_reason;
	}


	/**
	 * @param apply_reason the apply_reason to set
	 */
	public void setApply_reason(String apply_reason) {
		this.apply_reason = apply_reason;
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
