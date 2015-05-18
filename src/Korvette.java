
public class Korvette extends Schiffe {
	private int index;
	private int size=3;
	private int regZeit=0;
	private boolean versenkt= false;
	
	public Korvette(int index){
		this.index=index;
	}
	
	public int getSize(){
		return this.size;
	}
	
	public int getId(){
		return index;
	}
	
	public void setReg(){
		this.regZeit=1;
	}
}
