package com.zy.views.sensor;
import com.zou.tools.DataSwitch;
import com.zy.sensors.Sensor;

public class Sensor_Breaker extends Sensor{
	public Sensor_Breaker(byte[] senserData, String gatewayIp) {
		setSensorIcon(SensorIcons.breakerIcon);
		
		
		
		switch (senserData[2] & 0x03) {
		case 0x00:
			valueString += "复电 | 有电 ";
			break;
		case 0x01:
			valueString += "断电 | 有电";
			break;
		case 0x02:
			valueString += "复电 | 无电";
			break;
		case 0x03:
			valueString += "断电 | 无电";
			break;
		}
	}
}
