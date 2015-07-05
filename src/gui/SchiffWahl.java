package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

public class SchiffWahl extends JPanel {

	
	private JLabel schiffe1= new JLabel("");
	private JLabel schiffe2= new JLabel("UBoot");
	private JLabel schiffe3= new JLabel("Korvette");
	private JLabel schiffe4= new JLabel("Fregatte");
	private JLabel schiffe5= new JLabel("Zerstörer");
	private JLabel schiffe6= new JLabel("Schiffe gesamt: 4");
	private JLabel[]alleSchiffe={schiffe2,schiffe3,schiffe4,schiffe5,schiffe1,schiffe6};
	
	//Anzeige, dass man Schiffe wählen soll
	private JLabel sAnzeige=new JLabel("Wähle die Schiffe:");
	
	//JLabel für die maximale Zahl der Schiffe
	private JLabel max;
	
	//IntegerArray mit den Zahlen von 0 bis zur maximalen Schiffzahl
	private static Integer[] anzSchiffe;
	
	//JList für jeden Schifftypen (wird mit anzSchiffe gebildet)

	private JList<Integer> liste1= new JList<Integer>(initArray());
	private JList<Integer> liste2= new JList<Integer>(initArray());
	private JList<Integer> liste3= new JList<Integer>(initArray());
	private JList<Integer> liste4= new JList<Integer>(initArray());
	
	//JScrollPane, in welche die Listen eingebunden werden
	private JScrollPane ubZ;
	private JScrollPane koZ;
	private JScrollPane frZ;
	private JScrollPane zeZ;
	
	public SchiffWahl(){
		super();
		
		//Hintergrundfarbe definieren
		setBackground(Design.hintergrund);
		
		
		//Layoutmanager definieren
		setLayout(new GridBagLayout());
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.insets= Design.grundabstand;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		
		
		sAnzeige.setHorizontalAlignment(SwingConstants.CENTER);
		sAnzeige.setFont(Design.unterM);
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.gridwidth=2;
		add(sAnzeige,gbc);
		
		max=new JLabel("max :  4");
		max.setFont(Design.unterM);
		max.setHorizontalAlignment(SwingConstants.CENTER);
		gbc.gridx=0;
		gbc.gridy=1;
		add(max,gbc);
		
		//JPanel als Platzhalter
		JPanel placehld=new JPanel();
		placehld.setBackground(Design.hintergrund);
		gbc.gridx=0;
		gbc.gridy=2;
		add(placehld,gbc);
		
		
		//Anzeige der einzelnen Schiffe
		gbc.gridwidth=1;
		for(int i=0;i<5;i++){
			gbc.gridy++;
			alleSchiffe[i].setFont(Design.labelFont);
			add(alleSchiffe[i],gbc);
		}
		gbc.gridy=8;
		gbc.gridwidth=2;
		alleSchiffe[5].setFont(Design.labelFont);
		add(alleSchiffe[5],gbc);
		gbc.gridwidth=1;
		
		//scrollbare JListe für alle Schiffe (zur Auswahl der Menge)
		anzSchiffe=new Integer[500];
		int r=0;
		for(Integer i=499;i>=0;i--){
			anzSchiffe[r]=i;
			r++;
		}
		
		//Liste für das Uboot
		gbc.gridx=1;
		gbc.gridy=3;
		
		liste1=new JList<Integer>(anzSchiffe);
		liste1.setFont(Design.inFeld);
		liste1.setVisibleRowCount(1);
		liste1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		liste1.setSelectedIndex(498);
		ubZ= new JScrollPane(liste1);
		ubZ.getViewport().setViewPosition(new Point(0,28*498));
		add(ubZ,gbc);
		

		//Liste für die Korvette
		gbc.gridx=1;
		gbc.gridy=4;
		liste2=new JList<Integer>(anzSchiffe);
		liste2.setFont(Design.inFeld);
		liste2.setVisibleRowCount(1);
		liste2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		liste2.setSelectedIndex(498);
		koZ= new JScrollPane(liste2);
		koZ.getViewport().setViewPosition(new Point(0,28*498));
		add(koZ,gbc);

		
		//Liste für die Fregatte
		gbc.gridx=1;
		gbc.gridy=5;
		liste3=new JList<Integer>(anzSchiffe);
		liste3.setFont(Design.inFeld);
		liste3.setVisibleRowCount(1);
		liste3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		liste3.setSelectedIndex(498);
		frZ= new JScrollPane(liste3);
		frZ.getViewport().setViewPosition(new Point(0,28*498));
		add(frZ,gbc);
		

		//Liste für den Zerstörer
		gbc.gridx=1;
		gbc.gridy=6;
		liste4=new JList<Integer>(anzSchiffe);
		liste4.setFont(Design.inFeld);
		liste4.setVisibleRowCount(1);
		liste4.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		liste4.setSelectedIndex(498);
		zeZ= new JScrollPane(liste4);
		zeZ.getViewport().setViewPosition(new Point(0,(28*498)));
		add(zeZ,gbc);		
	}
	
	public static Integer[] initArray(){
		anzSchiffe=new Integer[500];
		int r=0;
		for(Integer i=499;i>=0;i--){
			anzSchiffe[r]=i;
			r++;
		}
		return anzSchiffe;
	}
	
	public JList<Integer> getList(int numb){
		if(numb==1)
			return liste1;
		if(numb==2)
			return liste2;
		if(numb==3)
			return liste3;
		if(numb==4)
			return liste4;
		
		//kommt nie vor
		return liste1;
	}
	
	public JLabel[] getSchiffe(){
		return alleSchiffe;
	}
	
	public JLabel getMax(){
		return max;
	}
}
