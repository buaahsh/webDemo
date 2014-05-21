/**
 * 
 */
package cn.edu.buaa.gridWeb.dataservice.account.data;

/**
 * @author caorongqiang
 *
 */
public class EmailData {
	private String subject=null;
	private String content=null;
	private String ccmails=null;
	private String tomails=null;
	private String bcmail=null;
	private boolean applyTableFlag=true;
	
	
	
	public EmailData() {
		super();
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the ccmails
	 */
	public String getCcmails() {
		return ccmails;
	}
	/**
	 * @param ccmails the ccmails to set
	 */
	public void setCcmails(String ccmails) {
		this.ccmails = ccmails;
	}
	/**
	 * @return the tomails
	 */
	public String getTomails() {
		return tomails;
	}
	/**
	 * @param tomails the tomails to set
	 */
	public void setTomails(String tomails) {
		this.tomails = tomails;
	}
	/**
	 * @return the bcmail
	 */
	public String getBcmail() {
		return bcmail;
	}
	/**
	 * @param bcmail the bcmail to set
	 */
	public void setBcmail(String bcmail) {
		this.bcmail = bcmail;
	}
	/**
	 * @return the applyTableFlag
	 */
	public boolean isApplyTableFlag() {
		return applyTableFlag;
	}
	/**
	 * @param applyTableFlag the applyTableFlag to set
	 */
	public void setApplyTableFlag(boolean applyTableFlag) {
		this.applyTableFlag = applyTableFlag;
	}
	
	
}
