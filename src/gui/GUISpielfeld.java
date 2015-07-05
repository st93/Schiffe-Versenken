package gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

public class GUISpielfeld extends JPanel{
	

	private MeinButton[][] btnArray;
	private int feldgr;
	private JPanel spielfeld;
	
	public GUISpielfeld(int feldgr){
		super();
		this.feldgr=feldgr;
		
		setBackground(Design.hintergrund);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc2=new GridBagConstraints();
		//Spielfeld mit Buttons und Scrollleiste erstellen
		spielfeld=new JPanel();
		spielfeld.setBackground(Design.hintergrund);
		
		spielfeld.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.ipady=15;
		gbc.ipadx=-10;
		
		JScrollPane jsp=new JScrollPane();
		jsp.setPreferredSize(new Dimension(258*2,269*2));
		btnArray=new MeinButton[feldgr][feldgr];
		for(int i=0;i<feldgr;i++){
			for(int k=0;k<feldgr;k++){
				gbc.gridx=i+1;
				gbc.gridy=k+1;
				btnArray[i][k]=new MeinButton(i,k);
				btnArray[i][k].setBackground(Design.wasser);
				//btnArray[i][k].addActionListener(new MeinActionListener());
				if(feldgr>20){
					btnArray[i][k].setToolTipText("Zeile: "+String.valueOf(i+1)+"/ Spalte: "+String.valueOf(k+1));
				}
				spielfeld.add(btnArray[i][k],gbc);
			}
		}
		for(int i=1;i<=feldgr;i++){
			gbc.gridx=0;
			gbc.gridy=i;
			gbc.ipadx=0;
			gbc.ipady=0;
			
			gbc2.gridx=i;
			gbc2.gridy=0;
			spielfeld.add(new JLabel(String.valueOf(i)),gbc);
			spielfeld.add(new JLabel(String.valueOf(i)),gbc2);
		}
		
		jsp.setViewportView(spielfeld);
		gbc2.gridx=0;
		gbc2.gridy=0;
		
		//jsp zu Layout hinzufügen und das ganze mit setVisible sichtbar machen
		add(jsp,gbc2);
		setVisible(true);
	}
	private class MeinActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			MeinButton btn=(MeinButton)e.getSource();
			if(btn.getIsClicked()){
				btn.setBackground(Design.wasser);
				btn.setIsClicked(false);
			}
			else{
				btn.setBackground(Color.RED);
				btn.setIsClicked(true);
			}
			
			/*for(int i=0; i<btnArray.length;i++){
				for(int k=0; k<feldgr;k++){
					if(e.getSource()==btnArray[i][k]){
						if(btnArray[i][k].getIsClicked()){
							btnArray[i][k].setBackground(Color.BLACK);
							btnArray[i][k].setIsClicked(false);
						}
						else{
							btnArray[i][k].setBackground(Color.RED);
							btnArray[i][k].setIsClicked(true);
						}
						repaint();
					}
				}
			}*/
			
		}

	}
	
	public MeinButton[][] getBtnArray(){
		return this.btnArray;
	}

}
