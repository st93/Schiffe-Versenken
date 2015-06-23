import java.util.*;
public class Abfragen {
	private static Scanner sc=new Scanner(System.in);
	
	public static int frageOrt(){
		int i=sc.nextInt();
		return i-1;
	}
	public static int frageInt(){
		int i=sc.nextInt();
		return i;
	}
	
	public static int randomInt(int min, int max) {

	    Random random = new Random();
	    int randomNum = random.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	public static int randomIntArround(int x){
		int y=randomInt(x-1,x+1);
		while(y==x){
			y=randomInt(x-1,x+1);
		}
		
		return y;
	}
}
