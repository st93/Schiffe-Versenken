package gui;




import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Schiffe.*;
import SpielLogik.*;

public class GUISpielverlauf extends JPanel{
	private int feldgr;
	private Spieler[] spielerArray;
	private boolean nullGekl=true;
	private int ausrichtung;
	private int intZeile=-1;
	private int intSpalte=-1;
	private Schiffe s;
	private GUISpielfeld feld;
	//private Board spielerFeld;
	private int spielerIndex=0;
	
	
	
	private JComboBox zeileBox;
	private JLabel zeile=new JLabel("Zeile:");
	private JComboBox spalteBox;
	private JLabel spalte=new JLabel("Spalte:");
	private JComboBox schiffeBox;
	private JLabel schiffe=new JLabel("Schiff:");
	private JComboBox ausBox;
	private JLabel aus=new JLabel("Ausrichtung");
	
	private JLabel instruc= new JLabel(" ist an der Reihe");
	private JTabbedPane tabs;
	private JPanel alleEig;
	private JPanel felder;
	private JButton schuss;
	
	public GUISpielverlauf(Spieler[] spielerArray){
		super();
		this.spielerArray=spielerArray;
		feldgr=spielerArray[0].getSpielerFeld().getSize();
		setBackground(Design.hintergrund);
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc=new GridBagConstraints();
		
		alleEig=new JPanel();
		alleEig.setLayout(new GridBagLayout());
		GridBagConstraints pgbc=new GridBagConstraints();
		
		felder=new JPanel();
		
		tabs=new JTabbedPane();
		tabs.setForeground(Design.hintergrund);
		for(int i=0;i<spielerArray.length;i++){
			GUISpielfeld sp=spielerArray[i].getGuifeld();
			for (MeinButton[] x:sp.getBtnArray()){
				for(MeinButton y:x){
					y.addActionListener(new MeinActionListenerSchiessen());
				}
			}
			//tabs.setPreferredSize(new Dimension(258*2,269*2));
			tabs.add(spielerArray[i].getName(),sp);
		}
		felder.add(tabs);
		
		gbc.gridx=2;
		gbc.gridy=1;
		gbc.gridheight=5;
		gbc.gridwidth=5;
		add(felder,gbc);
		instruc.setFont(Design.titel);
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.gridwidth=2;
		add(instruc,gbc);
		
		alleEig= new JPanel();
		gbc.gridwidth=1;
		gbc.gridx=0;
		gbc.gridy=1;
		add(alleEig,gbc);
		alleEig.setLayout(new GridBagLayout());
		alleEig.setBackground(Design.hintergrund);
		pgbc.fill=GridBagConstraints.HORIZONTAL;
		
		pgbc.gridx=0;
		pgbc.gridy=0;
		schiffe.setFont(Design.unterM);
		alleEig.add(schiffe,pgbc);
		
		pgbc.gridx=0;
		pgbc.gridy=1;
		zeile.setFont(Design.unterM);
		alleEig.add(zeile,pgbc);
		
		pgbc.gridx=0;
		pgbc.gridy=2;
		spalte.setFont(Design.unterM);
		alleEig.add(spalte,pgbc);
		
		pgbc.gridx=0;
		pgbc.gridy=3;
		aus.setFont(Design.unterM);
		alleEig.add(aus,pgbc);
		
		
		//JComboBox für die Schiffe
		//
		schiffeBox=new JComboBox<String>();
		schiffeBox.setFont(Design.inFeld);
		schiffeBox.addItem("UBoot");
		schiffeBox.addItem("Korvette");
		schiffeBox.addItem("Fregatte");
		schiffeBox.addItem("Zerstörer");
		//ausBox.addItemListener(new meinAusrichtungsListener());
		pgbc.gridx=1;
		pgbc.gridy=0;
		alleEig.add(schiffeBox,pgbc);
		
		
		//JComboBox für die Auswahl der Zeile
		//
		zeileBox=new JComboBox<Integer>();
		zeileBox.setFont(Design.inFeld);
		for(int i=0;i<spielerArray[0].getSpielerFeld().getSize();i++){
			zeileBox.addItem((i+1));
		}
		zeileBox.setSelectedIndex(-1);
		zeileBox.setMaximumRowCount(5);
		//zeileBox.addItemListener(new meinZeilenSpaltenListener());
		pgbc.gridx=1;
		pgbc.gridy=1;
		alleEig.add(zeileBox,pgbc);
		
		//JComboBox für die Auswahl der Spalte
		//
		spalteBox=new JComboBox<Integer>();
		spalteBox.setFont(Design.inFeld);
		for(int i=0;i<spielerArray[0].getSpielerFeld().getSize();i++){
			spalteBox.addItem((i+1));
		}
		spalteBox.setSelectedIndex(-1);
		spalteBox.setMaximumRowCount(5);
		//spalteBox.addItemListener(new meinZeilenSpaltenListener());
		pgbc.gridx=1;
		pgbc.gridy=2;
		alleEig.add(spalteBox,pgbc);
		
		
		//JComboBox für die Ausrichtung
		//
		ausBox=new JComboBox<String>();
		ausBox.setFont(Design.inFeld);
		ausBox.addItem("Horizontal");
		ausBox.addItem("Vertikal");
		//ausBox.addItemListener(new meinAusrichtungsListener());
		pgbc.gridx=1;
		pgbc.gridy=3;
		alleEig.add(ausBox,pgbc);
		
		
		//SchiessenButton erstellen
		//
		schuss=new JButton("Schuss");
		schuss.setFont(Design.inFeld);
		schuss.setEnabled(false);
		schuss.addActionListener(new MeinActionListener());
		gbc.gridx=0;
		gbc.gridy=2;
		add(schuss,gbc);
		setVisible(true);
		
			
		spielerIndex=kITest(spielerIndex);
		if(spielerIndex<spielerArray.length){
			feld=spielerArray[spielerIndex].getGuifeld();
			add(feld,gbc);
			revalidate();
			repaint();
		}
		
	}
	
	private class MeinActionListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			/*if(schiffNum<spieler.getSchiffListe().size()-1){
				setzeSchiff(schiffNum);
				schiffNum++;
				updateFeld();
			}
			else{
				setzeSchiff(schiffNum);
				updateFeld();
				System.out.println("alle schiffe gesetzt");
				fertig.setEnabled(true);
				setzen.setEnabled(false);
				//setzen.setEnabled(false);
			}*/
		}
		
	}
	
	private class MeinActionListenerSchiessen implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			MeinButton[][] btnArray=feld.getBtnArray();
			MeinButton btn=(MeinButton)e.getSource();
				
				int i=btn.getSpalte();
				int k=btn.getZeile();
						
				if(btn.getIsClicked()){
				btn.setIsClicked(false);
				updateFeld();
				nullGekl=true;
							
				}
				else{
					if(nullGekl){
						btn.setIsClicked(true);
						btn.setBackground(Design.zeiger);
						nullGekl=false;
						
					}
					else{
						btnArray[intSpalte-1][intZeile-1].setIsClicked(false);
						updateFeld();
						btn.setIsClicked(true);
						btn.setBackground(Design.zeiger);
								
					}
				}
			intSpalte=i+1;
			intZeile=k+1;
			spalteBox.setSelectedIndex(i);
			zeileBox.setSelectedIndex(k);
		}
				
	}
	
	public void updateFeld(){
		for(Spieler spieler:spielerArray){
			Square[][]s=spieler.getSpielerFeld().getFeld();
			for (int i=0;i<spieler.getSpielerFeld().getSize();i++){
				for(int k=0;k<spieler.getSpielerFeld().getSize();k++){
					if(s[k][i].getCounter()==0){
						feld.getBtnArray()[k][i].setBackground(Design.wasser);
						feld.getBtnArray()[k][i].setIsClicked(false);
					}
					if(s[i][k].getCounter()==2){
						feld.getBtnArray()[k][i].setBackground(Design.sTreffer);
						feld.getBtnArray()[k][i].setIsClicked(false);
					}
					
					if(s[k][i].getCounter()==3){
						feld.getBtnArray()[k][i].setBackground(Design.wTreffer);
						feld.getBtnArray()[k][i].setIsClicked(false);
					}
					
					if(s[k][i].getCounter()==4){
						feld.getBtnArray()[k][i].setBackground(Design.versenkt);
						feld.getBtnArray()[k][i].setIsClicked(false);
					}
				}
			}
		}
		
	}
	
	public void resetPara(){

		nullGekl=true;
		ausrichtung=1;
		intSpalte=-1;
		intZeile=-1;
		zeileBox.setSelectedIndex(-1);
		spalteBox.setSelectedIndex(-1);
	}
	
	public int kITest(int s){
		
		if(s<spielerArray.length){
			while(spielerArray[s].getIsKI()==true){
				if(s<spielerArray.length-1){
					s++;
				}
				else{
					return s;
				}
			}
		}
		return s;
	}
}
