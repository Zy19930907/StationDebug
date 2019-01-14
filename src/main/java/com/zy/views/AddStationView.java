package com.zy.views;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.zou.tools.JMIPV4AddressField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class AddStationView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMIPV4AddressField ipField = new JMIPV4AddressField("192.168.1.200");
	private Font font = new Font("宋体", Font.BOLD, 24);
	private JTextField position;
	JLabel positionLabel = new JLabel("安装位置");
	@SuppressWarnings("rawtypes")
	JComboBox port = new JComboBox();
	JButton add = new JButton("添加分站");
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AddStationView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 197);
		setLocationRelativeTo(null);// 窗体居中显示
		setTitle("新增分站");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ipField.setBounds(10, 10, 268, 40);
		
		//IP输入框
		ipField.setFont(font);
		contentPane.add(ipField);
		
		port.setFont(font);
		port.setModel(new DefaultComboBoxModel(new String[] {"5000", "5001", "5002"}));
		port.setBounds(288, 10, 136, 40);
		contentPane.add(port);
		
		positionLabel.setFont(font);
		positionLabel.setBounds(10, 60, 107, 40);
		contentPane.add(positionLabel);
		
		position = new JTextField();
		position.setHorizontalAlignment(SwingConstants.CENTER);
		position.setToolTipText("请输入分站安装地址");
		position.setFont(font);
		position.setBounds(127, 60, 297, 40);
		contentPane.add(position);
		position.setColumns(10);
		
		add.setFont(font);
		add.setBounds(61, 110, 321, 40);
		contentPane.add(add);
		
	}
}
