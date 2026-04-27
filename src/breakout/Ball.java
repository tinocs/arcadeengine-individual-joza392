package breakout;

import engine.Actor;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

public class Ball extends Actor{

	private int dx;
	private int dy;
	@Override
	
	public void act(long now) {
		
		if (((BallWorld) getWorld()).getPaused() == true) { // If the world is paused
			
		}else {
			

			move(dx, dy);
			if (getX() <= 0 || getX() + getWidth() >= getWorld().getWidth()) {
				dx = -dx;
			}
			if (getY() <= 0 || getY() + getHeight() >= getWorld().getHeight()) {
				dy = -dy;
			}
			
			if (getY() + getHeight() >= getWorld().getHeight()) {
				BallWorld w = (BallWorld) getWorld();
				w.getScore().setScore(w.getScore().getScore() - 1000);
				w.setPaused(true);
				
				
				w.getLives().setLives(w.getLives().getLives() - 1);
				
				setX(getWorld().getWidth() / 2);
			    setY(3 * getWorld().getHeight()/4 - 20);

			    dx = 5;
			    dy = -5;
			}
			
			if (getOneIntersectingObject(Paddle.class) != null) {
				dy = -dy;
			}
			
			if (getOneIntersectingObject(Brick.class) != null) {
				Brick brick = getOneIntersectingObject(Brick.class);
				if (getX() >= brick.getX() - brick.getWidth()/2 && getX() <= brick.getX() + brick.getWidth()/2) {
					dy = -dy;
				}else if (getY() >= brick.getY() - brick.getHeight()/2 && getY() <= brick.getY() + brick.getHeight()/2) {
					dx = -dx;
				}else {
					dx = -dx;
					dy = -dy;
				}
				
				BallWorld w = (BallWorld) getWorld();
				w.getScore().setScore(w.getScore().getScore() + 100);
				w.remove(brick);
			}
		}
		
	}
	
	public Ball() {
		String path = getClass().getClassLoader().getResource("breakoutresources/ball.png").toString();
		Image img = new Image(path);
		setImage(img);
		dx = 5;
		dy = 5;
	}

}
