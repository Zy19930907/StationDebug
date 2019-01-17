package com.zy.views;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.zy.views.groupconfig.GroupPanel;

public class MusicPlayView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private GroupPanel groupPanel = new GroupPanel();
	public MusicPlayView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		getContentPane().add(groupPanel);
	}

}
