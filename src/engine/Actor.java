package engine;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.image.ImageView;

public abstract class Actor extends ImageView{

	public Actor() {
		
	}
	
	public abstract void act(long now);
	
	
	public void addedToWorld() {
		
	}
	
	public double getHeight() {
		return getBoundsInLocal().getHeight();
	}
	
	public <A extends Actor> java.util.List<A> getIntersectingObjects(java.lang.Class<A> cls){
		ArrayList<A> list = new ArrayList<>();
		for (Node n : getParent().getChildrenUnmodifiable()) {
			if (n != this && cls.isInstance(n)) {
				Actor a = (Actor) n;
				if (a.getBoundsInParent().intersects(this.getBoundsInParent())) {
					list.add((A) a);
				}
			}
		}
		return list;
	}
	
	public <A extends Actor> java.util.List<A> getOneIntersectingObject(java.lang.Class<A> cls){
		return new ArrayList<A>();
	}
	
	public double getWidth() {
		return getBoundsInLocal().getWidth();
	}
	
	public World getWorld() {
		return (World) getParent();
	}
	
	public void move(double dx, double dy) {
		this.setX(this.getX() + dx);
		this.setY(this.getY() + dy);
	}
}
