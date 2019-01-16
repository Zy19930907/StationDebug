package com.zy.devices;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.zou.tools.DataSwitch;
import com.zou.tools.DateTool;
import com.zou.tools.IpSwitch;
import com.zy.beans.SubStationBean;
import com.zy.quartz.jobdetails.SubStationJobdetil;
import com.zy.quartz.triggers.SubStationHeartTrigger;

import StationDebug.App;

public class SubStation {
	private SubStationBean bean;
	private InputStream inputStream;
	private OutputStream outputStream;
	private Socket socket = new Socket();
	private InetSocketAddress address;
	private LinkServer linkServer = new LinkServer();
	private final byte[] heart = App.cmdMaker.getCurInfoCmd();

	private SubStationHeartTrigger subStationTaskTrigger;
	private SubStationJobdetil subStationJobDetail;
	private volatile boolean listen = true;
	
	private Listen listenMsg = new Listen();
	public SubStationBean getBean() {
		return bean;
	}

	public void setBean(SubStationBean bean) {
		this.bean = bean;
	}

	public SubStation(SubStationBean bean) {
		this.bean = bean;
		address = IpSwitch.getInetSocketAdress(bean.getIpString());
		subStationTaskTrigger = new SubStationHeartTrigger(bean.getIpString(), "subStationGroup");
		subStationJobDetail = new SubStationJobdetil(this);
		linkServer.start();
	}
	
	public void send(byte[] data){
		if(outputStream != null) {
			try {
				outputStream.write(data, 0, data.length);
				outputStream.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}
	
	public void SendHeart() {
		send(heart);
	}
	
	public void close() {
		if(socket != null){
			listen = false;
			try {
				if(inputStream != null)
					inputStream.close();
				if(outputStream != null)
					outputStream.close();
				socket.close();
				socket = null;
				inputStream = null;
				outputStream = null;
				System.gc();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private class LinkServer extends Thread{
		@Override
		public void run() {
			try {
				socket.connect(address, 5000);
				if(socket.isConnected()){
					inputStream = socket.getInputStream();
					outputStream = socket.getOutputStream();
					listen = true;
					listenMsg = null;
					listenMsg = new Listen();
					listenMsg.start();
					App.taskScheduler.bindJob(subStationJobDetail, subStationTaskTrigger);
				}
			} catch (IOException e) {
				close();
				e.printStackTrace();
			}
		}
	}
	
	private class Listen extends Thread{
		private int recvLen;
		private byte[] msg = new byte[4096];
		private byte[] data;
		@Override
		public void run() {
			while(listen) {
				try {
					if((recvLen = inputStream.read(msg, 0, msg.length))!=-1) {
						data = null;
						data = new byte[recvLen];
						System.arraycopy(msg, 0, data, 0, recvLen);
						System.out.println(DateTool.getTimeHMSS()+DataSwitch.bytesToHexString(data));
					}
				} catch (IOException e) {
					listen = false;
					close();
					e.printStackTrace();
				}
			}
		}
	}
}
