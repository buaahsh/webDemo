package cn.edu.buaa.gridWeb.model;

import java.util.ArrayList;

public class UserApplyInfo {
	/**
	 * @param args
	 * 用来保持用户申请表信息
	 * 
	 * 用户个人信息：
	 * 用户名:User4	通信地址：XXXXXXXXX
	 * 邮箱:User4@sccas.cn	联系电话：xxx-xxxxxxx
	 * 申请人:贾六	申请材料:
	 * 申请单位:XXXXXX	项目信息:
	 * 
	 * 申请软硬件信息：
	 * 申请内存:8G	软件需求:gaussian、vasp
	 * 申请硬盘:50G	VPN:User4
	 * 机时总量:1万CPU小时	其它:
	 * 
	 * 这些信息都是用户需要添加的
	 * 
	 */
	public String name;
	public String adress = null;
	public String email = null;
	public String tel = null;
	public String applicant = null;
	public String applyDoc = null;
	public String applyCompany = null;
	public String projectInfo = null;
	public String memory = null;
	public String soft = null;
	public String hard = null;
	public String VPN = null;
	public String machineHours = null;
	public String other = null;
	
	public String uid = null;
	
	public UserApplyInfo(String name){
		this.name = name;
	}
	
	public void updateApplyInfo(String adress, String emall, String tel,
			String applicant, String applyDoc, String applyCampany,
			String projectInfo, String memory, String soft,
			String hard, String VPN, String machineHous, String other){
		this.adress = adress;
		this.email = emall;
		this.tel = tel;
		this.applicant = applicant;
		this.applyDoc = applyDoc;
		this.applyCompany = applyCampany;
		this.projectInfo = projectInfo;
		this.memory = memory;
		this.soft = soft;
		this.hard = hard;
		this.VPN = VPN;
		this.machineHours = machineHous;
		this.other = other;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getApplyDoc() {
		return applyDoc;
	}

	public void setApplyDoc(String applyDoc) {
		this.applyDoc = applyDoc;
	}

	public String getApplyCompany() {
		return applyCompany;
	}

	public void setApplyCompany(String applyCompany) {
		this.applyCompany = applyCompany;
	}

	public String getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(String projectInfo) {
		this.projectInfo = projectInfo;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public String getSoft() {
		return soft;
	}

	public void setSoft(String soft) {
		this.soft = soft;
	}

	public String getHard() {
		return hard;
	}

	public void setHard(String hard) {
		this.hard = hard;
	}

	public String getVPN() {
		return VPN;
	}

	public void setVPN(String vPN) {
		VPN = vPN;
	}

	public String getMachineHours() {
		return machineHours;
	}

	public void setMachineHours(String machineHours) {
		this.machineHours = machineHours;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	
	
}
