package com.zy.sensors;

import javax.swing.Icon;
import com.zy.views.sensor.SensorIcons;

public class Sensor {
	private Icon sensorIcon;
	private Icon linkIcon;
	private Icon canIcon;
	private String addrString;
	protected String valueString = "----------";
	
	public Icon getCanIcon() {
		return canIcon;
	}
	public void setCanIcon(Icon canIcon) {
		this.canIcon = canIcon;
	}
	
	public Icon getSensorIcon() {
		return sensorIcon;
	}
	public void setSensorIcon(Icon sensorIcon) {
		this.sensorIcon = sensorIcon;
	}
	public Icon getLinkIcon() {
		return linkIcon;
	}
	public void setLinkIcon(Icon linkIcon) {
		this.linkIcon = linkIcon;
	}
	public String getAddrString() {
		return addrString;
	}
	public void setAddrString(String addrString) {
		this.addrString = addrString;
	}
	public String getValueString() {
		return valueString;
	}
	public void setValueString(String valueString) {
		this.valueString = valueString;
	}
	
	public void freshPublicStatus(byte[] sensorData) {
		
		if ((sensorData[2]&0x80)==0) {
			setLinkIcon(SensorIcons.linkedIcon);   
		}
		else {
			setLinkIcon(SensorIcons.dislinkedIcon); 
		}
		
		switch (sensorData[2]&0x60){
        case 0x00:
      	  setCanIcon(SensorIcons.can1Icon);
            break;
        case 0x20:
        	setCanIcon(SensorIcons.can2Icon);
            break;
        case 0x40:
        	setCanIcon(SensorIcons.can1Icon);
            break;
        case 0x60:
        	setCanIcon(SensorIcons.can1Icon);
            break;
		}
	}
}
