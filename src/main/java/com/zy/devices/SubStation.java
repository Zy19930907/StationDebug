package com.zy.devices;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.zou.tools.IpSwitch;
import com.zy.beans.SubStationBean;

public class SubStation {
	private SubStationBean bean;
	private InputStream inputStream;
	private OutputStream outputStream;
	private Socket socket = new Socket();
	private InetSocketAddress address;
	private LinkServer linkServer = new LinkServer();
	
	public SubStation(SubStationBean bean) {
		this.bean = bean;
		address = IpSwitch.getInetSocketAdress(bean.getIpString());
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
	
	public void close() {
		if(socket != null){
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
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
