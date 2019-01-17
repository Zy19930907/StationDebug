package com.zy.views.groupconfig;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import StationDebug.App;

public class GroupPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Font font = new Font("宋体", Font.BOLD, 24);
	private ButtonGroup group = new ButtonGroup();
	private int selectedGroup;
	JLabel label;

	public GroupPanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		for (int i = 0; i < 8; i++) {
			JCheckBox groupn = new JCheckBox("广播分组" + String.valueOf(i + 1));
			groupn.setFont(font);
			
			if(i==0)
				groupn.setSelected(true);
			groupn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if (groupn.isSelected()) {
						switch (groupn.getText()) {
						case "广播分组1":
							selectedGroup = 0;
							App.groupConfigView.freshSubStation(0);
							break;
						case "广播分组2":
							selectedGroup = 1;
							App.groupConfigView.freshSubStation(1);
							break;
						case "广播分组3":
							selectedGroup = 2;
							App.groupConfigView.freshSubStation(2);
							break;
						case "广播分组4":
							selectedGroup = 3;
							App.groupConfigView.freshSubStation(3);
							break;
						case "广播分组5":
							selectedGroup = 4;
							App.groupConfigView.freshSubStation(4);
							break;
						case "广播分组6":
							selectedGroup = 5;
							App.groupConfigView.freshSubStation(5);
							break;
						case "广播分组7":
							selectedGroup = 6;
							App.groupConfigView.freshSubStation(6);
							break;
						case "广播分组8":
							selectedGroup = 7;
							App.groupConfigView.freshSubStation(7);
							break;
						default:
							break;
						}
					}
				}
			});
			group.add(groupn);
			add(groupn);
			if (i != 7) {
				label = new JLabel(" ");
				add(label);
			}
		}
	}

	public int getSelectedGroup() {
		return selectedGroup;
	}
}
