package breakout;

import engine.Actor;
import javafx.scene.image.Image;

public class Brick extends Actor{
	
	public Brick(char num) {
		if (num == '1') {
			String path = getClass().getClassLoader().getResource("breakoutresources/brick.png").toString();
			Image img = new Image(path);
			setImage(img);
		}else if (num == '2') {
			String path = getClass().getClassLoader().getResource("breakoutresources/brick2.png").toString();
			Image img = new Image(path);
			setImage(img);
		}
	}

	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
