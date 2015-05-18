import java.util.Scanner;


public class Schiffe {
	private int size=1;
	private int index=0;
	private int height;
	private int width;
	private Direction richtung= new Direction();
	private int regZeit=0;
	private boolean versenkt= false;
	
	public void setHeight(int h){
		this.height=h;
	}
	
	public int getHeight(){
		return this.height;
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
	
	public int getId(){
		return index;
	}
	
	public int getReg(){
		return regZeit;
	}
	
	public void setReg(){
		this.regZeit=1;
	}
	
	public void updateReg(){
		if (regZeit>0){
			this.regZeit--;
		}
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
