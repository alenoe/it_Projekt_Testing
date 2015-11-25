package Server;

import gameLogic.PlayerInGameM;

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
		
		
      while(!this.socket.isClosed()){
      try {
		m = (HashMap<String, Object>) serverInputStream.readObject();
		String k = (new ArrayList<String>(m.keySet())).get(0);
		
		
		switch (k){
		case "Username":
			synchronized (m){
				userName = (String) m.get("Username");
				UserM userM = new UserM(userName);
				System.out.println(userM);
				userM.setUsername("Arschloch");
				System.out.println(userM);
				m.put("Username", userM);
				serverOutputStream.writeObject(m);
				serverOutputStream.flush();    	
			}
			
		    
		    
		    
		    Person p = new Person("Simon", 33);
		    HashMap<String, Person> m2 = new  HashMap<String, Person>();
		    m2.put("Person", p);
		    
		    synchronized (serverOutputStream){
		    	serverOutputStream.writeObject(m2);
			    serverOutputStream.flush();	
		    }
			
			break;
		}
		
        
       
        
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      

//          serverOutputStream.writeObject(m);
//          serverOutputStream.flush();
      }
	}
}
