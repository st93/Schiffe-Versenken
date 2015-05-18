//import java.io.DataInputStream;;
public class Spielfeld_alt {
	public int laenge;
	public int breite;
	public char[][] feld;
	public char wasser='~';
	public char schiff='\u25A6';
	public char schiffTreffer='\u2B1B';
	public char wasserTreffer='\u2A02';
	
	public Spielfeld_alt (int groesse){
		this.laenge=groesse;
		this.breite=groesse;
		this.feld= new char[groesse][groesse];
		for(int i=0;i<feld.length;i++){
            for(int k=0;k<feld[0].length;k++){
                setWasser(i,k);
            }
        }
		
		
		
	}
	
	public void setWasser(int height, int width){
		this.feld[height][width]=wasser;
	}
	
	public void setWasserTreffer(int height, int width){
		this.feld[height][width]=wasserTreffer;
	}
	
	public void setSchiffTreffer(int height, int width){
		this.feld[height][width]=schiffTreffer;
	}
	
	public void setSchiff(int height, int width){
		this.feld[height][width]=schiff;
	}
	
	public void print(){
        for(char[]array:feld){
            for(char i:array){
                System.out.print(i);
                System.out.print(" ");
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }
	
    public static void main(String[]args){
        //System.out.println("Wie gross soll das Feld sein?");
        //int a=io.readline();
       
        Spielfeld_alt game =new Spielfeld_alt(10);
        game.print();
    }
	
}
