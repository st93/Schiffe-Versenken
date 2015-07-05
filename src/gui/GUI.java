package gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JFrame{
	private MeinButton[][] btnArray;
	
	GUI(String title){
		super(title);
		
		//größe festlegen
		this.setSize(800, 800);
		
		JPanel rechts=new JPanel();
		JPanel links=new JPanel();
		
		links.setLayout(new GridLayout(4,2));
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx=0;
		gbc.gridy=0;
		this.add(links,gbc);
		
		gbc.gridx=1;
		gbc.gridy=0;
		//ButtonMatrix
		
		GUISpielfeld gsp=new GUISpielfeld(10);
		for(MeinButton[] x:gsp.getBtnArray()){
			for(MeinButton y:x){
				y.addActionListener(new MeinActionListener());
			}
		}
		
		//JComboBox
		
		//Buttons einfügen
		//Button1 erstellen
		JButton button1=new JButton("Button 1");
		//Hintergrundfarbe einstellen
		button1.setBackground(Color.BLACK);
		button1.setForeground(Color.GREEN);
		
		links.add(button1);
		links.add(new JButton("Button 2"));
		links.add(new JButton("Button 3"));
		links.add(new JButton("Button 4"));
		
		add(gsp,gbc);
		
		//Canvas
		//this.add(new Canvas());
		
		//schließen ermöglichen
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//sichtbar machen
		this.setVisible(true);
	}
	
	
	private class MeinActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			MeinButton btn=(MeinButton)e.getSource();
			if(btn.getIsClicked()){
				btn.setBackground(Color.BLACK);
				btn.setIsClicked(false);
			}
			else{
				btn.setBackground(Color.RED);
				btn.setIsClicked(true);
			}
			
		}

	}
	public static void main(String[] args){
		GUI gui=new GUI("meinFenster");
		//gui.setVisible(false);
		//gui.dispose();
	}
}
