package Client;

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
	private String userName;
	
	private ObjectOutputStream clientOutputStream;
	private ObjectInputStream clientInputStream;
	private Thread  t;
	
	
	public Client(String port, String ip, String userName){
		this.port = Integer.parseInt(port);
		this.iP = ip;
		this.userName = userName;
		
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
	
	
	public void setPersonName(String personName){
		this.userName = userName;
	}
	public String getPersonName(){
		return this.userName;
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
		
      while(!this.client.isClosed()){
      try {
    	
		m = (HashMap<String, Object>) clientInputStream.readObject();
		String k = (new ArrayList<String>(m.keySet())).get(0);
		
		switch (k){
		case "Username":
			um = (UserM) m.get("Username");
			System.out.println(um);
			
			
			break;
			
		case "Person":
			p = (Person) m.get("Person");
			System.out.println(p);
			
			
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

	public void sendNameToServer() {
		try {
			
			HashMap<String, String> m = new HashMap<String, String>();
			m.put("Username", userName);
			clientOutputStream.writeObject(m);
			m.clear();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
	
}