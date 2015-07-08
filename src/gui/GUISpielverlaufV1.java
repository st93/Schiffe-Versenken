package gui;




import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Schiffe.*;
import SpielLogik.*;

public class GUISpielverlaufV1 extends JPanel{
	private int feldgr;
	private Spieler[] spielerArray;
	private Spieler aktiveS;
	private boolean nullGekl=true;
	private int ausrichtung=2;
	private int intZeile=-1;
	private int intSpalte=-1;
	private Schiffe aktiveSchiff;
	private GUISpielfeld feld;
	private int spielerIndex=0;
	private int tabIndex=-1;
	private boolean treffer;
	private Spieler ziel;
	
	
	private JPanel[] alleFelder;
	private GUISpielfeld[] alleSpielfelder;
	private JComboBox<Integer> zeileBox=new JComboBox<Integer>();
	private JLabel zeile=new JLabel("Zeile:");
	private JComboBox<Integer> spalteBox=new JComboBox<Integer>();
	private JLabel spalte=new JLabel("Spalte:");
	private JComboBox<String> schiffeBox;
	private JLabel schiffe=new JLabel("Schiff:");
	private JComboBox<String> ausBox;
	private JLabel aus=new JLabel("Ausrichtung");
	
	private JLabel instruc= new JLabel(" ist an der Reihe");
	private JTabbedPane tabs;
	private JPanel alleEig;
	private JPanel felder;
	private JButton schuss;
	
	public GUISpielverlaufV1(Spieler[] spielerArray,Spieler spieler){
		super();
		this.spielerArray=spielerArray;
		
		this.aktiveS=spieler;
		instruc.setText(aktiveS.getName()+" ist an der Reihe");
		feldgr=spielerArray[0].getSpielerFeld().getSize();
		setBackground(Design.hintergrund);
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc=new GridBagConstraints();
		
		alleEig=new JPanel();
		alleEig.setLayout(new GridBagLayout());
		GridBagConstraints pgbc=new GridBagConstraints();
		
		felder=new JPanel();
		
		alleFelder=new JPanel[spielerArray.length];
		alleSpielfelder=new GUISpielfeld[spielerArray.length];
		
		tabs=new JTabbedPane();
		tabs.setBackground(Design.hintergrund);
		tabs.addChangeListener(new MeinChangeListener());
		for(int i=0;i<spielerArray.length;i++){
			if(spielerArray[i]==aktiveS){
				alleSpielfelder[i]=spielerArray[i].getGuifeld();
				
			}
			else{
				
				alleSpielfelder[i]=spielerArray[i].getGuifeld();
				//alleSpielfelder[i]=new GUISpielfeld(feldgr);
				for (MeinButton[] x:alleSpielfelder[i].getBtnArray()){
					for(MeinButton y:x){
						y.addActionListener(new MeinActionListenerSchiessen());
					}
				}
			}
			tabs.setPreferredSize(new Dimension(258*2,269*2));
			alleFelder[i]=new JPanel();
			alleFelder[i].add(alleSpielfelder[i]);
			tabs.add(spielerArray[i].getName(),alleFelder[i]);
			feld=alleSpielfelder[0];
			updateFeld();
			revalidate();
			repaint();
			
		}
		felder.add(tabs);
		felder.setBackground(Design.hintergrund);
		
		gbc.gridx=3;
		gbc.gridy=2;
		gbc.gridheight=5;
		gbc.gridwidth=5;
		add(tabs,gbc);
		instruc.setFont(Design.titel);
		gbc.gridx=3;
		gbc.gridy=0;
		gbc.gridheight=1;
		gbc.gridwidth=5;
		add(instruc,gbc);
		
		alleEig= new JPanel();
		gbc.gridwidth=3;
		gbc.gridheight=5;
		gbc.gridx=0;
		gbc.gridy=3;
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
		int uboZ=0;
		int korZ=0;
		int freZ=0;
		int zerZ=0;
		for(Schiffe schiffe:aktiveS.getSchiffListe()){
			if(schiffe.getTyp()==1&&schiffe.schussBereit()&&uboZ==0){
				schiffeBox.addItem("UBoot");
				uboZ++;
			}
			if(schiffe.getTyp()==2&&schiffe.schussBereit()&&korZ==0){
				schiffeBox.addItem("Korvette");
				korZ++;
			}
			if(schiffe.getTyp()==3&&schiffe.schussBereit()&&freZ==0){
				freZ++;
				schiffeBox.addItem("Fregatte");
			}
			if(schiffe.getTyp()==4&&schiffe.schussBereit()&&zerZ==0){
				zerZ++;
				schiffeBox.addItem("Zerstörer");
			}
		}
		schiffeBox.setSelectedIndex(-1);
		schiffeBox.addItemListener(new SchiffListener());
		pgbc.gridx=1;
		pgbc.gridy=0;
		alleEig.add(schiffeBox,pgbc);
		
		
		//JComboBox für die Auswahl der Zeile
		//
		this.zeileBox=new JComboBox<Integer>();
		zeileBox.setFont(Design.inFeld);
		for(int i=0;i<spielerArray[0].getSpielerFeld().getSize();i++){
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
		this.spalteBox=new JComboBox<Integer>();
		spalteBox.setFont(Design.inFeld);
		for(int i=0;i<spielerArray[0].getSpielerFeld().getSize();i++){
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
		ausBox.addItem("Horizontal");
		ausBox.addItem("Vertikal");
		ausBox.addItemListener(new MeinAusrichtungsListener());
		pgbc.gridx=1;
		pgbc.gridy=3;
		alleEig.add(ausBox,pgbc);
		
		
		//SchiessenButton erstellen
		//
		schuss=new JButton("Schuss");
		schuss.setFont(Design.inFeld);
		schuss.setEnabled(false);
		schuss.addActionListener(new MeinSchussListener());
		gbc.gridx=0;
		gbc.gridy=10;
		add(schuss,gbc);
		
			
		//gbc.gridx=0;
		//gbc.gridy=10;
		/*spielerIndex=kITest(spielerIndex);
		if(spielerIndex<spielerArray.length){
			feld=spielerArray[spielerIndex].getGuifeld();
			add(feld,gbc);
			revalidate();
			repaint();
		}
		
		updateFeld();*/
		
		repaint();
		revalidate();
		setVisible(true);
		
	}
	
	private class MeinSchussListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			resetSchiffBox();
			if(ausrichtung==1){
				MeinButton[][] array=ziel.getGuifeld().getBtnArray();
			}
			updateFeld();
		}
		
	}
	
	
	private class MeinActionListenerSchiessen implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int x=tabs.getSelectedIndex();
			feld=(GUISpielfeld) alleSpielfelder[x];
			MeinButton[][] btnArray=feld.getBtnArray();
			MeinButton btn=(MeinButton)e.getSource();
				
				int i=btn.getSpalte();
				int k=btn.getZeile();
						
				if(btn.getIsClicked()){
				btn.setIsClicked(false);
				btn.setBackground(Design.wasser);
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
						btnArray[intSpalte-1][intZeile-1].setBackground(Design.wasser);
						updateFeld();
						btn.setIsClicked(true);
						btn.setBackground(Design.zeiger);
								
					}
				}
			intSpalte=i+1;
			intZeile=k+1;
			spalteBox.setSelectedIndex(i);
			zeileBox.setSelectedIndex(k);
			testeBereit();
		}
				
	}
	
	private class MeinAusrichtungsListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange()==ItemEvent.SELECTED){
				String string=(String)e.getItem();
				if(string=="Horizontal"){
					ausrichtung=2;
					testeBereit();
				}
				else if(string=="Vertikal"){
					ausrichtung=1;
					testeBereit();
				}
				else{
					schuss.setEnabled(false);
				}
				
			}
			
		}
		
	}
	
	private class MeinChangeListener implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent e) {
			int i=tabs.getSelectedIndex();
			if(i!=tabIndex){
				if(i>=0){
					tabIndex=i;
					feld=alleSpielfelder[i];
					ziel=spielerArray[i];
				}
			}
			resetPara();
			
		}
		
	}
	
	private class SchiffListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange()==ItemEvent.SELECTED){
				String string=(String)e.getItem();
				if(string=="UBoot"){
					for(Schiffe schiffe:aktiveS.getSchiffListe()){
						if(schiffe.getTyp()==1){
							aktiveSchiff=schiffe;
							testeBereit();
							break;
						}
					}
				}
				if(string=="Korvette"){
					for(Schiffe schiffe:aktiveS.getSchiffListe()){
						if(schiffe.getTyp()==2){
							aktiveSchiff=schiffe;
							testeBereit();
							break;
						}
					}
				}
				if(string=="Fregatte"){
					for(Schiffe schiffe:aktiveS.getSchiffListe()){
						if(schiffe.getTyp()==3){
							aktiveSchiff=schiffe;
							testeBereit();
							break;
						}
					}
				}
				if(string=="Zerstörer"){
					for(Schiffe schiffe:aktiveS.getSchiffListe()){
						if(schiffe.getTyp()==4){
							aktiveSchiff=schiffe;
							testeBereit();
							break;
						}
					}
				}
			}
			
		}
		
	}
	
	private class meinZeilenSpaltenListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange()==ItemEvent.SELECTED){
				Integer i=(Integer)e.getItem();
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
	
	public void updateFeld(){
		for(Spieler spieler:spielerArray){
			GUISpielfeld feldX=spieler.getGuifeld();
			spieler.getSpielerFeld().printCounter();
			Square[][]s=spieler.getSpielerFeld().getFeld();
			for (int i=0;i<spieler.getSpielerFeld().getSize();i++){
				for(int k=0;k<spieler.getSpielerFeld().getSize();k++){
//					if(spieler==aktiveS){
					  switch(s[i][k].getCounter()){
			                
			                	case 0:
			                		feldX.getBtnArray()[k][i].setBackground(Design.wasser);
			                		break;
			                	case 1:
			                		feldX.getBtnArray()[k][i].setBackground(Design.schiff);
			                		break;
			                		
			                	case 2:
			                		feldX.getBtnArray()[k][i].setBackground(Design.sTreffer);
			                		break;
			                		
			                	case 3:
			                		feldX.getBtnArray()[k][i].setBackground(Design.wTreffer);
			                		break;
			                		
			                	case 4:
			                		feldX.getBtnArray()[k][i].setBackground(Design.versenkt);
			                		break;
			                		
			                	default:
			                		feldX.getBtnArray()[k][i].setBackground(Color.PINK);
			                		break;
			                }
					  feldX.getBtnArray()[k][i].setIsClicked(false);
					  }

						/*if(s[i][k].getCounter()==0){
							feldX.getBtnArray()[k][i].setBackground(Design.wasser);
							feldX.getBtnArray()[k][i].setIsClicked(false);
						}
						if(s[i][k].getCounter()==1){
							
							feldX.getBtnArray()[k][i].setBackground(Design.schiff);
							feldX.getBtnArray()[k][i].setIsClicked(false);
						}
						if(s[i][k].getCounter()==2){
							feldX.getBtnArray()[k][i].setBackground(Color.RED);
							feldX.getBtnArray()[k][i].setIsClicked(false);
						}
						
						if(s[i][k].getCounter()==3){
							feldX.getBtnArray()[k][i].setBackground(Design.wTreffer);
							feldX.getBtnArray()[k][i].setIsClicked(false);
						}
						
						if(s[i][k].getCounter()==4){
							feldX.getBtnArray()[k][i].setBackground(Design.versenkt);
							feldX.getBtnArray()[k][i].setIsClicked(false);
						}
//					}
/*					else{
						if(s[i][k].getCounter()==0){
							feld.getBtnArray()[k][i].setBackground(Design.wasser);
							feld.getBtnArray()[k][i].setIsClicked(false);
						}
						if(s[i][k].getCounter()==1){
							feld.getBtnArray()[k][i].setBackground(Design.wasser);
							feld.getBtnArray()[k][i].setIsClicked(false);
						}
						if(s[i][k].getCounter()==2){
							feld.getBtnArray()[k][i].setBackground(Design.sTreffer);
							feld.getBtnArray()[k][i].setIsClicked(false);
						}
						
						if(s[i][k].getCounter()==3){
							feld.getBtnArray()[k][i].setBackground(Design.wTreffer);
							feld.getBtnArray()[k][i].setIsClicked(false);
						}
						
						if(s[i][k].getCounter()==4){
							feld.getBtnArray()[k][i].setBackground(Design.versenkt);
							feld.getBtnArray()[k][i].setIsClicked(false);
						}
					}*/
				}
			}
		
		revalidate();
		repaint();
		
	}
	
	public void resetSchiffBox(){
		int uboZ=0;
		int korZ=0;
		int freZ=0;
		int zerZ=0;
		schiffeBox.removeAllItems();
		for(Schiffe schiffe:aktiveS.getSchiffListe()){
			if(schiffe.getTyp()==1&&schiffe.getVersenkt()==false&& schiffe.getReg()==0&&uboZ==0){
				schiffeBox.addItem("UBoot");
				uboZ++;
			}
			if(schiffe.getTyp()==2&&schiffe.getVersenkt()==false&& schiffe.getReg()==0&&korZ==0){
				schiffeBox.addItem("Korvette");
				korZ++;
			}
			if(schiffe.getTyp()==3&&schiffe.getVersenkt()==false&& schiffe.getReg()==0&&freZ==0){
				freZ++;
				schiffeBox.addItem("Fregatte");
			}
			if(schiffe.getTyp()==4&&schiffe.getVersenkt()==false&& schiffe.getReg()==0&&zerZ==0){
				zerZ++;
				schiffeBox.addItem("Zerstörer");
			}
		}
		schiffeBox.setSelectedIndex(-1);
	}
	
	public void resetPara(){

		nullGekl=true;
		ausrichtung=2;
		intSpalte=-1;
		intZeile=-1;
		this.zeileBox.setSelectedIndex(-1);
		this.spalteBox.setSelectedIndex(-1);
		repaint();
	}
	
	public void klicken(int zeile,int spalte){
		
		feld.getBtnArray()[spalte-1][zeile-1].doClick();
		
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
	
	public boolean schiessen(){
		Board spielboard=ziel.getSpielerFeld();
		if(spielboard.schiessen(intZeile-1, intSpalte-1, ausrichtung, aktiveSchiff)){
			updateFeld();
			return true;
		}
		updateFeld();
		return false;
		
	}
	
	public void testeBereit(){
		if(schiffeBox.getSelectedIndex()!=-1&&intZeile!=-1&&intSpalte!=-1){
			schuss.setEnabled(true);
		}
	}
	
	public JButton getSchussBtn(){
		return schuss;
	}
	
	public Schiffe getAktiveSchiff(){
		return aktiveSchiff;
	}
	
	public void setAktiveS(Spieler s){
		this.aktiveS=s;
	}
	
}
