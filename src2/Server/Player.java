package Server;

import java.io.Serializable;

public class Player implements Serializable{
		
	private int lifePoints;
	private int victoryPoints;
	private String playerName;
	private boolean inTokyo;
	
	public Player(String playerName){
		this.lifePoints = 10;
		this.victoryPoints = 0;
		this.playerName = playerName;
		this.inTokyo = false;
	}
	
	public String getName(){
		return this.playerName;
	}
	
	public void setlifePoints(int i){
		this.lifePoints = i;
	}
	
	public int getLifePoints(){
		return this.lifePoints;
	}
	public void setvictoryPoints(int i){
		this.victoryPoints = i;
	}
	public void heal(int i){
		if (this.lifePoints >= 10){
			this.lifePoints +=i;
		}else{
			System.out.println("Maximum an Herzen erreicht.");
		}
	}
	public void getDamage(int i){
		this.lifePoints -= i;
		if(this.lifePoints <= 0 ){
			System.out.println(this.playerName+" hat verloren.");
		}else{
			//do nothing
		}
	}
	
	public void getVictoryPoints(int i){
		this.victoryPoints += i;
		if(this.victoryPoints >= 20){
			System.out.println(this.playerName+" hat gewonnen.");
		}else{
			//do nothing
		}
	}
	public void goToTokyo(){
		this.inTokyo = true;
	}
	public void leftTokyo(){
		this.inTokyo = false;
	}
	
	public String toString(){
		return "Player: "+this.playerName+" LifePoints: "+this.lifePoints+" VictoryPoints: "+this.victoryPoints+" inTokyo: "+this.inTokyo;
	}

}
