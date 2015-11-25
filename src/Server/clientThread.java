package Server;

import gameLogic.PlayerInGameM;

import java.io.IOException;
import java.io.NotSerializableException;
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
	private UserM userM;
	
	
	
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
		
		
      while(!this.socket.isClosed()){
      try {
		m = (HashMap<String, Object>) serverInputStream.readObject();
		String k = (new ArrayList<String>(m.keySet())).get(0);
		
		
		
		switch (k){
		case "Username":
			
			userName = (String) m.get("Username");
				UserM userM = new UserM(userName);
				Server.addUserMToList(userM);
				
				PlayerInGameM plg1 = new PlayerInGameM(userM.getUsername(), 0, 0);
				System.out.println(plg1);
				
				this.sendMsg("PlayerInGameM1", plg1);
				
				if(id == 1){
					this.sendMsg("User1", userM);
				}else{
					Server.broadcastToAll("User2", userM);
					Server.broadcastToOne(1, "User1", Server.getuserMList().get(0));
				}
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
	    System.out.println(hMap);
	    
	    try{
	    	serverOutputStream.writeObject(hMap);
	    serverOutputStream.flush();
	    } catch(NotSerializableException e){
	    	System.out.println("kann nicht Serialisiert werden");
	    }
	    
	}
}
