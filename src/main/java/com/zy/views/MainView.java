package com.zy.views;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import com.zou.tools.MyTree;

public class MainView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Font font = new Font("宋体", Font.BOLD, 24);
	private MyTree stationTree = new MyTree("综合分站");
	
	public MainView() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainView.class.getResource("/com/zy/imgs/station.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 786);
		setTitle("综合分站测试工具-T0.0.2");
		setLocationRelativeTo(null);// 窗体居中显示
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//设备树控件
		stationTree.setFont(font);
		stationTree.setBounds(0, 10, 300, 741);
		contentPane.add(stationTree);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(307, 10, 927, 741);
		contentPane.add(tabbedPane);
		
		setVisible(true);
	}
	
	public void AddNewNode(String nString,Object userObject) {
		stationTree.AddNode(nString, userObject);
	}
}
