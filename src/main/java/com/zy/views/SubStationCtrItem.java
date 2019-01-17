package com.zy.views;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import StationDebug.App;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

public class SubStationCtrItem extends JPopupMenu{
	private static final long serialVersionUID = 1L;
	private JMenuItem delStation = new JMenuItem("删除分站");
	private JMenuItem disLinkItem = new JMenuItem("断开连接");
	private JMenuItem lookInfoItem = new JMenuItem("查看分站信息");
	private JMenuItem configGroupItem = new JMenuItem("配置广播组");
	
	public SubStationCtrItem() {
		delStation.setIcon(new ImageIcon(SubStationCtrItem.class.getResource("/com/zy/imgs/delete.png")));
		delStation.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				App.stationManger.removeStation(App.mainView.getSelectedStation());
			}
		});
		delStation.setFont(App.font);
		add(delStation);
		disLinkItem.setIcon(new ImageIcon(SubStationCtrItem.class.getResource("/com/zy/imgs/dislink.png")));
		disLinkItem.setFont(App.font);
		add(disLinkItem);
		lookInfoItem.setIcon(new ImageIcon(SubStationCtrItem.class.getResource("/com/zy/imgs/serch.png")));
		lookInfoItem.setFont(App.font);
		add(lookInfoItem);
		configGroupItem.setIcon(new ImageIcon(SubStationCtrItem.class.getResource("/com/zy/imgs/group.png")));
		configGroupItem.setFont(App.font);
		configGroupItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				App.groupConfigView.freshSubStation(0);
				App.groupConfigView.setVisible(true);
			}
		});
		add(configGroupItem);
	}
}
