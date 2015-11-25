package Message;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.HashMap;
import Server.UserM;
import gameLogic.DiceM;
import gameLogic.PlayerInGameM;
import gameLogic.p;

public class Message implements Serializable{
	
	private final Object contents;
	private final String type;
	
	public Message(final Object contents, String type){
		this.contents = contents;
		this.type = type;
	}
	
	public <T> T getContents(Class<T> clazz){
		return (T) contents;
	}
	
	public String getType(){
		return type;
	}
	
	public PlayerInGameM readPlayerInGameM(Message msg){
    	PlayerInGameM player = msg.getContents(PlayerInGameM.class);
    	return player;
    }
	
	static evaluateMessage(Message msg){
		String type = msg.getType();
		switch (type){
		case "UserM":
			return "UserM";
			break;
		case "DiceM":
			return "DiceM";
			break;
		case "PlayerInGameM":
			PlayerInGameM p = new PlayerInGameM(readPlayerInGame(msg);
			break;
		}
	}

}
