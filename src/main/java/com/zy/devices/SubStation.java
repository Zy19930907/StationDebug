package com.zy.devices;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;

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
	private volatile int SentCnt = 0;
	private volatile Sensor[] sensors = new Sensor[128];
	private volatile Sensor[] boardcasts = new Sensor[128];
	private volatile int boardcastCnt = 0;
	private SimpleDateFormat format = new SimpleDateFormat("SSS");

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
		for (int i = 0; i < 128; i++) {
			sensors[i] = new Sensor();
			sensors[i].setSensorIcon(SensorIcons.undefineIcon);
			sensors[i].setLinkIcon(SensorIcons.noneIcon);
			sensors[i].setCanIcon(SensorIcons.noneIcon);
			sensors[i].setAddrString(String.valueOf(i + 1) + "#未定义");
			sensors[i].setStation(this);

			boardcasts[i] = new Sensor();
			boardcasts[i].setSensorIcon(SensorIcons.undefineIcon);
			boardcasts[i].setLinkIcon(SensorIcons.noneIcon);
			boardcasts[i].setCanIcon(SensorIcons.noneIcon);
			boardcasts[i].setAddrString(String.valueOf(i + 1) + "#未定义");
			boardcasts[i].setStation(this);
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
		SentCnt++;
		LogRecoder.saveLog_h(
				System.getProperty("user.dir") + "\\Logs\\SubStation\\"
						+ bean.getIpString().substring(0, bean.getIpString().indexOf(":")) + "\\"
						+ DateTool.getFileNameYYMMDD() + "\\" + DateTool.getTimeH() + ".txt",
				"【SEND】----->" + DateTool.getTimeHMSS() + DataSwitch.bytesToHexString(heart));
		if(SentCnt>=4){
			App.taskScheduler.deletJob(SubStation.this);
			close();
		}
	}

	public void close() {
		if (socket != null) {
			listen = false;
			try {
				if (inputStream != null)
					inputStream.close();
				if (outputStream != null)
					outputStream.close();
				socket.close();
				socket = null;
				inputStream = null;
				outputStream = null;
				SentCnt = 0;
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
				SentCnt = 0;
				socket = null;
				socket = new Socket();
				socket.connect(address, 5000);
				if (socket.isConnected()) {
					inputStream = socket.getInputStream();
					outputStream = socket.getOutputStream();
					listen = true;
					listenMsg = null;
					listenMsg = new Listen();
					listenMsg.start();
					subStationTaskTrigger = new SubStationHeartTrigger(bean.getIpString(), "subStationGroup");
					subStationJobDetail = new SubStationJobdetil(SubStation.this);
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
						boardcastCnt = 0;
						switch (data[9]) {
						case 0x60:// 分站实时数据
							SentCnt = 0;
							LogRecoder.saveLog_m(
									System.getProperty("user.dir") + "\\Logs\\SubStation\\"
											+ bean.getIpString().substring(0, bean.getIpString().indexOf(":")) + "\\"
											+ DateTool.getFileNameYYMMDD() + "\\" + DateTool.getTimeH() + ".txt",
									"【RECV】<-----" + DateTool.getTimeHMSS() + DataSwitch.bytesToHexString(data));
							for (i = 0; i < 128; i++) {
								sensors[i].setDefine(false);
							}
							SensorCnt = (((DataSwitch.abs(data[11]) * 256) + DataSwitch.abs(data[10])) - 11) / 5;// 数据中包含传感器个数
							for (i = 0; i < SensorCnt; i++) {
								for (j = 0; j < 5; j++)
									sensorBuf[j] = data[20 + 5 * i + j];
								App.senserFactory.freshSenser(sensors[i], sensorBuf);
								if (sensors[i].getSensorIcon().equals(SensorIcons.boadrCastIcon)) {
									boardcasts[boardcastCnt++] = sensors[i];
								}
								LogRecoder.saveLog_m(System.getProperty("user.dir") + "\\Logs\\SubStation\\"
										+ bean.getIpString().substring(0, bean.getIpString().indexOf(":")) + "\\"
										+ DateTool.getFileNameYYMMDD() + "\\" + DateTool.getTimeH() + ".txt",
										"【记录" + format.format(i + 1) + "】" + "["
												+ DataSwitch.bytesToHexString(sensorBuf) + "] >>>> "
												+ sensors[i].getAddrString() + "---" + sensors[i].getValueString());
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
			linkServer = null;
			linkServer = new LinkServer();
			linkServer.start();
		}
	}
}
