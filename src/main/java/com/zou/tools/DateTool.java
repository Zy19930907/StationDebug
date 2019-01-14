package com.zou.tools;

import java.text.SimpleDateFormat;

public class DateTool {
	static SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
	static SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH-mm");
	static SimpleDateFormat format = new SimpleDateFormat("S");
	static SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH");
	static SimpleDateFormat format3 = new SimpleDateFormat("HH:mm:ss");
	static SimpleDateFormat format4 = new SimpleDateFormat("HH:mm:ss:SSS");
	static SimpleDateFormat format5 = new SimpleDateFormat("yyyy年MM月dd日  EEE  HH:mm:ss");
	static SimpleDateFormat format6 = new SimpleDateFormat("yyyy-MM-dd");
	static SimpleDateFormat format7 = new SimpleDateFormat("HH-mm-ss");
	
	public String getDateString(long millis) {
		return ("【"+format0.format(millis)+"】");
	}
	public static String getDelayString(long millis) {
		return ("【"+format.format(millis)+"Ms】");
	}
	public static String getLogFileName(long millis) {
		return ("["+format1.format(millis)+"]");
	}
	public String getTimeH() {
		return (format2.format(System.currentTimeMillis()));
	}
	public String getTimeHMS() {
		return ("【"+format3.format(System.currentTimeMillis())+"】");
	}
	public static String getTimeHMSS() {
		return ("【"+format4.format(System.currentTimeMillis())+"】");
	}
	
	public String getTimeEEEHMSS() {
		return (format5.format(System.currentTimeMillis()));
	}
	
	public static String getFileNameHHMMSS(){
		return  ("["+format7.format(System.currentTimeMillis())+"]");
	}
	
	public static String getFileNameYYMMDD(){
		return  ("["+format6.format(System.currentTimeMillis())+"]");
	}
}
