package com.zy.views.sensor;

import com.zou.tools.DataSwitch;
import com.zy.sensors.Sensor;

public class Sensor_BoardCast extends Sensor {
	private float Battery;
	private int Volum;
	public Sensor_BoardCast(byte[] sensorData, String gatewayIp) {
		setSensorIcon(SensorIcons.boadrCastIcon);
		freshPublicStatus(sensorData);
		Battery = DataSwitch.abs(sensorData[3]);
		Battery /= 10;
		Volum = DataSwitch.abs(sensorData[4]);
		switch (sensorData[2] & 0x01) {
		case 0x00:
			valueString = "空闲";
			break;
		case 0x01:
			valueString = "忙碌";
			break;
		}
		switch (sensorData[2] & 0x10) {
		case 0x10:
			valueString = "语音线中断";
			break;
		case 0x00:
			
			break;
		}
		valueString += (" | 电池电压：" + String.valueOf(Battery) + "V");
		valueString += (" | 当前音量："+String.valueOf(Volum));
	}
}
