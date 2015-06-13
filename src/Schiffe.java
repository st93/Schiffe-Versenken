import java.util.Scanner;


public class Schiffe {
	private int size;
	public final String name;
	private int height;
	private int width;
	private int index;
	public int schaden;
	public final int typ;
	private Direction richtung;
	protected int regZeit;
	private boolean versenkt;
	
	public Schiffe(){
		this.richtung=new Direction();
		this.versenkt=false;
		this.typ=0;
		this.name="Schiff";
	}
	
	public void setHeight(int h){
		this.height=h;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	public int getTyp(){
		return this.typ;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getSchaden(){
		return this.schaden;
	}
	
	public void setWidth(int w){
		this.width=w;
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getSize(){
		return this.size;
	}
	
	public int getIndex(){
		return index;
	}
	
	public int getReg(){
		return this.regZeit;
	}
	
	public void setReg(){
		this.regZeit=2;
	}
	
	/*
	 * Methode, um die Regenerationszeit um eins zu verringern
	 */
	public void updateReg(){
		if (this.regZeit>0){
			this.regZeit--;
		}
	}
	
	public boolean schussBereit(){
		return(versenkt==false&&regZeit==0);
	}
	
	public Direction getDirection(){
		return this.richtung;
	}
	
	public void setLinks(boolean x){
		richtung.setLinks(x);
	}
	
	public void setOben(boolean x){
		richtung.setOben(x);
	}
	
	public void setUnten(boolean x){
		richtung.setUnten(x);
	}
	
	public void setRechts(boolean x){
		richtung.setRechts(x);
	}
	
	public void setVersenkt(){
		this.versenkt=true;
	}
	
	public boolean getVersenkt(){
		return versenkt;
	}
}
