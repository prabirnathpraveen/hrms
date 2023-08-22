package com.main.hrms.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "leaves") 
public class Leaves {
	@Id
	private int leaveid;
	private int employeeid;
	private String leavetype;
	private LocalDate startdate;
	private LocalDate enddate;
	private String leavestatus;
	private String leavereason;
	private int numberofleaves;
	private LocalTime createdtime;
	private LocalTime updatedtime;
	public int getLeaveid() {
		return leaveid;
	}
	public void setLeaveid(int leaveid) {
		this.leaveid = leaveid;
	}
	public int getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}
	public String getLeavetype() {
		return leavetype;
	}
	public void setLeavetype(String leavetype) {
		this.leavetype = leavetype;
	}
	public LocalDate getStartdate() {
		return startdate;
	}
	public void setStartdate(LocalDate startdate) {
		this.startdate = startdate;
	}
	public LocalDate getEnddate() {
		return enddate;
	}
	public void setEnddate(LocalDate enddate) {
		this.enddate = enddate;
	}
	public String getLeavestatus() {
		return leavestatus;
	}
	public void setLeavestatus(String leavestatus) {
		this.leavestatus = leavestatus;
	}
	public String getLeavereason() {
		return leavereason;
	}
	public void setLeavereason(String leavereason) {
		this.leavereason = leavereason;
	}
	public int getNumberofleaves() {
		return numberofleaves;
	}
	public void setNumberofleaves(int numberofleaves) {
		this.numberofleaves = numberofleaves;
	}
	public LocalTime getCreatedtime() {
		return createdtime;
	}
	public void setCreatedtime(LocalTime createdtime) {
		this.createdtime = createdtime;
	}
	public LocalTime getUpdatedtime() {
		return updatedtime;
	}
	public void setUpdatedtime(LocalTime updatedtime) {
		this.updatedtime = updatedtime;
	}
	
}
