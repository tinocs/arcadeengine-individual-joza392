package breakout;

import engine.Actor;
import javafx.scene.image.Image;

public class Ball extends Actor{

	private int dx;
	private int dy;
	@Override
	
	public void act(long now) {
		move(dx, dy);
		if (getX() <= 0 || getX() + getWidth() >= getWorld().getWidth()) {
			dx = -dx;
		}
		if (getY() <= 0 || getY() + getHeight() >= getWorld().getHeight()) {
			dy = -dy;
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
