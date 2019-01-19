package com.zy.views.sensor;

import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.zy.sensors.Sensor;

import StationDebug.App;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.Toolkit;

public class BreakerCtrView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private BreakerCtrPanel breakerCtrPanel = new BreakerCtrPanel();
	private Sensor breaker;
	public JTable table = new JTable();
	DefaultTableCellRenderer r = new DefaultTableCellRenderer();
	private Object[][] actInfo = new Object[50][2];
	private DefaultTableModel wornInfoModel;
	private GetLinkInfo getLinkInfo = new GetLinkInfo();
	public BreakerCtrView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(BreakerCtrView.class.getResource("/com/zy/imgs/breaker.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		getContentPane().add(breakerCtrPanel);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		
		r.setHorizontalAlignment(JLabel.CENTER);
		wornInfoModel = new DefaultTableModel(actInfo, new String[] {"关联地址","关联类型"});
		table.setRowSelectionAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("宋体", Font.PLAIN, 12));
		table.setDefaultRenderer(Object.class, r);
		table.setModel(wornInfoModel);
		table.getColumnModel().getColumn(0).setMaxWidth(80);
		table.getColumnModel().getColumn(0).setMinWidth(80);
		int i=0;
		for(i=0;i<50;i++)
			table.setRowHeight(i, 36);
		table.getTableHeader().setFont(App.font16);
		table.setFont(App.font);
		scrollPane.setViewportView(table);
		
	}
	
	public void SetBreaker(Sensor breaker) {
		this.breaker = breaker;
		setTitle(breaker.getAddrString());
		getLinkInfo.start();
	}
	
	public void getLinkInfo() {
		getLinkInfo = null;
		getLinkInfo = new GetLinkInfo();
		getLinkInfo.start();
	}
	
	public void SendHandCtr(byte ctr,byte duandian) {
		breaker.getStation().send(App.cmdMaker.getHandCtrCmd((byte)breaker.getAddr(), ctr, duandian));
	}
	
	private class GetLinkInfo extends Thread {
		@Override
		public void run() {
			try {
				breaker.getStation().send(App.cmdMaker.getGuanlianAddrCmd((byte)breaker.getAddr()));
				Thread.sleep(60);
				breaker.getStation().send(App.cmdMaker.getGuanlianTypeCmd((byte)breaker.getAddr()));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
