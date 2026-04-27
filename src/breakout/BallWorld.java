package breakout;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import engine.World;
import javafx.event.EventHandler;
import javafx.scene.Node;
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
	private boolean lostShown = false;
	
	public BallWorld() {
		setPrefSize(800, 600);
	}

	@Override
	public void act(long now) {
		
		
		if (isPaused == false) {
			if (getLives().getLives() <= 0 && lostShown == false) {
				Text gameLost = new Text("Game Over! press any key to go back");
				gameLost.setFont(new Font(20));
				gameLost.setX(getWidth()/2);
				gameLost.setY(getHeight()/2);
				getChildren().add(gameLost);
				lostShown = true;
				setOnKeyPressed(e -> {
					Stage s = (Stage) getScene().getWindow();
					Breakout b = new Breakout();
					try {
						b.start(s);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				});
			}
			boolean zeroBricks = true;
			for(Node n: getChildren()) {
				if (n instanceof Brick) {
					zeroBricks = false;
				}
			}
			
			if (zeroBricks == true) {
				level++;
				if(level >=3) {
					Text gameOver = new Text("Game Over! press any key to go back");
					gameOver.setFont(new Font(20));
					gameOver.setX(getWidth()/2);
					gameOver.setY(getHeight()/2);
					getChildren().add(gameOver);
					setOnKeyPressed(e -> {
						Stage s = (Stage) getScene().getWindow();
						Breakout b = new Breakout();
						try {
							b.start(s);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					});
					
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
		
		
		setOnMousePressed(e -> {
			if (e.getButton() == MouseButton.PRIMARY) {
				isPaused = false;
			}
		});
		setOnKeyPressed(e -> {
		    if (e.getCode() == KeyCode.SPACE) {
		        isPaused = false;
		    }
		});
		
		level = 1;
		b = new Ball();
		add(b);
		b.setX(getWidth()/2);
		b.setY(3 * getHeight()/4 - 20);
		
		Paddle p = new Paddle();
		add(p);
		p.setX(getWidth()/2);
		p.setY(3 * getHeight() /4);
		
		
		setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getX() < getWidth() - p.getImage().getWidth())
				p.setX(event.getX());
			}});
		
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


}
