package breakout;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Lives extends Text{
	private int value;
	
	public void updateDisplay() {
		setText("Lives: " + value);
	}
	
	public Lives() {
		value = 3;
		setFont(new Font(20));
		updateDisplay();
	}
	
	public int getLives() {
		return value;
	}
	
	public void setLives(int lives) {
		value = lives;
		updateDisplay();
	}
}
