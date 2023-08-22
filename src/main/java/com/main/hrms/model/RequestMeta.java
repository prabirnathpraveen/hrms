package com.main.hrms.model;

import org.springframework.stereotype.Component;

@Component
public class RequestMeta {
	private int userid;
    private String username;
    private String mailid;
    private String role;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMailid() {
		return mailid;
	}
	public void setMailid(String mailid) {
		this.mailid = mailid;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public static void setRequestMeta() {
		
	}
}
