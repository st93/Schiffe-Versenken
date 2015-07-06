package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import SpielLogik.*;
import Schiffe.*;


public class MeinFrame extends JFrame{
	private int setzenIndex=0;
	private int spielerIndex=0;
	private int spielerZahl=2;
	private int computerZahl=0;
	private int spielFeldGr=9;
	private int sGes=4;
	private Spieler[] spielerArray;
	private boolean spielBereit;
	private Spiel game;
	private GUISchiffe[] setzen;
	private GUISpielverlaufV2[] spielverlauf;
	private GUISpielfeld[] spielfelder;
	private int aktiveSpielverlauf;
	
	private Thread t;
	private boolean tSteuerung=true;
	private boolean gewaehlt=false;
	private boolean treffer=false;
	private int rundenZahl=0;
	private boolean abbruch=false;
	
	private JLabel titel;
	
	
	private JButton startBtn;
	
	//JPanel SchiffWahl erstellen
	private SchiffWahl schiffWahl=new SchiffWahl();
	
	//JPanel Grundeinstellungen erstellen
	private Grundeinstellungen basics=new Grundeinstellungen();
	
	
	
	public MeinFrame(String name){
		super(name);
		
		//minimale Größe festlegen
		setMinimumSize(new Dimension(950,710));
		
		//Farbe Hintergrund
		getContentPane().setBackground(Design.hintergrund);
		
		//Größe festlegen
		setSize(1200,800);
		
		//Layout-Manager (GridBag) festlegen
		setLayout(new GridBagLayout());
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.insets=new Insets(5,5,5,5);
		
		//Titellabel erstellen und hinzufügen
		titel=new JLabel("Willkommen");
		titel.setFont(Design.titel);
		gbc.gridwidth=3;
		gbc.gridx=1;
		gbc.gridy=0;
		add(titel,gbc);
		
		//gbc.gridwidth auf 1 setzen
		gbc.gridwidth=1;
		
		//Alle folgenden Elemente auf einer Linie beginnen lassen
		gbc.fill=GridBagConstraints.HORIZONTAL;
		
		//JPanel placehldr
		JPanel placehldr=new JPanel();
		placehldr.setBackground(Design.hintergrund);
		gbc.gridx=1;
		gbc.gridy=1;
		gbc.gridwidth=3;
		add(placehldr,gbc);
		
		//JPanel Grundeinstellungen hinzufügen
		gbc.gridx=0;
		gbc.gridy=2;
		gbc.gridwidth=2;
		add(basics,gbc);
		
		//JPanel SchiffWahl einfügen und initialisieren
		gbc.gridx=3;
		gbc.gridy=2;
		gbc.gridwidth=1;
		gbc.gridheight=2;
		add(schiffWahl,gbc);
		
		//JPanel placehldr
		JPanel placehldr1=new JPanel();
		placehldr1.setBackground(Design.hintergrund);
		gbc.gridx=1;
		gbc.gridy=3;
		gbc.gridwidth=3;
		add(placehldr1,gbc);
		
		
		//StartButton hinzufügen
		startBtn=new JButton("Spiel starten");
		startBtn.setEnabled(true);
		startBtn.setFont(Design.unterM);
		gbc.gridwidth=3;
		gbc.gridx=1;
		gbc.gridy=5;
		startBtn.addActionListener(new StartActionListener());
		add(startBtn,gbc);
		
		//Listener hinzufügen
		//Liste für Anzahl der Schiffe
		for(int i=1;i<5;i++){
			schiffWahl.getList(i).addListSelectionListener(new SchiffListListener());
		}
		
		//Checkboxen für Anzahl Spieler
		basics.getSpielerBox().addItemListener(new MeinItemListener());
		
		//MeineCheckBox
		for(MeineCheckBox i:basics.getAlleCbtn()){
			i.addItemListener(new CheckBoxListener());
		}
		
		//JTextfield alle Spielernamen
		for(JTextField i:basics.getAlleSpieler()){
			i.addFocusListener(new FocusListener(){
				
				@Override
				public void focusGained(FocusEvent arg0) {
					i.selectAll();
					
				}

				@Override
				public void focusLost(FocusEvent arg0) {
					
				}
			});
		}
		
		//Liste mit möglicher SpielerZahl
		basics.getList().addListSelectionListener(new MeinListListener());
		
		
		//schließem auf Knopfdruck
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//sichtbar machen
		setVisible(true);
	}
	
	private class StartActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			game= new Spiel(erstelleSpieler());
			getContentPane().removeAll();
			for(Spieler s:spielerArray){
				s.setGuifeld(new GUISpielfeld(spielFeldGr));
			}
			if(spielerZahl!=computerZahl){
				setzen=new GUISchiffe[spielerZahl];
				spielerIndex=kITest(spielerIndex);
				if(spielerIndex<spielerArray.length){
					System.out.println("als erstes setzt"+spielerIndex);
					System.out.println("an der stelle vom Array: "+ setzenIndex);
					setzen[setzenIndex]=new GUISchiffe(spielerArray[spielerIndex]);
					setzen[setzenIndex].getFertig().addActionListener(new MeinActionListener1());
					add(setzen[setzenIndex]);
					revalidate();
					repaint();
				}
			}
			else{
				for(GUISchiffe x:setzen){
					remove(x);
				}
				getContentPane().removeAll();
				gameloop();
			}
		}
		
	}
	
	private class MeinActionListener1 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			spielerIndex++;
			setzenIndex++;
			spielerIndex=kITest(spielerIndex);
			if(spielerIndex<spielerArray.length){
				setzen[setzenIndex]=new GUISchiffe(spielerArray[spielerIndex]);
				setzen[setzenIndex].getFertig().addActionListener(new MeinActionListener1());
				getContentPane().removeAll();
				add(setzen[setzenIndex]);
				revalidate();
				repaint();
			}
			else{
				getContentPane().removeAll();
				System.out.println("vor Gameloop.");
				gameloop();
			}
			/*if(setzen[spielerIndex].getGesetzt()){
				System.out.println("schiffe wurden gesetzt");
				spielerIndex++;
				System.out.println("spielerIndex: "+spielerIndex);
				if(spielerIndex<spielerArray.length){
					setzen[spielerIndex]=new GUISchiffe(spielerArray[spielerIndex]);
					setzen[spielerIndex].getFertig().addActionListener(new MeinActionListener1());
					getContentPane().removeAll();
					add(setzen[spielerIndex]);
					revalidate();
					repaint();
				}
				else{
					
					System.out.println("Starte das Spiel!");
				}
			}
			else{
				System.out.println("nächstes Boot");
			}*/
		}
		
		
	}
	
	private class MeinItemListener implements ItemListener{


		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange()== ItemEvent.SELECTED){
				String stringZahl=(String) e.getItem();
				stringZahl.trim();
				if(stringZahl=="2 Spieler"){
					for(int i=0;i<2;i++){
						basics.getAlleSpieler()[i].setEnabled(true);
						basics.getAlleCbtn()[i].setEnabled(true);
						setSpielerZahl(i+1);
						//remove(alleSpieler[5]);
						//repaint();
					}
					for(int i=2;i<6;i++){
						basics.getAlleSpieler()[i].setEnabled(false);
						basics.getAlleCbtn()[i].setEnabled(false);
					}
				}
				else if(stringZahl=="3 Spieler"){
					for(int i=0;i<3;i++){
						basics.getAlleSpieler()[i].setEnabled(true);
						basics.getAlleCbtn()[i].setEnabled(true);
						setSpielerZahl(i+1);
					}
					for(int i=3;i<6;i++){
						basics.getAlleSpieler()[i].setEnabled(false);
						basics.getAlleCbtn()[i].setEnabled(false);
					}
				}
				else if(stringZahl=="4 Spieler"){
					for(int i=0;i<4;i++){
						basics.getAlleSpieler()[i].setEnabled(true);
						basics.getAlleCbtn()[i].setEnabled(true);
						setSpielerZahl(i+1);
					}
					for(int i=4;i<6;i++){
						basics.getAlleSpieler()[i].setEnabled(false);
						basics.getAlleCbtn()[i].setEnabled(false);
						
					}
				}
				else if(stringZahl=="5 Spieler"){
					for(int i=0;i<5;i++){
						basics.getAlleSpieler()[i].setEnabled(true);
						basics.getAlleCbtn()[i].setEnabled(true);
						setSpielerZahl(i+1);
					}
					for(int i=5;i<6;i++){
						basics.getAlleSpieler()[i].setEnabled(false);
						basics.getAlleCbtn()[i].setEnabled(false);
					}
				}
				else if(stringZahl=="6 Spieler"){
					for(int i=0;i<6;i++){
						basics.getAlleSpieler()[i].setEnabled(true);
						basics.getAlleCbtn()[i].setEnabled(true);
						setSpielerZahl(i+1);
					}
				}
				
			}
			
		}
		
	}
	
	public void gameloop(){
		this.spielverlauf=new GUISpielverlaufV2[spielerZahl];
		this.spielfelder=new GUISpielfeld[spielerZahl];
		for(int i=0;i<spielerArray.length;i++){
			spielfelder[i]=new GUISpielfeld(spielFeldGr);
			spielerArray[i].setGuifeld(spielfelder[i]);
		}
		for(Spieler y:spielerArray){
			y.setSpielerArray(spielerArray);
		}
		for(int i=0;i<spielerArray.length;i++){
			System.out.println(i);
			spielverlauf[i]=new GUISpielverlaufV2(spielfelder,spielerArray[i],i);
			spielverlauf[i].getSchussBtn().addActionListener(new MeinSchussListener());
		}
		t=new Thread(){
			public void run(){
				while(!game.abbruchBed()&&!abbruch){
					System.out.println("ich bin ein thread");
					for(int i=0;i<spielerArray.length;i++){
						aktiveSpielverlauf=i;
						Spieler aktiv=spielerArray[i];
						if(aktiv.guiSchiffCheck()){
							if(aktiv.getImSpiel()){
								if(!aktiv.getIsKI()){
									getContentPane().removeAll();
									//spielverlauf[i].setAktiveS(aktiv);
									//spielverlauf[i].resetSchiffBox();
									//spielverlauf[i].updateFeld();
									
									add(spielverlauf[i]);
									revalidate();
									repaint();
									while(tSteuerung){
										yield();	
										if(gewaehlt){
											tSteuerung=false;
											
											try {
												sleep(1000);
											} catch (InterruptedException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
										
									}
									
								
							
							
									tSteuerung=true;
									gewaehlt=false;
							
								}
								else{
									//KI misst
								}
							}
							if(game.abbruchBed()){
								abbruch=true;
								break;
							}
							if(treffer){
								i--;
								aktiv.versenktup(spielerArray);
							}
							treffer=false;
						}
						else{
							getContentPane().removeAll();
						}
			
					}
					rundenZahl++;
					if(!game.abbruchBed()){
						for(Spieler k:spielerArray){
							for(Schiffe s:k.getSchiffListe()){
								s.updateReg();
							}
						}
						
					}
					else{
						abbruch=true;
					}
					//Speichern einfügen
				}
			}
			
		};
		t.start();
}
	
private class MeinSchussListener implements ActionListener{
		
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
			
			if(spielverlauf[aktiveSpielverlauf].schiessen()){
				treffer=true;
			}
			/*for(GUISpielverlaufV1 x : spielverlauf){
				x.updateFeld();
			}*/
			gewaehlt=true;
		}
		
	}
	
	private class SchiffListListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			JList x=(JList)e.getSource();
			if(x==schiffWahl.getList(1)){
				int ubo=(int)x.getSelectedValue();
				int kor=(int)schiffWahl.getList(2).getSelectedValue();
				int fre=(int)schiffWahl.getList(3).getSelectedValue();
				int zer=(int)schiffWahl.getList(4).getSelectedValue();
				sGes=ubo+kor+fre+zer;
				schiffWahl.getSchiffe()[5].setText("Schiffe gesamt: " +String.valueOf(sGes));
				if(sGes>(spielFeldGr/2)){
					startBtn.setEnabled(false);
				}
				else{
					startBtn.setEnabled(true);
				}
				
				
			}
			if(x==schiffWahl.getList(2)){
				int ubo=(int)schiffWahl.getList(1).getSelectedValue();
				int kor=(int)x.getSelectedValue();
				int fre=(int)schiffWahl.getList(3).getSelectedValue();
				int zer=(int)schiffWahl.getList(4).getSelectedValue();
				sGes=ubo+kor+fre+zer;
				schiffWahl.getSchiffe()[5].setText("Schiffe gesamt: " +String.valueOf(sGes));
				if(sGes>(spielFeldGr/2)){
					startBtn.setEnabled(false);
				}
				else{
					startBtn.setEnabled(true);
				}
			}
			if(x==schiffWahl.getList(3)){
				int ubo=(int)schiffWahl.getList(1).getSelectedValue();
				int kor=(int)schiffWahl.getList(2).getSelectedValue();
				int fre=(int)x.getSelectedValue();
				int zer=(int)schiffWahl.getList(4).getSelectedValue();
				sGes=ubo+kor+fre+zer;
				schiffWahl.getSchiffe()[5].setText("Schiffe gesamt: " +String.valueOf(sGes));
				if(sGes>(spielFeldGr/2)){
					startBtn.setEnabled(false);
				}
				else{
					startBtn.setEnabled(true);
				}
			}
			if(x==schiffWahl.getList(4)){
				int ubo=(int)schiffWahl.getList(1).getSelectedValue();
				int kor=(int)schiffWahl.getList(2).getSelectedValue();
				int fre=(int)schiffWahl.getList(3).getSelectedValue();
				int zer=(int)x.getSelectedValue();
				sGes=ubo+kor+fre+zer;
				schiffWahl.getSchiffe()[5].setText("Schiffe gesamt: " +String.valueOf(sGes));
				if(sGes>(spielFeldGr/2)){
					startBtn.setEnabled(false);
				}
				else{
					startBtn.setEnabled(true);
				}
			}
		}
		
	}
	
	
	private class MeinListListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			int i=basics.getList().getSelectedValue();
			spielFeldGr=i;
			String s=String.valueOf((i/2));
			schiffWahl.getMax().setText("max:  "+s);
			if(sGes>(spielFeldGr/2)){
				startBtn.setEnabled(false);
			}
			else{
				startBtn.setEnabled(true);
			}
			
		}
		
	}
	
	private class CheckBoxListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange()== ItemEvent.SELECTED){
				MeineCheckBox a=(MeineCheckBox) e.getSource();
				int index=a.index;
				basics.getAlleLabel()[index].setText("Computer");
				setComputerZahl(getComputerZahl()+1);
			}
			if(e.getStateChange()== ItemEvent.DESELECTED){
				MeineCheckBox a=(MeineCheckBox) e.getSource();
				int index=a.index;
				basics.getAlleLabel()[index].setText("  Mensch  ");
				setComputerZahl(getComputerZahl()-1);
			}
			
		}
		
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
				btn.setBackground(Design.zeiger);
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
	
	public JButton getButton(){
		return this.startBtn;
	}
	
	public int getSpielerZahl() {
		return spielerZahl;
	}


	public void setSpielerZahl(int spielerZahl) {
		this.spielerZahl = spielerZahl;
	}
	
	public void setSpielFeldGr(int spielFeldGr){
		this.spielFeldGr=spielFeldGr;
	}
	
	public int getComputerZahl(){
		return this.computerZahl;
	}
	
	public void setComputerZahl(int computerZahl){
		this.computerZahl=computerZahl;
	}
	
	public ArrayList<Schiffe> erstelleSchiffe(){
		ArrayList<Schiffe> schiffListe=new ArrayList<Schiffe>();
		int ubo=(int)schiffWahl.getList(1).getSelectedValue();
		int kor=(int)schiffWahl.getList(2).getSelectedValue();
		int fre=(int)schiffWahl.getList(3).getSelectedValue();
		int zer=(int)schiffWahl.getList(4).getSelectedValue();
		for(int i=0;i<ubo;i++){
			schiffListe.add(new UBoot(i+1));				
		}
		for(int i=0;i<kor;i++){
			schiffListe.add(new Korvette(i+1));
		}
		for(int i=0;i<fre;i++){
			schiffListe.add(new Fregatte(i+1));
		}
		for(int i=0;i<zer;i++){
			schiffListe.add(new Zerstoerer(i+1));
		}
		return schiffListe;
	}
	
	public Spieler[] erstelleSpieler(){
		Spieler[] spielerArray=new Spieler[spielerZahl];
		for(int i=0;i<spielerZahl;i++){
			String k=basics.getAlleSpieler()[i].getText();
			if(basics.getAlleCbtn()[i].isSelected()){
				spielerArray[i]=new KI(k);
				spielerArray[i].setSchiffListe(erstelleSchiffe());
				spielerArray[i].erstelleFeld(spielFeldGr);
				spielerArray[i].setIsKI(true);
				spielerArray[i].setSpielerArray(spielerArray);
				System.out.println("dran ist computer: "+spielerArray[i].getName());
				spielerArray[i].setzeSchiffe();
			}
			else{
				spielerArray[i]=new Spieler(k);
				spielerArray[i].setSchiffListe(erstelleSchiffe());
				spielerArray[i].erstelleFeld(spielFeldGr);
				spielerArray[i].setIsKI(false);
				spielerArray[i].setSpielerArray(spielerArray);
				System.out.println("spieler");
				//spielerArray[i].setzeSchiffe();
			}
		}
		this.spielerArray=spielerArray;
		return spielerArray;
	}
	
	/*public void setzeSchiff(int spielerIndex,int schiffIndex){
		Spieler sp=spielerArray[spielerIndex];
		ArrayList<Schiffe> s=sp.getSchiffListe();
		Schiffe schiff=s.get(schiffIndex);
		int zeile=setzen.getIntZeile();
		int spalte=setzen.getIntSpalte();
		int aus=setzen.getAusrichtung();
		System.out.println("Schiff: "+schiff.getName()+" Zeile: "+zeile+"Spalte: "+spalte+"Ausrichtung: "+aus);
		if(aus==1){
			sp.getSpielerFeld().schiffSetzenRechts(zeile-1, spalte-1, schiff);
		}
		if(aus==2){
			sp.getSpielerFeld().schiffSetzenLinks(zeile-1, spalte-1, schiff);
		}
		if(aus==3){
			sp.getSpielerFeld().schiffSetzenOben(zeile-1, spalte-1, schiff);
		}
		if(aus==4){
			sp.getSpielerFeld().schiffSetzenUnten(zeile-1, spalte-1, schiff);
		}
	}*/
	
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
	
	public static void main(String[] args){
		MeinFrame mf=new MeinFrame("Schiffe Versenken");
	}


	

	
}
