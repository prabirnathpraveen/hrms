package com.main.hrms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Department {
	
	@Id
	private int departmentid;
	private String departmentname;
	private String location;
	
	public int getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(int departmentid) {
		this.departmentid = departmentid;
	}
	public String getDepartmentname() {
		return departmentname;
	}
	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}
