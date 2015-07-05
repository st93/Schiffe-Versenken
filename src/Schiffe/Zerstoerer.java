package Schiffe;


import SpielLogik.*;

public class Zerstoerer extends Schiffe {
	private int index;
	public final int typ;
	public final String name;
	private int size;
	private Direction richtung;
	protected int regZeit;
	public final int schaden;
	private boolean versenkt;
	
	public Zerstoerer(int index){
		this.index=index;
		this.typ=4;
		this.name="Zerst�rer";
		this.regZeit=0;
		this.schaden=3;
		this.size=5;
		this.versenkt=false;
		this.richtung=new Direction();
	}
	
	public int getSize(){
		return this.size;
	}
	
	public void setReg(){
		this.regZeit=3;
	}
	
	public int getIndex(){
		return index;
	}
	
	public int getSchaden(){
		return this.schaden;
	}
	
	public int getTyp(){
		return this.typ;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getReg(){
		return this.regZeit;
	}
	
	public boolean schussBereit(){
		return(versenkt==false&&regZeit==0);
	}
	
	public void updateReg(){
		if (this.regZeit>0){
			this.regZeit--;
		}
	}
	
}