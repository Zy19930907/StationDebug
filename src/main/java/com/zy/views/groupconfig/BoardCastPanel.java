package com.zy.views.groupconfig;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Format;
import java.text.SimpleDateFormat;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardCastPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Font font = new Font("宋体", Font.BOLD, 24);
	private JLabel jLabel;
	private long index, selectFlag;
	private Format format = new SimpleDateFormat("SSS");
	private MyRadioButton[] radioButtons = new MyRadioButton[8];
	public  Color boardCastSelectButton_DisSelect = new Color(0xD9D9D9);
	public  Color boardCastSelectButton_Selected = new Color(0xB0E2FF);
	public  Color boardCastSelectText_Selected = new Color(0x2E8B57);
	public  Color boardCastSelectText_DisSelect = new Color(0x000000);
	
	public BoardCastPanel(int index) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		this.index = index;
		for (int i = 0; i < 8; i++) {
			MyRadioButton radioButton = new MyRadioButton();
			radioButton.setBackground(boardCastSelectButton_DisSelect);
			radioButton.setForeground(boardCastSelectText_DisSelect);
			radioButton.setText(format.format(index * 8+i+1) + "#广播");
			radioButton.setIndex(i);
			radioButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(radioButton.isSelected()) {
						selectFlag |= (1<<radioButton.getIndex());
						radioButton.setBackground(boardCastSelectButton_Selected);
						radioButton.setForeground(boardCastSelectText_Selected);
					}else {
						selectFlag &= ~(1<<radioButton.getIndex());
						radioButton.setBackground(boardCastSelectButton_DisSelect);
						radioButton.setForeground(boardCastSelectText_DisSelect);
					}
				}
			});
			radioButton.setFont(font);
			radioButtons[i] = radioButton;
			add(radioButton);
			if (i != 7) {
				jLabel = new JLabel("   ");
				add(jLabel);
			}
		}
	}

	public long getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public long getSelectFlag() {
		return selectFlag;
	}

	public void setSelectFlag(int selectFlag) {
		this.selectFlag = selectFlag;
	}
	
	public void disSelectAll() {
		for(int i=0;i<8;i++)
			radioButtons[i].setSelected(false);
	}
	
	public void setSelect(int flag) {
		selectFlag = flag;
		for(int i=0;i<8;i++) {
			if(((flag >> i) & 0x01) == 0x01) {
				radioButtons[i].setBackground(boardCastSelectButton_Selected);
				radioButtons[i].setForeground(boardCastSelectText_Selected);
				radioButtons[i].setSelected(true);
			}
			else {
				radioButtons[i].setBackground(boardCastSelectButton_DisSelect);
				radioButtons[i].setForeground(boardCastSelectText_DisSelect);
				radioButtons[i].setSelected(false);
			}
		}
	}
}
