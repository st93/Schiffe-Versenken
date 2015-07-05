package gui;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;

public class MeineCheckBox extends JCheckBox{
	final int index;
	public MeineCheckBox(int index){
		this.index=index;
		setEnabled(false);
		setBackground(new Color(24,116,205));
		setForeground(Color.WHITE);
	}
	
}
