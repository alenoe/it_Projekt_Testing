package Server;



import gameLogic.DiceM;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;




public class clientThread extends Thread{
	
	private Socket socket;
	private ObjectInputStream serverInputStream;
	private ObjectOutputStream serverOutputStream;
	private int id;

	
	public clientThread(int id, Socket socket) throws IOException{
		this.socket = socket;
		this.id = id;
		
		serverInputStream = new    
		ObjectInputStream(socket.getInputStream());
		
		serverOutputStream = new 
		ObjectOutputStream(socket.getOutputStream());

	}
	
	public void run(){
		try{
			listen();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public void listen()throws IOException {
		HashMap<String, Object> m = null;
		String userName = null;
		Player player = null;
		
      while(!this.socket.isClosed()){
      try {
		m = (HashMap<String, Object>) serverInputStream.readObject();
		String k = (new ArrayList<String>(m.keySet())).get(0);
		
		
		switch (k){
		case "Username":
			userName = (String) m.get("Username");
			player = new Player(userName);
			Server.addToPlayerList(player);
			
			if(this.id == 0){
				this.sendMsg("Player1", Server.getPlayerList().get(0));
				
			}else{
				Server.braodcastToOne(1, "Player1", Server.getPlayerList().get(0));
				Server.broadcastToAll("Player2", Server.getPlayerList().get(1));
			}
			
			break;
			
		case "Spieler1amZug":
			String spielzug = (String) m.get("Spieler1amZug");
			Server.braodcastToOne(0, "Spieler1würfeln", false);
			Server.braodcastToOne(1, "Spieler1würfeln", true);
			
			break;
			
		case "Spieler2amZug":
			String spielzug2 = (String) m.get("Spieler2amZug");
			Server.braodcastToOne(0, "Spieler2würfeln", true);
			Server.braodcastToOne(1, "Spieler2würfeln", false);
			
			break;
			
		case "Roll1":
			 DiceM d1 = new DiceM(6);
			 d1.rollDice();
			 Server.broadcastToAll("Server_hat_gewürfelt", d1);
			
			break;
			
		}
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
 }
	
	public void sendMsg(String type, Object o) throws IOException {

	    HashMap<String, Object> hMap = new  HashMap<String, Object>();
	    hMap.put(type, o);
	    
	    serverOutputStream.writeObject(hMap);
	    serverOutputStream.flush();
	}
}
