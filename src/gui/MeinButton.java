package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MeinButton extends JButton{
	private int zeile;
	private int spalte;
	boolean isClicked;
	public MeinButton(int spalte,int zeile){
		super();
		//this.setSize(10, 10);
		this.zeile=zeile;
		this.spalte=spalte;
		this.isClicked=false;
		
		
	}
	
	public boolean getIsClicked(){
		return isClicked;
	}
	
	public void setIsClicked(boolean isClicked){
		this.isClicked=isClicked;
	}
	
	public int getZeile(){
		return zeile;
	}
	
	public int getSpalte(){
		return spalte;
	}
	
}
