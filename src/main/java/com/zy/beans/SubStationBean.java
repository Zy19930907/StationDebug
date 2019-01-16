package com.zy.beans;

public class SubStationBean {
	private String ipString;
	private String position;
	private long[][] groupConfig = new long[8][2];
	
	public long[][] getGroupConfig() {
		return groupConfig;
	}

	public void setGroupConfig(long[][] groupConfig) {
		this.groupConfig = groupConfig;
	}

	public SubStationBean() {
		
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
	
	public void setGroup(int index,long flagL,long flagH) {
		groupConfig[index][0] = flagL;
		groupConfig[index][1] = flagH;
	}
}
