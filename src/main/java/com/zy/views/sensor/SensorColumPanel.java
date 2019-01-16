package com.zy.views.sensor;

import javax.swing.JPanel;

import com.zy.sensors.Sensor;

import javax.swing.BoxLayout;

public class SensorColumPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private int index;
	private SensorPanel[] sensorPanels = new SensorPanel[4];
	public SensorColumPanel(int index) {
		this.index = index;
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		for(int i=0;i<4;i++){
			SensorPanel sensorPanel = new SensorPanel();
			add(sensorPanel);
			sensorPanels[i] = sensorPanel;
		}
	}
	
	public void upDateSensor(Sensor[] sensors,int page) {
		for(int i=0;i<4;i++)
			sensorPanels[i].upDateSensor(sensors[(4*index)+i+(page*32)]);
	}
}
