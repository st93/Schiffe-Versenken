package gui;

//import gui.MeinFrame.CheckBoxListener;
//import gui.MeinFrame.MeinItemListener;
//import gui.MeinFrame.MeinListListener;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

public class Grundeinstellungen extends JPanel{

	private JLabel spieler;
	private JLabel feld;
	
	//JComboBox alle Spieler
	private JComboBox<String> spielerBox;
	private JList<Integer> liste;
	private Integer[] array=new Integer[994];
	
	private JTextField spieler1=new JTextField("Spieler 1",8);
	private JTextField spieler2=new JTextField("Spieler 2",8);
	private JTextField spieler3=new JTextField("Spieler 3",8);
	private JTextField spieler4=new JTextField("Spieler 4",8);
	private JTextField spieler5=new JTextField("Spieler 5",8);
	private JTextField spieler6=new JTextField("Spieler 6",8);
	private JTextField[] alleSpieler={spieler1,spieler2,spieler3,spieler4,spieler5,spieler6};
	
	private JPanel panel1=new JPanel();
	private JPanel panel2=new JPanel();
	private JPanel panel3=new JPanel();
	private JPanel panel4=new JPanel();
	private JPanel panel5=new JPanel();
	private JPanel panel6=new JPanel();
	private JPanel[] allePanel={panel1,panel2,panel3,panel4,panel5,panel6};
	
	
	private MeineCheckBox cbtn1=new MeineCheckBox(0);
	private MeineCheckBox cbtn2=new MeineCheckBox(1);
	private MeineCheckBox cbtn3=new MeineCheckBox(2);
	private MeineCheckBox cbtn4=new MeineCheckBox(3);
	private MeineCheckBox cbtn5=new MeineCheckBox(4);
	private MeineCheckBox cbtn6=new MeineCheckBox(5);
	private MeineCheckBox[] alleCbtn={cbtn1,cbtn2,cbtn3,cbtn4,cbtn5,cbtn6};
	
	private JLabel label1=new JLabel("  Mensch  ");
	private JLabel label2=new JLabel("  Mensch  ");
	private JLabel label3=new JLabel("  Mensch  ");
	private JLabel label4=new JLabel("  Mensch  ");
	private JLabel label5=new JLabel("  Mensch  ");
	private JLabel label6=new JLabel("  Mensch  ");
	private JLabel[] alleLabel={label1,label2,label3,label4,label5,label6};
	
	public Grundeinstellungen(){
		super();
		//Hintergrundfarbe
		setBackground(Design.hintergrund);
		
		//Layoutmanager hinzufügen
		setLayout(new GridBagLayout());
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.insets=Design.grundabstand;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		
		
		//Label "Spielerzahl" hinzufügen
		spieler=new JLabel("Spielerzahl: ");
		spieler.setFont(Design.unterM);
		gbc.gridwidth=2;
		gbc.gridx=0;
		gbc.gridy=0;
		add(spieler,gbc);
		
		//Label "Spielfeldgröße" hinuzfügen
		feld=new JLabel("Spielfeldgröße: ");
		feld.setFont(Design.unterM);
		gbc.gridwidth=2;
		gbc.gridx=0;
		gbc.gridy=1;
		add(feld,gbc);
		
		
		//JCombobox spieler hinzufügen
		spielerBox=new JComboBox<String>();
		spielerBox.setFont(Design.inFeld);
		gbc.gridwidth=1;
		gbc.gridx=2;
		gbc.gridy=0;
		spielerBox.addItem("2 Spieler");
		spielerBox.addItem("3 Spieler");
		spielerBox.addItem("4 Spieler");
		spielerBox.addItem("5 Spieler");
		spielerBox.addItem("6 Spieler");
		add(spielerBox,gbc);
		
		//JList in JScrollPane für Feldgröße
		int k=0;
		for(Integer i=999;i>=6;i--){
			this.array[k]=i;
			k++;
		}
		liste=new JList<Integer>(array);
		liste.setFont(Design.inFeld);
		liste.setVisibleRowCount(1);
		liste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		liste.setSelectedIndex(990);
		gbc.gridx=2;
		gbc.gridy=1;
		JScrollPane sz= new JScrollPane(liste);
		sz.getViewport().setViewPosition(new Point(0,(28*990)));
		add(sz,gbc);
		
		//Textfeld für Spieler
		gbc.gridwidth=1;
		gbc.gridx=0;
		gbc.gridy=2;
		for(int i=2;i<6;i++){
			alleSpieler[i].setEnabled(false);
		}
		for(JTextField x:alleSpieler){
			x.setFont(Design.inFeld);
			add(x,gbc);
			gbc.gridy++;
		}
		
		//JCheckbtn in JPanel Mensch oder Computer
		GridBagConstraints pgbc=new GridBagConstraints();
		pgbc.gridx=0;
		pgbc.gridy=0;

		//gbc
		gbc.gridx=2;
		gbc.gridy=2;
		int zlr=0;
		for(int i=0;i<2;i++){
			alleCbtn[i].setEnabled(true);
		}
		for(JPanel p: allePanel){
			p.setLayout(new GridBagLayout());
			p.add(alleCbtn[zlr],pgbc);
			alleCbtn[zlr].setBackground(Design.hintergrund);
			p.setBackground(Design.hintergrund);
			//p.setBorder(BorderFactory.createLineBorder(Color.black));
			p.setEnabled(false);
			add(p,gbc);
			gbc.gridy++;
			pgbc.gridy++;
			zlr++;
		}
		
		
		//Anzeige Mensch oder KI
		gbc.gridwidth=1;
		gbc.gridx=1;
		gbc.gridy=2;
		for(JLabel x:alleLabel){
			x.setFont(new Font("Verdana",Font.BOLD, 20));
			x.setHorizontalAlignment(SwingConstants.CENTER);
			//x.setBorder(BorderFactory.createLineBorder(Color.black));
			add(x,gbc);
			gbc.gridy++;
		}
	}
	public JList<Integer> getList(){
		return liste;
	}
	
	public MeineCheckBox[] getAlleCbtn(){
		return alleCbtn;
	}
	
	public JLabel[] getAlleLabel(){
		return alleLabel;
	}
	
	public JTextField[] getAlleSpieler(){
		return alleSpieler;
	}
	public JComboBox<String> getSpielerBox(){
		return spielerBox;
	}
}
