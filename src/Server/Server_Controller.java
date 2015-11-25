package Server;

import javafx.event.ActionEvent;

import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import Server.TextAreaHandler;

public class Server_Controller implements Initializable {

	@FXML
	BorderPane borderPane;
	
	@FXML
	TextField tfIP;

	@FXML
	TextField tfPort;

	@FXML
	Button btnStart;

	@FXML
	Label lHostName;
	
	@FXML
	TextArea txtAServer;
	
	TextAreaHandler textAreaHandler = new TextAreaHandler();

	InetAddress addr;
	
	Server s;
	
	private final Logger logger = Logger.getLogger("");

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		 textAreaHandler.setLevel(Level.INFO);
	        Logger defaultLogger = Logger.getLogger("");
	        defaultLogger.addHandler(textAreaHandler);
	        
	        borderPane.setBottom(textAreaHandler.getTextArea());
		
		try {
			addr = InetAddress.getLocalHost();
			tfIP.setText(addr.getHostAddress());
			tfIP.setPromptText("Deine jetztige IP: "+addr.getHostAddress());
			lHostName.setText(addr.getHostName());
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		tfPort.setText("14000");
		tfPort.setPromptText("Bitte Portnummer eingeben.");
		
		
		// ChangeListener for the text-property of the web address
				tfPort.textProperty().addListener(
						// Parameters of any PropertyChangeListener
						(observable, oldValue, newValue) -> {
							validatePort(newValue);
						});
		
		
		
		btnStart.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						try{
							s = new Server("localhost", Integer.parseInt(tfPort.getText()));
						}catch (NumberFormatException e){
							logger.info("Server konnte nicht gestartet werden weil der Port fehlt oder ungültig ist.");
						}
						
						
					}
				}).start();

			}
		});
	}
	private void validatePort(String newValue) {
		boolean valid = false;
		String[] parts = newValue.split("\\.");

		// check for a numeric address first: 4 parts, each an integer 0 to 255
		if (parts.length == 4) {
			valid = true;
			for (String part : parts) {
				try {
					int value = Integer.parseInt(part);
					if (value < 0 || value > 255) valid = false;
				} catch (NumberFormatException e) {
					// wasn't an integer
					valid = false;
				}
			}
		}
}
}