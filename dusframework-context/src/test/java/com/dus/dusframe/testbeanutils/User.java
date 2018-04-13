package com.dus.dusframe.testbeanutils;

public class User extends People{
	private String user;
	private boolean status;
	private double score;
	
	private Students sutd;
	
	
	
	public Students getSutd() {
		return sutd;
	}
	public void setSutd(Students sutd) {
		this.sutd = sutd;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	
	
}
