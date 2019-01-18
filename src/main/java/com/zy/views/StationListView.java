package com.zy.views;

import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.zy.beans.BootBean;

import StationDebug.App;
import java.awt.Toolkit;
public class StationListView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Gson gson = new Gson();
	private String[] tableTitle = { "分站IP", "分站MAC", "安装位置", "软件版本" };
	private Object[][] devInfo;
	private DefaultTableModel devInfoModel;
	DefaultTableCellRenderer Rstyle = new DefaultTableCellRenderer();
	public volatile int devCnt = 0;
	private JTable table;
	private JPanel panel;
	public StationListView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(StationListView.class.getResource("/com/zy/imgs/serch.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1139, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		Rstyle.setHorizontalAlignment(JLabel.CENTER);

		devInfo = new Object[256][4];
		devInfoModel = new DefaultTableModel(devInfo, tableTitle);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		table = new JTable();
		table.setSurrendersFocusOnKeystroke(true);
		table.getTableHeader().setFont(App.font);
		table.setModel(devInfoModel);
		table.setFont(App.font);
		table.getColumnModel().getColumn(0).setMaxWidth(240);
		table.getColumnModel().getColumn(0).setMinWidth(240);
		table.getColumnModel().getColumn(1).setMaxWidth(280);
		table.getColumnModel().getColumn(1).setMinWidth(280);
		table.getColumnModel().getColumn(3).setMaxWidth(160);
		table.getColumnModel().getColumn(3).setMinWidth(160);
		table.setRowHeight(32);
		
				table.setDefaultRenderer(Object.class, Rstyle);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 84, 1180, 539);
				scrollPane.setViewportView(table);
				contentPane.add(scrollPane);
		
		panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		setTitle("局域网分站列表");
		setContentPane(contentPane);
	}

	public void ClearTable() {
		devCnt = 0;
		table.removeAll();
//		for (int i = 0; i < 256; i++) {
//			table.setValueAt("", i, 0);
//			table.setValueAt("", i, 1);
//			table.setValueAt("", i, 2);
//			table.setValueAt("", i, 3);
//		}
	}

	public void UdpRecv(DatagramPacket packet, byte[] data) {
		ClearTable();
		try {
			BootBean ackBean = gson.fromJson(new String(data, "GB2312"), BootBean.class);
			switch (ackBean.getCmdType()) {
			case "AckSerch":
				table.setValueAt(ackBean.getIpAddr(), devCnt, 0);
				table.setValueAt(ackBean.getMacAddr().toUpperCase(), devCnt, 1);
				table.setValueAt(ackBean.getPosition(), devCnt, 2);
				table.setValueAt(ackBean.getSoftVer(), devCnt, 3);
				devCnt++;
				setVisible(true);
				break;
			}
		} catch (JsonSyntaxException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		setVisible(true);
	}
}
