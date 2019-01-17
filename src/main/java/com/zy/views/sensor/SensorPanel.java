package com.zy.views.sensor;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.zy.sensors.Sensor;

import StationDebug.App;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		imgBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

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
		boolean isUndefine = sensor.isDefine();
		if(sensor.getSensorIcon().equals(SensorIcons.undefineIcon) && (isUndefine)) {
			imgBtn.setIcon(SensorIcons.linkbreakIcon);
			addr.setText(sensor.getAddrString().replaceAll("未定义", "连接断开"));
		}	
		else {
			addr.setText(sensor.getAddrString());
			imgBtn.setIcon(sensor.getSensorIcon());
		}	
		canIcon.setIcon(sensor.getCanIcon());
		linkIcon.setIcon(sensor.getLinkIcon());
		listenValue.setText(sensor.getValueString());
		imgBtn.setVisible(isUndefine);
		addr.setVisible(isUndefine);
		canIcon.setVisible(isUndefine);
		linkIcon.setVisible(isUndefine);
		listenValue.setVisible(isUndefine);
	}
}
