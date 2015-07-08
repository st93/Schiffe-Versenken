package Allgemeines;
import java.io.*;
import java.util.Scanner;

import SpielLogik.Spieler;

public class Speichern{
	private static File f= new File("test.txt");
	
	public static Spieler[] laden() throws ClassNotFoundException{
		ObjectInputStream is;
		Spieler[] s = null;
		try {
			is = new ObjectInputStream(new FileInputStream(f));
			s=(Spieler[]) is.readObject();
			is.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	
	public static void speichern(Spieler[] spielerArray){
		System.out.println("Möchten Sie speichern?");
		System.out.println("Ja:'1' Nein: '2'");
		Scanner sc=new Scanner(System.in);
		int string=sc.nextInt();
		
		if(string==1){
			try {
				ObjectOutputStream os=new ObjectOutputStream(new FileOutputStream(f));
				os.writeObject(spielerArray);
				os.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		
		
	}
}
