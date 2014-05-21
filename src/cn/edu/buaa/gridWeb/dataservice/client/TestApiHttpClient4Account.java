package cn.edu.buaa.gridWeb.dataservice.client;

public class TestApiHttpClient4Account {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApiHttpClient4Account client=new ApiHttpClient4Account("api.scgrid.cn","v1", 80,
				443, "", true);
		client.login("test", "caorq", "api2013july");
		client.getAccountProfile();
		client.queryProjects();
		client.logout();
		//client.logout();
	}

}
