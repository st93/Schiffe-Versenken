import java.util.*;
public class KI extends Spieler{
	public KI(int x){
		super("Computer "+x);
	}
	public KI(String y){
		super();
		this.name=y;
	}
	
	public void schuss(Schiffe s, Spieler sp){
		sp.getSpielerFeld().printFeind();
		int hoehe=randomInt(1,spielerFeld.getSize()-1);
		int breite=randomInt(1,spielerFeld.getSize()-1);
		int a=randomInt(1,2);
		System.out.println(this.name+" w�hlt ZielKoordinaten..");
		System.out.println("Zeile: "+hoehe+1);
		System.out.println("Spalte: "+breite+1);
		if(a==1){
			System.out.println("Ausrichtung: vertikal");
		}
		if(a==2){
			System.out.println("Ausrichtung: horizontal");
		}
		if(sp.getSpielerFeld().schiessen(hoehe,breite,a,s)){
			System.out.println("Treffer!!!");
			sp.getSpielerFeld().printFeind();
			s.setReg();
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
			s.setReg();
		}
	}
	
	public Spieler spielerWahl(){
		for(int i=0;i<spielerArray.length;i++){
			if(spielerArray[i].getImSpiel()&&spielerArray[i].getName()!=this.getName()){
				System.out.println(spielerArray[i].getName()+" kann angegriffen werden! w�hle: "+i);
			}
			
		}
		int geg=randomInt(0,spielerArray.length-1);
		if(spielerArray[geg].getImSpiel()&&spielerArray[geg].getName()!=this.getName()){
			System.out.println(spielerArray[geg].getName()+" wurde ausgew�hlt");
			return spielerArray[geg];
		}
		return this.spielerWahl();
	}

	public Schiffe bootWahl(){
		int b=randomInt(1,4);
		for(Schiffe s:schiffListe){
			if(s.getTyp()==b && s.schussBereit()){
				System.out.println(s.getName()+" "+ s.getIndex()+" soll schie�en");
				return s;
			}
			
		}
		return bootWahl();
	}
	
	public void setzeSchiffe(){
		for(int i=0;i<schiffListe.size();i++){
			Schiffe s=schiffListe.get(i);
			s.getDirection().clean();
			System.out.println("Setze " + s.getName()+ " "+s.getIndex());
			int hoehe=randomInt(1,spielerFeld.getSize()-1);
			int breite=randomInt(1,spielerFeld.getSize()-1);
			boolean l=false;
			boolean r=false;
			boolean u=false;
			boolean o=false;
			if(spielerFeld.koordinatenCheck(hoehe, breite, s)){
				s.setHeight(hoehe);
				s.setWidth(breite);
				System.out.println("Zeile: "+ hoehe);
				System.out.println("Zeile: "+ breite);
				System.out.println(s.getName()+" "+s.getIndex()+ " kann hier gesetzt werden!");
				System.out.println("");
				System.out.println(this.name+" w�hlt die Richtung, in die das Boot gesetzt werden soll!");
				System.out.println("");
				System.out.println("");
				l=s.getDirection().getLinks();
				r=s.getDirection().getRechts();
				o=s.getDirection().getOben();
				u=s.getDirection().getUnten();
				int y=randomInt(1,4);
				while(!(y==1&&l||y==2&&r||y==3&&o||y==4&&u)){
					y=randomInt(1,4);
				}
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
				
			}
			else{
				System.out.println("Das Boot kann hier nicht gesetzt werden");
				i--;
			}
		}
	}
	
	public static int randomInt(int min, int max) {

	    Random random = new Random();
	    int randomNum = random.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
}
