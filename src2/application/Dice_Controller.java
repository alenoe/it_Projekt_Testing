package application;



import gameLogic.DiceM;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import Server.Player;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;


public class Dice_Controller implements Initializable, Runnable{
	
	@FXML
	TextField tfUsername;
	@FXML
	Label lUsername1;
	@FXML
	Label lUsername2;
	@FXML
	TextField tfIP;
	@FXML
	TextField tfPort;
	@FXML
	Button btnConnect;
	@FXML
	Label lMessage;
	
	@FXML
	BorderPane borderPane;
	@FXML
	Button btnRoll;
	@FXML
	Button btnDice1;
	@FXML
	Button btnDice2;
	@FXML
	Button btnDice3;
	@FXML
	Button btnDice4;
	@FXML
	Button btnDice5;
	@FXML
	Button btnDice6;
	
	private Socket client;
	private static ObjectOutputStream clientOutputStream;
	private ObjectInputStream clientInputStream;
	
	private Player player1 = new Player("");
	private Player player2 = new Player("");
	
	
	private DiceM d1 = new DiceM(6);
	
	private ArrayList<Button> DicebtnList = new ArrayList<Button>();
	
	
//	private boolean pressed1 = false;
//	private boolean pressed2 = false;
//	private boolean pressed3 = false;
//	private boolean pressed4 = false;
//	private boolean pressed5 = false;
//	private boolean pressed6 = false;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		 
		
		borderPane.setStyle("-fx-background-color: #FFFFFF");
		
		tfUsername.setPromptText("Username");
		tfPort.setPromptText("Portnummer");
		tfPort.setText("14000");
		tfIP.setPromptText("IPAdresse");
		tfIP.setText("localhost");
		
		
		
				try {
		            client = new Socket(tfIP.getText(), Integer.parseInt(tfPort.getText()));
		            System.out.println("Netzwerkverbindung konnte hergestellt werden");
		            clientOutputStream = new ObjectOutputStream(client.getOutputStream());
		          
				} catch(Exception e1) {
		            System.out.println("Netzwerkverbindung konnte nicht hergestellt werden");
		            e1.printStackTrace();    
		    }
				Thread t = new Thread(this);
				t.start();
				
				btnConnect.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						try {
							sendMsg("Username", tfUsername.getText());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
					}
				});
					
	
		
		
		
        
		
		
		
		DicebtnList.add(btnDice1);
		DicebtnList.add(btnDice2);
		DicebtnList.add(btnDice3);
		DicebtnList.add(btnDice4);
		DicebtnList.add(btnDice5);
		DicebtnList.add(btnDice6);
		
		
		
		for (Button btn: DicebtnList){
			btn.setDisable(true);
		}
		
		btnRoll.setDisable(true);
		
		
		
		
		btnRoll.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try {
					sendMsg("Roll1", "Roll1");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});

		
		btnDice1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
//				pressed1 = setCheck(btnDice1, pressed1, d1.getDie(0).getID());
			}
		});
		
		btnDice2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
//				pressed2 = setCheck(btnDice2, pressed2, d1.getDie(1).getID());
			}
		});
		btnDice3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
//				pressed3 = setCheck(btnDice3, pressed3, d1.getDie(2).getID());
			 
			}
		});
		btnDice4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
//				pressed4 = setCheck(btnDice4, pressed4, d1.getDie(3).getID());
			 
			}
		});
		btnDice5.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
//				pressed5 = setCheck(btnDice5, pressed5, d1.getDie(4).getID());
			}
		});
		btnDice6.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
//				pressed6 = setCheck(btnDice6, pressed6, d1.getDie(5).getID());
				
			}
		});
		
	}

	
		
		
		
		
		
		
		
		
		
		public void run() {
			
			try {
				clientInputStream = new ObjectInputStream(client.getInputStream());
			}catch (NullPointerException e){
			}
			catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}  
			HashMap<String, Object> m = null;
			
			
	      while(!this.client.isClosed()){
	      try {
	    	
			m = (HashMap<String, Object>) clientInputStream.readObject();
			String k = (new ArrayList<String>(m.keySet())).get(0);
			
			switch (k){
			case "Player1":
				Player player = (Player) m.get("Player1");
				Platform.runLater(() -> {
           		 this.player1 = player;
           		 lUsername1.setText(this.player1.getName());});
				break;
				
			case "Player2":
				Player player2 = (Player) m.get("Player2");
				Platform.runLater(() -> {
	           		 this.player2 = player2;
	           		lUsername2.setText(this.player2.getName());try {
						sendMsg("Spieler1amZug", "Spieler1");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}});
				break;
			
			case "Spieler1würfeln":
				boolean s = (boolean) m.get("Spieler1würfeln");
				Platform.runLater(() -> {
					for (Button btn: DicebtnList){
						btn.setDisable(s);
					}
					btnRoll.setDisable(s);
				});
				
				break;
			
			case "Spieler2würfeln":
				boolean s2 = (boolean) m.get("Spieler2würfeln");
				Platform.runLater(() -> {
					for (Button btn: DicebtnList){
						btn.setDisable(s2);
					}
					btnRoll.setDisable(s2);
				});
				
				break;
			
			case "Server_hat_gewürfelt":
				DiceM d1 = (DiceM) m.get("Server_hat_gewürfelt");
				
				Platform.runLater(() -> {
					
				btnDice1.setGraphic(new ImageView(d1.getDie(0).getFacePicture()));
				btnDice2.setGraphic(new ImageView(d1.getDie(1).getFacePicture()));
				btnDice3.setGraphic(new ImageView(d1.getDie(2).getFacePicture()));
				btnDice4.setGraphic(new ImageView(d1.getDie(3).getFacePicture()));
				btnDice5.setGraphic(new ImageView(d1.getDie(4).getFacePicture()));
				btnDice6.setGraphic(new ImageView(d1.getDie(5).getFacePicture()));
				});
			}
		
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
	   }
			
			
			
			
		}
		
		public static void sendMsg(String type, Object o) throws IOException {
		    HashMap<String, Object> hMap = new  HashMap<String, Object>();
		    hMap.put(type, o);
		    try{
		    	 clientOutputStream.writeObject(hMap);
				    clientOutputStream.flush();
		    } catch(NullPointerException e){
		    	
		    }
		   
		}

		
	}


