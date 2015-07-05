package SpielLogik;
import java.io.Serializable;


public class Square implements Serializable {
	
	private int counter=0;
	
	public Square(){
		
	}
	
	public Square(int counter){
		if(counter<5 && counter>=0){
			this.counter=counter;
		}
		else{
			System.out.println("keine g�ltige Eingabe");
		}
	}
	
	public int getCounter(){
		return this.counter;
	}
	
	public void setCounter(int newCounter){
		if(newCounter<5 && counter>=0){
			this.counter=newCounter;
		}
		else{
			System.out.println("keine g�ltige Eingabe");
		}
	}
	
	
	
}
