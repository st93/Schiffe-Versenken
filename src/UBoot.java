
public class UBoot extends Schiffe {
	//private boolean status;
	//private boolean schussFrei;
	private int index;
	private int size=2;
	private int regZeit=0;
	private boolean versenkt= false;
	
	public UBoot(int index){
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