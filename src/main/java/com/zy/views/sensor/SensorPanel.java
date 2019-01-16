package com.zy.views.sensor;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.zy.sensors.Sensor;

import StationDebug.App;

public class SensorPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private JTextField addr;
	private JTextField listenValue;
	private JTextField textField;
	JButton imgBtn = new JButton("");
	JLabel canIcon = new JLabel("");
	JLabel linkIcon = new JLabel("");
	public SensorPanel() {
		setLayout(null);

		imgBtn.setHorizontalAlignment(SwingConstants.CENTER);
		imgBtn.setIcon(SensorIcons.undefineIcon);
		imgBtn.setBounds(2, 2, 76, 76);
		add(imgBtn);
		
		linkIcon.setIcon(SensorIcons.noneIcon);
		linkIcon.setBounds(84, 4, 32, 32);
		add(linkIcon);
		
		canIcon.setIcon(SensorIcons.noneIcon);
		canIcon.setBounds(126, 4, 32, 32);
		add(canIcon);
		
		addr = new JTextField();
		addr.setHorizontalAlignment(SwingConstants.CENTER);
		addr.setEditable(false);
		addr.setFont(App.font16);
		addr.setBounds(168, 4, 151, 32);
		add(addr);
		addr.setColumns(10);
		
		listenValue = new JTextField();
		listenValue.setHorizontalAlignment(SwingConstants.CENTER);
		listenValue.setEditable(false);
		listenValue.setFont(App.font16);
		listenValue.setBounds(84, 42, 235, 32);
		add(listenValue);
		listenValue.setColumns(10);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setEditable(false);
		textField.setBounds(1, 1, 323, 78);
		add(textField);
		textField.setColumns(10);
	}
	
	public void upDateSensor(Sensor sensor) {
		imgBtn.setIcon(sensor.getSensorIcon());
		canIcon.setIcon(sensor.getCanIcon());
		addr.setText(sensor.getAddrString());
		linkIcon.setIcon(sensor.getLinkIcon());
		listenValue.setText(sensor.getValueString());
	}
}
