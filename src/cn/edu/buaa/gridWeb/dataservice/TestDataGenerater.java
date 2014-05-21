package cn.edu.buaa.gridWeb.dataservice;

import java.util.ArrayList;
import java.util.List;

import cn.edu.buaa.gridWeb.dataservice.account.data.AccountSaveData;
import cn.edu.buaa.gridWeb.dataservice.account.data.AccountStatus;
import cn.edu.buaa.gridWeb.dataservice.account.data.ProjectSaveData;
import cn.edu.buaa.gridWeb.dataservice.account.data.SoftwareSaveData;
import cn.edu.buaa.gridWeb.dataservice.account.data.UserSaveFullData;
import cn.edu.buaa.gridWeb.dataservice.client.ApiHttpClient4Account;

public class TestDataGenerater {

	static ApiHttpClient4Account client=new ApiHttpClient4Account("rest.scgrid.cn","v1", 80,
			443, "", true);
	static{
		client.login("test", "caorq", "api2013july");
	}
	static void generateTestAccount(int index)
	{
			
		String username="hsh_"+String.valueOf(index);
		//在数据库加用户
		UserSaveFullData udata=new UserSaveFullData();
		AccountSaveData account=new AccountSaveData();
		account.setAddress("中关村南四街四号");
		account.setApplicant("黄绍晗");
		account.setCompany("中科院网络中心");
		account.setDisk("100GB");
		account.setEmail("buaahsh@foxmail.com");
		account.setExtra_email("service@sccas.cn");
		account.setExtra_tel("010-58812107");
		account.setInterest("云计算");
		account.setMem("20GB");
		account.setSecurity("VPN");
		account.setTel("010-58812152");
		account.setWalltime("1000万小时");
		udata.setAccount(account);
		ProjectSaveData project=new ProjectSaveData();
		//project.setId(8);
		project.setApply_reason("计算需求很大");
		project.setEnd_month("201405");
		project.setFund_type("863基金");
		project.setProject_code("ab133");
		project.setProject_name("测试项目");
		project.setStart_month("201211");
		List<ProjectSaveData> projects=new ArrayList<ProjectSaveData>();
		projects.add(project);
		udata.setProjects(projects);
		SoftwareSaveData software=new SoftwareSaveData();
		software.setApp_name("app_cao");
		List<SoftwareSaveData> softwares=new ArrayList<SoftwareSaveData>();
		softwares.add(software);
		udata.setSoftwares(softwares);		
		client.saveAccount(username, udata);
		
		AccountStatus status=new AccountStatus();
		status.setLock_status(1);
		status.setMap_status(2);
		status.setUser_status(2);
		client.setAccountStatus(username, status);
	}
	
	static void generateTestData()
	{
		for(int i=1; i < 11; i++)
			generateTestAccount(i);
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		generateTestData();
	}

}
