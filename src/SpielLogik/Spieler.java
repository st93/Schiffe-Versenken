package SpielLogik;

import Schiffe.*;
import gui.*;

import java.io.Serializable;
import java.util.*;

import javax.swing.JOptionPane;

import Allgemeines.Abfragen;


public class Spieler implements Serializable{
	protected GUISpielfeld guifeld;
	protected boolean imSpiel;
	protected String name;
	protected Board spielerFeld;
	protected ArrayList<Schiffe> schiffListe;
	protected Spieler[] spielerArray;
	private boolean getroffen;
	private int schussVersuche;
	private boolean isKI;
	
	public Spieler(String name){
		this.imSpiel=true;
		this.name=name;
		this.setGetroffen(false);
	}
	
	public Spieler(){
		this.imSpiel=true;
		this.setGetroffen(false);
	}
	
	public int getSchussVersuche() {
		return schussVersuche;
	}

	public void setSchussVersuche(int schussVersuche) {
		this.schussVersuche = schussVersuche;
	}

	/*
	 * Methode, welche wahr ist, wenn in der Schiffliste eines Spielers kein Schiff mehr intakt ist
	 */
	public boolean spielerAus(){
		boolean x=true;
		for(Schiffe k:this.getSchiffListe()){
			if(!k.getVersenkt()){
				x=false;
			}
		}
		if(x==true){
			System.out.println(this.getName()+" ist ausgeschieden!");
			this.setImSpiel(false);
			return x;
		}
		return x;
	}
	
	/*
	 * Fehler!? wenn einer ausgeschieden ist, dann immer true/false??
	 * Methode, welche wahr ist, wenn irgendein Spieler neu ausgeschieden ist
	 */
	public boolean spielerAus(Spieler[] spielerArray){
		boolean x=true;
		for(Spieler i:spielerArray){
			for(Schiffe k:i.getSchiffListe()){
				if(!k.getVersenkt()){
					x=false;
				}
			}
			if(x==true){
				System.out.println(i.getName()+" ist ausgeschieden!");
				i.setImSpiel(false);
				return x;
			}
		}
		return x;
	}
	
	/*
	 * Methode, die pr�ft, ob ein Schiff versenkt ist
	 */
	public boolean versenktup(Spieler[] spielerArray){
		for(Spieler i:spielerArray){
			for(Schiffe k:i.getSchiffListe()){
				if(!k.getVersenkt()){
					if(k.getDirection().getLinks()){
						System.out.println(k.getName()+" Zeile: "+k.getHeight()+" Spalte: "+k.getWidth());
						if(i.getSpielerFeld().versenktLinks(k.getHeight(), k.getWidth(), k)){
							System.out.println("Schiff wurde versenkt");
							i.getSpielerFeld().printFeind();
							this.setGetroffen(false);
							return true;
						}
					}
					else if(k.getDirection().getRechts()){
						if(i.getSpielerFeld().versenktRechts(k.getHeight(), k.getWidth(), k)){
							System.out.println("Schiff wurde versenkt");
							i.getSpielerFeld().printFeind();
							this.setGetroffen(false);
							return true;
						}
					}
					else if(k.getDirection().getOben()){
						if(i.getSpielerFeld().versenktOben(k.getHeight(), k.getWidth(), k)){
							System.out.println("Schiff wurde versenkt");
							i.getSpielerFeld().printFeind();
							this.setGetroffen(false);
							return true;
						}
					}
					else if(k.getDirection().getUnten()){
						if(i.getSpielerFeld().versenktUnten(k.getHeight(), k.getWidth(), k)){
							System.out.println("Schiff wurde versenkt");
							i.getSpielerFeld().printFeind();
							this.setGetroffen(false);
							return true;
						}
					}
				}
			}
		}
		return false;
		
	}
	
	/*
	 * �berpr�ft, ob ein Schiff verf�gbar ist
	 * liefert true, wenn mindestens ein Schiff verf�gbar ist
	 */
	public boolean schiffCheck(){
		for(Schiffe s:schiffListe){
			if((s.getVersenkt()==false)&& s.getReg()==0){
				return true;
			}
		}
		System.out.println("Leider kein Schiff bereit..");
		return false;
	}
	
	
	/*
	 * �berpr�ft, ob ein Schiff verf�gbar ist
	 * liefert true, wenn mindestens ein Schiff verf�gbar ist
	 */
	public boolean guiSchiffCheck(){
		for(Schiffe s:schiffListe){
			if((s.getVersenkt()==false)&& s.getReg()==0){
				return true;
			}
		}
		JOptionPane.showMessageDialog(null, "Leider ist kein Schiff schussbereit", "Information", JOptionPane.OK_OPTION);
		return false;
	}
	
	
	/*
	 * Methode, mit der geschossen wird
	 */
	public void schiessen(){
		int x=0;
		for(Spieler i:spielerArray){
			if(i.getImSpiel()){
				x++;
			}
		}
		if(x>1){
			Spieler sp=this.spielerWahl();
			this.shoot(sp);
		}
		
	}
	
	/*
	 * �berpr�ft zuerst, ob ein Spieler besiegt wurde und schie�t, wenn ein Schiff verf�gbar ist
	 */
	public void shoot(Spieler sp){
		this.versenktup(spielerArray);
		if(!sp.spielerAus()){
			if(this.schiffCheck()){
				this.bootVerfuegbar();
				Schiffe s=this.bootWahl();
				this.schuss(s,sp);
			}
		}
		else{
			this.schiessen();
		}
		
	}
	
	/*
	 * �berpr�ft, mit welchem Boot geschossen werden kann
	 */
	public void bootVerfuegbar(){
		boolean u=false;
		boolean k=false;
		boolean f=false;
		boolean z=false;
		for(Schiffe s:this.schiffListe){			
			if(s.schussBereit()){
				int typ=s.getTyp();
				
				switch(typ){
				case 1:
					u=true;
					break;
				case 2:
					k=true;
					break;
				case 3:
					f=true;
					break;
				case 4:
					z=true;
					break;
				default:
					break;
				}
				
			}
		}
		if(u){
			System.out.println("Es kann mit einem Uboot geschossen werden.");
		}
		if(k){
			System.out.println("Es kann mit einer Korvette geschossen werden.");
		}
		if(f){
			System.out.println("Es kann mit einer Fregatte geschossen werden.");
		}
		if(z){
			System.out.println("Es kann mit einem Zerst�rer geschossen werden.");
		}
	}
	
	/*
	 * Methode, zur Bestimmung der Zielkoordinaten und Ausrichtung, liefert auf die Konsole, ob getroffen wurde, oder nicht
	 */
	public void schuss(Schiffe s, Spieler sp){
		sp.getSpielerFeld().printFeind();
		System.out.println("Tippen sie die Zielkoordinaten!");
		System.out.println("Waehlen sie eine Zeile!");
		int hoehe=Abfragen.frageOrt();
		System.out.println("Waehlen sie eine Spalte!");
		int breite=Abfragen.frageOrt();
		if(!spielerFeld.schussKoordinaten(hoehe, breite)){
			System.out.println("Bitte waehlen Sie Koordinaten innerhalb des Feldes");
			this.schuss(s,sp);
		}
		System.out.println("Waehlen sie die Ausrichtung!(1=vertikal; 2=horizontal)");
		int a=Abfragen.frageInt();
		if(!(a==2||a==1)){
			System.out.println("Fehler, Bitte tippen Sie 1 oder 2.");
		}
		if(sp.getSpielerFeld().schiessen(hoehe,breite,a,s)){
			System.out.println("Treffer!!!");
			sp.getSpielerFeld().printFeind();
			//s.setReg();
			System.out.println(s.getName() + " hat geschossen.");
			if(sp.spielerAus()){
				this.schiessen();
			}
			else{
				this.shoot(sp);
			}
			
			
		}
		else{
			System.out.println("Kein Treffer..");
			sp.getSpielerFeld().printFeind();
			//s.setReg();
		}
	}

	
	/*
	 * Methode, mit welcher der Gegner f�r den n�chsten Schuss bestimmt wird
	 */
	public Spieler spielerWahl(){
		for(int i=0;i<spielerArray.length;i++){
			if(spielerArray[i].getImSpiel()&&spielerArray[i].getName()!=this.getName()){
				System.out.println(spielerArray[i].getName()+" kann angegriffen werden! w�hle: "+i);
			}
			
		}
		int geg=Abfragen.frageInt();
		if(spielerArray[geg].getImSpiel()&&spielerArray[geg].getName()!=this.getName()){
			return spielerArray[geg];
		}
		System.out.println("Bitte w�hle einen der genannten Spieler aus!");
		return this.spielerWahl();
	}
	
	/*
	 * Methode, um zu pr�fen, ob ein Schiff enthalten und verf�gbar ist
	 */
	public boolean schiffEnthalten(int groesse){
		for(Schiffe s:schiffListe){
			if(s.getSize()==groesse && s.getVersenkt()){
				return true;
			}
		}
		return false;
	}
	/*
	 * Methode, mit der das Boot zum Schie�en ausgew�hlt wird
	 */
	public Schiffe bootWahl(){
		System.out.println("W�hlen sie ein Boot zum Schie�en! 1=Uboot 2=Korvette 3=Fregatte 4=Zerst�rer");
		int b=Abfragen.frageInt();
		//Schiffe s;
		for(Schiffe s:schiffListe){
			if(s.getTyp()==b&&s.schussBereit()){
				System.out.println(s.getName()+" "+ s.getIndex()+" soll schie�en");
				return s;
			}
			
		}
		System.out.println("Bitte w�hle ein verf�gbares Boot aus!");
		return bootWahl();
	}
		/*for(int i=1;i<5;i++){
			if(b==i){
				
			}
		}*/
		/*if(b==4){
			System.out.println("Es soll mit einem Zerst�rer geschossen werden!");
			for(int i=0;i<schiffListe.size();i++){
				s=schiffListe.get(i);
				if(s.getTyp()==4&&s.schussBereit()){
					return s;
				}
			}
			/*int counter=0;
			do{
				s=schiffListe.get(counter);
				counter++;
			}while(s.getSize()!=5 && !s.schussBereit());
			return schiffListe.get(counter-1);*/
		/*}
		else if(b==3){
			System.out.println("Es soll mit einer Fregatte geschossen werden!");
			for(int i=0;i<schiffListe.size();i++){
				s=schiffListe.get(i);
				if(s.getTyp()==3&&s.schussBereit()){
					return s;
				}
			}
			/*int counter=0;
			do{
				s=schiffListe.get(counter);
				counter++;
			}while(s.getSize()!=4 && !s.schussBereit());
			return schiffListe.get(counter-1);*/
		/*}
		else if(b==2){
			System.out.println("Es soll mit einer Korvette geschossen werden!");
			for(int i=0;i<schiffListe.size();i++){
				s=schiffListe.get(i);
				if(s.getTyp()==2&&s.schussBereit()){
					return s;
				}
			}
			/*int counter=0;
			do{
				s=schiffListe.get(counter);
				counter++;
			}while(s.getSize()!=3 && !s.schussBereit());
			return schiffListe.get(counter-1);*/
		/*}
		else if(b==1){
			System.out.println("Es soll mit einem Uboot geschossen werden!");
			for(int i=0;i<schiffListe.size();i++){
				s=schiffListe.get(i);
				if(s.getTyp()==2&&s.schussBereit()){
					System.out.println(s.getName()+" wird schie�en!");
					return s;
				}
			}
			/*
			int counter=0;
			do{
				s=schiffListe.get(counter);
				counter++;
			}while(s.getSize()!=2 && !s.schussBereit());
			return schiffListe.get(counter-1);*/
		/*}
		else{
			System.out.println("Fehler, bitte zahl zwischen 1 und 4 tippen!");
			this.bootWahl();
		}
		return schiffListe.get(schiffListe.size()-1);
	}*/
	
	public void setImSpiel(boolean x){
		this.imSpiel=x;
	}
	
	public boolean getImSpiel(){
		return imSpiel;
	}
	
	public void setName(String s){
		this.name=s;
	}
	
	public String getName(){
		return this.name;
	}
	
	public Board getSpielerFeld(){
		return this.spielerFeld;
	}
	
	/*
	 * ruft den Konstruktor der Klasse Board auf und speichert das Board in dem Spieler
	 */
	public void erstelleFeld(int size){
		this.spielerFeld=new Board(size);
	}
	
	public void setSpielerArray(Spieler[]s){
		this.spielerArray=s;
	}
	
	/*
	 * �bernimmt die Schiffliste aus der Klasse Spiel und kopiert sie, damit die Listen
	 * von jedem Spieler einzeln ver�ndert werden k�nnen
	 */
	public void setSchiffListe(List<Schiffe> schiffListeAlt){
		this.schiffListe= new ArrayList<Schiffe>();
		for(Schiffe s:schiffListeAlt){
			int typ=s.getTyp();
			if(typ==1){
				this.schiffListe.add(new UBoot(s.getIndex()));
			}
			if(typ==2){
				this.schiffListe.add(new Korvette(s.getIndex()));				
			}
			if(typ==3){
				this.schiffListe.add(new Fregatte(s.getIndex()));	
			}
			if(typ==4){
				this.schiffListe.add(new Zerstoerer(s.getIndex()));	
			}
		}
	}
	
	public ArrayList<Schiffe> getSchiffListe(){
		return this.schiffListe;
	}
	
	/*
	 * Methode zum setzen der Schiffe (mit �berpr�fung der Koordinaten)
	 */
	public void setzeSchiffe(){
			for(int i=0;i<schiffListe.size();i++){
				Schiffe s=schiffListe.get(i);
				s.getDirection().clean();
				System.out.println("Setze " + s.getName()+ " "+s.getIndex());
				int hoehe=frageHoehe();
				int breite=frageBreite();
				boolean l=false;
				boolean r=false;
				boolean u=false;
				boolean o=false;
				if(spielerFeld.koordinatenCheck(hoehe, breite, s)){
					s.setHeight(hoehe);
					s.setWidth(breite);
					System.out.println(s.getName()+" "+s.getIndex()+ " kann hier gesetzt werden!");
					System.out.println("");
					System.out.println("W�hle die Richtung, in die das Boot gesetzt werden soll!");
					System.out.println("");
					System.out.println("Tippe:");
					if(s.getDirection().getLinks()){
						l=true;
						System.out.println("'1' f�r links");
					}
					if(s.getDirection().getRechts()){
						r=true;
						System.out.println("'2' f�r rechts");
					}
					if(s.getDirection().getOben()){
						o=true;
						System.out.println("'3' f�r oben");
					}
					if(s.getDirection().getUnten()){
						u=true;
						System.out.println("'4' f�r unten");
					}
					System.out.println("");
					
					int y=Abfragen.frageInt();
					if(y==1 && l==true){
						spielerFeld.schiffSetzenLinks(hoehe,breite,s);
						System.out.println(s.getName()+ " "+ s.getIndex()+" wurde nach Links gesetzt");
						s.getDirection().clean();
						s.getDirection().setLinks(true);
						spielerFeld.print();
					}
					else if(y==2 && r==true){
						spielerFeld.schiffSetzenRechts(hoehe,breite,s);
						System.out.println(s.getName()+ " "+ s.getIndex()+" wurde nach Rechts gesetzt");
						s.getDirection().clean();
						s.getDirection().setRechts(true);
						spielerFeld.print();
					}
					else if(y==4 && u==true){
						spielerFeld.schiffSetzenUnten(hoehe,breite,s);
						System.out.println(s.getName()+ " "+ s.getIndex()+" wurde nach unten gesetzt");
						s.getDirection().clean();
						s.getDirection().setUnten(true);
						spielerFeld.print();
					}
					else if(y==3 && o==true){
						spielerFeld.schiffSetzenOben(hoehe,breite,s);
						System.out.println(s.getName()+ " "+ s.getIndex()+" wurde nach oben gesetzt");
						s.getDirection().clean();
						s.getDirection().setOben(true);
						spielerFeld.print();
					}
					else{
						System.out.println("Fehler");
						System.out.println("Bitte w�hle eine Richtung aus, in die das Boot gesetzt werden kann");
						spielerFeld.print();
						i--;
					}
					
				}
				else{
					System.out.println("Das Boot kann hier nicht gesetzt werden");
					i--;
				}
			}
	}

			/*for(int i=0;i<schiffListe.size();i++){
				Schiffe s=schiffListe.get(i);
				s.getDirection().clean();
				int size=s.getSize();
				switch(size){
				
				
				case 2:
					System.out.println("Setze Uboot " +s.getIndex());
					int h�he=frageH�he();
					int breite=frageBreite();
					boolean l=false;
					boolean r=false;
					boolean u=false;
					boolean o=false;
					if(spielerFeld.koordinatenCheck(h�he, breite, s)){
						s.setHeight(h�he);
						s.setWidth(breite);
						System.out.println("Das Uboot kann gesetzt werden!");
						if(s.getDirection().getLinks()){
							System.out.println("Das Uboot kann von der Startposition nach links gesetzt werden!");
							l=true;
						}
						if(s.getDirection().getRechts()){
							System.out.println("Das Uboot kann von der Startposition nach rechts gesetzt werden!");
							r=true;
						}
						if(s.getDirection().getOben()){
							System.out.println("Das Uboot kann von der Startposition nach oben gesetzt werden!");
							o=true;
						}
						if(s.getDirection().getUnten()){
							System.out.println("Das Uboot kann von der Startposition nach unten gesetzt werden!");
							u=true;
						}
						System.out.println("W�hle die Richtung, in die das Uboot gesetzt werden soll!"
								+"(links=1, rechts=2, unten=3, oben=4)");
						int y=scDir.nextInt();
						if(y==1 && l==true){
							spielerFeld.schiffSetzenLinks(h�he,breite,s);
							System.out.println("Das Boot wurde nach Links gesetzt");
							s.getDirection().clean();
							s.getDirection().setLinks(true);
							spielerFeld.print();
						}
						else if(y==2 && r==true){
							spielerFeld.schiffSetzenRechts(h�he,breite,s);
							System.out.println("Das Boot wurde nach Rechts gesetzt");
							s.getDirection().clean();
							s.getDirection().setRechts(true);
							spielerFeld.print();
						}
						else if(y==3 && u==true){
							spielerFeld.schiffSetzenUnten(h�he,breite,s);
							System.out.println("Das Boot wurde nach unten gesetzt");
							s.getDirection().clean();
							s.getDirection().setUnten(true);
							spielerFeld.print();
						}
						else if(y==4 && o==true){
							spielerFeld.schiffSetzenOben(h�he,breite,s);
							System.out.println("Das Boot wurde nach oben gesetzt");
							s.getDirection().clean();
							s.getDirection().setOben(true);
							spielerFeld.print();
						}
						else{
							System.out.println("Fehler");
							spielerFeld.print();
						}
						
					}
					else{
						System.out.println("Das Boot kann hier nicht gesetzt werden");
						i--;
					}
					break;
				
				
				case 3:
					System.out.println("Setze Korvette " +s.getIndex());
					h�he=frageH�he();
					breite=frageBreite();
					l=false;
					r=false;
					u=false;
					o=false;
					if(spielerFeld.koordinatenCheck(h�he, breite, s)){
						s.setHeight(h�he);
						s.setWidth(breite);
						System.out.println("Die Korvette kann gesetzt werden!");
						if(s.getDirection().getLinks()){
							System.out.println("Die Korvette kann von der Startposition nach links gesetzt werden!");
							l=true;
						}
						if(s.getDirection().getRechts()){
							System.out.println("Die Korvette kann von der Startposition nach rechts gesetzt werden!");
							r=true;
						}
						if(s.getDirection().getOben()){
							System.out.println("Die Korvette kann von der Startposition nach oben gesetzt werden!");
							o=true;
						}
						if(s.getDirection().getUnten()){
							System.out.println("Die Korvette kann von der Startposition nach unten gesetzt werden!");
							u=true;
						}
						System.out.println("W�hle die Richtung, in die Die Korvette gesetzt werden soll!"
								+"(links=1, rechts=2, unten=3, oben=4)");
						int y=scDir.nextInt();
						if(y==1 && l==true){
							spielerFeld.schiffSetzenLinks(h�he,breite,s);
							System.out.println("Das Boot wurde nach Links gesetzt");
							s.getDirection().clean();
							s.getDirection().setLinks(true);
							spielerFeld.print();
						}
						else if(y==2 && r==true){
							spielerFeld.schiffSetzenRechts(h�he,breite,s);
							System.out.println("Das Boot wurde nach Rechts gesetzt");
							s.getDirection().clean();
							s.getDirection().setRechts(true);
							spielerFeld.print();
						}
						else if(y==3 && u==true){
							spielerFeld.schiffSetzenUnten(h�he,breite,s);
							System.out.println("Das Boot wurde nach unten gesetzt");
							s.getDirection().clean();
							s.getDirection().setUnten(true);
							spielerFeld.print();
						}
						else if(y==4 && o==true){
							spielerFeld.schiffSetzenOben(h�he,breite,s);
							System.out.println("Das Boot wurde nach oben gesetzt");
							s.getDirection().clean();
							s.getDirection().setOben(true);
							spielerFeld.print();
						}
						else{
							System.out.println("Fehler");
							spielerFeld.print();
						}
						
					}
					else{
						System.out.println("Das Boot kann hier nicht gesetzt werden");
						i--;
					}
					break;
				
				
				case 4: 
					System.out.println("Setze Fregatte " +s.getIndex());
					h�he=frageH�he();
					breite=frageBreite();
					l=false;
					r=false;
					u=false;
					o=false;
					if(spielerFeld.koordinatenCheck(h�he, breite, s)){
						s.setHeight(h�he);
						s.setWidth(breite);
						System.out.println("Die Fregatte kann gesetzt werden!");
						if(s.getDirection().getLinks()){
							System.out.println("Die Fregatte kann von der Startposition nach links gesetzt werden!");
							l=true;
						}
						if(s.getDirection().getRechts()){
							System.out.println("Die Fregatte kann von der Startposition nach rechts gesetzt werden!");
							r=true;
						}
						if(s.getDirection().getOben()){
							System.out.println("Die Fregatte kann von der Startposition nach oben gesetzt werden!");
							o=true;
						}
						if(s.getDirection().getUnten()){
							System.out.println("Die Fregatte kann von der Startposition nach unten gesetzt werden!");
							u=true;
						}
						System.out.println("W�hle die Richtung, in die die Fregatte gesetzt werden soll!"
								+"(links=1, rechts=2, unten=3, oben=4)");
						int y=scDir.nextInt();
						if(y==1 && l==true){
							spielerFeld.schiffSetzenLinks(h�he,breite,s);
							System.out.println("Das Boot wurde nach Links gesetzt");
							s.getDirection().clean();
							s.getDirection().setLinks(true);
							spielerFeld.print();
						}
						else if(y==2 && r==true){
							spielerFeld.schiffSetzenRechts(h�he,breite,s);
							System.out.println("Das Boot wurde nach Rechts gesetzt");
							s.getDirection().clean();
							s.getDirection().setRechts(true);
							spielerFeld.print();
						}
						else if(y==3 && u==true){
							spielerFeld.schiffSetzenUnten(h�he,breite,s);
							System.out.println("Das Boot wurde nach unten gesetzt");
							s.getDirection().clean();
							s.getDirection().setUnten(true);
							spielerFeld.print();
						}
						else if(y==4 && o==true){
							spielerFeld.schiffSetzenOben(h�he,breite,s);
							System.out.println("Das Boot wurde nach oben gesetzt");
							s.getDirection().clean();
							s.getDirection().setOben(true);
							spielerFeld.print();
						}
						else{
							System.out.println("Fehler");
							spielerFeld.print();
						}
						
					}
					else{
						System.out.println("Das Boot kann hier nicht gesetzt werden");
						i--;
					}
					break;
				
				
				case 5:
					System.out.println("Setze Zerst�rer " +s.getIndex());
					h�he=frageH�he();
					breite=frageBreite();
					l=false;
					r=false;
					u=false;
					o=false;
					if(spielerFeld.koordinatenCheck(h�he, breite, s)){
						s.setHeight(h�he);
						s.setWidth(breite);
						System.out.println("Der Zerst�rer kann gesetzt werden!");
						if(s.getDirection().getLinks()){
							System.out.println("Der Zerst�rer kann von der Startposition nach links gesetzt werden!");
							l=true;
						}
						if(s.getDirection().getRechts()){
							System.out.println("Der Zerst�rer kann von der Startposition nach rechts gesetzt werden!");
							r=true;
						}
						if(s.getDirection().getOben()){
							System.out.println("Der Zerst�rer kann von der Startposition nach oben gesetzt werden!");
							o=true;
						}
						if(s.getDirection().getUnten()){
							System.out.println("Der Zerst�rer kann von der Startposition nach unten gesetzt werden!");
							u=true;
						}
						System.out.println("W�hle die Richtung, in die der Zerst�rer gesetzt werden soll!"
								+"(links=1, rechts=2, unten=3, oben=4)");
						int y=scDir.nextInt();
						if(y==1 && l==true){
							spielerFeld.schiffSetzenLinks(h�he,breite,s);
							System.out.println("Das Boot wurde nach Links gesetzt");
							s.getDirection().clean();
							s.getDirection().setLinks(true);
							spielerFeld.print();
						}
						else if(y==2 && r==true){
							spielerFeld.schiffSetzenRechts(h�he,breite,s);
							System.out.println("Das Boot wurde nach Rechts gesetzt");
							s.getDirection().clean();
							s.getDirection().setRechts(true);
							spielerFeld.print();
						}
						else if(y==3 && u==true){
							spielerFeld.schiffSetzenUnten(h�he,breite,s);
							System.out.println("Das Boot wurde nach unten gesetzt");
							s.getDirection().clean();
							s.getDirection().setUnten(true);
							spielerFeld.print();
						}
						else if(y==4 && o==true){
							spielerFeld.schiffSetzenOben(h�he,breite,s);
							System.out.println("Das Boot wurde nach oben gesetzt");
							s.getDirection().clean();
							s.getDirection().setOben(true);
							spielerFeld.print();
						}
						else{
							System.out.println("Fehler");
							spielerFeld.print();
						}
						
					}
					else{
						System.out.println("Das Boot kann hier nicht gesetzt werden");
						i--;
					}
					break;
				
				
				default:
				System.out.println("Fehler, der eigentlich nie auftreten kann...");
					break;
				}
			}
		//}
		/*else{
			System.out.println("Bitte w�hle Koordinaten innerhalb des Spielfeldes aus!");
			//Rekursion
		}*/
		
	/*	
	}
	*/
	
	
	/*
	 * Methode, um die Zeile abzufragen
	 */
	public int frageHoehe(){
		System.out.println("In welcher Zeile soll der Startpunkt sein?");
		int hoehe=Abfragen.frageOrt();
		return hoehe;
	}
	/*
	 * Methode, um die Spalte abzufragen
	 */
	public int frageBreite(){
		System.out.println("In welcher Spalte soll der Startpunkt sein?");
		int breite=Abfragen.frageOrt();
		return breite;
	}

	public boolean getGetroffen() {
		return getroffen;
	}

	public void setGetroffen(boolean getroffen) {
		this.getroffen = getroffen;
	}
	
	public GUISpielfeld getGuifeld(){
		return this.guifeld;
	}
	public void setGuifeld(GUISpielfeld guiSpielfeld){
		this.guifeld=guiSpielfeld;
	}

	public void setIsKI(boolean b) {
		this.isKI=b;
		
	}
	
	public Spieler[] getSpielerArray(){
		return spielerArray;
	}

	public boolean getIsKI() {
	
		return isKI;
	}
}
