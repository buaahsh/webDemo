package cn.edu.buaa.gridWeb.dataservice.restapi;

public class TestDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RESTMaster instance=RESTMaster.getInstance();
		instance.userLogin("test", "caorq", "api2013july");
		instance.userLogout("caorq");
	}

}
