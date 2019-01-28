package com.zy.views;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import StationDebug.App;

import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class ExcuteView extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel wornInfoModel;
	private Object[][] actInfo = new Object[200][3];
	DefaultTableCellRenderer r = new DefaultTableCellRenderer();
	public JTable table_1;
	private JScrollPane scrollPane;
	public ExcuteView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ExcuteView.class.getResource("/com/zy/imgs/group.png")));
		setResizable(false);
		setBounds(100, 100, 1000, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		r.setHorizontalAlignment(JLabel.CENTER);

		wornInfoModel = new DefaultTableModel(actInfo, new String[] {"序号","触发地址","动作地址","触发条件"});
		
		table_1 = new JTable();
		table_1.setBounds(10, 52, 564, 130);
		table_1.setRowSelectionAllowed(false);
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_1.setFont(App.font16);
		table_1.getTableHeader().setFont(App.font16);
		table_1.getTableHeader().setDefaultRenderer(r);
		table_1.setDefaultRenderer(Object.class, r);
		table_1.setModel(wornInfoModel);
		int i=0;
		for(i=0;i<200;i++) {
			table_1.setRowHeight(i, 36);
			table_1.setValueAt(String.valueOf(i+1), i, 0);
		}
		table_1.getColumnModel().getColumn(0).setMaxWidth(80);
		table_1.getColumnModel().getColumn(0).setMinWidth(80);
		table_1.getColumnModel().getColumn(1).setMaxWidth(120);
		table_1.getColumnModel().getColumn(1).setMinWidth(120);
		table_1.getColumnModel().getColumn(2).setMaxWidth(120);
		table_1.getColumnModel().getColumn(2).setMinWidth(120);
		table_1.getTableHeader().setFont(App.font16);
		table_1.setFont(App.font);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		scrollPane.setViewportView(table_1);
	}
}