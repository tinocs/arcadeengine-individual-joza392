package breakout;

import engine.Actor;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Paddle extends Actor{

	private int dx;
	public Paddle() {
		String path = getClass().getClassLoader().getResource("breakoutresources/paddle.png").toString();
		Image img = new Image(path);
		setImage(img);
		dx = 6;
	}
	
	@Override
	public void act(long now) {
		
		if(getWorld().isKeyPressed(KeyCode.LEFT)){
			if (getX() >= 0) {
				move(-dx, 0);
				if (getX() < getWorld().getScene().getX()/2) {
					BallWorld w = (BallWorld) getWorld();
					w.scroll(dx);
				}
			}
		}
		
		if(getWorld().isKeyPressed(KeyCode.RIGHT)){
			if (getX() + getWidth() <= getWorld().getWidth()) {
				move(dx, 0);
				if (getX() > getWorld().getScene().getX()/2) {
					BallWorld w = (BallWorld) getWorld();
					w.scroll(-dx);
				}
			}
			
			
		}
		
		
	}
	
	public int getDX() {
		return dx;
	}

}
