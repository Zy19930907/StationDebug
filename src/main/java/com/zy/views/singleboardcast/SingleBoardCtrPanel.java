package com.zy.views.singleboardcast;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.zou.tools.FileTool;
import com.zy.sensors.Sensor;

import StationDebug.App;

public class SingleBoardCtrPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	JSlider slider = new JSlider();
	JLabel volumlabel = new JLabel("  ");
	private SimpleDateFormat format = new SimpleDateFormat("SSS");
	public SingleBoardCtrPanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(SingleBoardCtrPanel.class.getResource("/com/zy/imgs/preMusic.png")));
		btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(btnNewButton);

		JLabel label_1 = new JLabel("  ");
		add(label_1);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon(SingleBoardCtrPanel.class.getResource("/com/zy/imgs/pause.png")));
		btnNewButton_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(btnNewButton_1);

		JLabel label_2 = new JLabel("  ");
		add(label_2);

		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setIcon(new ImageIcon(SingleBoardCtrPanel.class.getResource("/com/zy/imgs/stopMusic.png")));
		btnNewButton_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(btnNewButton_2);

		JLabel label_3 = new JLabel("  ");
		add(label_3);

		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setIcon(new ImageIcon(SingleBoardCtrPanel.class.getResource("/com/zy/imgs/nextMusic.png")));
		btnNewButton_3.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(btnNewButton_3);
		
		JLabel label = new JLabel("        ");
		add(label);

		JLabel label_5 = new JLabel("  ");
		label_5.setIcon(new ImageIcon(SingleBoardCtrPanel.class.getResource("/com/zy/imgs/volumde.png")));
		add(label_5);

		slider.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				int i = slider.getValue();
				byte[] cmd;
				Sensor bSensor = App.singleBoardCastCtrView.getBoardcast();
				cmd = App.cmdMaker.getSetVolumCmd((byte)bSensor.getAddr(), (byte)i);
				App.singleBoardCastCtrView.SendBoardCastCmd(cmd);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});

		slider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				volumlabel.setText(format.format(slider.getValue()));
			}
		});
		
		JLabel label_4 = new JLabel("");
		add(label_4);
		add(slider);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(SingleBoardCtrPanel.class.getResource("/com/zy/imgs/volumadd.png")));
		add(lblNewLabel);
		
		volumlabel.setFont(App.font);
		add(volumlabel);

		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.setToolTipText("转换歌曲文件");
		btnNewButton_4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FileTool.ChooseDir();
				App.singleBoardCastCtrView.freshMusic(FileTool.readMusicListBean(false));
			}
		});
		
		JLabel label_6 = new JLabel("   ");
		add(label_6);
		btnNewButton_4.setIcon(new ImageIcon(SingleBoardCtrPanel.class.getResource("/com/zy/imgs/switchMusic.png")));
		add(btnNewButton_4);
	}
	public void showVolum(int volum) {
		slider.setValue(volum);
		volumlabel.setText(format.format(volum));
	}
}
