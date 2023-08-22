package com.main.hrms.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Salary {
	@Id
	private int salaryid; 
	private int employeeid;
	private int salaryamount;
	private LocalDate effectivedate;
	private String createdby;
	private boolean isactive;
	public int getSalaryid() {
		return salaryid;
	}
	public void setSalaryid(int salaryid) {
		this.salaryid = salaryid;
	}
	public int getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}
	public int getSalaryamount() {
		return salaryamount;
	}
	public void setSalaryamount(int salaryamount) {
		this.salaryamount = salaryamount;
	}
	public LocalDate getEffectivedate() {
		return effectivedate;
	}
	public void setEffectivedate(LocalDate effectivedate) {
		this.effectivedate = effectivedate;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	}
