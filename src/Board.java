
public class Board {
	private int size;
	
	private Square[][] feld;
	private char wasser='~';
	private char schiff='S';
	private char schiffTreffer='T';
	private char wasserTreffer='W';
	private char schiffVersenkt='V';
	private int höhe;
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
		this.höhe=h;
		this.breite=b;
		if(oben(höhe,breite)){
			if(obenLinks(höhe,breite)){
				if(!feldFreiEck1(höhe,breite)){
					return false;
				}
				if(richtungEck1Rechts(höhe,breite,s) || richtungEck1Unten(höhe,breite,s)){
					if(richtungEck1Rechts(höhe,breite,s)){
						
					}
					if(richtungEck1Unten(höhe,breite,s)){
						
					}
					return true;
				}
				else{
					return false;
				}
			}
			else if(obenRechts(höhe,breite)){
				if(!feldFreiEck2(höhe,breite)){
					return false;
				}
				if(richtungEck2Links(höhe,breite,s) || richtungEck2Unten(höhe,breite,s)){
					if(richtungEck2Links(höhe,breite,s)){
						
					}
					if(richtungEck2Unten(höhe,breite,s)){
						
					}
					return true;
				}
				else{
					return false;
				}
			}
			else{
				if(!feldFreiOben(höhe,breite)){
					return false;
				}
				if(richtungObenLinks(höhe,breite,s) || richtungObenRechts(höhe,breite,s) || richtungObenUnten(höhe,breite,s)){
					if(richtungObenRechts(höhe,breite,s)){
						
					}
					if(richtungObenUnten(höhe,breite,s)){
						
					}
					if(richtungObenLinks(höhe,breite,s)){
						
					}
					return true;
				}
				else{
					return false;
				}
			}
		}
		else if(unten(höhe,breite)){
			if(untenLinks(höhe,breite)){
				if(!feldFreiEck4(höhe,breite)){
					return false;
				}
				if(richtungEck4Rechts(höhe,breite,s) || richtungEck4Oben(höhe,breite,s)){
					if(richtungEck4Rechts(höhe,breite,s)){

					}
					if(richtungEck4Oben(höhe,breite,s)){
						
					}
					return true;
				}
				else{
					return false;
				}
			}
			else if(untenRechts(höhe,breite)){
				if(!feldFreiEck3(höhe,breite)){
					return false;
				}
				if(richtungEck3Links(höhe,breite,s) || richtungEck3Oben(höhe,breite,s)){
					if(richtungEck3Links(höhe,breite,s)){
						
					}
					if(richtungEck3Oben(höhe,breite,s)){
						
					}
					return true;
				}
				else{
					return false;
				}
			}
			else{
				if(!feldFreiUnten(höhe,breite)){
					return false;
				}
				if(richtungUntenLinks(höhe,breite,s) || richtungUntenRechts(höhe,breite,s) || richtungUntenOben(höhe,breite,s)){
					if(richtungUntenRechts(höhe,breite,s)){
						
					}
					if(richtungUntenLinks(höhe,breite,s)){
						
					}
					if(richtungUntenOben(höhe,breite,s)){
						
					}
					return true;
				}
				else{
					return false;
				}
			}
		}
		else if(links(höhe,breite)){
			if(untenLinks(höhe,breite)){
				if(!feldFreiEck3(höhe,breite)){
					return false;
				}
				if(richtungEck4Rechts(höhe,breite,s) || richtungEck4Oben(höhe,breite,s)){
					if(richtungEck4Rechts(höhe,breite,s)){
						
					}
					if(richtungEck4Oben(höhe,breite,s)){
						
					}
					return true;
				}
				else{
					return false;
				}
			}
			else if(obenLinks(höhe,breite)){
				if(!feldFreiEck1(höhe,breite)){
					return false;
				}
				if(richtungEck1Rechts(höhe,breite,s) || richtungEck1Unten(höhe,breite,s)){
					if(richtungEck1Rechts(höhe,breite,s)){
						
					}
					if(richtungEck1Unten(höhe,breite,s)){
						
					}
					return true;
				}
				else{
					return false;
				}
			}
			else{
				if(!feldFreiLinks(höhe,breite)){
					return false;
				}
				if(richtungLinksOben(höhe,breite,s) || richtungLinksRechts(höhe,breite,s) || richtungLinksUnten(höhe,breite,s)){
					if(richtungLinksUnten(höhe,breite,s)){
						
					}
					if(richtungLinksRechts(höhe,breite,s)){
						
					}
					if(richtungLinksUnten(höhe,breite,s)){
						
					}
					return true;
				}
				else{
					return false;
				}
			}
			
		}
		else if(rechts(höhe,breite)){
			if(obenRechts(höhe,breite)){
				if(!feldFreiEck2(höhe,breite)){
					return false;
				}
				if(richtungEck2Links(höhe,breite,s) || richtungEck2Unten(höhe,breite,s)){
					if(richtungEck2Links(höhe,breite,s)){
						
					}
					if(richtungEck2Unten(höhe,breite,s)){
						
					}
					return true;
				}
				else{
					return false;
				}
			}
			else if(untenRechts(höhe,breite)){
				if(!feldFreiEck4(höhe,breite)){
					return false;
				}
				if(richtungEck3Links(höhe,breite,s) || richtungEck3Oben(höhe,breite,s)){
					if(richtungEck3Links(höhe,breite,s)){
						
					}
					if(richtungEck3Oben(höhe,breite,s)){
						
					}
					return true;
				}
				else{
					return false;
				}
			}
			else{
				if(!feldFreiRechts(höhe,breite)){
					return false;
				}
				if(richtungRechtsLinks(höhe,breite,s) || richtungRechtsOben(höhe,breite,s) || richtungRechtsUnten(höhe,breite,s)){
					if(richtungRechtsLinks(höhe,breite,s)){
						
					}
					if(richtungRechtsOben(höhe,breite,s)){
						
					}
					if(richtungRechtsUnten(höhe,breite,s)){
						
					}
					return true;
				}
				else{
					return false;
				}
			}
			
		}
		else{
			if(!feldFrei(höhe,breite)){
				return false;
			}
			if(richtungOben(höhe, breite, s) || richtungUnten(höhe, breite, s) || 
				richtungLinks(höhe, breite, s) || richtungRechts(höhe, breite, s)){
				if(richtungOben(höhe, breite, s)){
					
				}
				if(richtungUnten(höhe, breite, s)){
					
				}
				if(richtungLinks(höhe, breite, s)){
	
				}
				if(richtungRechts(höhe, breite, s)){
					
				}
				return true;
			}
			else{
				return false;
			}
		}
	}
	
	public boolean richtungOben(int höhe, int breite, Schiffe s){ //prüft, ob vom Startpunkt nach oben das Schiff 
		int schiffSize=s.getSize()-1;								  //gesetzt werden kann (nicht am Rand)
		if(höhe-schiffSize>0){	
			for(int i=höhe-schiffSize;i<=höhe;i++){
				if(feldFrei(i,breite)){
					s.getDirection().setOben(true);
				}
				else{
					s.getDirection().setOben(false);
					return false;
				}
			}
		}
		else if(höhe-schiffSize==0){
			if(feldFreiOben(höhe-schiffSize,breite)){
				for(int i=höhe-schiffSize+1;i<=höhe;i++){
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
	
	public boolean richtungUnten(int höhe, int breite, Schiffe s){//prüft, ob vom Startpunkt nach unten das Schiff
		int schiffSize=s.getSize()-1;								  //gesetzt werden kann  (nicht am Rand)
		if(höhe+schiffSize<this.size-1){
			for(int i=höhe;i<=höhe+schiffSize;i++){
				if(feldFrei(i,breite)){
					s.getDirection().setUnten(true);
				}
				else{
					s.getDirection().setUnten(false);
					return false;
				}
			}
		}
		else if(höhe+schiffSize==this.size-1){
			if(feldFreiUnten(höhe+schiffSize,breite)){
				for(int i=höhe;i<höhe+schiffSize;i++){
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
	
	public boolean richtungLinks(int höhe, int breite, Schiffe s){//prüft, ob vom Startpunkt nach links das Schiff
		int schiffSize=s.getSize()-1;								  //gesetzt werden kann  (nicht am Rand)
		if(breite-schiffSize>0){	
			for(int i=breite-schiffSize;i<=breite;i++){
				if(feldFrei(höhe,i)){
					s.getDirection().setLinks(true);
				}
				else{
					s.getDirection().setLinks(false);
					return false;
				}
			}
		}
		else if(breite-schiffSize==0){
			if(feldFreiLinks(höhe,breite-schiffSize)){
				for(int i=breite-schiffSize+1;i<=breite;i++){
					if(feldFrei(höhe,i)){
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
	
	public boolean richtungRechts(int höhe, int breite, Schiffe s){//prüft, ob vom Startpunkt nach rechts das Schiff
		int schiffSize=s.getSize()-1;								   //gesetzt werden kann (nicht am Rand)
		if(breite+schiffSize<this.size-1){
			for(int i=breite;i<=breite+schiffSize;i++){
				if(feldFrei(höhe,i)){
					s.getDirection().setRechts(true);
				}
				else{
					s.getDirection().setRechts(false);
					return false;
				}
			}
		}
		else if(breite+schiffSize==this.size-1){
			if(feldFreiRechts(höhe,breite+schiffSize)){
				for(int i=breite;i<breite+schiffSize;i++){
					if(feldFrei(höhe,i)){
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
	
	public boolean richtungEck1Rechts(int höhe, int breite, Schiffe s){//prüft, ob vom Eckpunkt oben-links nach rechts das Schiff
		int schiffSize=s.getSize();								   //gesetzt werden kann linker Rand if-abfrage, wegen ecken
		if(breite+schiffSize<this.size){
			for(int i=breite+1;i<=breite+schiffSize;i++){
				if(feldFreiOben(höhe,i)){
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
	
	public boolean richtungEck1Unten(int höhe, int breite, Schiffe s){//prüft, ob vom Eckpunkt oben-links nach unten das Schiff
		int schiffSize=s.getSize();								   //gesetzt werden kann linker Rand if-abfrage, wegen ecken
				if(höhe+schiffSize<this.size-1){
			for(int i=höhe+1;i<=höhe+schiffSize;i++){
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
	
	public boolean richtungEck2Links(int höhe, int breite, Schiffe s){//prüft, ob vom Eckpunkt oben-rechts nach links das Schiff
		int schiffSize=s.getSize();								   //gesetzt werden kann linker Rand if-abfrage, wegen ecken
		if(breite-schiffSize>0){
			for(int i=breite-schiffSize;i<=breite-1;i++){
				if(feldFreiOben(höhe,i)){
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
	
	public boolean richtungEck2Unten(int höhe, int breite, Schiffe s){//prüft, ob vom Eckpunkt oben-rechts nach unten das Schiff
		int schiffSize=s.getSize();								   //gesetzt werden kann linker Rand if-abfrage, wegen ecken
				if(höhe+schiffSize<this.size-1){
			for(int i=höhe+1;i<=höhe+schiffSize;i++){
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
	
	public boolean richtungEck3Links(int höhe, int breite, Schiffe s){//prüft, ob vom Eckpunkt unten-rechts nach links das Schiff
		int schiffSize=s.getSize();								   //gesetzt werden kann linker Rand if-abfrage, wegen ecken
		if(breite-schiffSize>0){
			for(int i=breite-schiffSize;i<=breite-1;i++){
				if(feldFreiUnten(höhe,i)){
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
	
	public boolean richtungEck3Oben(int höhe, int breite, Schiffe s){//prüft, ob vom Eckpunkt unten-rechts nach oben das Schiff
		int schiffSize=s.getSize();								   //gesetzt werden kann linker Rand if-abfrage, wegen ecken
		if(höhe-schiffSize>0){
			for(int i=höhe-schiffSize;i<=höhe-1;i++){
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
	
	public boolean richtungEck4Rechts(int höhe, int breite, Schiffe s){//prüft, ob vom Eckpunkt unten-links nach rechts das Schiff
		int schiffSize=s.getSize();								   //gesetzt werden kann linker Rand if-abfrage, wegen ecken
		if(breite+schiffSize<this.size-1){
			for(int i=breite+1;i<=breite+schiffSize;i++){
				if(feldFreiUnten(höhe,i)){
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
	
	public boolean richtungEck4Oben(int höhe, int breite, Schiffe s){//prüft, ob vom Eckpunkt unten-links nach oben das Schiff
		int schiffSize=s.getSize();								   //gesetzt werden kann linker Rand if-abfrage, wegen ecken
		if(höhe-schiffSize>0){
			for(int i=höhe-schiffSize;i<=höhe-1;i++){
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
	
	public boolean richtungLinksRechts(int höhe, int breite, Schiffe s){//prüft, ob vom Startpunkt nach rechts das Schiff
		int schiffSize=s.getSize();								   //gesetzt werden kann linker Rand if-abfrage, wegen ecken
		if(breite+schiffSize<this.size-1){
			for(int i=breite;i<=breite+schiffSize;i++){
				if(feldFreiLinks(höhe,i)){
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
	
	public boolean richtungLinksOben(int höhe, int breite, Schiffe s){//prüft, ob vom Startpunkt nach oben das Schiff
		int schiffSize=s.getSize()-1;								   //gesetzt werden kann linker Rand if-abfrage, wegen ecken
		if(höhe-schiffSize>0){
			for(int i=höhe-schiffSize;i<=höhe;i++){
				if(feldFreiLinks(i,breite)){
					s.getDirection().setOben(true);
				}
				else{
					s.getDirection().setOben(false);
					return false;
				}
			}
		}
		else if(höhe-schiffSize==0){
			if(feldFreiEck1(höhe-schiffSize,breite)){
				for(int i=höhe-schiffSize+1;i<=höhe;i++){
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
	
	public boolean richtungLinksUnten(int höhe, int breite, Schiffe s){//prüft, ob vom Startpunkt nach unten das Schiff
		int schiffSize=s.getSize()-1;								   //gesetzt werden kann linker Rand if-abfrage, wegen ecken
		if(höhe+schiffSize<this.size-1){
			for(int i=höhe;i<=höhe+schiffSize;i++){
				if(feldFreiLinks(i,breite)){
					s.getDirection().setUnten(true);
				}
				else{
					s.getDirection().setUnten(false);
					return false;
				}
			}
		}
		else if(höhe+schiffSize==this.size-1){
			if(feldFreiEck4(höhe+schiffSize,breite)){
				for(int i=höhe;i<höhe+schiffSize;i++){
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
	
	public boolean richtungRechtsLinks(int höhe, int breite, Schiffe s){//prüft, ob vom Startpunkt nach links das Schiff
		int schiffSize=s.getSize()-1;								  //gesetzt werden kann rechter Rand if-abfrage, wegen ecken
		if(breite-schiffSize>0){
			for(int i=breite-schiffSize;i<=breite;i++){
				if(feldFreiRechts(höhe,i)){
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
	
	public boolean richtungRechtsUnten(int höhe, int breite, Schiffe s){//prüft, ob vom Startpunkt nach unten das Schiff
		int schiffSize=s.getSize()-1;								   //gesetzt werden kann rechter Rand if-abfrage, wegen ecken
		if(höhe+schiffSize<this.size-1){
			for(int i=höhe;i<=höhe+schiffSize;i++){
				if(feldFreiRechts(i,breite)){
					s.getDirection().setUnten(true);
				}
				else{
					s.getDirection().setUnten(false);
					return false;
				}
			}
		}
		else if(höhe+schiffSize==this.size-1){
			if(feldFreiEck3(höhe+schiffSize,breite)){
				for(int i=höhe;i<höhe+schiffSize;i++){
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
	
	public boolean richtungRechtsOben(int höhe, int breite, Schiffe s){//prüft, ob vom Startpunkt nach oben das Schiff
		int schiffSize=s.getSize()-1;								   //gesetzt werden kann rechter Rand if-abfrage, wegen ecken
		if(höhe-schiffSize>0){
			for(int i=höhe-schiffSize;i<=höhe;i++){
				if(feldFreiRechts(i,breite)){
					s.getDirection().setOben(true);
				}
				else{
					s.getDirection().setOben(false);
					return false;
				}
			}
		}
		else if(höhe-schiffSize==0){
			if(feldFreiEck2(höhe-schiffSize,breite)){
				for(int i=höhe-schiffSize+1;i<=höhe;i++){
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
	
	public boolean richtungObenUnten(int höhe, int breite, Schiffe s){//prüft, ob vom Startpunkt nach unten das Schiff
		int schiffSize=s.getSize()-1;								  //gesetzt werden kann oberer Rand if-abfrage, wegen Ecken
		if(höhe+schiffSize<this.size){
			for(int i=höhe;i<=höhe+schiffSize;i++){
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
	
	public boolean richtungObenLinks(int höhe, int breite, Schiffe s){//prüft, ob vom Startpunkt nach links das Schiff
		int schiffSize=s.getSize()-1;								  //gesetzt werden kann oberer Rand if-abfrage, wegen Ecken
		if(breite-schiffSize>0){
			for(int i=breite-schiffSize;i<=breite-1;i++){
				if(feldFreiOben(höhe,i)){
					s.getDirection().setLinks(true);
				}
				else{
					s.getDirection().setLinks(false);
					return false;
				}
			}
		}
		else if(breite-schiffSize==0){
			if(feldFreiEck1(höhe,breite-schiffSize)){
				for(int i=breite-schiffSize+1;i<=breite;i++){
					if(feldFreiOben(höhe,i)){
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
	
	public boolean richtungObenRechts(int höhe, int breite, Schiffe s){//prüft, ob vom Startpunkt nach rechts das Schiff
		int schiffSize=s.getSize()-1;								  //gesetzt werden kann oberer Rand if-abfrage, wegen Ecken
		if(breite+schiffSize<this.size-1){
			for(int i=breite;i<=breite+schiffSize;i++){
				if(feldFreiOben(höhe,i)){
					s.getDirection().setRechts(true);
				}
				else{
					s.getDirection().setRechts(false);
					return false;
				}
			}
		}
		else if(breite+schiffSize==this.size-1){
			if(feldFreiEck2(höhe,breite+schiffSize)){
				for(int i=breite;i<breite+schiffSize;i++){
					if(feldFreiOben(höhe,i)){
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
	
	public boolean richtungUntenLinks(int höhe, int breite, Schiffe s){//prüft, ob vom Startpunkt nach links das Schiff
		int schiffSize=s.getSize()-1;								  //gesetzt werden kann unterer Rand if-abfrage, wegen Ecken
		if(breite-schiffSize>0){
			for(int i=breite-schiffSize;i<=breite-1;i++){
				if(feldFreiUnten(höhe,i)){
					s.getDirection().setLinks(true);
				}
				else{
					s.getDirection().setLinks(false);
					return false;
				}
			}
		}
		else if(breite-schiffSize==0){
			if(feldFreiEck4(höhe,breite-schiffSize)){
				for(int i=breite-schiffSize+1;i<=breite;i++){
					if(feldFreiUnten(höhe,i)){
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
	
	public boolean richtungUntenRechts(int höhe, int breite, Schiffe s){//prüft, ob vom Startpunkt nach rechts das Schiff
		int schiffSize=s.getSize()-1;								  //gesetzt werden kann unterer Rand if-abfrage, wegen Ecken
		if(breite+schiffSize<this.size-1){
			for(int i=breite;i<=breite+schiffSize;i++){
				if(feldFreiUnten(höhe,i)){
					s.getDirection().setRechts(true);
				}
				else{
					s.getDirection().setRechts(false);
					return false;
				}
			}
		}
		else if(breite+schiffSize==this.size-1){
			if(feldFreiEck3(höhe,breite+schiffSize)){
				for(int i=breite;i<breite+schiffSize;i++){
					if(feldFreiUnten(höhe,i)){
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
	
	public boolean richtungUntenOben(int höhe, int breite, Schiffe s){//prüft, ob vom Startpunkt nach oben das Schiff
		int schiffSize=s.getSize()-1;								  //gesetzt werden kann unterer Rand if-abfrage, wegen Ecken
		if(höhe-schiffSize>0){
			for(int i=höhe-schiffSize;i<=höhe;i++){
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
	
	public boolean feldFrei(int höhe,int breite){
		boolean y=true;
		for(int i=höhe-1;i<=höhe+1;i++){  // ob über, auf und unter dem Feld Wasser ist
			if(this.getSquare(i, breite).getCounter()!=0){
				y=false;
			}
		} // end for
		for(int k=breite-1;k<=breite+1;k++){ //ob links, auf und rechts von dem Feld Wasser ist
			if(this.getSquare(höhe, k).getCounter()!=0){
				y=false;
			}
		}
		return y;
	}
	
	public boolean feldFreiOben(int höhe,int breite){// obere Reihe Nachbarn prüfen
		boolean y=true;
		for(int i=höhe;i<=höhe+1;i++){
			if(this.getSquare(i, breite).getCounter()!=0){
				y=false;
			}
		}
		for(int k=breite-1;k<=breite+1;k++){
			if(this.getSquare(höhe, k).getCounter()!=0){
				y=false;
			}
		}
		return y;
	}
	
	public boolean feldFreiUnten(int höhe,int breite){// untere Reihe Nachbarn prüfen
		boolean y=true;
		for(int i=höhe-1;i<=höhe;i++){
			if(this.getSquare(i, breite).getCounter()!=0){
				y=false;
			}
		}
		for(int k=breite-1;k<=breite+1;k++){
			if(this.getSquare(höhe, k).getCounter()!=0){
				y=false;
			}
		}
		return y;
	}
	
	public boolean feldFreiEck1(int höhe, int breite){
		if((this.getSquare(höhe, breite).getCounter()==0)&&(this.getSquare(höhe+1, breite).getCounter()==0)&&
				(this.getSquare(höhe, breite+1).getCounter()==0)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean feldFreiEck2(int höhe, int breite){
		if((this.getSquare(höhe, breite).getCounter()==0)&&(this.getSquare(höhe+1, breite).getCounter()==0)&&
				(this.getSquare(höhe, breite-1).getCounter()==0)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean feldFreiEck3(int höhe, int breite){
		if((this.getSquare(höhe, breite).getCounter()==0)&&(this.getSquare(höhe-1, breite).getCounter()==0)&&
				(this.getSquare(höhe, breite-1).getCounter()==0)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean feldFreiEck4(int höhe, int breite){
		if((this.getSquare(höhe, breite).getCounter()==0)&&(this.getSquare(höhe-1, breite).getCounter()==0)&&
				(this.getSquare(höhe, breite+1).getCounter()==0)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean feldFreiLinks(int höhe,int breite){// linke Reihe Nachbarn prüfen
		boolean y=true;
		for(int i=höhe-1;i<=höhe+1;i++){
			if(this.getSquare(i, breite).getCounter()!=0){
				y=false;
			}
		}
		for(int k=breite;k<=breite+1;k++){
			if(this.getSquare(höhe,k).getCounter()!=0){
				y=false;
			}
		}
		return y;
	}
	
	public boolean feldFreiRechts(int höhe,int breite){// rechte Reihe Nachbarn prüfen
		boolean y=true;
		for(int i=höhe-1;i<=höhe+1;i++){
			if(this.getSquare(i, breite).getCounter()!=0){
				y=false;
			}
		}
		for(int k=breite-1;k<=breite;k++){
			if(this.getSquare(höhe, k).getCounter()!=0){
				y=false;
			}
		}
		return y;
	}
	
	public boolean obenLinks(int höhe, int breite){//prüft, ob die Koordinaten zur Ecke oben-links gehören
		if(höhe==0 && breite==0){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean obenRechts(int höhe, int breite){//prüft, ob die Koordinaten zur Ecke oben-rechts gehören
		if(höhe==0 && breite==this.size-1){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean untenRechts(int höhe, int breite){//prüft, ob die Koordinaten zur Ecke unten-Rechts gehören
		if(höhe==this.size-1 && breite==this.size-1){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean untenLinks(int höhe, int breite){//prüft, ob die Koordinaten zur Ecke unten-links gehören
		if(höhe==this.size-1 && breite==0){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean oben(int höhe, int breite){
		if(höhe==0){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean unten(int höhe, int breite){
		höhe++;
		if(höhe==size){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean links(int höhe, int breite){
		if(breite==0){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean rechts(int höhe, int breite){
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
		int zähler=1;
        for(Square[]array:feld){
        	if(zähler<10){
        		System.out.print(zähler);
        		System.out.print("  ");
        		zähler++;
        	}
        	else{
        		System.out.print(zähler);
        		System.out.print(" ");
        		zähler++;
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
		int zähler=1;
        for(Square[]array:feld){
        	if(zähler<10){
        		System.out.print(zähler);
        		System.out.print("  ");
        		zähler++;
        	}
        	else{
        		System.out.print(zähler);
        		System.out.print(" ");
        		zähler++;
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
	
	public boolean versenktLinks(int höhe, int breite, Schiffe s){
		boolean versenkt=true;
		int schiffSize=s.getSize();
		for(int i=breite-schiffSize+1;i<=breite;i++){
			if(this.getSquare(höhe, i).getCounter()==1||this.getSquare(höhe, i).getCounter()==0){
				versenkt=false;
				return versenkt;
			}
		}
		s.setVersenkt();
		this.versenktSetzenLinks(höhe, breite, s);
		return versenkt;
	}
	
	public boolean versenktRechts(int höhe, int breite, Schiffe s){
		boolean versenkt=true;
		int schiffSize=s.getSize();
		for(int i=breite;i<=breite+schiffSize-1;i++){
			if(this.getSquare(höhe, i).getCounter()==1||this.getSquare(höhe, i).getCounter()==0){
				versenkt=false;
				return versenkt;
			}
		}
		s.setVersenkt();
		this.versenktSetzenRechts(höhe, breite, s);
		return versenkt;
	}
	
	public boolean versenktOben(int höhe, int breite, Schiffe s){
		boolean versenkt=true;
		int schiffSize=s.getSize();
		for(int i=höhe-schiffSize+1;i<=höhe;i++){
			if(this.getSquare(höhe, i).getCounter()==1||this.getSquare(höhe, i).getCounter()==0){
				versenkt=false;
				return versenkt;
			}
		}
		s.setVersenkt();
		this.versenktSetzenOben(höhe, breite, s);
		return versenkt;
	}
	
	public boolean versenktUnten(int höhe, int breite, Schiffe s){
		boolean versenkt=true;
		int schiffSize=s.getSize();
		for(int i=höhe;i<=höhe+schiffSize-1;i++){
			if(this.getSquare(höhe, i).getCounter()==1||this.getSquare(höhe, i).getCounter()==0){
				versenkt=false;
				return versenkt;
			}
		}
		s.setVersenkt();
		this.versenktSetzenUnten(höhe, breite, s);
		return versenkt;
	}
	
	public void schiffSetzenLinks(int höhe, int breite, Schiffe s){
		int schiffSize=s.getSize();
		for(int i=breite-schiffSize+1;i<=breite;i++){
			this.setSchiff(höhe,i);
		}
	}
	
	public void schiffSetzenRechts(int höhe, int breite, Schiffe s){
		int schiffSize=s.getSize();
		for(int i=breite;i<=breite+schiffSize-1;i++){
			this.setSchiff(höhe,i);
		}
	}
	
	public void schiffSetzenOben(int höhe, int breite, Schiffe s){
		int schiffSize=s.getSize();
		for(int i=höhe-schiffSize+1;i<=höhe;i++){
			this.setSchiff(i,breite);
		}
	}
	
	public void schiffSetzenUnten(int höhe, int breite, Schiffe s){
		int schiffSize=s.getSize();
		for(int i=höhe;i<=höhe+schiffSize-1;i++){
			this.setSchiff(i,breite);
		}
	}
	
	public void versenktSetzenLinks(int höhe, int breite, Schiffe s){
		int schiffSize=s.getSize();
		for(int i=breite-schiffSize+1;i<=breite;i++){
			this.setSchiffVersenkt(höhe,i);
		}
	}
	
	public void versenktSetzenRechts(int höhe, int breite, Schiffe s){
		int schiffSize=s.getSize();
		for(int i=breite;i<=breite+schiffSize-1;i++){
			this.setSchiffVersenkt(höhe,i);
		}
	}
	
	public void versenktSetzenOben(int höhe, int breite, Schiffe s){
		int schiffSize=s.getSize();
		for(int i=höhe-schiffSize+1;i<=höhe;i++){
			this.setSchiffVersenkt(i,breite);
		}
	}
	
	public void versenktSetzenUnten(int höhe, int breite, Schiffe s){
		int schiffSize=s.getSize();
		for(int i=höhe;i<=höhe+schiffSize-1;i++){
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
	
	public boolean schießen(int h, int b, int a, Schiffe s){
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
