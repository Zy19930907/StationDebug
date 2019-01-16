package com.zy.views.sensor;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import StationDebug.App;

public class SensorPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private JTextField addr;
	private JTextField listenValue;
	public SensorPanel() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(SensorPanel.class.getResource("/com/zy/imgs/change.png")));
		lblNewLabel.setBounds(0, 0, 74, 74);
		add(lblNewLabel);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(SensorPanel.class.getResource("/com/zy/imgs/dislink.png")));
		label.setBounds(84, 0, 32, 32);
		add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(SensorPanel.class.getResource("/com/zy/imgs/serch.png")));
		label_1.setBounds(126, 0, 32, 32);
		add(label_1);
		
		addr = new JTextField();
		addr.setEditable(false);
		addr.setFont(App.font18);
		addr.setBounds(168, 2, 122, 32);
		add(addr);
		addr.setColumns(10);
		
		listenValue = new JTextField();
		listenValue.setEditable(false);
		listenValue.setFont(App.font);
		listenValue.setBounds(84, 40, 206, 32);
		add(listenValue);
		listenValue.setColumns(10);
	}
}
