package com.main.hrms.model;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "attendance")
public class Attendance {
	@Id
	private int attendanceid;
	private int employeeid;
	private LocalTime checkintime;
	private LocalTime checkouttime;
	private LocalTime workinghours;
	private boolean ispresent;
	
	public boolean isIspresent() {
		return ispresent;
	}
	public void setIspresent(boolean ispresent) {
		this.ispresent = ispresent;
	}
	public int getAttendanceid() {
		return attendanceid;
	}
	public void setAttendanceid(int attendanceid) {
		this.attendanceid = attendanceid;
	}
	public int getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}
	public LocalTime getCheckintime() {
		return checkintime;
	}
	public void setCheckintime(LocalTime checkintime) {
		this.checkintime = checkintime;
	}
	public LocalTime getCheckouttime() {
		return checkouttime;
	}
	public void setCheckouttime(LocalTime checkouttime) {
		this.checkouttime = checkouttime;
	}
	public LocalTime getWorkinghours() {
		return workinghours;
	}
	public void setWorkinghours(LocalTime workinghours) {
		this.workinghours = workinghours;
	}
	
	
} 
