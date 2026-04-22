package breakout;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Breakout extends Application{

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Breakout");
		BorderPane root = new BorderPane();
		BallWorld world = new BallWorld();
		Scene scene = new Scene(root);
		root.setCenter(world);
		world.start();
		stage.setScene(scene);
		stage.show();
	}

}
