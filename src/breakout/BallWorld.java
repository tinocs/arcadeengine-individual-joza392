package breakout;

import engine.World;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class BallWorld extends World{
	
	private Score score;
	
	public BallWorld() {
		setPrefSize(800, 600);
	}

	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDimensionsInitialized() {
		Ball b = new Ball();
		add(b);
		b.setX(getWidth()/2);
		b.setY(getHeight()/2);
		
		Paddle p = new Paddle();
		add(p);
		p.setX(getWidth()/2);
		p.setY(3 * getHeight() /4);
		
		
		setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				p.setX(event.getX());
			}});
		
		score = new Score();
		score.setX(getWidth()/2 - 50);
		score.setY(getHeight()/8);
		getChildren().add(score);

	}
	
	public Score getScore() {
		return score;
	}

	
}
