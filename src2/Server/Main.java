package Server;
	
import java.net.URL;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	@Override
	public void start(final Stage stage) throws Exception {

		final URL fxmlUrl = getClass().getResource(
				"Server.fxml");
		final FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
		fxmlLoader.setController(new Server_Controller());
		final Parent root = fxmlLoader.load();
		stage.setScene(new Scene(root, 700, 300));
		stage.setTitle("King_of_Tokyo");
		stage.show();
	
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit(); // ends any JavaFX activities
                System.exit(0);  // end all activities (our server task) - not good code
            }
        });
	
	
	}

	public static void main(final String[] args) {
		launch(args);
		
		
	}
}