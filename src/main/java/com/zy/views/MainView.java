package com.zy.views;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.zy.devices.SubStation;
import com.zy.views.sensor.SensorColumPanel;
import java.awt.Component;

public class MainView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int i;
	private int page = 0;
	JPanel panel = new JPanel();
	private SensorColumPanel[] sensorColumPanels = new SensorColumPanel[32];
	int pageCnt = 1;
	public StationTreePanel stationTreePanel = new StationTreePanel();
	public MainView() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainView.class.getResource("/com/zy/imgs/station.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1680, 980);
		setTitle("综合分站测试工具-T0.0.2");
		setLocationRelativeTo(null);// 窗体居中显示
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		stationTreePanel.setBounds(5, 5, 312, 937);
		stationTreePanel.setAlignmentX(Component.RIGHT_ALIGNMENT);

		stationTreePanel.setPreferredSize(new Dimension(312, 997));
		getContentPane().add(stationTreePanel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(327, 5, 1337, 937);
		panel.setBounds(0, 0, 0, 0);
		panel.setPreferredSize(new Dimension(scrollPane.getWidth() - 32, 85 * 32));
		scrollPane.getVerticalScrollBar().setUnitIncrement(85);
		scrollPane.getVerticalScrollBar().setMaximum(30);
		scrollPane.setViewportView(panel);

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		contentPane.add(scrollPane);
		for (i = 0; i < 32; i++) {
			SensorColumPanel sensorColumPanel = new SensorColumPanel(i);
			panel.add(sensorColumPanel);
			sensorColumPanels[i] = sensorColumPanel;
		}
		
		setVisible(true);
	}

	public void UpdateCurInfo(SubStation station) {
		for (i = 0; i < 32; i++)
			sensorColumPanels[i].upDateSensor(station.getSensors(), page);
	}
}
