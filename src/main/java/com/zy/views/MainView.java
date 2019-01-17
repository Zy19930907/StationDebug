package com.zy.views;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.TreePath;

import com.zou.tools.MyTree;
import com.zy.devices.SubStation;
import com.zy.views.sensor.SensorColumPanel;

import StationDebug.App;

public class MainView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MyTree stationTree = new MyTree("综合分站       ");
	TreePath path;
	private SubStationItem stationItem = new SubStationItem();
	private SubStationCtrItem stationCtrItem = new SubStationCtrItem();
	private int i;
	private int page = 0;
	JLabel label = new JLabel(("   第"+String.valueOf(page+1)+"页   "));
	JPanel panel = new JPanel();
	private SensorColumPanel[] sensorColumPanels = new SensorColumPanel[8];
	
	public MainView() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainView.class.getResource("/com/zy/imgs/station.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1650, 1000);
		setTitle("综合分站测试工具-T0.0.2");
		setLocationRelativeTo(null);// 窗体居中显示
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//设备树控件
		stationTree.setFont(App.font);
		stationTree.setBounds(0, 10, 300, 951);
		stationTree.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				if (SwingUtilities.isRightMouseButton(e)) {
					path = stationTree.getPathForLocation(e.getX(), e.getY());
					stationTree.setSelectionPath(path);
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
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(App.font);
		tabbedPane.setBounds(310, 0, 1324, 961);
		contentPane.add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("实时数据", null, panel_1, null);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		for(i=0;i<8;i++) {
			SensorColumPanel sensorColumPanel = new SensorColumPanel(i);
			panel.add(sensorColumPanel);
			sensorColumPanels[i] = sensorColumPanel;
		}
		
		panel_1.add(panel);
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setPreferredSize(new Dimension(panel_1.getWidth(), panel_1.getHeight()));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		JButton preBtn = new JButton("");
		preBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(page>0) 
					page--;
				label.setText("   第"+String.valueOf(page+1)+"页   ");
				UpdateCurInfo((SubStation) stationTree.getSelectObject());
			}
		});
		preBtn.setIcon(new ImageIcon(MainView.class.getResource("/com/zy/imgs/prepage.png")));
		panel_2.add(preBtn);
		
		label.setFont(App.font);
		panel_2.add(label);
		
		JButton nextBtn = new JButton("");
		nextBtn.setIcon(new ImageIcon(MainView.class.getResource("/com/zy/imgs/nextpage.png")));
		nextBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(page<(((SubStation) stationTree.getSelectObject()).getSensorCnt() / 32))
					page++;
				label.setText("   第"+String.valueOf(page+1)+"页   ");
				UpdateCurInfo((SubStation) stationTree.getSelectObject());
			}
		});
		panel_2.add(nextBtn);
		
		setVisible(true);
	}
	
	public void AddNewNode(String nString,Object userObject) {
		stationTree.AddNode(nString, userObject);
	}

	public void RemoveNode(String nString) {
		stationTree.removeNode(nString);
	}
	
	public SubStation getSelectedStation() {
		return (SubStation) stationTree.getSelectObject();
	}
	
	public void UpdateCurInfo(SubStation station) {
		for(i=0;i<8;i++) {
			sensorColumPanels[i].upDateSensor(station.getSensors(), page);
		}
	}
}
