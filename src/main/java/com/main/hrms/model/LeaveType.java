package com.main.hrms.model;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "leavetypes") 
public class LeaveType {
	@Id
	private int leavetypeid;  
	private String leavetype;  
	private int availablebalance;
	private LocalTime createdtime;
	private String createdby;
	private LocalTime updatedtime; 
	private boolean isactive;
	public int getLeavetypeid() {
		return leavetypeid;
	}
	public void setLeavetypeid(int leavetypeid) {
		this.leavetypeid = leavetypeid;
	}
	public String getLeavetype() {
		return leavetype;
	}
	public void setLeavetype(String leavetype) {
		this.leavetype = leavetype;
	}
	public int getAvailablebalance() {
		return availablebalance;
	}
	public void setAvailablebalance(int availablebalance) {
		this.availablebalance = availablebalance;
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
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	
	
	
}
