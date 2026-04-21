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
		
	}
	
	public Ball() {
		String path = getClass().getClassLoader().getResource("breakoutresources/ball.png").toString();
		Image img = new Image(path);
		setImage(img);
		dx = 5;
		dy = 5;
	}

}
