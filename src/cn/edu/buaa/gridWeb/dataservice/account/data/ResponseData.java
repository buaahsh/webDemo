/**
 * 
 */
package cn.edu.buaa.gridWeb.dataservice.account.data;

/**
 * @author caorongqiang
 * 返回的数据
 */
public class ResponseData {
	private long request_id=0;
	private int status_code=0;
	private String status_msg=null;
	private Object response_params=null;
	
	public ResponseData() {
		super();
	}

	/**
	 * @return the request_id
	 */
	public long getRequest_id() {
		return request_id;
	}

	/**
	 * @param request_id the request_id to set
	 */
	public void setRequest_id(long request_id) {
		this.request_id = request_id;
	}

	/**
	 * @return the status_code
	 */
	public int getStatus_code() {
		return status_code;
	}

	/**
	 * @param status_code the status_code to set
	 */
	public void setStatus_code(int status_code) {
		this.status_code = status_code;
	}

	/**
	 * @return the status_msg
	 */
	public String getStatus_msg() {
		return status_msg;
	}

	/**
	 * @param status_msg the status_msg to set
	 */
	public void setStatus_msg(String status_msg) {
		this.status_msg = status_msg;
	}

	/**
	 * @return the response_params
	 */
	public Object getResponse_params() {
		return response_params;
	}

	/**
	 * @param response_params the response_params to set
	 */
	public void setResponse_params(Object response_params) {
		this.response_params = response_params;
	}
	
	
}
