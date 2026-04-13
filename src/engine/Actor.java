package engine;

import java.util.ArrayList;

import javafx.scene.image.ImageView;

public abstract class Actor extends ImageView{

	public Actor() {
		
	}
	
	public abstract void act(long now);
	
	
	public void addedToWorld() {
		
	}
	
	public double getHeight() {
		return 0;
	}
	
	public <A extends Actor> java.util.List<A> getIntersectingObjects(){
		return new ArrayList<A>();
	}
	
	public <A extends Actor> java.util.List<A> getOneIntersectingObject(){
		return new ArrayList<A>();
	}
	
	public double getWidth() {
		return 0;
	}
	
	public World getWorld() {
		return null;
	}
	
	public void move(double dx, double dy) {
		
	}
}
