package breakout;

import engine.Actor;
import engine.Sound;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.util.Duration;
import javafx.animation.FadeTransition;

public class Ball extends Actor{

	private int dx;
	private int dy;
	private Paddle p;
	@Override
	
	public void act(long now) {
		
		if (((BallWorld) getWorld()).getPaused() == true) { // If the world is paused
			
		}else {
			
			if (((BallWorld) getWorld()).getOver() == false){
				move(dx, dy);
				if (getX() <= 0 || getX() + getWidth() >= getWorld().getWidth()) {
					Sound bounceSound = new Sound("breakoutresources/ball_bounce.wav");
					bounceSound.play();
					dx = -dx;
				}
				if (getY() <= 0 || getY() + getHeight() >= getWorld().getHeight()) {
					Sound bounceSound = new Sound("breakoutresources/ball_bounce.wav");
					bounceSound.play();
					dy = -dy;
				}
				
				if (getY() + getHeight() >= getWorld().getHeight()) {
					BallWorld w = (BallWorld) getWorld();
					w.getScore().setScore(w.getScore().getScore() - 1000);
					Sound liveLostSound = new Sound("breakoutresources/lose_life.wav");
					liveLostSound.play();
					w.setPaused(true);
					
					
					w.getLives().setLives(w.getLives().getLives() - 1);
					
					setX(p.getX() + p.getImage().getWidth()/2);
				    setY(3 * getWorld().getHeight()/4 - 20);
	
				    dx = 5;
				    dy = -5;
				}
				
				if (getOneIntersectingObject(Paddle.class) != null) {
					p = (Paddle) getOneIntersectingObject(Paddle.class);
					Sound bounceSound = new Sound("breakoutresources/ball_bounce.wav");
					bounceSound.play();
					dy = -dy;
				}
				
				if (getOneIntersectingObject(Brick.class) != null) {
					Brick brick = getOneIntersectingObject(Brick.class);
					Sound hitBrickSound = new Sound("breakoutresources/brick_hit.wav");
					hitBrickSound.play();
					BallWorld w = (BallWorld) getWorld();
					FadeTransition ft = new FadeTransition(Duration.millis(100), brick);
					ft.setFromValue(1.0);
					ft.setToValue(0.0);

					ft.play();
					ft.setOnFinished(e ->{
						w.remove(brick);
					});
					
					if (getX() >= brick.getX() - brick.getWidth()/2 && getX() <= brick.getX() + brick.getWidth()/2) {
						
						dy = -dy;
					}else if (getY() >= brick.getY() - brick.getHeight()/2 && getY() <= brick.getY() + brick.getHeight()/2) {
						
						dx = -dx;
					}else {
						
						dx = -dx;
						dy = -dy;
					}
					
					
					w.getScore().setScore(w.getScore().getScore() + 100);
					
				}
			}
		}
		
	}
	
	public Ball() {
		String path = getClass().getClassLoader().getResource("breakoutresources/ball.png").toString();
		Image img = new Image(path);
		setImage(img);
		dx = 5;
		dy = -5;
	}

}
