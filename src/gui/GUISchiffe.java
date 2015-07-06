package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Schiffe.Schiffe;
import SpielLogik.*;



public class GUISchiffe extends JPanel {
	
	private boolean nullGekl=true;
	private boolean gesetzt;
	private int ausrichtung;
	private GUISpielfeld feld;
	private Board spielerFeld;
	private Spieler spieler;
	private int intZeile=-1;
	private int intSpalte=-1;
	private Schiffe s;
	private int schiffNum=0;
	private ArrayList<Schiffe> slist;
	
	private JPanel alleEig;
	
	private JLabel instruc=new JLabel("Setze deine Schiffe");
	private JLabel schiffe=new JLabel("Schiffe: ");
	private JLabel zeile=new JLabel("Zeile: ");
	private JLabel spalte=new JLabel("Spalte: ");
	private JLabel aus=new JLabel("Ausrichtung: ");
	
	private JLabel schiffeLabel=new JLabel();
	private JComboBox<Integer> zeileBox=new JComboBox<Integer>();
	private JComboBox<Integer> spalteBox=new JComboBox<Integer>();
	private JComboBox<String> ausBox;
	
	private JButton setzen;
	private JButton fertig;
	
	public GUISchiffe(Spieler spieler){
			super();
			this.spieler= spieler;
			this.gesetzt=false;
			ausrichtung=1;
			spielerFeld=spieler.getSpielerFeld();
			slist=spieler.getSchiffListe();
			this.s=slist.get(schiffNum);
			this.schiffeLabel.setText(s.getName());
			this.schiffeLabel.setFont(Design.inFeld);
			
			//getContentPane().setBackground(Design.hintergrund);
			setBackground(Design.hintergrund);
			setSize(1000,850);
			setMinimumSize(new Dimension(850,680));
			setLayout(new GridBagLayout());
			GridBagConstraints gbc=new GridBagConstraints();
			
			/*
			alleFelder=new GUISpielfeld[spielerArray.length];
			for(int i=0;i<spielerArray.length;i++){
				alleFelder[i]=spielerArray[i].getGuifeld();
			}*/
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
			GridBagConstraints pgbc=new GridBagConstraints();
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
			pgbc.gridx=1;
			pgbc.gridy=0;
			alleEig.add(schiffeLabel,pgbc);
			
			//JComboBox für die Auswahl der Zeile
			//
			zeileBox=new JComboBox<Integer>();
			zeileBox.setFont(Design.inFeld);
			for(int i=0;i<spieler.getSpielerFeld().getSize();i++){
				zeileBox.addItem((i+1));
			}
			zeileBox.setSelectedIndex(-1);
			zeileBox.setMaximumRowCount(5);
			zeileBox.addItemListener(new meinZeilenSpaltenListener());
			pgbc.gridx=1;
			pgbc.gridy=1;
			alleEig.add(zeileBox,pgbc);
			
			//JComboBox für die Auswahl der Spalte
			//
			spalteBox=new JComboBox<Integer>();
			spalteBox.setFont(Design.inFeld);
			for(int i=0;i<spieler.getSpielerFeld().getSize();i++){
				spalteBox.addItem((i+1));
			}
			spalteBox.setSelectedIndex(-1);
			spalteBox.setMaximumRowCount(5);
			spalteBox.addItemListener(new meinZeilenSpaltenListener());
			pgbc.gridx=1;
			pgbc.gridy=2;
			alleEig.add(spalteBox,pgbc);
			
			
			//JComboBox für die Ausrichtung
			//
			ausBox=new JComboBox<String>();
			ausBox.setFont(Design.inFeld);
			ausBox.addItem("Rechts");
			ausBox.addItem("Links");
			ausBox.addItem("Oben");
			ausBox.addItem("Unten");
			ausBox.addItemListener(new MeinAusrichtungsListener());
			pgbc.gridx=1;
			pgbc.gridy=3;
			alleEig.add(ausBox,pgbc);
			
			
			//Spielfeld erstellen
			//
			feld=spieler.getGuifeld();
			feld.setPreferredSize(new Dimension(258*2,269*2));
			for(MeinButton[] btnArray:feld.getBtnArray()){
				for(MeinButton btn:btnArray){
					btn.addActionListener(new MeinActionListenerSetzen());
			
				}
			}
			gbc.gridx=1;
			gbc.gridy=1;
			add(feld,gbc);
			
			//SetzenButton erstellen
			//
			setzen=new JButton("Setzen");
			setzen.setFont(Design.inFeld);
			setzen.setEnabled(false);
			setzen.addActionListener(new MeinActionListener());
			gbc.gridx=0;
			gbc.gridy=2;
			add(setzen,gbc);
			
			//Fertig Button erstellen
			//
			fertig=new JButton("nächster Spieler");
			fertig.setFont(Design.inFeld);
			fertig.setEnabled(false);
			gbc.gridx=1;
			gbc.gridy=2;
			add(fertig,gbc);
			
			
			setVisible(true);
	}
	
	private class MeinActionListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(schiffNum<spieler.getSchiffListe().size()-1){
				
				setzeSchiff(schiffNum);
				schiffNum++;
				updateFeld();
				spieler.setGuifeld(feld);
			}
			else{
				setzeSchiff(schiffNum);
				schiffNum++;
				updateFeld();
				System.out.println("alle schiffe gesetzt");
				fertig.setEnabled(true);
				setzen.setEnabled(false);
				spieler.setGuifeld(feld);
			}
		}
		
	}
	
	
	private class MeinActionListenerSetzen implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			MeinButton[][] btnArray=feld.getBtnArray();
			MeinButton btn=(MeinButton)e.getSource();
				
				int i=btn.getSpalte();
				int k=btn.getZeile();
						
				if(btn.getIsClicked()){
				btn.setIsClicked(false);
				btn.setBackground(Design.wasser);
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
						btnArray[intSpalte-1][intZeile-1].setBackground(Design.wasser);
						btn.setIsClicked(true);
						btn.setBackground(Design.zeiger);
								
					}
				}
			intSpalte=i+1;
			intZeile=k+1;
			testDat();
			spalteBox.setSelectedIndex(i);
			zeileBox.setSelectedIndex(k);
		}
				
	}
			
	
	
	private class MeinAusrichtungsListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			/*JComboBox<String> x=(JComboBox<String>)e.getSource();
			x.get()*/
			if(e.getStateChange()==ItemEvent.SELECTED){
				String s=(String)e.getItem();
				if(s=="Rechts"){
					ausrichtung=1;
					testDat();
				}
				else if(s=="Links"){
					ausrichtung=2;
					testDat();
				}
				else if(s=="Oben"){
					ausrichtung=3;
					testDat();
				}
				else if(s=="Unten"){
					ausrichtung=4;
					testDat();
				}
				else{
					setzen.setEnabled(false);
				}
			}
			
		}
		
	}
	
	private class meinZeilenSpaltenListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange()==ItemEvent.SELECTED){
				Integer i=(Integer)e.getItem();
				System.out.println(i);
				System.out.println("Zeile "+intZeile);
				System.out.println("Spalte "+intSpalte);
				if((JComboBox<Integer>)e.getSource()==zeileBox){
					if(i==intZeile){
						
					}
					else if(intZeile<1){
						intZeile=i;
						if(intSpalte>0){
							klicken(i,intSpalte);
						}
					}
					else{
						klicken(i,intSpalte);
					}
					
				}
				else if((JComboBox<Integer>)e.getSource()==spalteBox){
					if(i==intSpalte){
						
					}
					else if(intSpalte<1){
						intSpalte=i;
						if(intZeile>0){
							klicken(i,intZeile);
						}
					}
					else{
						klicken(intZeile,i);
					}
					
				}
			}
			
		}
		
	}

	
	public void testDat(){
		if(schiffNum<spieler.getSchiffListe().size()){
			s=slist.get(schiffNum);
			s.getDirection().clean();
			if(spieler.getSpielerFeld().koordinatenCheck(intZeile-1, intSpalte-1, s)){
				System.out.println("Schiff Kann man setzen!");
				String check=(String)ausBox.getSelectedItem();
				System.out.println("check= "+check);
				if(check=="Rechts"&&s.getDirection().getRechts()){
					setzen.setEnabled(true);
					System.out.println("rechts");
					
				}
				else if(check=="Links"&&s.getDirection().getLinks()){
					setzen.setEnabled(true);
					System.out.println("links");
				}
				else if(check=="Oben"&&s.getDirection().getOben()){
					setzen.setEnabled(true);
					System.out.println("oben");
				}
				else if(check=="Unten"&&s.getDirection().getUnten()){
					setzen.setEnabled(true);
					System.out.println("unten");
				}
				else {
					setzen.setEnabled(false);
				}
				
			}
			else{
				setzen.setEnabled(false);
				System.out.println("kann nicht gesetzt werden");
			}
		}
	}
	
	public void klicken(int zeile,int spalte){
		
		feld.getBtnArray()[spalte-1][zeile-1].doClick();
		
	}
	
	public boolean getGesetzt(){
		return gesetzt;
	}
	public JButton getFertig(){
		return fertig;
	}
	public void updateFeld(){
		Square[][] square=spielerFeld.getFeld();
		for (int i=0;i<spielerFeld.getSize();i++){
			for(int k=0;k<spielerFeld.getSize();k++){
				if(square[k][i].getCounter()==0){
					feld.getBtnArray()[k][i].setBackground(Design.wasser);
					feld.getBtnArray()[k][i].setIsClicked(false);
				}
				if(square[i][k].getCounter()==1){
					feld.getBtnArray()[k][i].setBackground(Design.schiff);
					feld.getBtnArray()[k][i].setEnabled(false);
					feld.getBtnArray()[k][i].setIsClicked(false);
				}
			}
		}
		gesetzt=false;
		nullGekl=true;
		ausrichtung=1;
		intSpalte=-1;
		intZeile=-1;
		if(schiffNum<slist.size()){
			schiffeLabel.setText(slist.get(schiffNum).getName());
		}
		zeileBox.setSelectedIndex(-1);
		spalteBox.setSelectedIndex(-1);
		ausBox.setSelectedIndex(0);
		setzen.setEnabled(false);
	}
	
	
	public void setzeSchiff(int schiffIndex){
		ArrayList<Schiffe> s=spieler.getSchiffListe();
		Schiffe schiff=s.get(schiffIndex);
		System.out.println("Schiff: "+schiff.getName()+" Zeile: "+intZeile+"Spalte: "+intSpalte+"Ausrichtung: "+ausrichtung);
		if(ausrichtung==1){
			spieler.getSpielerFeld().schiffSetzenRechts(intZeile-1, intSpalte-1, schiff);
			schiff.getDirection().clean();
			schiff.setRechts(true);
		}
		if(ausrichtung==2){
			spieler.getSpielerFeld().schiffSetzenLinks(intZeile-1, intSpalte-1, schiff);
			schiff.getDirection().clean();
			schiff.setLinks(true);
		}
		if(ausrichtung==3){
			spieler.getSpielerFeld().schiffSetzenOben(intZeile-1, intSpalte-1, schiff);
			schiff.getDirection().clean();
			schiff.setOben(true);
		}
		if(ausrichtung==4){
			spieler.getSpielerFeld().schiffSetzenUnten(intZeile-1, intSpalte-1, schiff);
			schiff.getDirection().clean();
			schiff.setUnten(true);
		}
	}
	
	public int getIntZeile(){
		return intZeile;
	}
	
	public int getIntSpalte(){
		return intSpalte;
	}
	public int getAusrichtung(){
		return ausrichtung;
	}
	public GUISpielfeld getFeld(){
		return feld;
	}
}
