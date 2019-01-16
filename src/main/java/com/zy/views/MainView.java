package com.zy.views;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.TreePath;

import com.zou.tools.MyTree;
import com.zy.devices.SubStation;

import StationDebug.App;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;

public class MainView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MyTree stationTree = new MyTree("综合分站       ");
	TreePath path;
	private SubStationItem stationItem = new SubStationItem();
	private SubStationCtrItem stationCtrItem = new SubStationCtrItem();
	@SuppressWarnings("unused")
	private SubStation selectedStation;
	
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
		stationTree.setFont(App.font);
		stationTree.setBounds(0, 10, 300, 741);
		stationTree.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				if (SwingUtilities.isRightMouseButton(e)) {
					path = stationTree.getPathForLocation(e.getX(), e.getY());
					stationTree.setSelectionPath(path);
					SubStation station = (SubStation) stationTree.getSelectObject();
					if(station != null)
						selectedStation = station;
					if (path != null) {
						if (path.getLastPathComponent().toString()
								.equals("综合分站       ")) {
							stationItem.show(stationTree,
									e.getX(), e.getY());
						}else {
							stationCtrItem.show(stationTree,
									e.getX(), e.getY());
						}
					}
				}
			}
		});
		contentPane.add(stationTree);
		
		JPanel panel = new JPanel();
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(App.font);
		tabbedPane.setBounds(307, 10, 927, 741);
		contentPane.add(tabbedPane);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("实时数据", null, scrollPane, null);
		scrollPane.setViewportView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		setVisible(true);
	}
	
	public void AddNewNode(String nString,Object userObject) {
		stationTree.AddNode(nString, userObject);
	}

	public SubStation getSelectedStation() {
		return selectedStation;
	}

	public void setSelectedStation(SubStation selectedStation) {
		this.selectedStation = selectedStation;
	}
}
