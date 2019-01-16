package com.zy.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.google.gson.Gson;
import com.zy.beans.BootBean;

import StationDebug.App;

public class SubStationItem extends JPopupMenu {
	private static final long serialVersionUID = 1L;
	private JMenuItem addSubstation = new JMenuItem("添加综合分站");
	private JMenuItem serchSubstion = new JMenuItem("搜索综合分站");
	private Gson gson = new Gson();

	public SubStationItem() {
		addSubstation.setIcon(new ImageIcon(SubStationItem.class.getResource("/com/zy/imgs/add.png")));
		addSubstation.setFont(App.font);
		addSubstation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				App.addStationView = new AddStationView();
				App.addStationView.setVisible(true);
			}
		});

		serchSubstion.setIcon(new ImageIcon(SubStationItem.class.getResource("/com/zy/imgs/serch.png")));
		serchSubstion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				byte[] cmd;
				String jString;
				BootBean bean = new BootBean();
				bean.setCmdType("SERCHDEV");// 设备搜索指令
				bean.setData(null);
				jString = gson.toJson(bean);
				try {
					cmd = jString.getBytes("GB2312");
					App.udpSender.SendUdp(cmd, "255.255.255.255");// 发送UDP广播
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
			}
		});
		serchSubstion.setFont(App.font);
		add(addSubstation);
		add(serchSubstion);
	}

}
