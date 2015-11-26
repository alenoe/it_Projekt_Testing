package Server;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;




public class Server {

    private String personName = null;
    private int port;
    private String ip;
    private ServerSocket socketConnection;
    private Socket pipe;
    private static int client_id = 0;
    private final Logger logger = Logger.getLogger("");
   
    static ArrayList<clientThread> clientList =  new ArrayList<clientThread> ();
    static ArrayList<Player> playerList = new ArrayList<Player>();
    
   public Server(String ip, int port){
	   this.ip = ip;
	   this.port = port;
	   startServer();
   }

   private void startServer(){

	   	int socketCounter = 0;
	   try {

	         socketConnection = new ServerSocket(port);

	         System.out.println(port +": Server wartet auf Clients...");
	         logger.info("Port: " + port + " : Server wartet auf Clients...");
	         while(!socketConnection.isClosed()){
	        	 if(socketCounter < 2){
	        		 pipe = socketConnection.accept();
	        
//	        		 client_id++;
		        	 System.out.println(client_id + ". Client hinzugefügt");
		        	 logger.info(client_id + ". Client hinzugefügt " + pipe.toString());
		        	 clientThread ct = new clientThread(client_id, pipe);
		        	 clientList.add(ct);
		        	 ct.start();
		        	 client_id++;
		        	 socketCounter++;
	        	 }else{
	        		 logger.info("Port: "+port+" Spiel kann losgehen.");
	        		 break;
		        	
	        	 }
	        	 
	         }    
	      }

	   catch(Exception e) {
   	 System.out.println(e);
   	 logger.info("Port "+port+ " ist schon besetzt oder ist fehlerhaft. Bitte einen anderen gültigen/offenen Port wählen.");
  }
 }   
      
   public static void broadcastToAll(String type, Object o) {
	   for(clientThread ct: clientList) {
		   try {
			ct.sendMsg(type,o);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
   }
   
   public static void braodcastToOne(int i, String type, Object o){
	   try {
		clientList.get(i).sendMsg(type, o);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
   public static ArrayList<Player> getPlayerList(){
	   return playerList;
   }
   public static void addToPlayerList(Player p){
	   playerList.add(p);
   }
}

