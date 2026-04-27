package breakout;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
		root.setCenter(world);
		
		
		VBox titleRoot = new VBox();
		titleRoot.setSpacing(100);
		titleRoot.setPrefWidth(800);
		titleRoot.setPrefHeight(600);
		Text title = new Text("Breakout");
		title.setFont(new Font(50));
		
		Scene titleScene = new Scene(titleRoot);
		
		Button play = new Button("Play");
		titleRoot.setAlignment(Pos.CENTER);
		titleRoot.getChildren().addAll(title, play);
		play.setOnAction(e -> {
			Scene level1 = new Scene(root);
			stage.setScene(level1);
			world.start();
			world.requestFocus();
		});
		
		
		
		
		stage.setScene(titleScene);
		stage.show();
		
		
	}

}
