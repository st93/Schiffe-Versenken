package Schiffe;

import SpielLogik.*;

public class Korvette extends Schiffe {
	private int index;
	public final int typ;
	public final String name;
	private int size;
	protected int regZeit;
	public final int schaden;
	private boolean versenkt;
	private Direction richtung;
	
	public Korvette(int index){
		this.size=3;
		this.typ=2;
		this.index=index;
		this.name="Korvette";
		this.regZeit=0;
		this.schaden=1;
		this.versenkt=false;
		this.richtung=new Direction();
		
	}
	
	public int getSize(){
		return this.size;
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
	
	public void setReg(){
		this.regZeit=1;
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
