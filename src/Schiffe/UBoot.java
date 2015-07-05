package Schiffe;


import SpielLogik.*;

public class UBoot extends Schiffe {
	//private boolean status;
	//private boolean schussFrei;
	private int index;
	public final int typ;
	private int size;
	public final String name;
	protected int regZeit;
	public final int schaden;
	private boolean versenkt;
	private Direction richtung;
	
	public UBoot(int index){
		this.index=index;
		this.typ=1;
		this.name="Uboot";
		this.size=2;
		this.schaden=1;
		this.regZeit=0;
		this.richtung=new Direction();
		this.versenkt=false;
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
	
	public void setReg(){
		this.regZeit=1;
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