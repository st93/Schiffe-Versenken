import java.util.*;
public class KI extends Spieler{
	public KI(int x){
		super();
		this.name="Computer "+x;
	}
	
	public void schuss(Schiffe s, Spieler sp){
		sp.getSpielerFeld().printFeind();
		int hoehe=randomInt(1,spielerFeld.getSize());
		int breite=randomInt(1,spielerFeld.getSize());
		int a=randomInt(1,2);
		System.out.println(this.name+" w‰hlt ZielKoordinaten..");
		System.out.println("Zeile: "+hoehe);
		System.out.println("Spalte: "+breite);
		if(a==1){
			System.out.println("Ausrichtung: vertikal");
		}
		if(a==2){
			System.out.println("Ausrichtung: horizontal");
		}
		if(sp.getSpielerFeld().schieﬂen(hoehe,breite,a,s)){
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
				System.out.println(spielerArray[i].getName()+" kann angegriffen werden! w‰hle: "+i);
			}
			
		}
		int geg=randomInt(1,spielerArray.length);
		if(!spielerArray[geg].getImSpiel()&&spielerArray[geg].getName()!=this.getName()){
			this.spielerWahl();
		}
		System.out.println(spielerArray[geg].getName()+"wurde ausgew‰hlt");
		return spielerArray[geg];
	}

	public Schiffe bootWahl(){
		System.out.println(this.name+" w‰hlt ein Boot zum Schieﬂen!");
		int b=randomInt(1,4);
		for(Schiffe s:schiffListe){
			if(s.getTyp()==b && s.schussBereit()){
				System.out.println(s.getName()+" "+ s.getIndex()+" soll schieﬂen");
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
			int hoehe=randomInt(1,spielerFeld.getSize());
			int breite=randomInt(1,spielerFeld.getSize());
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
				System.out.println(this.name+" w‰hlt die Richtung, in die das Boot gesetzt werden soll!");
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
