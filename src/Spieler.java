import java.util.*;
import java.util.Scanner;


public class Spieler {
	private boolean imSpiel=true;
	private int anzahlSchiffe=5;
	private String name;
	private Board spielerFeld;
	private Scanner scHöhe= new Scanner(System.in);
	private Scanner scBreite= new Scanner(System.in);
	private Scanner scDir= new Scanner(System.in);
	private Scanner scs= new Scanner(System.in);
	private Scanner scAus= new Scanner(System.in);
	private Scanner scGeg= new Scanner(System.in);
	private ArrayList<Schiffe> schiffListe;
	private Spieler[] spielerArray;
	
	public Spieler(String name){
		this.imSpiel=true;
		this.name=name;
	}
	public Spieler(){
		
	}
	
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
	
	public boolean versenktup(Spieler[] spielerArray){
		for(Spieler i:spielerArray){
			for(Schiffe k:i.getSchiffListe()){
				if(!k.getVersenkt()){
					if(k.getDirection().getLinks()){
						if(i.getSpielerFeld().versenktLinks(k.getHeight(), k.getWidth(), k)){
							System.out.println("Schiff wurde versenkt");
							i.getSpielerFeld().printFeind();
							return true;
						}
					}
					else if(k.getDirection().getRechts()){
						if(i.getSpielerFeld().versenktRechts(k.getHeight(), k.getWidth(), k)){
							System.out.println("Schiff wurde versenkt");
							i.getSpielerFeld().printFeind();
							return true;
						}
					}
					else if(k.getDirection().getOben()){
						if(i.getSpielerFeld().versenktOben(k.getHeight(), k.getWidth(), k)){
							System.out.println("Schiff wurde versenkt");
							i.getSpielerFeld().printFeind();
							return true;
						}
					}
					else if(k.getDirection().getUnten()){
						if(i.getSpielerFeld().versenktUnten(k.getHeight(), k.getWidth(), k)){
							System.out.println("Schiff wurde versenkt");
							i.getSpielerFeld().printFeind();
							return true;
						}
					}
				}
			}
		}
		return false;
		
	}
	
	public boolean schiffCheck(){
		for(Schiffe s:schiffListe){
			if((s.getVersenkt()==false)&& s.getReg()==0){
				return true;
			}
		}
		System.out.println("Leider kein Schiff bereit..");
		return false;
	}
	
	public void schießen(){
		Spieler sp=this.spielerWahl();
		this.shoot(sp);
		
	}
	
	public void shoot(Spieler sp){
		this.versenktup(spielerArray);
		if(this.schiffCheck()){
			this.bootVerfügbar();
			Schiffe s=this.bootWahl();
			this.schuss(s,sp);
		}
		
	}
	
	public void bootVerfügbar(){
		boolean u=false;
		boolean k=false;
		boolean f=false;
		boolean z=false;
		for(Schiffe s:schiffListe){
			if(!s.getVersenkt()&&s.getReg()==0){
				int size=s.getSize();
				
				switch(size){
				case 2:
					u=true;
					break;
				case 3:
					k=true;
					break;
				case 4:
					f=true;
					break;
				case 5:
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
			System.out.println("Es kann mit einem Zerstörer geschossen werden.");
		}
	}
	
	public void schuss(Schiffe s, Spieler sp){
		sp.getSpielerFeld().printFeind();
		System.out.println("Tippen sie die Zielkoordinaten!");
		System.out.println("Wählen sie eine Zeile!");
		int höhe=scHöhe.nextInt()-1;
		System.out.println("Wählen sie eine Spalte!");
		int breite=scBreite.nextInt()-1;
		if(!spielerFeld.schussKoordinaten(höhe, breite)){
			System.out.println("Bitte wählen Sie Koordinaten innerhalb des Feldes");
			this.schuss(s,sp);
		}
		System.out.println("Wählen sie die Ausrichtung!(1=vertikal; 2=horizontal)");
		int a=scAus.nextInt();
		if(!(a==2||a==1)){
			System.out.println("Fehler, Bitte tippen Sie 1 oder 2.");
		}
		if(sp.getSpielerFeld().schießen(höhe,breite,a,s)){
			System.out.println("Treffer!!!");
			sp.getSpielerFeld().printFeind();
			s.setReg();
			if(sp.spielerAus()){
				this.schießen();
			}
			else{
				this.shoot(sp);
			}
			
			
		}
		else{
			System.out.println("Kein Treffer..");
			sp.getSpielerFeld().printFeind();
			s.setReg();
		}
	}
	
	public Spieler spielerWahl(){
		for(int i=0;i<spielerArray.length;i++){
			if(spielerArray[i].getImSpiel()){
				System.out.println(spielerArray[i].getName()+" kann angegriffen werden! wähle: "+i);
			}
			
		}
		int geg=scGeg.nextInt();
		if(!spielerArray[geg].getImSpiel()){
			System.out.println("Bitte wähle einen der genannten Spieler aus!");
			this.spielerWahl();
		}
		return spielerArray[geg];
	}
	
	public Schiffe bootWahl(){
		System.out.println("Wählen sie ein Boot zum Schießen! 1=Zerstörer 2= Fregatte 3= Korvette 4=Uboot");
		int b=scs.nextInt();
		Schiffe s=new Schiffe();
		if(b==1){
			System.out.println("Es soll mit einem Zerstörer geschossen werden!");
			int counter=0;
			do{
				s=schiffListe.get(counter);
				counter++;
			}while(s.getSize()!=5);
			return schiffListe.get(counter-1);
		}
		else if(b==2){
			System.out.println("Es soll mit einer Fregatte geschossen werden!");
			int counter=0;
			do{
				s=schiffListe.get(counter);
				counter++;
			}while(s.getSize()!=4);
			return schiffListe.get(counter-1);
		}
		else if(b==3){
			System.out.println("Es soll mit einer Korvette geschossen werden!");
			int counter=0;
			do{
				s=schiffListe.get(counter);
				counter++;
			}while(s.getSize()!=3);
			return schiffListe.get(counter-1);
		}
		else if(b==4){
			System.out.println("Es soll mit einem Uboot geschossen werden!");
			int counter=0;
			do{
				s=schiffListe.get(counter);
				counter++;
			}while(s.getSize()!=2);
			return schiffListe.get(counter-1);
		}
		else{
			System.out.println("Fehler, bitte zahl zwischen 1 und 4 tippen!");
			this.bootWahl();
		}
		return s;
	}
	
	public void setImSpiel(boolean x){
		this.imSpiel=x;
	}
	
	public boolean getImSpiel(){
		return imSpiel;
	}
	
	public int getAnzahlSchiffe(){
		return this.anzahlSchiffe;
	}
	
	public void setName(String s){
		this.name=s;
	}
	
	public String getName(){
		return name;
	}
	
	public Board getSpielerFeld(){
		return this.spielerFeld;
	}
	
	public void erstelleFeld(int size){
		this.spielerFeld=new Board(size);
	}
	
	public void setSpielerArray(Spieler[]s){
		this.spielerArray=s;
	}
	
	public void setSchiffListe(List<Schiffe> schiffListe){
		int ub=1;
		int ko=1;
		int fr=1;
		int ze=1;
		this.schiffListe= new ArrayList<Schiffe>();
		for(Schiffe s:schiffListe){
			int sz=s.getSize();
			if(sz==2){
				this.schiffListe.add(new UBoot(ub));
				ub++;
			}
			if(sz==3){
				this.schiffListe.add(new Korvette(ko));
				ub++;				
			}
			if(sz==4){
				this.schiffListe.add(new Fregatte(fr));
				ub++;	
			}
			if(sz==5){
				this.schiffListe.add(new Zerstörer(ze));
				ub++;	
			}
		}
	}
	
	public List<Schiffe> getSchiffListe(){
		return this.schiffListe;
	}
	
	public void setzeSchiffe(){
			for(int i=0;i<schiffListe.size();i++){
				Schiffe s=schiffListe.get(i);
				s.getDirection().clean();
				int size=s.getSize();
				switch(size){
				
				
				case 2:
					System.out.println("Setze Uboot " +s.getId());
					int höhe=frageHöhe();
					int breite=frageBreite();
					boolean l=false;
					boolean r=false;
					boolean u=false;
					boolean o=false;
					if(spielerFeld.koordinatenCheck(höhe, breite, s)){
						s.setHeight(höhe);
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
						System.out.println("Wähle die Richtung, in die das Uboot gesetzt werden soll!"
								+"(links=1, rechts=2, unten=3, oben=4)");
						int y=scDir.nextInt();
						if(y==1 && l==true){
							spielerFeld.schiffSetzenLinks(höhe,breite,s);
							System.out.println("Das Boot wurde nach Links gesetzt");
							s.getDirection().clean();
							s.getDirection().setLinks(true);
							spielerFeld.print();
						}
						else if(y==2 && r==true){
							spielerFeld.schiffSetzenRechts(höhe,breite,s);
							System.out.println("Das Boot wurde nach Rechts gesetzt");
							s.getDirection().clean();
							s.getDirection().setRechts(true);
							spielerFeld.print();
						}
						else if(y==3 && u==true){
							spielerFeld.schiffSetzenUnten(höhe,breite,s);
							System.out.println("Das Boot wurde nach unten gesetzt");
							s.getDirection().clean();
							s.getDirection().setUnten(true);
							spielerFeld.print();
						}
						else if(y==4 && o==true){
							spielerFeld.schiffSetzenOben(höhe,breite,s);
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
					System.out.println("Setze Korvette " +s.getId());
					höhe=frageHöhe();
					breite=frageBreite();
					l=false;
					r=false;
					u=false;
					o=false;
					if(spielerFeld.koordinatenCheck(höhe, breite, s)){
						s.setHeight(höhe);
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
						System.out.println("Wähle die Richtung, in die Die Korvette gesetzt werden soll!"
								+"(links=1, rechts=2, unten=3, oben=4)");
						int y=scDir.nextInt();
						if(y==1 && l==true){
							spielerFeld.schiffSetzenLinks(höhe,breite,s);
							System.out.println("Das Boot wurde nach Links gesetzt");
							s.getDirection().clean();
							s.getDirection().setLinks(true);
							spielerFeld.print();
						}
						else if(y==2 && r==true){
							spielerFeld.schiffSetzenRechts(höhe,breite,s);
							System.out.println("Das Boot wurde nach Rechts gesetzt");
							s.getDirection().clean();
							s.getDirection().setRechts(true);
							spielerFeld.print();
						}
						else if(y==3 && u==true){
							spielerFeld.schiffSetzenUnten(höhe,breite,s);
							System.out.println("Das Boot wurde nach unten gesetzt");
							s.getDirection().clean();
							s.getDirection().setUnten(true);
							spielerFeld.print();
						}
						else if(y==4 && o==true){
							spielerFeld.schiffSetzenOben(höhe,breite,s);
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
					System.out.println("Setze Fregatte " +s.getId());
					höhe=frageHöhe();
					breite=frageBreite();
					l=false;
					r=false;
					u=false;
					o=false;
					if(spielerFeld.koordinatenCheck(höhe, breite, s)){
						s.setHeight(höhe);
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
						System.out.println("Wähle die Richtung, in die die Fregatte gesetzt werden soll!"
								+"(links=1, rechts=2, unten=3, oben=4)");
						int y=scDir.nextInt();
						if(y==1 && l==true){
							spielerFeld.schiffSetzenLinks(höhe,breite,s);
							System.out.println("Das Boot wurde nach Links gesetzt");
							s.getDirection().clean();
							s.getDirection().setLinks(true);
							spielerFeld.print();
						}
						else if(y==2 && r==true){
							spielerFeld.schiffSetzenRechts(höhe,breite,s);
							System.out.println("Das Boot wurde nach Rechts gesetzt");
							s.getDirection().clean();
							s.getDirection().setRechts(true);
							spielerFeld.print();
						}
						else if(y==3 && u==true){
							spielerFeld.schiffSetzenUnten(höhe,breite,s);
							System.out.println("Das Boot wurde nach unten gesetzt");
							s.getDirection().clean();
							s.getDirection().setUnten(true);
							spielerFeld.print();
						}
						else if(y==4 && o==true){
							spielerFeld.schiffSetzenOben(höhe,breite,s);
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
					System.out.println("Setze Zerstörer " +s.getId());
					höhe=frageHöhe();
					breite=frageBreite();
					l=false;
					r=false;
					u=false;
					o=false;
					if(spielerFeld.koordinatenCheck(höhe, breite, s)){
						s.setHeight(höhe);
						s.setWidth(breite);
						System.out.println("Der Zerstörer kann gesetzt werden!");
						if(s.getDirection().getLinks()){
							System.out.println("Der Zerstörer kann von der Startposition nach links gesetzt werden!");
							l=true;
						}
						if(s.getDirection().getRechts()){
							System.out.println("Der Zerstörer kann von der Startposition nach rechts gesetzt werden!");
							r=true;
						}
						if(s.getDirection().getOben()){
							System.out.println("Der Zerstörer kann von der Startposition nach oben gesetzt werden!");
							o=true;
						}
						if(s.getDirection().getUnten()){
							System.out.println("Der Zerstörer kann von der Startposition nach unten gesetzt werden!");
							u=true;
						}
						System.out.println("Wähle die Richtung, in die der Zerstörer gesetzt werden soll!"
								+"(links=1, rechts=2, unten=3, oben=4)");
						int y=scDir.nextInt();
						if(y==1 && l==true){
							spielerFeld.schiffSetzenLinks(höhe,breite,s);
							System.out.println("Das Boot wurde nach Links gesetzt");
							s.getDirection().clean();
							s.getDirection().setLinks(true);
							spielerFeld.print();
						}
						else if(y==2 && r==true){
							spielerFeld.schiffSetzenRechts(höhe,breite,s);
							System.out.println("Das Boot wurde nach Rechts gesetzt");
							s.getDirection().clean();
							s.getDirection().setRechts(true);
							spielerFeld.print();
						}
						else if(y==3 && u==true){
							spielerFeld.schiffSetzenUnten(höhe,breite,s);
							System.out.println("Das Boot wurde nach unten gesetzt");
							s.getDirection().clean();
							s.getDirection().setUnten(true);
							spielerFeld.print();
						}
						else if(y==4 && o==true){
							spielerFeld.schiffSetzenOben(höhe,breite,s);
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
			System.out.println("Bitte wähle Koordinaten innerhalb des Spielfeldes aus!");
			//Rekursion
		}*/
		
		
	}
	
	public int frageHöhe(){
		System.out.println("In welcher Zeile soll der Startpunkt sein?");
		int höhe=scHöhe.nextInt();
		return höhe-1;
	}
	public int frageBreite(){
		System.out.println("In welcher Spalte soll der Startpunkt sein?");
		int breite=scBreite.nextInt();
		return breite-1;
	}
}
