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
import javax.swing.JTextField;
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
	JLabel label = new JLabel(("  "));
	JPanel panel = new JPanel();
	private SensorColumPanel[] sensorColumPanels = new SensorColumPanel[8];
	int pageCnt = 1;
	private SubStation showStation;
	private JTextField textField;
	private JTextField textField_1;

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
		// 设备树控件
		stationTree.setFont(App.font);
		stationTree.setBounds(20, 21, 280, 929);
		stationTree.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				if ((SubStation) stationTree.getSelectObject() != null) {
					showStation = (SubStation) stationTree.getSelectObject();
				}
				if (SwingUtilities.isRightMouseButton(e)) {
					path = stationTree.getPathForLocation(e.getX(), e.getY());
					stationTree.setSelectionPath(path);
					if (path != null) {
						if (path.getLastPathComponent().toString().equals("综合分站       ")) {
							stationItem.show(stationTree, e.getX(), e.getY());
						} else {
							stationCtrItem.show(stationTree, e.getX(), e.getY());
						}
					}
				}
			}
		});
		contentPane.add(stationTree);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(336, 21, 1285, 929);
		contentPane.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		panel_1.add(panel);

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setPreferredSize(new Dimension(panel_1.getWidth(), panel_1.getHeight()));

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));

		JButton preBtn = new JButton("");
		preBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (page > 0)
					page--;
				pageCnt = showStation.getSensorCnt() / 32;
				if ((showStation.getSensorCnt() % 32) != 0)
					pageCnt += 1;
				label.setText("   第" + String.valueOf(page + 1)+"-"+String.valueOf(pageCnt)+ "页   ");
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
				if (page < (((SubStation) stationTree.getSelectObject()).getSensorCnt() / 32))
					page++;
				pageCnt = showStation.getSensorCnt() / 32;
				if ((showStation.getSensorCnt() % 32) != 0)
					pageCnt += 1;
				label.setText("   第" + String.valueOf(page + 1)+"-"+String.valueOf(pageCnt)+ "页   ");
				UpdateCurInfo((SubStation) stationTree.getSelectObject());
			}
		});
		panel_2.add(nextBtn);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setEditable(false);
		textField.setBounds(10, 10, 301, 951);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(326, 10, 1308, 951);
		contentPane.add(textField_1);
		textField_1.setEnabled(false);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		for (i = 0; i < 8; i++) {
			SensorColumPanel sensorColumPanel = new SensorColumPanel(i);
			panel.add(sensorColumPanel);
			sensorColumPanels[i] = sensorColumPanel;
		}

		setVisible(true);
	}

	public void AddNewNode(String nString, Object userObject) {
		stationTree.AddNode(nString, userObject);
	}

	public void RemoveNode(String nString) {
		stationTree.removeNode(nString);
	}

	public SubStation getSelectedStation() {
		return (SubStation) stationTree.getSelectObject();
	}

	public void UpdateCurInfo(SubStation station) {
		if ((SubStation) stationTree.getSelectObject() != null) {
			showStation = (SubStation) stationTree.getSelectObject();
		}
		for (i = 0; i < 8; i++) {
			sensorColumPanels[i].upDateSensor(station.getSensors(), page);
			
		}
		pageCnt = showStation.getSensorCnt() / 32;
		if ((showStation.getSensorCnt() % 32) != 0)
			pageCnt += 1;
		label.setText("   第" + String.valueOf(page + 1)+"-"+String.valueOf(pageCnt)+ "页   ");
	}
}
