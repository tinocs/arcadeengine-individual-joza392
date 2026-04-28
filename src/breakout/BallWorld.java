package breakout;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import engine.Actor;
import engine.Sound;
import engine.World;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class BallWorld extends World{

	private Score score;
	private int level;
	private Lives lives;
	private Ball b;
	private boolean isPaused = true;
	private boolean isOver = true;
	private boolean lostShown = false;
	private ImageView bgView;
	private Paddle p;

	public BallWorld() {
		setPrefSize(800, 600);
		setFocusTraversable(true);
	}

	@Override
	public void act(long now) {
		if (getLives().getLives() <= 0 && lostShown == false) {
			isOver = true;
			Sound gameLostSound = new Sound("breakoutresources/game_lost.wav");
			gameLostSound.play();

			lostShown = true;

			String message = "Game Over. You Lose.";  
			Alert a = new Alert(AlertType.INFORMATION, message, ButtonType.OK); 

			Stage stage = (Stage) a.getDialogPane().getScene().getWindow();

			stage.setAlwaysOnTop(true);
			stage.toFront();

			a.show();
 

			Stage s = (Stage) getScene().getWindow();
			Breakout b = new Breakout();
			try {
				b.start(s);
				return;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}

		}

		if (isKeyPressed(KeyCode.SPACE)) {
			isPaused = false;
		}
		if (isPaused == false) {



			boolean zeroBricks = true;
			for(Node n: getChildren()) {
				if (n instanceof Brick) {
					zeroBricks = false;
				}
			}

			if (zeroBricks == true) {
				level++;
				if(level >=3) {
					Sound gameWonSound = new Sound("breakoutresources/game_won.wav");
					gameWonSound.play();
					isOver = true;

					String message = "You Win!";  
					Alert a = new Alert(AlertType.INFORMATION, message, ButtonType.OK);  
					Stage stage = (Stage) a.getDialogPane().getScene().getWindow();

					stage.setAlwaysOnTop(true);
					stage.toFront();

					a.show(); 

					Stage s = (Stage) getScene().getWindow();
					Breakout b = new Breakout();
					try {
						b.start(s);
						return;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return;
					}


				}else {
					int r = 0;
					int c = 0;
					File f = new File("src/breakout/Level " + level + ".txt");
					try {
						Scanner s = new Scanner(f);
						r = s.nextInt();
						c = s.nextInt();


						s.nextLine();
						for (int i = 0; i < r; i++) {
							String str = s.nextLine();
							for (int j = 0; j < c; j++) {
								char ch = str.charAt(j);
								if (ch != '0') {
									Brick brick = new Brick(ch);
									add(brick);
									brick.setX((getWidth() - (c * brick.getImage().getWidth())) / 2 + j * brick.getImage().getWidth());
									brick.setY(100 + i * brick.getImage().getHeight());
								}
							}
						}
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

	}

	@Override
	public void onDimensionsInitialized() {

		isOver = false;
		setOnMousePressed(e -> {
			if (e.getButton() == MouseButton.PRIMARY) {
				isPaused = false;
			}
		});
		//		setOnKeyPressed(e -> {
		//			
		//
		//			
		//			
		//		    if (e.getCode() == KeyCode.SPACE) {
		//		        isPaused = false;
		//		    }
		//		});

		String path = getClass().getClassLoader().getResource("breakoutresources/breakout_background.png").toString();
		Image bg = new Image(path);
		bgView = new ImageView(bg);
		bgView.setX(getScene().getWidth()/2 - bg.getWidth()/2);
		getChildren().add(bgView);

		level = 1;
		b = new Ball();
		add(b);
		b.setX(getWidth()/2);
		b.setY(3 * getHeight()/4 - 20);

		p = new Paddle();
		add(p);
		p.setX(getWidth()/2);
		p.setY(3 * getHeight() /4);




		//		setOnMouseMoved(new EventHandler<MouseEvent>() {
		//			@Override
		//			public void handle(MouseEvent event) {
		//				if (event.getX() < getWidth() - p.getImage().getWidth())
		//				p.setX(event.getX());
		//			}});

		score = new Score();
		score.setX(getWidth()/2 + 50);
		score.setY(getHeight()/8);
		getChildren().add(score);

		lives = new Lives();
		lives.setX(getWidth()/2 - 50);
		lives.setY(getHeight()/8);
		getChildren().add(lives);

		int r = 0;
		int c = 0;
		File f = new File("src/breakout/Level " + level + ".txt");
		try {
			Scanner s = new Scanner(f);
			r = s.nextInt();
			c = s.nextInt();


			s.nextLine();
			for (int i = 0; i < r; i++) {
				String str = s.nextLine();
				for (int j = 0; j < c; j++) {
					char ch = str.charAt(j);
					if (ch != '0') {
						Brick brick = new Brick(ch);
						add(brick);
						brick.setX((getWidth() - (c * brick.getImage().getWidth())) / 2 + j * brick.getImage().getWidth());
						brick.setY(100 + i * brick.getImage().getHeight());
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		requestFocus();


	}

	public Score getScore() {
		return score;
	}

	public Lives getLives() {
		return lives;
	}

	public boolean getPaused() {
		return isPaused;
	}

	public void setPaused(boolean paused) {
		isPaused = paused;
	}

	public boolean getOver() {
		return isOver;
	}

	public void setOver(boolean over) {
		isOver = over;
	}

	public void scroll(double dx) {
		// For now, only move the background by the OPPOSITE of dx.
		// For example, if dx was 5 then you would move the background by -5.

		if (bgView.getX() + dx <= 0 && bgView.getX() + dx >= getScene().getWidth() - bgView.getImage().getWidth()) {
			bgView.setX(bgView.getX() + dx);
			List<Actor> actors = getObjects(Actor.class);
			for (Actor a: actors) {
				a.setX(a.getX() + dx);
			}
		}
	}


}