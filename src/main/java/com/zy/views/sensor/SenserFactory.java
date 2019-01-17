package com.zy.views.sensor;

import com.zou.tools.DataSwitch;
import com.zy.sensors.Sensor;

public class SenserFactory {
	private int nongdu;
	private double concen;
	private String listenValue, unit;
	private boolean moni = false;
	private float Battery;
	private int Volum;

	public void freshSenser(Sensor sensor, byte[] sensorData) {
		moni = false;
		unit = "";
		listenValue = "";
		nongdu = (((DataSwitch.abs(sensorData[4]) * 256) + DataSwitch.abs(sensorData[3])));
		concen = (double) (nongdu & 0x000003FF);
		sensor.freshPublicStatus(sensorData);
		sensor.setAddr(sensorData[0]);
		sensor.setDefine(true);
		if (sensorData[1] == 0x00) { // 低浓甲烷
			sensor.setAddrString(String.valueOf(DataSwitch.abs(sensorData[0])) + "#-低浓甲烷");
			sensor.setSensorIcon(SensorIcons.cH4Icon);
			unit = "%";
			moni = true;
		} else if (sensorData[1] == 0x01) {// 高低浓甲烷
			sensor.setAddrString(String.valueOf(DataSwitch.abs(sensorData[0])) + "#-高低浓甲烷");
			sensor.setSensorIcon(SensorIcons.cH4Icon);
			unit = "%";
			moni = true;
		} else if (sensorData[1] == 0x03) {// 激光甲烷
			sensor.setAddrString(String.valueOf(DataSwitch.abs(sensorData[0])) + "#-激光甲烷");
			sensor.setSensorIcon(SensorIcons.cH4Icon);
			unit = "%";
			moni = true;
		} else if (sensorData[1] == 0x04) {// 一氧化碳
			sensor.setAddrString(String.valueOf(DataSwitch.abs(sensorData[0])) + "#-CO");
			sensor.setSensorIcon(SensorIcons.coIcon);
			unit = "ppm";
			moni = true;
		} else if (sensorData[1] == 0x05) {// 氧气
			sensor.setAddrString(String.valueOf(DataSwitch.abs(sensorData[0])) + "#-氧气");
			sensor.setSensorIcon(SensorIcons.o2Icon);
			unit = "%";
			moni = true;

		} else if (sensorData[1] == 0x06) {// 负压
			sensor.setAddrString(String.valueOf(DataSwitch.abs(sensorData[0])) + "#-负压");
			sensor.setSensorIcon(SensorIcons.presureIcon);
			unit = "%";
			moni = true;

		} else if (sensorData[1] == 0x07) {// 温湿度
			sensor.setAddrString(String.valueOf(DataSwitch.abs(sensorData[0])) + "#-温湿度");
			sensor.setSensorIcon(SensorIcons.tempthIcon);
			unit = "%";
			moni = true;

		} else if (sensorData[1] == 0x09) {// 二氧化碳
			sensor.setAddrString(String.valueOf(DataSwitch.abs(sensorData[0])) + "#-二氧化碳");
			sensor.setSensorIcon(SensorIcons.co2Icon);
			unit = "%";
			moni = true;

		} else if (sensorData[1] == 0x1F) {// 断电器
			sensor.setAddrString(String.valueOf(DataSwitch.abs(sensorData[0])) + "#-断电器");
			sensor.setSensorIcon(SensorIcons.breakerIcon);
			switch (sensorData[2] & 0x03) {
			case 0x00:
				listenValue += "复电 | 有电 ";
				break;
			case 0x01:
				listenValue += "断电 | 有电";
				break;
			case 0x02:
				listenValue += "复电 | 无电";
				break;
			case 0x03:
				listenValue += "断电 | 无电";
				break;
			}
		} else if (sensorData[1] == 0x22) {// 开停
			sensor.setAddrString(String.valueOf(DataSwitch.abs(sensorData[0])) + "#-开停");
			sensor.setSensorIcon(SensorIcons.ktIcon);
			switch (sensorData[2] & 0x01) {
			case 0x00:
				listenValue = "开";
				break;
			case 0x01:
				listenValue += "停 ";
				break;
			}

		} else if (sensorData[1] == 0x23) {// 电源
			sensor.setAddrString(String.valueOf(DataSwitch.abs(sensorData[0])) + "#-电源");
			sensor.setSensorIcon(SensorIcons.powerIcon);
			switch (sensorData[2] & 0x01) {
			case 0x00:
				listenValue = "交流";
				break;
			case 0x01:
				listenValue = "直流";
				break;
			}
		} else if (sensorData[1] == 0x24) {// 读卡器
			sensor.setAddrString(String.valueOf(DataSwitch.abs(sensorData[0])) + "#-读卡器");
			sensor.setSensorIcon(SensorIcons.readerIcon);
		} else if (sensorData[1] == 0x28) {// 广播
			sensor.setAddrString(String.valueOf(DataSwitch.abs(sensorData[0])) + "#-广播终端");
			sensor.setSensorIcon(SensorIcons.boadrCastIcon);
			Battery = DataSwitch.abs(sensorData[3]);
			Battery /= 10;
			Volum = DataSwitch.abs(sensorData[4]);
			switch (sensorData[2] & 0x01) {
			case 0x00:
				listenValue = "空闲";
				break;
			case 0x01:
				listenValue = "忙碌";
				break;
			}
			switch (sensorData[2] & 0x10) {
			case 0x10:
				listenValue = "语音线中断";
				break;
			case 0x00:

				break;
			}
			listenValue += ("|电压：" + String.valueOf(Battery) + "V");
			listenValue += ("|音量：" + String.valueOf(100-Volum));
			sensor.setVolum(100-Volum);
		} else if (sensorData[1] == 0x08) {// 风速
			sensor.setAddrString(String.valueOf(DataSwitch.abs(sensorData[0])) + "#-风速");
			sensor.setSensorIcon(SensorIcons.windspeedIcon);
			unit = "m/s";
			moni = true;
		} else if (sensorData[1] == 0x0C) {// 粉尘
			sensor.setAddrString(String.valueOf(DataSwitch.abs(sensorData[0])) + "#-粉尘");
			sensor.setSensorIcon(SensorIcons.dustIcon);
			unit = "ppm";
			moni = true;
		} else if (sensorData[1] == 0x0D) {// 液位
			sensor.setAddrString(String.valueOf(DataSwitch.abs(sensorData[0])) + "#-液位");
			sensor.setSensorIcon(SensorIcons.llevelIcon);
			unit = "m";
			moni = true;
		} else if (sensorData[1] == 0x0E) {// 烟雾
			sensor.setAddrString(String.valueOf(DataSwitch.abs(sensorData[0])) + "#-烟雾");
			sensor.setSensorIcon(SensorIcons.smogIcon);
			switch (sensorData[2] & 0x01) {
			case 0x00:
				listenValue = "有烟";
				break;
			case 0x01:
				listenValue = "无烟";
				break;
			}
		} else if (sensorData[1] == 0x0F) {// 风门
			sensor.setAddrString(String.valueOf(DataSwitch.abs(sensorData[0])) + "#-风门");
			sensor.setSensorIcon(SensorIcons.airdoorIcon);
			if ((sensorData[2] & 0x0F) == 0x02) {
				listenValue = "两风门关闭";
			} else if ((sensorData[2] & 0x0F) == 0x03) {
				listenValue = "一开一闭";
			} else if ((sensorData[2] & 0x0F) == 0x04) {
				listenValue = "两风门开启";
			} else {
				switch (sensorData[2] & 0x01) {
				case 0x00:
					listenValue = "风门开";
					break;
				case 0x01:
					listenValue = "风门关";
					break;
				}
			}
		} else if (sensorData[1] == 0x2A) {// 风筒
			sensor.setAddrString(String.valueOf(DataSwitch.abs(sensorData[0])) + "#-风筒");
			sensor.setSensorIcon(SensorIcons.fengtongIcon);
			switch (sensorData[2]&0x01){
            case 0x00:
          	  listenValue ="有风";
                break;
            case 0x01:
          	  listenValue +="无风 ";
                break;
        }
		} else if (sensorData[1] == 0x38) {// 张家峁综保
			sensor.setAddrString(String.valueOf(DataSwitch.abs(sensorData[0])) + "#-张家峁综保");
			sensor.setSensorIcon(SensorIcons.coIcon);
		} else if (sensorData[1] == 0x39) {// 北京院电源
			sensor.setAddrString(String.valueOf(DataSwitch.abs(sensorData[0])) + "#-北京院电源");
			sensor.setSensorIcon(SensorIcons.powerIcon);
			switch (sensorData[2]&0x01){
            case 0x00:
          	  listenValue ="交流";
                break;
                
            case 0x01:
          	  listenValue ="直流";
                break;
        }
		} else {
			sensor.setAddrString(String.valueOf(DataSwitch.abs(sensorData[0])) + "#-未定义");
			sensor.setSensorIcon(SensorIcons.undefineIcon);
			listenValue = "------";
		}

		if (moni) {
			switch (sensorData[2] & 0x03) {
			case 0x00:
				listenValue = String.valueOf(concen);
				break;
			case 0x01:
				concen = concen / 10;
				listenValue = String.valueOf(concen);
				break;
			case 0x02:
				concen = concen / 100;
				listenValue = String.valueOf(concen);
				break;
			case 0x03:
				concen = concen / 1000;
				listenValue = String.valueOf(concen);
				break;
			}
		}
		sensor.setValueString(listenValue + unit);
	}

//	public String byteToSenserTypeString(byte type) {
//		   if(type ==0x00)
//			       return SenserType.SENSERTYPE_CH4LOW;
//		        else if(type ==0x01) 
//			        return SenserType.SENSERTYPE_CH4HIGHLOW;
//		        else if (type==0x03) 
//			        return SenserType.SENSERTYPE_LASERCH4;
//		        else  if(type==0x04) 
//			        return SenserType.SENSERTYPE_CO;
//		        else if (type==0x05) 
//			        return SenserType.SENSERTYPE_O2;
//		        else if (type==0x06) 
//			        return SenserType.SENSERTYPE_NATIVEPRE;
//		        else if(type== 0x07) 
//			        return SenserType.SENSERTYPE_TEMPHUMI;
//		        else if(type == 0x09) 
//			        return SenserType.SENSERTYPE_CO2;
//		        else if(type==0x1F) 
//			        return SenserType.SENSERTYPE_BREAKER;
//		        else if(type==0x22) 
//			        return SenserType.SENSERTYPE_OPENSWITCH;
//		        else  if(type==0x23) 
//			        return SenserType.SENSERTYPE_POWER;
//		        else  if(type==0x24) 
//			        return SenserType.SENSERTYPE_CARDREADER;
//		        else  if(type==0x28)
//			        return SenserType.SENSERTYPE_BOARDCAST;
//		        else if(type==0x08) 
//			        return SenserType.SENSERTYPE_WindSpeed;
//		        else if(type==0x0C) 
//			        return SenserType.SENSERTYPE_Dust;
//		        else if(type==0x0D) 
//			        return SenserType.SENSERTYPE_LIQUIDLEVEL;
//		        else if(type==0x0E) 
//			        return SenserType.SENSERTYPE_SMOG;
//		        else if(type==0x0F) 
//			        return SenserType.SENSERTYPE_WINDDOOR;
//		        else if(type==0x2A) 
//			        return SenserType.SENSERTYPE_Fengtong;
//		        else if(type == 0x38)
//			        return SenserType.SENSERTYPE_IPD_ZJM;
//		        else if(type == 0x39)
//			        return SenserType.SENSERTYPE_PKPOWER;
//		
//		return SenserType.SENSERTYPE_UNKONOW;
//	}
}
