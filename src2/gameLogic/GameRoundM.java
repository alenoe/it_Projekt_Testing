package gameLogic;

import java.util.ArrayList;

import interfaces.Observer;
import interfaces.Subject;
import gameLogic.PlayerInGameM;

public class GameRoundM implements Observer{
	
	public static final int MAX_PLAYERS = 2; // Wenn man mehr als 2 Spieler will, wäre es nützlich hier "MAX_PLAYERS = players.size()" 
	// zu machen. Der Server braucht sowieso eine Liste um die Clients/Spieler anzusprechen, die mit dem Spielbrett verbunden sind. 
	// Wenn ein Spieler rausfliegt, kann man ihn dann einfach von dieser Liste löschen und die ArrayList-Size nochmal einlesen.
	// - Kommentar von Raphael
	
	private Subject topic;
	private int plays;
	private ArrayList<PlayerInGameM> players = new ArrayList<PlayerInGameM>();
	private int roundStart;
	private int roundEnd;
	private DiceM dice;
	
	public GameRoundM(ArrayList<PlayerInGameM> players, DiceM dice){
		this.plays = 0;
		this.players = players;
		this.roundStart = players.get(0).getPlayerNumber();
		this.roundEnd = players.get(MAX_PLAYERS - 1).getPlayerNumber();
		this.dice = dice;
		
		for(PlayerInGameM p : players){
			boolean endOfPlay = false;
			this.addPlay();
			
		}
	}
	
	public boolean isFinished(){
		return true;
	}
	
	public int getRoundLength(){
		return players.size();
	}

	public void addPlay(){
			int currentPlayer = roundStart + plays;
			PlayM nextPlay = new PlayM(currentPlayer, players, dice);
			this.plays = plays ++;
	}
	
	//__________________________________________________________________
	//Observerimplementation
	//copied from: http://www.journaldev.com/1739/observer-design-pattern-in-java-example-tutorial
	//More Info: http://stackoverflow.com/questions/26640028/java-observe-many-objects-of-same-class
	
	@Override
	public void update(Subject o, Object arg) {
				
	}

	@Override
	public void setSubject(Subject sub) {
		this.players = (ArrayList<PlayerInGameM>) sub;
		
	}
	
	//__________________________________________________________________
	
}
