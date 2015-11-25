package application;



import gameLogic.DiceM;
import gameLogic.PlayerInGameM;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;









import Client.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;


public class Dice_Controller implements Initializable{
	
	@FXML
	TextField tfUsername;
	@FXML
	TextField tfIP;
	@FXML
	TextField tfPort;
	@FXML
	Button btnConnect;
	@FXML
	Label lMessage;
	
	@FXML
	Label lifePointsP1;
	@FXML
	Label lifePointsP2;
	@FXML
	Label StarsP1;
	@FXML
	Label Stars2;
	@FXML
	Label AnzeigeTokyoP1;
	@FXML
	Label AnzeigeTokyoP2;
	@FXML
	Label UsernameP1;
	@FXML
	Label UsernameP2;
	
	
	
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
	
	private Client clientSocket;
	
	private PlayerInGameM pIG1;
	private PlayerInGameM pIG2;

	private DiceM d1 = new DiceM(6);
	
	private ArrayList<Button> DicebtnList = new ArrayList<Button>();
	
	
	private boolean pressed1 = false;
	private boolean pressed2 = false;
	private boolean pressed3 = false;
	private boolean pressed4 = false;
	private boolean pressed5 = false;
	private boolean pressed6 = false;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		borderPane.setStyle("-fx-background-color: #FFFFFF");
		
		tfUsername.setPromptText("Username");
		tfPort.setPromptText("Portnummer");
		tfPort.setText("14000");
		tfIP.setPromptText("IPAdresse");
		tfIP.setText("localhost");
		
		
		btnConnect.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				clientSocket = new Client(tfPort.getText(), tfIP.getText());
				
					try {
						Client.sendMsg("Username", tfUsername.getText());
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
		
		
		
		
		btnRoll.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				for (Button btn: DicebtnList){
					btn.setDisable(false);
				}
				pressed1 = false;
				pressed2 = false;
				pressed3 = false;
				pressed4 = false;
				pressed5 = false;
				pressed6 = false;
				 setRoll();		 
			}
		});

		
		btnDice1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				pressed1 = setCheck(btnDice1, pressed1, d1.getDie(0).getID());	
			}
		});
		
		btnDice2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				pressed2 = setCheck(btnDice2, pressed2, d1.getDie(1).getID());
			}
		});
		btnDice3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				pressed3 = setCheck(btnDice3, pressed3, d1.getDie(2).getID());
			 
			}
		});
		btnDice4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				pressed4 = setCheck(btnDice4, pressed4, d1.getDie(3).getID());
			 
			}
		});
		btnDice5.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				pressed5 = setCheck(btnDice5, pressed5, d1.getDie(4).getID());
			}
		});
		btnDice6.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				pressed6 = setCheck(btnDice6, pressed6, d1.getDie(5).getID());
				
			}
		});
		
	}
	private void setRoll(){
		d1.rollDice();
		
		btnDice1.setGraphic(new ImageView(d1.getDie(0).getFacePicture()));
		btnDice2.setGraphic(new ImageView(d1.getDie(1).getFacePicture()));
		btnDice3.setGraphic(new ImageView(d1.getDie(2).getFacePicture()));
		btnDice4.setGraphic(new ImageView(d1.getDie(3).getFacePicture()));
		btnDice5.setGraphic(new ImageView(d1.getDie(4).getFacePicture()));
		btnDice6.setGraphic(new ImageView(d1.getDie(5).getFacePicture()));	
		
		for(int i = 0; i<d1.getSize(); i++){
			d1.getDie(i).removeLock();
		}
	}
	
		private boolean setCheck(Button btn, boolean k, int d){
			if(!k){
				btn.setGraphic(new ImageView(d1.getDie(d).getFacePictureLock()));
				d1.getDie(d).setLock();
				k = true;
				
			}else{
				btn.setGraphic(new ImageView(d1.getDie(d).getFacePicture()));
				d1.getDie(d).removeLock();
				k = false;
			}
			return k;
		}

		
	}


