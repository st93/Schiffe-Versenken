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
	
	public void erstelleSchiffe(){	
		schiffListe= new ArrayList<Schiffe>();
		System.out.println("Wie viele Schiffe soll es geben?");
		int schiffeZahl=scSchiffe.nextInt();
		int zerst�rerZ�hler=1;
		int fregattenZ�hler=1;
		int	korvettenZ�hler=1;
		int uBootZ�hler=1;
		for(int i=1;i<=schiffeZahl;i++){
			System.out.println("Welches Schiff soll als "+i+". erstellt werden?");
			System.out.println(" 1 = Zerst�rer, 2 = Fregatte, 3 = Korvette, 4 = Uboot");
			int x = scSchiffe.nextInt();
			switch(x){
				case 1: 
				schiffListe.add(new Zerst�rer(zerst�rerZ�hler));
				zerst�rerZ�hler++;
				System.out.println("Ein Zerst�rer wurde erstellt!");
				break;
						
				case 2: 
				schiffListe.add(new Fregatte(fregattenZ�hler));
				fregattenZ�hler++;
				System.out.println("Eine Fregatte wurde erstellt!");
				break;
						
				case 3:
				schiffListe.add(new Korvette(korvettenZ�hler));
				korvettenZ�hler++;
				System.out.println("Eine Korvette wurde erstellt!");
				break;
				
				case 4:
				schiffListe.add(new UBoot(uBootZ�hler));
				uBootZ�hler++;
				System.out.println("Ein UBoot wurde erstellt!");
				break;
				
				default: 
				System.out.println("Bitte eine Zahl zwischen 1 und 4 eingeben!");
				i--;
				break;
			}
		}
	}
	
	public void schiffeAufFeld(){
		for(Spieler i:this.spielerArray){
			System.out.println(i.getName()+ " ist an der Reihe, seine Schiffe zu setzen");
			i.setzeSchiffe();
		}
	}
	
	public void erstelleSpielfeld(){
		System.out.println("Wie gro� soll das Spielfeld sein?");
		System.out.println("Gib eine Zahl ein! (min. 6)");
		int feldSize=scSpielfeld.nextInt();
	
		if(feldSize<6){
			System.out.println("Bitte eine ganze Zahl, welche gr��er als 6 ist eingeben!");
			erstelleSpielfeld();
		}
	
		else{
			for(Spieler k:spielerArray){
			k.erstelleFeld(feldSize);
			}
		}
	}

	public void schiffeZuSpieler(){
		for(Spieler k:spielerArray){
			k.setSchiffListe(schiffListe);
		}
	}
	
	public void spielerZuSpieler(){
		for(Spieler k:spielerArray){
			k.setSpielerArray(spielerArray);
		}
	}
	
	public int getRundenZahl(){
		return this.rundenZahl;
	}
	
	public void spielVerlauf(){
		
		while(!abbruchBed()){
			for(Spieler i:spielerArray){
				if(i.getImSpiel()){
					System.out.println(i.getName()+" ist an der Reihe!");
					System.out.println("");
					i.schie�en();
				}
				if(abbruchBed()){
					break;
				}
			}
			rundenZahl++;
			for(Spieler k:spielerArray){
				for(Schiffe s:k.getSchiffListe()){
					s.updateReg();
				}
			}
		}
	}
	
	public boolean abbruchBed(){
		int x=0;
		for(Spieler i:spielerArray){
			if(i.getImSpiel()){
				x++;
			}
		}
		if(x>1){
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
