import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//import java.io.*;
public class Spiel {
	
	private Spieler[] spielerArray;
	private ArrayList<Schiffe> schiffListe;
	private int spielerZahl;
	private int rundenZahl=0;
	private Scanner scSpieler=new Scanner(System.in);
	private Scanner scSpielfeld=new Scanner(System.in);
	private Scanner scSchiffe = new Scanner(System.in);
	
	
	public Spiel(){
		
	}
	
	/*
	 * Methode, mit der das Spiel eingeleitet wird und alle erforderlichen Daten abgefragt werden
	 */
	public void einleitung(){
		erstelleSpieler();
		erstelleSpielfeld(); 
		erstelleSchiffe();
		schiffeZuSpieler();
		schiffeAufFeld();
		
	}

	
	public List<Schiffe> getSchiffList(){
		return this.schiffListe;
	}
	
 	/*
 	 * Methode, mit der ein Array mit allen Spielern gefüllt wird
 	 */
	public void erstelleSpieler(){	
		System.out.println("Wie viele Spieler nehmen teil? (tippe 2-6)\n");
		spielerZahl=scSpieler.nextInt();
		
		if(spielerZahl<2 || spielerZahl>6){
			System.out.println("Bitte eine Spielerzahl zwischen 2 und 6 eingeben!");
			erstelleSpieler();
		}
		
		spielerArray= new Spieler [spielerZahl];
		for(int i=1; i<=spielerZahl; i++){
			System.out.println("Wie ist der Name von Spieler " + i + " ?\n");
			String sname=scSpieler.next();
			spielerArray[i-1]=new Spieler(sname);
			System.out.println(sname);
			
		}
	}
	
	/*
	 * Methode mit der die Anzahl und Art der Schiffe abgefragt werden
	 */
	public void erstelleSchiffe(){	
		schiffListe= new ArrayList<Schiffe>();
		System.out.println("Wie viele Schiffe soll es geben?");
		int schiffeZahl=scSchiffe.nextInt();
		int zerstörerZähler=1;
		int fregattenZähler=1;
		int	korvettenZähler=1;
		int uBootZähler=1;
		for(int i=1;i<=schiffeZahl;i++){
			System.out.println("Welches Schiff soll als "+i+". erstellt werden?");
			System.out.println("Bitte gib eine Zahl ein:");
			System.out.println("1 = Uboot");
			System.out.println("2 = Korvette");
			System.out.println("3 = Fregatte");
			System.out.println("4 = Zerstörer");
			int x = scSchiffe.nextInt();
			if(x==4){ 
				schiffListe.add(new Zerstoerer(zerstörerZähler));
				zerstörerZähler++;
				System.out.println("Ein Zerstörer wurde erstellt!");
			}
			else if(x==3){ 
				schiffListe.add(new Fregatte(fregattenZähler));
				fregattenZähler++;
				System.out.println("Eine Fregatte wurde erstellt!");
			}
						
			else if(x==2){
				schiffListe.add(new Korvette(korvettenZähler));
				korvettenZähler++;
				System.out.println("Eine Korvette wurde erstellt!");
			}
				
			else if(x==1){
				schiffListe.add(new UBoot(uBootZähler));
				uBootZähler++;
				System.out.println("Ein UBoot wurde erstellt!");
			}
				
			else{ 
				System.out.println("Bitte eine Zahl zwischen 1 und 4 eingeben!");
				i--;
			}
		}
	}
	
	/*
	 * Methode, mit der das spielerArray durchgegangen wird und jeder Spieler seine Schiffe setzt
	 */
	public void schiffeAufFeld(){
		for(Spieler i:this.spielerArray){
			System.out.println(i.getName()+ " ist an der Reihe, seine Schiffe zu setzen");
			i.setzeSchiffe();
		}
	}
	
	/*
	 * Methode, um die Größe des Spielfelds abzufragen und ein Spielfeld für jeden Spieler zu erstellen
	 */
	public void erstelleSpielfeld(){
		System.out.println("Wie groß soll das Spielfeld sein?");
		System.out.println("Gib eine Zahl ein! (min. 6)");
		int feldSize=scSpielfeld.nextInt();
	
		if(feldSize<6){
			System.out.println("Bitte eine ganze Zahl, welche größer als 6 ist eingeben!");
			erstelleSpielfeld();
		}
	
		else{
			for(Spieler k:spielerArray){
			k.erstelleFeld(feldSize);
			}
		}
	}

	/*
	 * Methode, um die Schiffliste an die Spieler zu übertragen
	 */
	public void schiffeZuSpieler(){
		for(Spieler k:spielerArray){
			k.setSchiffListe(schiffListe);
		}
	}
	
	/*
	 * Methode, um das Spielerarray an die einzelnen Spieler zu übergeben
	 */
	public void spielerZuSpieler(){
		for(Spieler k:spielerArray){
			k.setSpielerArray(spielerArray);
		}
	}
	
	public int getRundenZahl(){
		return this.rundenZahl;
	}
	
	/*
	 * Methode, in welcher der Spielverlauf geregelt wird, bis alle ausgeschieden sind
	 */
	public void spielVerlauf(){
		while(!abbruchBed()){
			for(Spieler i:spielerArray){
				if(i.getImSpiel()){
					System.out.println(i.getName()+" ist an der Reihe!");
					System.out.println("");
					i.schiessen();
					for(Schiffe x:i.getSchiffListe()){
						System.out.println(x.getReg());
					}
				}
				if(abbruchBed()){
					System.out.println("Ende!");
					break;
				}
			}
			rundenZahl++;
			System.out.println("Runde "+ (rundenZahl-1) + " ist zu Ende.");
			for(Spieler k:spielerArray){
				for(Schiffe s:k.getSchiffListe()){
					s.updateReg();
				}
			}
		}
	}
	
	/*
	 * Methode, welche das Spielerarray durchläuft und testet, ob noch mehr als ein Spieler im Spiel sind
	 */
	public boolean abbruchBed(){
		int x=0;
		for(Spieler i:spielerArray){
			if(i.getImSpiel()){
				x++;
			}
		}
		if(x>1){
			System.out.println("Das Spiel geht mit "+ x+" Spielern weiter");
			return false;
		}
		else{
			for(Spieler i:spielerArray){
				if(i.getImSpiel()){
					System.out.println(i.getName()+" hat gewonnen!!");
				}
			}
			return true;
		}
	}
	
	public static void main (String[]args){
		Spiel spiel=new Spiel();
		spiel.einleitung();
		spiel.spielerZuSpieler();
		spiel.spielVerlauf();
		//System.out.println(spielerArray[1].getAnzahlSchiffe());
		

	
		
	}

}
