package com.zy.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.zy.beans.MusicBean;
import com.zy.views.sensor.SensorIcons;

import StationDebug.App;

public class MusicPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private JTextField musicName;
	JButton image = new JButton("");
	MusicBean bean;
	private SimpleDateFormat format = new SimpleDateFormat("SSSSS");
	public MusicPanel(MusicBean bean) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.bean = bean;
		image.setToolTipText("\u64AD\u653E");
		image.setIcon(SensorIcons.mp3Icon);
		image.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				App.singleBoardCastCtrView.play(bean.getIndex());	
			}
		});
		add(image);
		
		musicName = new JTextField();
		musicName.setEditable(false);
		musicName.setFont(App.font);
		add(musicName);
		musicName.setColumns(10);
		
		musicName.setText("["+format.format(bean.getIndex())+"] "+bean.getMusicName());
		if(bean.getMusicName().endsWith("wav") || bean.getMusicName().endsWith("WAV"))
			image.setIcon(SensorIcons.wavIcon);
	}
	public MusicBean getBean() {
		return bean;
	}
	public void setBean(MusicBean bean) {
		this.bean = bean;
	}
	
}
