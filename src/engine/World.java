package engine;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public abstract class World extends Pane{
	private AnimationTimer myTimer;
	private boolean isRunning;
	ArrayList<KeyCode> keys = new ArrayList<>();
	boolean widthSet;
	boolean heightSet;
	
	public World() {
		// widthProperty().addListener();
	}
	
	public abstract void act(long now) ;
	
	public void add(Actor actor) {
		add(actor);
		actor.addedToWorld();
	}
	
	public <A extends Actor> java.util.List<A> getObjects(){
		return new ArrayList<A>();
	}
	
	public <A extends Actor> java.util.List<A> getObjectsAt(double x, double y){
		return new ArrayList<A>();
	}
	
	public boolean isKeyPressed(KeyCode code) {
		return false;
	}
	
	public boolean isStopped() { 
		return false;
	}
	
	public abstract void onDimensionsInitialized();
	
	public void remove(Actor actor) {
		
	}
	
	public void start() {
		
	}
	
	public void stop() {
		
	}
	
}
