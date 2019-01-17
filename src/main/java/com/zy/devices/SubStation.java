package com.zy.devices;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.zou.tools.DataSwitch;
import com.zou.tools.DateTool;
import com.zou.tools.IpSwitch;
import com.zou.tools.LogRecoder;
import com.zy.beans.SubStationBean;
import com.zy.quartz.jobdetails.SubStationJobdetil;
import com.zy.quartz.triggers.SubStationHeartTrigger;
import com.zy.sensors.Sensor;
import com.zy.views.sensor.SensorIcons;

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

	private volatile int SensorCnt;
	private volatile Sensor[] sensors = new Sensor[128];

	public Sensor[] getSensors() {
		return sensors;
	}

	public int getSensorCnt() {
		return SensorCnt;
	}

	public void setSensorCnt(int sensorCnt) {
		SensorCnt = sensorCnt;
	}

	public void setSensors(Sensor[] sensors) {
		this.sensors = sensors;
	}

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
		for (int i = 0; i < 128; i++) {
			sensors[i] = new Sensor();
			sensors[i].setSensorIcon(SensorIcons.undefineIcon);
			sensors[i].setLinkIcon(SensorIcons.noneIcon);
			sensors[i].setCanIcon(SensorIcons.noneIcon);
			sensors[i].setAddrString(String.valueOf(i + 1) + "#未定义");
		}
		linkServer.start();
	}

	public void send(byte[] data) {
		if (outputStream != null) {
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
		if (socket != null) {
			listen = false;
			try {
				App.taskScheduler.deletJob(this);
				if (inputStream != null)
					inputStream.close();
				if (outputStream != null)
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

	private class LinkServer extends Thread {
		@Override
		public void run() {
			try {
				socket.connect(address, 5000);
				if (socket.isConnected()) {
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

	private class Listen extends Thread {
		private int recvLen;
		private byte[] msg = new byte[4096];
		private byte[] data;
		private int i, j;
		private byte[] sensorBuf = new byte[5];

		@Override
		public void run() {
			while (listen) {
				try {
					if ((recvLen = inputStream.read(msg, 0, msg.length)) != -1) {
						data = null;
						data = new byte[recvLen];
						System.arraycopy(msg, 0, data, 0, recvLen);
						switch (data[9]) {
						case 0x60:// 分站实时数据
							LogRecoder.saveLog_n(System.getProperty("user.dir") + "\\Logs\\SubStation\\"
									+ bean.getIpString().substring(0, bean.getIpString().indexOf(":")) + "\\"
									+ DateTool.getFileNameYYMMDD() + "\\"+DateTool.getTimeH()+".txt", "【RECV】<--"+DataSwitch.bytesToHexString(data));
							for(i=0;i<128;i++) {
								sensors[i].setDefine(false);
							}
							SensorCnt = (((DataSwitch.abs(data[11]) * 256) + DataSwitch.abs(data[10])) - 11) / 5;// 数据中包含传感器个数
							for (i = 0; i < SensorCnt; i++) {
								for (j = 0; j < 5; j++)
									sensorBuf[j] = data[20 + 5 * i + j];
								App.senserFactory.freshSenser(sensors[i], sensorBuf);
							}
							if (App.mainView.getSelectedStation() != null) {
								if (bean.getIpString()
										.equals(App.mainView.getSelectedStation().getBean().getIpString()))
									App.mainView.UpdateCurInfo(SubStation.this);
							}
							break;
						}
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
