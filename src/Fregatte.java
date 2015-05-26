
public class Fregatte extends Schiffe {
	private int index;
	private int size=4;
	private int regZeit;
	private boolean versenkt= false;
	
	public Fregatte(int index){
		this.index=index;
		this.regZeit=0;
	}
	
	public int getSize(){
		return this.size;
	}
	
	public int getId(){
		return index;
	}
	
	public void setReg(){
		this.regZeit=2;
	}
	
	public int getReg(){
		return this.regZeit;
	}
}
