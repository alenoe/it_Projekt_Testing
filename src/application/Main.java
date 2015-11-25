package application;
	
import java.net.URL;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	@Override
	public void start(final Stage stage) throws Exception {

		final URL fxmlUrl = getClass().getResource(
				"Board.fxml");
		final FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
		fxmlLoader.setController(new Dice_Controller());
		final Parent root = fxmlLoader.load();
		stage.setScene(new Scene(root, 1000, 700));
		stage.setTitle("King_of_Tokyo");
		stage.show();
	}

	public static void main(final String[] args) {
		launch(args);
	}
}
