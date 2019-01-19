package com.zy.views;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.tree.TreePath;

import com.zou.tools.MyTree;
import com.zy.devices.SubStation;

import StationDebug.App;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class StationTreePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private MyTree stationTree = new MyTree("综合分站                  ");
	TreePath path;
	private SubStationItem stationItem = new SubStationItem();
	private SubStationCtrItem stationCtrItem = new SubStationCtrItem();
	
	public StationTreePanel() {
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		scrollPane.setViewportView(stationTree);
		// 设备树控件
		stationTree.setFont(App.font);
		stationTree.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
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
}
