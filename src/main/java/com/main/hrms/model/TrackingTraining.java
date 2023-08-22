package com.main.hrms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="trackingtraining")
public class TrackingTraining {
	@jakarta.persistence.Id
	private int trackingid;
	private int employeeid;
	private int trainingid;
	private boolean isregister;
	public int getTrackingid() {
		return trackingid;
	}
	public void setTrackingid(int trackingid) {
		this.trackingid = trackingid;
	}
	public int getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}
	public int getTrainingid() {
		return trainingid;
	}
	public void setTrainingid(int trainingid) {
		this.trainingid = trainingid;
	}
	public boolean isIsregister() {
		return isregister;
	}
	public void setIsregister(boolean isregister) {
		this.isregister = isregister;
	}
}
