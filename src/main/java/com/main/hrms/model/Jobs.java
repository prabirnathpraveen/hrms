package com.main.hrms.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Jobs {
	
	@Id
	private int jobid;
	private String jobtitle;
	private int departmentid;
	public int getJobid() {
		return jobid;
	}
	public void setJobid(int jobid) {
		this.jobid = jobid;
	}
	public String getJobtitle() {
		return jobtitle;
	}
	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}
	public int getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(int departmentid) {
		this.departmentid = departmentid;
	}


}
