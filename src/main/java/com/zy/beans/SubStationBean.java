package com.zy.beans;

public class SubStationBean {
	private String ipString;
	private String position;
	
	public SubStationBean() {
		// TODO Auto-generated constructor stub
	}
	
	public SubStationBean(String ipString,String position) {
		this.ipString = ipString;
		this.position = position;
	}
	public String getIpString() {
		return ipString;
	}
	public void setIpString(String ipString) {
		this.ipString = ipString;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
}
