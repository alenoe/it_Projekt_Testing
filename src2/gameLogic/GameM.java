package gameLogic;

import java.util.ArrayList;

import interfaces.Observer;
import interfaces.Subject;

public class GameM implements Observer{
	
	public static final int MAX_VP = 20;
	public static final int MAX_HP = 10;
	
	//Klassenvariablen
	private DiceM dice = new DiceM(6);
	private boolean gameState;
	private ArrayList<PlayerInGameM> players = new ArrayList<PlayerInGameM>();
	private int rounds;
	private boolean endOfGame = false;
	
	public GameM(PlayerInGameM player1, PlayerInGameM player2){
		this.gameState = true;
		this.players.add(player1);
		this.players.add(player2);
		this.rounds = 0;
		// Hier könnte man noch eine Variable setzen anhand der dann das GUI erkennt: 
		// "AH! Ich muss Spieler-Feld 1 (Ape_green_king.png, Label, Button und co.) und 2 (Ape_violet_king.png und co.) laden!"
		// vllt. ein "if (player.size() == 2) { GuiFuer2Player.FXML }"
		// Idee von: http://stackoverflow.com/questions/11563298/how-to-change-sub-fxml-gui-parts-at-runtime-with-button-click
		// Man kann bei den Injections auch direkt Methoden, if-Abfragen und co. machen. Während des Initializable und während das Programm bereits läuft
		// - Kommentar von R
		
		while(endOfGame = false){
			this.addRound();
		}
	}
	
	public GameM(PlayerInGameM player1, PlayerInGameM player2, PlayerInGameM player3){
		this.gameState = true;
		this.players.add(player1);
		this.players.add(player2);
		this.players.add(player3);
		this.rounds = 0;
		// Hier könnte man noch eine Variable setzen anhand der dann das GUI erkennt: "AH! Ich muss Spieler-Feld 1, 2 und 3 laden!"
		// vllt. ein "if (player.size() == 3) { GuiFuer3Player.FXML }"
		// - Kommentar von R
	}
	
	public GameM(PlayerInGameM player1, PlayerInGameM player2, PlayerInGameM player3, PlayerInGameM player4){
		this.gameState = true;
		this.players.add(player1);
		this.players.add(player2);
		this.players.add(player3);
		this.players.add(player4);
		this.rounds = 0;
		// Hier könnte man noch eine Variable setzen anhand der dann das GUI erkennt: "AH! Ich muss Spieler-Feld 1, 2, 3 und 4 laden!"
		// vllt. ein "if (player.size() == 4) { GuiFuer4Player.FXML }"
		// - Kommentar von R
	}
	
//	public PlayerInGameM getCurrentPlayer(){
//		return 
//	}
	
	public void addRound(){
		if(endOfGame = false){
			this.rounds = rounds++;
			GameRoundM nextRound = new GameRoundM(players, dice);
		} else {
			endOfGame = true;
		}		
	}

	@Override
	public void update(Subject o, Object arg) {
		for(PlayerInGameM p : players){
			PlayerInGameM pNew = (PlayerInGameM) p.getUpdate(this);
			p.setVictoryPoints(pNew.getVictoryPoints());
			p.setHealthPoints(pNew.getHealthPoints());
			p.setPositionTokyo();
	}
	
	}

	@Override
	public void setSubject(Subject sub) {
	this.players = (ArrayList<PlayerInGameM>) sub;
	
	}

}
