package com.zy.views;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

public class MainView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Font font = new Font("宋体", Font.BOLD, 24);
	public MainView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 800);
		setTitle("综合分站测试工具-T0.0.1");
		setLocationRelativeTo(null);// 窗体居中显示
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTree tree = new JTree();
		tree.setFont(font);
		tree.setBounds(10, 10, 287, 741);
		contentPane.add(tree);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(307, 10, 917, 741);
		contentPane.add(tabbedPane);
		
		setVisible(true);
	}
}
