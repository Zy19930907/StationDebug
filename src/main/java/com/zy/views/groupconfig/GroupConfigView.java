package com.zy.views.groupconfig;

import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.zy.devices.SubStation;

import StationDebug.App;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GroupConfigView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private GroupPanel groupPanel = new GroupPanel();
	private Font font = new Font("宋体", Font.BOLD, 30);
	JButton config = new JButton("应用配置");
	private BoardCastPanel[] panels = new BoardCastPanel[16];
	private SubStation station;
	public GroupConfigView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1340, 800);
		setTitle("广播分组配置");
		setLocationRelativeTo(null);// 窗体居中显示
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		contentPane.add(groupPanel);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		scrollPane.setViewportView(panel);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		config.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = groupPanel.getSelectedGroup();
				long flagL=0,flagH=0;
				for(int i=0;i<8;i++){
					flagL |= (panels[i].getSelectFlag() << (i*8));
					flagH |= (panels[i+8].getSelectFlag() << (i*8));
				}
				station.getBean().setGroup(index, flagL, flagH);
				App.stationManger.upDateConfig();
			}
		});
		
		config.setFont(font);
		panel_1.add(config);
		
		for(int i=0;i<16;i++) {
			BoardCastPanel boardCastPanel = new BoardCastPanel(i);
			panels[i] = boardCastPanel;
			panel.add(boardCastPanel);
		}
	}
	public void freshSubStation(int group) {
		station = App.mainView.getSelectedStation();
		for(int i=0;i<8;i++) {
			panels[i].setSelect((int)((station.getBean().getGroupConfig()[group][0] >> (i*8)) & 0xFF));
			panels[i+8].setSelect((int)((station.getBean().getGroupConfig()[group][1] >> (i*8)) & 0xFF));
		}
	}
}
