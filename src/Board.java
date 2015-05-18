
public class Board {
	private int size;
	
	private Square[][] feld;
	private char wasser='~';
	private char schiff='S';
	private char schiffTreffer='T';
	private char wasserTreffer='W';
	private char schiffVersenkt='V';
	private int h�he;
	private int breite;
	
	public Board (int size){
		this.size=size;
		this.feld= new Square[size][size];
		for(int i=0;i<feld.length;i++){
            for(int k=0;k<feld[0].length;k++){
                this.feld[i][k]=new Square();
            }
        }
		
		
		
	}
	
	public int getSize(){
		return this.size;
	}
	
	public Square getSquare(int h, int w){
		return feld[h][w];
	}
	
	public void setWasser(int height, int width){
		this.getSquare(height, width).setCounter(0);
	}
	
	public void setWasserTreffer(int height, int width){
		this.getSquare(height, width).setCounter(3);
	}
	
	public void setSchiffTreffer(int height, int width){
		this.getSquare(height, width).setCounter(2);
	}
	
	public void setSchiff(int height, int width){
		this.getSquare(height, width).setCounter(1);
	}
	
	public void setSchiffVersenkt(int height, int width){
		this.getSquare(height, width).setCounter(4);
	}
	
	public boolean koordinatenCheck(int h, int b, Schiffe s){
		this.h�he=h;
		this.breite=b;
		if(oben(h�he,breite)){
			if(obenLinks(h�he,breite)){
				if(!feldFreiEck1(h�he,breite)){
					return false;
				}
				if(richtungEck1Rechts(h�he,breite,s) || richtungEck1Unten(h�he,breite,s)){
					if(richtungEck1Rechts(h�he,breite,s)){
						
					}
					if(richtungEck1Unten(h�he,breite,s)){
						
					}
					return true;
				}
				else{
					return false;
				}
			}
			else if(obenRechts(h�he,breite)){
				if(!feldFreiEck2(h�he,breite)){
					return false;
				}
				if(richtungEck2Links(h�he,breite,s) || richtungEck2Unten(h�he,breite,s)){
					if(richtungEck2Links(h�he,breite,s)){
						
					}
					if(richtungEck2Unten(h�he,breite,s)){
						
					}
					return true;
				}
				else{
					return false;
				}
			}
			else{
				if(!feldFreiOben(h�he,breite)){
					return false;
				}
				if(richtungObenLinks(h�he,breite,s) || richtungObenRechts(h�he,breite,s) || richtungObenUnten(h�he,breite,s)){
					if(richtungObenRechts(h�he,breite,s)){
						
					}
					if(richtungObenUnten(h�he,breite,s)){
						
					}
					if(richtungObenLinks(h�he,breite,s)){
						
					}
					return true;
				}
				else{
					return false;
				}
			}
		}
		else if(unten(h�he,breite)){
			if(untenLinks(h�he,breite)){
				if(!feldFreiEck4(h�he,breite)){
					return false;
				}
				if(richtungEck4Rechts(h�he,breite,s) || richtungEck4Oben(h�he,breite,s)){
					if(richtungEck4Rechts(h�he,breite,s)){

					}
					if(richtungEck4Oben(h�he,breite,s)){
						
					}
					return true;
				}
				else{
					return false;
				}
			}
			else if(untenRechts(h�he,breite)){
				if(!feldFreiEck3(h�he,breite)){
					return false;
				}
				if(richtungEck3Links(h�he,breite,s) || richtungEck3Oben(h�he,breite,s)){
					if(richtungEck3Links(h�he,breite,s)){
						
					}
					if(richtungEck3Oben(h�he,breite,s)){
						
					}
					return true;
				}
				else{
					return false;
				}
			}
			else{
				if(!feldFreiUnten(h�he,breite)){
					return false;
				}
				if(richtungUntenLinks(h�he,breite,s) || richtungUntenRechts(h�he,breite,s) || richtungUntenOben(h�he,breite,s)){
					if(richtungUntenRechts(h�he,breite,s)){
						
					}
					if(richtungUntenLinks(h�he,breite,s)){
						
					}
					if(richtungUntenOben(h�he,breite,s)){
						
					}
					return true;
				}
				else{
					return false;
				}
			}
		}
		else if(links(h�he,breite)){
			if(untenLinks(h�he,breite)){
				if(!feldFreiEck3(h�he,breite)){
					return false;
				}
				if(richtungEck4Rechts(h�he,breite,s) || richtungEck4Oben(h�he,breite,s)){
					if(richtungEck4Rechts(h�he,breite,s)){
						
					}
					if(richtungEck4Oben(h�he,breite,s)){
						
					}
					return true;
				}
				else{
					return false;
				}
			}
			else if(obenLinks(h�he,breite)){
				if(!feldFreiEck1(h�he,breite)){
					return false;
				}
				if(richtungEck1Rechts(h�he,breite,s) || richtungEck1Unten(h�he,breite,s)){
					if(richtungEck1Rechts(h�he,breite,s)){
						
					}
					if(richtungEck1Unten(h�he,breite,s)){
						
					}
					return true;
				}
				else{
					return false;
				}
			}
			else{
				if(!feldFreiLinks(h�he,breite)){
					return false;
				}
				if(richtungLinksOben(h�he,breite,s) || richtungLinksRechts(h�he,breite,s) || richtungLinksUnten(h�he,breite,s)){
					if(richtungLinksUnten(h�he,breite,s)){
						
					}
					if(richtungLinksRechts(h�he,breite,s)){
						
					}
					if(richtungLinksUnten(h�he,breite,s)){
						
					}
					return true;
				}
				else{
					return false;
				}
			}
			
		}
		else if(rechts(h�he,breite)){
			if(obenRechts(h�he,breite)){
				if(!feldFreiEck2(h�he,breite)){
					return false;
				}
				if(richtungEck2Links(h�he,breite,s) || richtungEck2Unten(h�he,breite,s)){
					if(richtungEck2Links(h�he,breite,s)){
						
					}
					if(richtungEck2Unten(h�he,breite,s)){
						
					}
					return true;
				}
				else{
					return false;
				}
			}
			else if(untenRechts(h�he,breite)){
				if(!feldFreiEck4(h�he,breite)){
					return false;
				}
				if(richtungEck3Links(h�he,breite,s) || richtungEck3Oben(h�he,breite,s)){
					if(richtungEck3Links(h�he,breite,s)){
						
					}
					if(richtungEck3Oben(h�he,breite,s)){
						
					}
					return true;
				}
				else{
					return false;
				}
			}
			else{
				if(!feldFreiRechts(h�he,breite)){
					return false;
				}
				if(richtungRechtsLinks(h�he,breite,s) || richtungRechtsOben(h�he,breite,s) || richtungRechtsUnten(h�he,breite,s)){
					if(richtungRechtsLinks(h�he,breite,s)){
						
					}
					if(richtungRechtsOben(h�he,breite,s)){
						
					}
					if(richtungRechtsUnten(h�he,breite,s)){
						
					}
					return true;
				}
				else{
					return false;
				}
			}
			
		}
		else{
			if(!feldFrei(h�he,breite)){
				return false;
			}
			if(richtungOben(h�he, breite, s) || richtungUnten(h�he, breite, s) || 
				richtungLinks(h�he, breite, s) || richtungRechts(h�he, breite, s)){
				if(richtungOben(h�he, breite, s)){
					
				}
				if(richtungUnten(h�he, breite, s)){
					
				}
				if(richtungLinks(h�he, breite, s)){
	
				}
				if(richtungRechts(h�he, breite, s)){
					
				}
				return true;
			}
			else{
				return false;
			}
		}
	}
	
	public boolean richtungOben(int h�he, int breite, Schiffe s){ //pr�ft, ob vom Startpunkt nach oben das Schiff 
		int schiffSize=s.getSize()-1;								  //gesetzt werden kann (nicht am Rand)
		if(h�he-schiffSize>0){	
			for(int i=h�he-schiffSize;i<=h�he;i++){
				if(feldFrei(i,breite)){
					s.getDirection().setOben(true);
				}
				else{
					s.getDirection().setOben(false);
					return false;
				}
			}
		}
		else if(h�he-schiffSize==0){
			if(feldFreiOben(h�he-schiffSize,breite)){
				for(int i=h�he-schiffSize+1;i<=h�he;i++){
					if(feldFrei(i,breite)){
						s.getDirection().setOben(true);
					}
					else{
						s.getDirection().setOben(false);
						return false;
					}
				}
			}
			else{
				s.getDirection().setOben(false);
				return false;
			}
		}
		else{
			s.getDirection().setOben(false);
			return false;
		}
		return true;
	}
	
	public boolean richtungUnten(int h�he, int breite, Schiffe s){//pr�ft, ob vom Startpunkt nach unten das Schiff
		int schiffSize=s.getSize()-1;								  //gesetzt werden kann  (nicht am Rand)
		if(h�he+schiffSize<this.size-1){
			for(int i=h�he;i<=h�he+schiffSize;i++){
				if(feldFrei(i,breite)){
					s.getDirection().setUnten(true);
				}
				else{
					s.getDirection().setUnten(false);
					return false;
				}
			}
		}
		else if(h�he+schiffSize==this.size-1){
			if(feldFreiUnten(h�he+schiffSize,breite)){
				for(int i=h�he;i<h�he+schiffSize;i++){
					if(feldFrei(i,breite)){
						s.getDirection().setUnten(true);
					}
					else{
						s.getDirection().setUnten(false);
						return false;
					}
				}
			}
			else{
				s.getDirection().setUnten(false);
				return false;
			}
		}
		else{
			s.getDirection().setUnten(false);
			return false;
		}
		return true;
	}
	
	public boolean richtungLinks(int h�he, int breite, Schiffe s){//pr�ft, ob vom Startpunkt nach links das Schiff
		int schiffSize=s.getSize()-1;								  //gesetzt werden kann  (nicht am Rand)
		if(breite-schiffSize>0){	
			for(int i=breite-schiffSize;i<=breite;i++){
				if(feldFrei(h�he,i)){
					s.getDirection().setLinks(true);
				}
				else{
					s.getDirection().setLinks(false);
					return false;
				}
			}
		}
		else if(breite-schiffSize==0){
			if(feldFreiLinks(h�he,breite-schiffSize)){
				for(int i=breite-schiffSize+1;i<=breite;i++){
					if(feldFrei(h�he,i)){
						s.getDirection().setLinks(true);
					}
					else{
						s.getDirection().setLinks(false);
						return false;
					}
				}
			}
			else{
				s.getDirection().setLinks(false);
				return false;
			}
		}
		else{
			s.getDirection().setLinks(false);
			return false;
		}
		return true;
	}
	
	public boolean richtungRechts(int h�he, int breite, Schiffe s){//pr�ft, ob vom Startpunkt nach rechts das Schiff
		int schiffSize=s.getSize()-1;								   //gesetzt werden kann (nicht am Rand)
		if(breite+schiffSize<this.size-1){
			for(int i=breite;i<=breite+schiffSize;i++){
				if(feldFrei(h�he,i)){
					s.getDirection().setRechts(true);
				}
				else{
					s.getDirection().setRechts(false);
					return false;
				}
			}
		}
		else if(breite+schiffSize==this.size-1){
			if(feldFreiRechts(h�he,breite+schiffSize)){
				for(int i=breite;i<breite+schiffSize;i++){
					if(feldFrei(h�he,i)){
						s.getDirection().setRechts(true);
					}
					else{
						s.getDirection().setRechts(false);
						return false;
					}
				}
			}
			else{
				s.getDirection().setRechts(false);
				return false;
			}
		}
		else{
			s.getDirection().setRechts(false);
			return false;
		}
		return true;
	}
	
	public boolean richtungEck1Rechts(int h�he, int breite, Schiffe s){//pr�ft, ob vom Eckpunkt oben-links nach rechts das Schiff
		int schiffSize=s.getSize();								   //gesetzt werden kann linker Rand if-abfrage, wegen ecken
		if(breite+schiffSize<this.size){
			for(int i=breite+1;i<=breite+schiffSize;i++){
				if(feldFreiOben(h�he,i)){
					s.getDirection().setRechts(true);
				}
				else{
					s.getDirection().setRechts(false);
					return false;
				}
			}
		}
		else{
			s.getDirection().setRechts(false);
			return false;
		}
		return true;
	}
	
	public boolean richtungEck1Unten(int h�he, int breite, Schiffe s){//pr�ft, ob vom Eckpunkt oben-links nach unten das Schiff
		int schiffSize=s.getSize();								   //gesetzt werden kann linker Rand if-abfrage, wegen ecken
				if(h�he+schiffSize<this.size-1){
			for(int i=h�he+1;i<=h�he+schiffSize;i++){
				if(feldFreiLinks(i,breite)){
					s.getDirection().setUnten(true);
				}
				else{
					s.getDirection().setUnten(false);
					return false;
				}
			}
		}
		else{
			s.getDirection().setUnten(false);
			return false;
		}
		return true;
	}
	
	public boolean richtungEck2Links(int h�he, int breite, Schiffe s){//pr�ft, ob vom Eckpunkt oben-rechts nach links das Schiff
		int schiffSize=s.getSize();								   //gesetzt werden kann linker Rand if-abfrage, wegen ecken
		if(breite-schiffSize>0){
			for(int i=breite-schiffSize;i<=breite-1;i++){
				if(feldFreiOben(h�he,i)){
					s.getDirection().setLinks(true);
				}
				else{
					s.getDirection().setLinks(false);
					return false;
				}
			}
		}
		else{
			s.getDirection().setLinks(false);
			return false;
		}
		return true;
	}
	
	public boolean richtungEck2Unten(int h�he, int breite, Schiffe s){//pr�ft, ob vom Eckpunkt oben-rechts nach unten das Schiff
		int schiffSize=s.getSize();								   //gesetzt werden kann linker Rand if-abfrage, wegen ecken
				if(h�he+schiffSize<this.size-1){
			for(int i=h�he+1;i<=h�he+schiffSize;i++){
				if(feldFreiRechts(i,breite)){
					s.getDirection().setUnten(true);
				}
				else{
					s.getDirection().setUnten(false);
					return false;
				}
			}
		}
		else{
			s.getDirection().setUnten(false);
			return false;
		}
		return true;
	}
	
	public boolean richtungEck3Links(int h�he, int breite, Schiffe s){//pr�ft, ob vom Eckpunkt unten-rechts nach links das Schiff
		int schiffSize=s.getSize();								   //gesetzt werden kann linker Rand if-abfrage, wegen ecken
		if(breite-schiffSize>0){
			for(int i=breite-schiffSize;i<=breite-1;i++){
				if(feldFreiUnten(h�he,i)){
					s.getDirection().setLinks(true);
				}
				else{
					s.getDirection().setLinks(false);
					return false;
				}
			}
		}
		else{
			s.getDirection().setLinks(false);
			return false;
		}
		return true;
	}
	
	public boolean richtungEck3Oben(int h�he, int breite, Schiffe s){//pr�ft, ob vom Eckpunkt unten-rechts nach oben das Schiff
		int schiffSize=s.getSize();								   //gesetzt werden kann linker Rand if-abfrage, wegen ecken
		if(h�he-schiffSize>0){
			for(int i=h�he-schiffSize;i<=h�he-1;i++){
				if(feldFreiRechts(i,breite)){
					s.getDirection().setOben(true);
				}
				else{
					s.getDirection().setOben(false);
					return false;
				}
			}
		}
		else{
			s.getDirection().setOben(false);
			return false;
		}
		return true;
	}
	
	public boolean richtungEck4Rechts(int h�he, int breite, Schiffe s){//pr�ft, ob vom Eckpunkt unten-links nach rechts das Schiff
		int schiffSize=s.getSize();								   //gesetzt werden kann linker Rand if-abfrage, wegen ecken
		if(breite+schiffSize<this.size-1){
			for(int i=breite+1;i<=breite+schiffSize;i++){
				if(feldFreiUnten(h�he,i)){
					s.getDirection().setRechts(true);
				}
				else{
					s.getDirection().setRechts(false);
					return false;
				}
			}
		}
		else{
			s.getDirection().setRechts(false);
			return false;
		}
		return true;
	}
	
	public boolean richtungEck4Oben(int h�he, int breite, Schiffe s){//pr�ft, ob vom Eckpunkt unten-links nach oben das Schiff
		int schiffSize=s.getSize();								   //gesetzt werden kann linker Rand if-abfrage, wegen ecken
		if(h�he-schiffSize>0){
			for(int i=h�he-schiffSize;i<=h�he-1;i++){
				if(feldFreiLinks(i,breite)){
					s.getDirection().setOben(true);
				}
				else{
					s.getDirection().setOben(false);
					return false;
				}
			}
		}
		else{
			s.getDirection().setOben(false);
			return false;
		}
		return true;
	}
	
	public boolean richtungLinksRechts(int h�he, int breite, Schiffe s){//pr�ft, ob vom Startpunkt nach rechts das Schiff
		int schiffSize=s.getSize();								   //gesetzt werden kann linker Rand if-abfrage, wegen ecken
		if(breite+schiffSize<this.size-1){
			for(int i=breite;i<=breite+schiffSize;i++){
				if(feldFreiLinks(h�he,i)){
					s.getDirection().setRechts(true);
				}
				else{
					s.getDirection().setRechts(false);
					return false;
				}
			}
		}
		else{
			s.getDirection().setRechts(false);
			return false;
		}
		return true;
	}
	
	public boolean richtungLinksOben(int h�he, int breite, Schiffe s){//pr�ft, ob vom Startpunkt nach oben das Schiff
		int schiffSize=s.getSize()-1;								   //gesetzt werden kann linker Rand if-abfrage, wegen ecken
		if(h�he-schiffSize>0){
			for(int i=h�he-schiffSize;i<=h�he;i++){
				if(feldFreiLinks(i,breite)){
					s.getDirection().setOben(true);
				}
				else{
					s.getDirection().setOben(false);
					return false;
				}
			}
		}
		else if(h�he-schiffSize==0){
			if(feldFreiEck1(h�he-schiffSize,breite)){
				for(int i=h�he-schiffSize+1;i<=h�he;i++){
					if(feldFreiLinks(i,breite)){
						s.getDirection().setOben(true);
					}
					else{
						s.getDirection().setOben(false);
						return false;
					}
				}
			}
			else{
				s.getDirection().setOben(false);
				return false;
			}
		}
		else{
			s.getDirection().setOben(false);
			return false;
		}
		return true;
	}
	
	public boolean richtungLinksUnten(int h�he, int breite, Schiffe s){//pr�ft, ob vom Startpunkt nach unten das Schiff
		int schiffSize=s.getSize()-1;								   //gesetzt werden kann linker Rand if-abfrage, wegen ecken
		if(h�he+schiffSize<this.size-1){
			for(int i=h�he;i<=h�he+schiffSize;i++){
				if(feldFreiLinks(i,breite)){
					s.getDirection().setUnten(true);
				}
				else{
					s.getDirection().setUnten(false);
					return false;
				}
			}
		}
		else if(h�he+schiffSize==this.size-1){
			if(feldFreiEck4(h�he+schiffSize,breite)){
				for(int i=h�he;i<h�he+schiffSize;i++){
					if(feldFreiLinks(i,breite)){
						s.getDirection().setUnten(true);
					}
					else{
						s.getDirection().setUnten(false);
						return false;
					}
				}
			}
			else{
				s.getDirection().setUnten(false);
				return false;
			}
		}
		else{
			s.getDirection().setUnten(false);
			return false;
		}
		return true;
	}
	
	public boolean richtungRechtsLinks(int h�he, int breite, Schiffe s){//pr�ft, ob vom Startpunkt nach links das Schiff
		int schiffSize=s.getSize()-1;								  //gesetzt werden kann rechter Rand if-abfrage, wegen ecken
		if(breite-schiffSize>0){
			for(int i=breite-schiffSize;i<=breite;i++){
				if(feldFreiRechts(h�he,i)){
					s.getDirection().setLinks(true);
				}
				else{
					s.getDirection().setLinks(false);
					return false;
				}
			}
		}
		else{
			s.getDirection().setLinks(false);
			return false;
		}
		return true;
	}
	
	public boolean richtungRechtsUnten(int h�he, int breite, Schiffe s){//pr�ft, ob vom Startpunkt nach unten das Schiff
		int schiffSize=s.getSize()-1;								   //gesetzt werden kann rechter Rand if-abfrage, wegen ecken
		if(h�he+schiffSize<this.size-1){
			for(int i=h�he;i<=h�he+schiffSize;i++){
				if(feldFreiRechts(i,breite)){
					s.getDirection().setUnten(true);
				}
				else{
					s.getDirection().setUnten(false);
					return false;
				}
			}
		}
		else if(h�he+schiffSize==this.size-1){
			if(feldFreiEck3(h�he+schiffSize,breite)){
				for(int i=h�he;i<h�he+schiffSize;i++){
					if(feldFreiRechts(i,breite)){
						s.getDirection().setUnten(true);
					}
					else{
						s.getDirection().setUnten(false);
						return false;
					}
				}
			}
			else{
				s.getDirection().setUnten(false);
				return false;
			}
		}
		else{
			s.getDirection().setUnten(false);
			return false;
		}
		return true;
	}
	
	public boolean richtungRechtsOben(int h�he, int breite, Schiffe s){//pr�ft, ob vom Startpunkt nach oben das Schiff
		int schiffSize=s.getSize()-1;								   //gesetzt werden kann rechter Rand if-abfrage, wegen ecken
		if(h�he-schiffSize>0){
			for(int i=h�he-schiffSize;i<=h�he;i++){
				if(feldFreiRechts(i,breite)){
					s.getDirection().setOben(true);
				}
				else{
					s.getDirection().setOben(false);
					return false;
				}
			}
		}
		else if(h�he-schiffSize==0){
			if(feldFreiEck2(h�he-schiffSize,breite)){
				for(int i=h�he-schiffSize+1;i<=h�he;i++){
					if(feldFreiRechts(i,breite)){
						s.getDirection().setOben(true);
					}
					else{
						s.getDirection().setOben(false);
						return false;
					}
				}
			}
			else{
				s.getDirection().setOben(false);
				return false;
			}
		}
		else{
			s.getDirection().setOben(false);
			return false;
		}
		return true;
	}
	
	public boolean richtungObenUnten(int h�he, int breite, Schiffe s){//pr�ft, ob vom Startpunkt nach unten das Schiff
		int schiffSize=s.getSize()-1;								  //gesetzt werden kann oberer Rand if-abfrage, wegen Ecken
		if(h�he+schiffSize<this.size){
			for(int i=h�he;i<=h�he+schiffSize;i++){
				if(feldFreiOben(i,breite)){
					s.getDirection().setUnten(true);
				}
				else{
					s.getDirection().setUnten(false);
					return false;
				}
			}
		}
		else{
			s.getDirection().setUnten(false);
			return false;
		}
		return true;
	}
	
	public boolean richtungObenLinks(int h�he, int breite, Schiffe s){//pr�ft, ob vom Startpunkt nach links das Schiff
		int schiffSize=s.getSize()-1;								  //gesetzt werden kann oberer Rand if-abfrage, wegen Ecken
		if(breite-schiffSize>0){
			for(int i=breite-schiffSize;i<=breite-1;i++){
				if(feldFreiOben(h�he,i)){
					s.getDirection().setLinks(true);
				}
				else{
					s.getDirection().setLinks(false);
					return false;
				}
			}
		}
		else if(breite-schiffSize==0){
			if(feldFreiEck1(h�he,breite-schiffSize)){
				for(int i=breite-schiffSize+1;i<=breite;i++){
					if(feldFreiOben(h�he,i)){
						s.getDirection().setLinks(true);
					}
					else{
						s.getDirection().setLinks(false);
						return false;
					}
				}
			}
			else{
				s.getDirection().setLinks(false);
				return false;
			}
		}
		else{
			s.getDirection().setLinks(false);
			return false;
		}
		return true;
	}
	
	public boolean richtungObenRechts(int h�he, int breite, Schiffe s){//pr�ft, ob vom Startpunkt nach rechts das Schiff
		int schiffSize=s.getSize()-1;								  //gesetzt werden kann oberer Rand if-abfrage, wegen Ecken
		if(breite+schiffSize<this.size-1){
			for(int i=breite;i<=breite+schiffSize;i++){
				if(feldFreiOben(h�he,i)){
					s.getDirection().setRechts(true);
				}
				else{
					s.getDirection().setRechts(false);
					return false;
				}
			}
		}
		else if(breite+schiffSize==this.size-1){
			if(feldFreiEck2(h�he,breite+schiffSize)){
				for(int i=breite;i<breite+schiffSize;i++){
					if(feldFreiOben(h�he,i)){
						s.getDirection().setRechts(true);
					}
					else{
						s.getDirection().setRechts(false);
						return false;
					}
				}
			}
			else{
				s.getDirection().setRechts(false);
				return false;
			}
		}
		else{
			s.getDirection().setRechts(false);
			return false;
		}
		return true;
	}
	
	public boolean richtungUntenLinks(int h�he, int breite, Schiffe s){//pr�ft, ob vom Startpunkt nach links das Schiff
		int schiffSize=s.getSize()-1;								  //gesetzt werden kann unterer Rand if-abfrage, wegen Ecken
		if(breite-schiffSize>0){
			for(int i=breite-schiffSize;i<=breite-1;i++){
				if(feldFreiUnten(h�he,i)){
					s.getDirection().setLinks(true);
				}
				else{
					s.getDirection().setLinks(false);
					return false;
				}
			}
		}
		else if(breite-schiffSize==0){
			if(feldFreiEck4(h�he,breite-schiffSize)){
				for(int i=breite-schiffSize+1;i<=breite;i++){
					if(feldFreiUnten(h�he,i)){
						s.getDirection().setLinks(true);
					}
					else{
						s.getDirection().setLinks(false);
						return false;
					}
				}
			}
			else{
				s.getDirection().setLinks(false);
				return false;
			}
		}
		else{
			s.getDirection().setLinks(false);
			return false;
		}
		return true;
	}
	
	public boolean richtungUntenRechts(int h�he, int breite, Schiffe s){//pr�ft, ob vom Startpunkt nach rechts das Schiff
		int schiffSize=s.getSize()-1;								  //gesetzt werden kann unterer Rand if-abfrage, wegen Ecken
		if(breite+schiffSize<this.size-1){
			for(int i=breite;i<=breite+schiffSize;i++){
				if(feldFreiUnten(h�he,i)){
					s.getDirection().setRechts(true);
				}
				else{
					s.getDirection().setRechts(false);
					return false;
				}
			}
		}
		else if(breite+schiffSize==this.size-1){
			if(feldFreiEck3(h�he,breite+schiffSize)){
				for(int i=breite;i<breite+schiffSize;i++){
					if(feldFreiUnten(h�he,i)){
						s.getDirection().setRechts(true);
					}
					else{
						s.getDirection().setRechts(false);
						return false;
					}
				}
			}
			else{
				s.getDirection().setRechts(false);
				return false;
			}
		}
		else{
			s.getDirection().setRechts(false);
			return false;
		}
		return true;
	}
	
	public boolean richtungUntenOben(int h�he, int breite, Schiffe s){//pr�ft, ob vom Startpunkt nach oben das Schiff
		int schiffSize=s.getSize()-1;								  //gesetzt werden kann unterer Rand if-abfrage, wegen Ecken
		if(h�he-schiffSize>0){
			for(int i=h�he-schiffSize;i<=h�he;i++){
				if(feldFreiUnten(i,breite)){
					s.getDirection().setOben(true);
				}
				else{
					s.getDirection().setOben(false);
					return false;
				}
			}
		}
		else{
			s.getDirection().setOben(false);
			return false;
		}
		return true;
	}
	
	public boolean feldFrei(int h�he,int breite){
		boolean y=true;
		for(int i=h�he-1;i<=h�he+1;i++){  // ob �ber, auf und unter dem Feld Wasser ist
			if(this.getSquare(i, breite).getCounter()!=0){
				y=false;
			}
		} // end for
		for(int k=breite-1;k<=breite+1;k++){ //ob links, auf und rechts von dem Feld Wasser ist
			if(this.getSquare(h�he, k).getCounter()!=0){
				y=false;
			}
		}
		return y;
	}
	
	public boolean feldFreiOben(int h�he,int breite){// obere Reihe Nachbarn pr�fen
		boolean y=true;
		for(int i=h�he;i<=h�he+1;i++){
			if(this.getSquare(i, breite).getCounter()!=0){
				y=false;
			}
		}
		for(int k=breite-1;k<=breite+1;k++){
			if(this.getSquare(h�he, k).getCounter()!=0){
				y=false;
			}
		}
		return y;
	}
	
	public boolean feldFreiUnten(int h�he,int breite){// untere Reihe Nachbarn pr�fen
		boolean y=true;
		for(int i=h�he-1;i<=h�he;i++){
			if(this.getSquare(i, breite).getCounter()!=0){
				y=false;
			}
		}
		for(int k=breite-1;k<=breite+1;k++){
			if(this.getSquare(h�he, k).getCounter()!=0){
				y=false;
			}
		}
		return y;
	}
	
	public boolean feldFreiEck1(int h�he, int breite){
		if((this.getSquare(h�he, breite).getCounter()==0)&&(this.getSquare(h�he+1, breite).getCounter()==0)&&
				(this.getSquare(h�he, breite+1).getCounter()==0)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean feldFreiEck2(int h�he, int breite){
		if((this.getSquare(h�he, breite).getCounter()==0)&&(this.getSquare(h�he+1, breite).getCounter()==0)&&
				(this.getSquare(h�he, breite-1).getCounter()==0)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean feldFreiEck3(int h�he, int breite){
		if((this.getSquare(h�he, breite).getCounter()==0)&&(this.getSquare(h�he-1, breite).getCounter()==0)&&
				(this.getSquare(h�he, breite-1).getCounter()==0)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean feldFreiEck4(int h�he, int breite){
		if((this.getSquare(h�he, breite).getCounter()==0)&&(this.getSquare(h�he-1, breite).getCounter()==0)&&
				(this.getSquare(h�he, breite+1).getCounter()==0)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean feldFreiLinks(int h�he,int breite){// linke Reihe Nachbarn pr�fen
		boolean y=true;
		for(int i=h�he-1;i<=h�he+1;i++){
			if(this.getSquare(i, breite).getCounter()!=0){
				y=false;
			}
		}
		for(int k=breite;k<=breite+1;k++){
			if(this.getSquare(h�he,k).getCounter()!=0){
				y=false;
			}
		}
		return y;
	}
	
	public boolean feldFreiRechts(int h�he,int breite){// rechte Reihe Nachbarn pr�fen
		boolean y=true;
		for(int i=h�he-1;i<=h�he+1;i++){
			if(this.getSquare(i, breite).getCounter()!=0){
				y=false;
			}
		}
		for(int k=breite-1;k<=breite;k++){
			if(this.getSquare(h�he, k).getCounter()!=0){
				y=false;
			}
		}
		return y;
	}
	
	public boolean obenLinks(int h�he, int breite){//pr�ft, ob die Koordinaten zur Ecke oben-links geh�ren
		if(h�he==0 && breite==0){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean obenRechts(int h�he, int breite){//pr�ft, ob die Koordinaten zur Ecke oben-rechts geh�ren
		if(h�he==0 && breite==this.size-1){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean untenRechts(int h�he, int breite){//pr�ft, ob die Koordinaten zur Ecke unten-Rechts geh�ren
		if(h�he==this.size-1 && breite==this.size-1){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean untenLinks(int h�he, int breite){//pr�ft, ob die Koordinaten zur Ecke unten-links geh�ren
		if(h�he==this.size-1 && breite==0){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean oben(int h�he, int breite){
		if(h�he==0){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean unten(int h�he, int breite){
		h�he++;
		if(h�he==size){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean links(int h�he, int breite){
		if(breite==0){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean rechts(int h�he, int breite){
		breite++;
		if(breite==size){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void print(){
		System.out.print("  ");
		for(int k=1;k<=size;k++){
			if(k<10){
				System.out.print(k);
				System.out.print("  ");
			}
			else{
				System.out.print(k);
				System.out.print(" ");
			}
		}
		System.out.println("");
		int z�hler=1;
        for(Square[]array:feld){
        	if(z�hler<10){
        		System.out.print(z�hler);
        		System.out.print("  ");
        		z�hler++;
        	}
        	else{
        		System.out.print(z�hler);
        		System.out.print(" ");
        		z�hler++;
        	}
        	for(Square i:array){
            	switch (i.getCounter()){
                
                	case 0:
                		System.out.print(wasser);
                		System.out.print("  ");
                		break;
                	case 1:
                		System.out.print(schiff);
                		System.out.print("  ");
                		break;
                		
                	case 2:
                		System.out.print(schiffTreffer);
                		System.out.print("  ");
                		break;
                		
                	case 3:
                		System.out.print(wasserTreffer);
                		System.out.print("  ");
                		break;
                		
                	case 4:
                		System.out.print(schiffVersenkt);
                		System.out.print("  ");
                		break;
                		
                	default:
                		System.out.print("Fehler");
                		break;
                }

            }
            System.out.println("");
            System.out.println("");
        }
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }
	
	public void printFeind(){
		System.out.print("  ");
		for(int k=1;k<=size;k++){
			if(k<10){
				System.out.print(k);
				System.out.print("  ");
			}
			else{
				System.out.print(k);
				System.out.print(" ");
			}
		}
		System.out.println("");
		int z�hler=1;
        for(Square[]array:feld){
        	if(z�hler<10){
        		System.out.print(z�hler);
        		System.out.print("  ");
        		z�hler++;
        	}
        	else{
        		System.out.print(z�hler);
        		System.out.print(" ");
        		z�hler++;
        	}
        	for(Square i:array){
            	switch (i.getCounter()){
                
                	case 0:
                		System.out.print(wasser);
                		System.out.print("  ");
                		break;
                	case 1:
                		System.out.print(wasser);
                		System.out.print("  ");
                		break;
                		
                	case 2:
                		System.out.print(schiffTreffer);
                		System.out.print("  ");
                		break;
                		
                	case 3:
                		System.out.print(wasserTreffer);
                		System.out.print("  ");
                		break;
                		
                	case 4:
                		System.out.print(schiffVersenkt);
                		System.out.print("  ");
                		break;
                		
                	default:
                		System.out.print("Fehler");
                		break;
                }

            }
            System.out.println("");
            System.out.println("");
        }
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }
	
	public void printCounter(){
        for(Square[]array:feld){
            for(Square i:array){
                System.out.print(i.getCounter());                
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }
	
	public boolean versenktLinks(int h�he, int breite, Schiffe s){
		boolean versenkt=true;
		int schiffSize=s.getSize();
		for(int i=breite-schiffSize+1;i<=breite;i++){
			if(this.getSquare(h�he, i).getCounter()==1||this.getSquare(h�he, i).getCounter()==0){
				versenkt=false;
				return versenkt;
			}
		}
		s.setVersenkt();
		this.versenktSetzenLinks(h�he, breite, s);
		return versenkt;
	}
	
	public boolean versenktRechts(int h�he, int breite, Schiffe s){
		boolean versenkt=true;
		int schiffSize=s.getSize();
		for(int i=breite;i<=breite+schiffSize-1;i++){
			if(this.getSquare(h�he, i).getCounter()==1||this.getSquare(h�he, i).getCounter()==0){
				versenkt=false;
				return versenkt;
			}
		}
		s.setVersenkt();
		this.versenktSetzenRechts(h�he, breite, s);
		return versenkt;
	}
	
	public boolean versenktOben(int h�he, int breite, Schiffe s){
		boolean versenkt=true;
		int schiffSize=s.getSize();
		for(int i=h�he-schiffSize+1;i<=h�he;i++){
			if(this.getSquare(h�he, i).getCounter()==1||this.getSquare(h�he, i).getCounter()==0){
				versenkt=false;
				return versenkt;
			}
		}
		s.setVersenkt();
		this.versenktSetzenOben(h�he, breite, s);
		return versenkt;
	}
	
	public boolean versenktUnten(int h�he, int breite, Schiffe s){
		boolean versenkt=true;
		int schiffSize=s.getSize();
		for(int i=h�he;i<=h�he+schiffSize-1;i++){
			if(this.getSquare(h�he, i).getCounter()==1||this.getSquare(h�he, i).getCounter()==0){
				versenkt=false;
				return versenkt;
			}
		}
		s.setVersenkt();
		this.versenktSetzenUnten(h�he, breite, s);
		return versenkt;
	}
	
	public void schiffSetzenLinks(int h�he, int breite, Schiffe s){
		int schiffSize=s.getSize();
		for(int i=breite-schiffSize+1;i<=breite;i++){
			this.setSchiff(h�he,i);
		}
	}
	
	public void schiffSetzenRechts(int h�he, int breite, Schiffe s){
		int schiffSize=s.getSize();
		for(int i=breite;i<=breite+schiffSize-1;i++){
			this.setSchiff(h�he,i);
		}
	}
	
	public void schiffSetzenOben(int h�he, int breite, Schiffe s){
		int schiffSize=s.getSize();
		for(int i=h�he-schiffSize+1;i<=h�he;i++){
			this.setSchiff(i,breite);
		}
	}
	
	public void schiffSetzenUnten(int h�he, int breite, Schiffe s){
		int schiffSize=s.getSize();
		for(int i=h�he;i<=h�he+schiffSize-1;i++){
			this.setSchiff(i,breite);
		}
	}
	
	public void versenktSetzenLinks(int h�he, int breite, Schiffe s){
		int schiffSize=s.getSize();
		for(int i=breite-schiffSize+1;i<=breite;i++){
			this.setSchiffVersenkt(h�he,i);
		}
	}
	
	public void versenktSetzenRechts(int h�he, int breite, Schiffe s){
		int schiffSize=s.getSize();
		for(int i=breite;i<=breite+schiffSize-1;i++){
			this.setSchiffVersenkt(h�he,i);
		}
	}
	
	public void versenktSetzenOben(int h�he, int breite, Schiffe s){
		int schiffSize=s.getSize();
		for(int i=h�he-schiffSize+1;i<=h�he;i++){
			this.setSchiffVersenkt(i,breite);
		}
	}
	
	public void versenktSetzenUnten(int h�he, int breite, Schiffe s){
		int schiffSize=s.getSize();
		for(int i=h�he;i<=h�he+schiffSize-1;i++){
			this.setSchiffVersenkt(i,breite);
		}
	}
	
	public boolean schussKoordinaten(int h, int b){
		if(h<0||b<0||h>=size||b>=size){
			return false;
		}
		else{
			return true;
		}
	}
	
	public boolean schie�en(int h, int b, int a, Schiffe s){
		//unfertig
		boolean treffer=false;
		int gr=s.getSize()-2;
		if(gr==0){
			gr++;
		}
			if(a==1){
				if(gr==1){
					if(this.getSquare(h, b).getCounter()==1){
						treffer=true;
						this.setSchiffTreffer(h,b);
					}
				}
				else if(gr==2){
					if(h>=(size-1)/2){
						for(int i=h;i>h-2;i--){
							if(this.getSquare(i, b).getCounter()==1){
								treffer=true;
								this.setSchiffTreffer(i, b);
							}
							else if(this.getSquare(i, b).getCounter()==0){
								this.setWasserTreffer(i, b);
							}
						}
					}
					else{
						for(int i=h;i<h+2;i++){
							if(this.getSquare(i, b).getCounter()==1){
								treffer=true;
								this.setSchiffTreffer(i, b);
							}
							else if(this.getSquare(i, b).getCounter()==0){
								this.setWasserTreffer(i, b);
							}
						}
					}
				}
				else{
					if(h>=(size-1)/2){
						for(int i=h;i>h-3;i--){
							if(this.getSquare(i, b).getCounter()==1){
								treffer=true;
								this.setSchiffTreffer(i, b);
							}
							else if(this.getSquare(i, b).getCounter()==0){
								this.setWasserTreffer(i, b);
							}
						}
					}
					else{
						for(int i=h;i<h+3;i++){
							if(this.getSquare(i, b).getCounter()==1){
								treffer=true;
								this.setSchiffTreffer(i, b);
							}
							else if(this.getSquare(i, b).getCounter()==0){
								this.setWasserTreffer(i, b);
							}
						}
					}
				}
			}
			if(a==2){
				if(gr==1){
					if(this.getSquare(h, b).getCounter()==1){
						treffer=true;
						this.setSchiffTreffer(h,b);
					}
				}
				else if(gr==2){
					if(b>=(size-1)/2){
						for(int i=b;i>b-2;i--){
							if(this.getSquare(h, i).getCounter()==1){
								treffer=true;
								this.setSchiffTreffer(h, i);
							}
							else if(this.getSquare(h, i).getCounter()==0){
								this.setWasserTreffer(h, i);
							}
						}
					}
					else{
						for(int i=b;i<b+2;i++){
							if(this.getSquare(h, i).getCounter()==1){
								treffer=true;
								this.setSchiffTreffer(h, i);
							}
							else if(this.getSquare(h, i).getCounter()==0){
								this.setWasserTreffer(h, i);
							}
						}
					}
				}
				else{
					if(b>=(size-1)/2){
						for(int i=b;i>b-3;i--){
							if(this.getSquare(h, i).getCounter()==1){
								treffer=true;
								this.setSchiffTreffer(h, i);
							}
							else if(this.getSquare(h, i).getCounter()==0){
								this.setWasserTreffer(h, i);
							}
						}
					}
					else{
						for(int i=b;i<b+3;i++){
							if(this.getSquare(h, i).getCounter()==1){
								treffer=true;
								this.setSchiffTreffer(h, i);
							}
							else if(this.getSquare(h, i).getCounter()==0){
								this.setWasserTreffer(h, i);
							}
						}
					}
				}
			}
			return treffer;
		
	}
	
    public static void main(String[]args){
        //System.out.println("Wie gross soll das Feld sein?");
        //int a=io.readline();
       
        Spielfeld_alt game =new Spielfeld_alt(10);
        game.print();
    }
}
