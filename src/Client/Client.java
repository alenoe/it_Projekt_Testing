package Client;

import gameLogic.PlayerInGameM;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import Server.Person;
import Server.UserM;

public class Client implements Runnable{
	
	private String iP;
	private Integer port;
	private Socket client;
	
	
	private static ObjectOutputStream clientOutputStream;
	private ObjectInputStream clientInputStream;
	private Thread  t;
	
	
	public Client(String port, String ip){
		this.port = Integer.parseInt(port);
		this.iP = ip;
		
		
		try {
            client = new Socket(this.iP, this.port);
            System.out.println("Netzwerkverbindung konnte hergestellt werden");
            
            clientOutputStream = new ObjectOutputStream(client.getOutputStream());
    
		} catch(Exception e) {
            System.out.println("Netzwerkverbindung konnte nicht hergestellt werden");
            e.printStackTrace();
    }
		t = new Thread(this);
		t.start();
		
	}
	
	
	
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			clientInputStream = new ObjectInputStream(client.getInputStream());
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}  
		HashMap<String, Object> m = null;
		Person p = null;
		UserM um = null;
		PlayerInGameM pIG = null;
		
      while(!this.client.isClosed()){
      try {
    	
		m = (HashMap<String, Object>) clientInputStream.readObject();
		String k = (new ArrayList<String>(m.keySet())).get(0);
		
		switch (k){
		case "User1":
			um = (UserM) m.get("User1");
			System.out.println(um);
			
			
			break;
			
		case "User2":
			um = (UserM) m.get("User2");
			System.out.println(um);
			
			
			break;	
		
		case "PlayerInGameM1":
			pIG = (PlayerInGameM) m.get("PlayerInGameM1");
			System.out.println(pIG);
			
			break;
		
		
		
			
		
			
			
			
		}
		
        
//        clientInputStream.writeObject(m);
//        clientInputStream.flush();
        
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch(IOException e){
		try {
			clientInputStream.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
     	 
//          serverOutputStream.writeObject(m);
//          serverOutputStream.flush();
      }
			
}


	public static void sendMsg(String type, Object o) throws IOException {

	    HashMap<String, Object> hMap = new  HashMap<String, Object>();
	    hMap.put(type, o);
	    
	    clientOutputStream.writeObject(hMap);
	    clientOutputStream.flush();
	}
	
}