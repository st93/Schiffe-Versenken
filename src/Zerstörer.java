
public class Zerst�rer extends Schiffe {
	private int index;
	private int size=5;
	private Direction richtung= new Direction();
	private int regZeit;
	private boolean versenkt= false;
	
	public Zerst�rer(int index){
		this.index=index;
		this.regZeit=0;
	}
	
	public int getSize(){
		return this.size;
	}
	
	public void setReg(){
		this.regZeit=3;
	}
	
	public int getId(){
		return index;
	}
	
	public void schie�en(int b, int h){
		
	}
	
	public int getReg(){
		return this.regZeit;
	}
	
}