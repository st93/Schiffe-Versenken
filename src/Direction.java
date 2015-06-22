import java.io.Serializable;


public class Direction implements Serializable {
	private boolean links=false;
	private boolean rechts=false;
	private boolean oben=false;
	private boolean unten=false;
	
	public Direction(){
	}
	
	public void setLinks(boolean x){
		this.links=x;
	}
	
	public boolean getLinks(){
		return this.links;
	}
	
	
	public void setRechts(boolean x){
		this.rechts=x;
	}
	
	public boolean getRechts(){
		return this.rechts;
	}
	
	
	public void setOben(boolean x){
		this.oben=x;
	}
	
	public boolean getOben(){
		return this.oben;
	}
	
	
	public void setUnten(boolean x){
		this.unten=x;
	}
	
	public boolean getUnten(){
		return this.unten;
	}
	
	public void clean(){
		this.links=false;
		this.rechts=false;
		this.oben=false;
		this.unten=false;
	}
	
}
