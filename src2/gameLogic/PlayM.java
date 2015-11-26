package gameLogic;

import java.util.ArrayList;

public class PlayM {
	
	public static final int MAX_VP=20;
	public static final int MAX_HP=10;
	
	private int playNumber;
	private boolean playEnd;
	private PlayerInGameM player;
	private ArrayList<PlayerInGameM> playerList;
	private DiceM dice;
	
	public PlayM(int plays, ArrayList<PlayerInGameM> playerList, DiceM dice){
		this.playNumber = 0;
		this.playEnd = false;
		this.player = playerList.get(plays);
		this.playerList = playerList;
		this.dice = dice;
	}
	
	public int getVictoryPoints(){
		int victoryPoints = 0;
		for(int i = 0; i <= 3; i++){
			int numberOfDiceWithValueI = dice.getNumberOfDiceWithValue(i);
			switch(numberOfDiceWithValueI){
			case 3:
				victoryPoints = victoryPoints + i;
				break;
			case 4:
				victoryPoints = victoryPoints + i + 1;
				break;
			case 5:
				victoryPoints = victoryPoints + i + 2;
				break;
			case 6:
				victoryPoints = victoryPoints + i + 3;
				break;
			}			
		} return victoryPoints;		
	}
	
	public int getHeal(){
		int heal = dice.getNumberOfDiceWithValue(4);			
		return heal;	
	}
	
	public int getDamage(){
		int damage = dice.getNumberOfDiceWithValue(5);
		return damage;
	}
	
	public void setPlayEnd(){
		this.playNumber = 3;
	}
	
	public void isFinished(){
		for(PlayerInGameM p : playerList){
		//Der Schaden wird auf den Gegnern zugefügt.
			if(p != player){
				p.setDamage(getDamage());
			}
		}
		//Verteilung der Herzeinheiten als Lebenspunkte für den Spieler.
			player.getHealed(getHeal());

		//Wenn der Spieler in Tokyo ist, erhält er für diese Runde seine zwei Bonuspunkte.
		if(player.getPosition() == true){
			player.setVictoryPoints(getVictoryPoints() + 2);
		//Falls nicht erhält er seine gewürfelten Punkte.	
		} else {
			player.setVictoryPoints(getVictoryPoints());
		}
		//Beendet den Spielzug
		this.playEnd = true;
	}
	
	public boolean getPlayEnd(){
		return playEnd;
	}
}
