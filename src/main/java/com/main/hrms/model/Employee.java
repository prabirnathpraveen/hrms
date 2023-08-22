package com.main.hrms.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

@Entity
public class Employee {
	
	@Id
	private int employeeid;
	private String firstname;
	private String lastname;
	private String emailid;
	private String passwordhash;
	private Long phonenumber;
	private LocalDate hiredate;
	private LocalTime createdtime;
	private String createdby;
	private LocalTime updatedtime;
	private String updatedby;
	private boolean  isactive;
	private String roles;
	private int departmentid;
	private int jobid;
	private int managerid;
	public int getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getPasswordhash() {
		return passwordhash;
	}
	public void setPasswordhash(String passwordhash) {
		this.passwordhash = passwordhash;
	}
	public Long getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(Long phonenumber) {
		this.phonenumber = phonenumber;
	}
	public LocalDate getHiredate() {
		return hiredate;
	}
	public void setHiredate(LocalDate hiredate) {
		this.hiredate = hiredate;
	}
	public LocalTime getCreatedtime() {
		return createdtime;
	}
	public void setCreatedtime(LocalTime createdtime) {
		this.createdtime = createdtime;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public LocalTime getUpdatedtime() {
		return updatedtime;
	}
	public void setUpdatedtime(LocalTime updatedtime) {
		this.updatedtime = updatedtime;
	}
	public String getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public int getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(int departmentid) {
		this.departmentid = departmentid;
	}
	public int getJobid() {
		return jobid;
	}
	public void setJobid(int jobid) {
		this.jobid = jobid;
	}
	public int getManagerid() {
		return managerid;
	}
	public void setManagerid(int managerid) {
		this.managerid = managerid;
	}
	
	public static LocalTime getFormattedTime() {
        LocalDateTime myDateObj = LocalDateTime.now();
        LocalTime formattedTime = LocalTime.of(
            myDateObj.getHour(),
            myDateObj.getMinute(),
            myDateObj.getSecond()
        );
        return formattedTime;
    }
	
	
//	@PrePersist
//	public void before() {
//		LocalTime formattedTime = getFormattedTime();
//		if(createdtime == null ) {
//			this.createdtime = formattedTime;
//		}
//	}
	
}
