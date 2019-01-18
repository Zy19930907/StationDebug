package com.zy.views.sensor;

import javax.swing.JPanel;

import StationDebug.App;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BreakerCtrPanel extends JPanel{
	JButton btnNewButton_1 = new JButton("");
	public BreakerCtrPanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				App.breakerCtrView.getLinkInfo();
			}
		});
		btnNewButton.setToolTipText("查询关联信息");
		btnNewButton.setIcon(new ImageIcon(BreakerCtrPanel.class.getResource("/com/zy/imgs/getinfo.png")));
		add(btnNewButton);
		
		
		btnNewButton_1.setToolTipText("手动断电");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(btnNewButton_1.getToolTipText().equals("手动断电")) {
					btnNewButton_1.setToolTipText("手动复电");
					App.breakerCtrView.SendHandCtr((byte)0x01, (byte)0x01);
					btnNewButton_1.setIcon(SensorIcons.repowerICON);
				}else {
					btnNewButton_1.setToolTipText("手动断电");
					App.breakerCtrView.SendHandCtr((byte)0x01, (byte)0x00);
					btnNewButton_1.setIcon(SensorIcons.breakPowerIcon);
				}
			}
		});
		btnNewButton_1.setIcon(SensorIcons.breakPowerIcon);
		add(btnNewButton_1);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				App.breakerCtrView.SendHandCtr((byte)0x00, (byte)0x00);
			}
		});
		button.setIcon(new ImageIcon(BreakerCtrPanel.class.getResource("/com/zy/imgs/nohandctr.png")));
		button.setToolTipText("取消手控");
		add(button);
	}
	private static final long serialVersionUID = 1L;

}
