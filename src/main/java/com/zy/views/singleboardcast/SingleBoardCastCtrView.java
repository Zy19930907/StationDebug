package com.zy.views.singleboardcast;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.zou.tools.FileTool;
import com.zy.beans.MusicListBean;
import com.zy.sensors.Sensor;

import StationDebug.App;

import java.awt.Toolkit;

public class SingleBoardCastCtrView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private SingleBoardCtrPanel singleBoardCtrPanel = new SingleBoardCtrPanel();
	JPanel panel_1 = new JPanel();
	private Sensor boardcast;

	public Sensor getBoardcast() {
		return boardcast;
	}

	public void setBoardcast(Sensor boardcast) {
		this.boardcast = boardcast;
		singleBoardCtrPanel.showVolum(boardcast.getVolum());
	}

	public SingleBoardCastCtrView() {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(SingleBoardCastCtrView.class.getResource("/com/zy/imgs/mp3.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		getContentPane().add(singleBoardCtrPanel);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getVerticalScrollBar().setUnitIncrement(60);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
	}

	public void setBardCast(Sensor boardCast) {
		boardcast = boardCast;
		setTitle(boardCast.getAddrString());
		singleBoardCtrPanel.slider.setValue(boardCast.getVolum());
		singleBoardCtrPanel.volumlabel.setText(String.valueOf(boardCast.getVolum()));
		App.singleBoardCastCtrView.freshMusic(FileTool.readMusicListBean(false));
	}

	public void freshMusic(MusicListBean bean) {
		if (bean != null) {
			panel_1.removeAll();
			for (int i = 0; i < bean.getMusicBeans().size(); i++) {
				MusicPanel panel = new MusicPanel(bean.getMusicBeans().get(i));
				panel_1.add(panel);
			}
			panel_1.updateUI();
		}
	}

	public void SendBoardCastCmd(byte[] cmd) {
		boardcast.getStation().send(cmd);
	}

	public void play(int index) {
		SendBoardCastCmd(App.cmdMaker.getSinglePlayCmd((byte) boardcast.getAddr(), index));
	}
}
