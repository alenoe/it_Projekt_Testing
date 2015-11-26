package gameLogic;

import java.io.Serializable;
import java.util.TreeMap;

import gameLogic.DieM;

public class DiceM implements Serializable{
	
	public TreeMap<Integer, DieM> diceMap = new TreeMap<Integer, DieM>();
	
	public DiceM(int numberOfDice){
		for(int i = 0; i < numberOfDice; i++){
			this.diceMap.put(i, new DieM(i));
		}
	}

	//W�rfelt alle W�rfel deren Variabel lock nicht auf true gesetzt ist.
	
	public void rollDice(){
		for (int i = 0; i < this.diceMap.size(); i++){
			DieM tempDie = this.diceMap.get(i);
			if(tempDie.getLock() == false){
				tempDie.roll();
				this.diceMap.put(i, tempDie);
			}	
		}
	}
	
	public int getID(int id){
		return diceMap.get(id).getID();
	}
	
	//gibt das W�rfel Objekt mit der �bergebenen ID zur�ck.
	
	public DieM getDie(int id){
		return diceMap.get(id);
	}
	
	//setzt den Wert der locked Variable, des W�rfels mit der angegebenen ID auf true.
	
	public void setLock(int id){
		try{
			this.getDie(id).setLock();			
		} catch (IndexOutOfBoundsException e){
		}
	}
	
	//gibt den Wert des W�rfels mit der angegebenen id zur�ck.
	
	public int getDieValue(int id){
		return diceMap.get(id).getValue();
	}
	
	//gibt das W�rfelzeichen zur�ck: !!! muss auf das Board Model ausgelagert werden !!!
	
	public String getDieFacePicture(int id){
		return diceMap.get(id).getFacePicture();
	}
	
	//Die Anzahl eines bestimmten Wertes aus dem Dice Objekt auslesen.	
	
	public int getNumberOfDiceWithValue(int value){
		int counter = 0;
		for(int i = 0; i < diceMap.size(); i++){
			if(this.getDieValue(i) == value){
				counter++;
			} 	
		} return counter;
	}
	
	public int getSize(){
		return this.diceMap.size();
	}
	
/*	public static void main(String[] args) {
		
		DiceM testDice = new DiceM(6);
		for(int i = 0; i < 6; i++){
			System.out.println("ID: " + testDice.getID(i) + " Wert: " + testDice.getDieValue(i) + " " + testDice.getDieFacePicture(i));
		}
		testDice.setLock(1);
		testDice.setLock(5);

		testDice.rollDice();
		System.out.println(" ");
		for(int i = 0; i < 6; i++){
			System.out.println("ID: " + testDice.getID(i) + " Wert: " + testDice.getDieValue(i) + " " + testDice.getDieFacePicture(i));
		}
		System.out.println("Anzahl Einsen: " + testDice.getNumberOfDiceWithValue(1));
		System.out.println("Anzahl Zweien: " + testDice.getNumberOfDiceWithValue(2));
		System.out.println("Anzahl Dreien: " + testDice.getNumberOfDiceWithValue(3));
		System.out.println("Anzahl Tatzen: " + testDice.getNumberOfDiceWithValue(4));
		System.out.println("Anzahl Herzen: " + testDice.getNumberOfDiceWithValue(5));
	}*/
}
