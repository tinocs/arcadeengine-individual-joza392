package engine;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public abstract class World extends Pane{
	private MyAnimationTimer myTimer;
	private boolean isRunning;
	ArrayList<KeyCode> keys;
	boolean widthSet;
	boolean heightSet;
	boolean initialized;
	
	public World() {
		widthSet = false;
		heightSet = false;
		initialized = false;
		keys = new ArrayList<>();
		isRunning = false;
		HeightListener hListen = new HeightListener();
		WidthListener wListen = new WidthListener();
		widthProperty().addListener(wListen);
		heightProperty().addListener(hListen);
		SceneListener sListen = new SceneListener();
		sceneProperty().addListener(sListen);
		setOnKeyPressed(e -> {
			if (keys.contains(e.getCode()) == false) {
				KeyCode code = e.getCode();
				keys.add(code);
			}
		});
		
		setOnKeyReleased(e -> {
			if (keys.contains(e.getCode()) == true) {
				KeyCode code = e.getCode();
				keys.remove(code);
			}
		});
		
		myTimer = new MyAnimationTimer();
	}
	
	public abstract void act(long now) ;
	
	public void add(Actor actor) {
		getChildren().add(actor);
		actor.addedToWorld();
	}
	
	public <A extends Actor> java.util.List<A> getObjects(java.lang.Class<A> cls){
		List<A> actors = new ArrayList<A>();
		for (Node actor: getChildren()) {
			if (cls.isInstance(actor)) {
				actors.add(cls.cast(actor));
			}
		}
		return actors;
	}
	
	public <A extends Actor> java.util.List<A> getObjectsAt(double x, double y, java.lang.Class<A> cls){
		ArrayList<A> actors = new ArrayList<A>();
		for (Node actor: getChildren()) {
			if (cls.isInstance(actor)) {
				if (actor.getBoundsInParent().contains(x, y)) {
					actors.add(cls.cast(actor));
				}
			}
		}
		return actors;
	}
	
	public boolean isKeyPressed(KeyCode code) {
		if (keys.contains(code)){
			return true;
		}
		return false;
	}
	
	public boolean isStopped() { 
		if (isRunning == true) {
			return false;
		}
		return true;
	}
	
	public abstract void onDimensionsInitialized();
	
	public void remove(Actor actor) {
		getChildren().remove(actor);
	}
	
	public void start() {
		myTimer.start();
		isRunning = true;
	}
	
	public void stop() {
		myTimer.stop();
		isRunning = false;
	}
	
	private class HeightListener implements ChangeListener<Number> {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			if (oldValue.doubleValue() == 0 && newValue.doubleValue() > 0) {
				heightSet = true;
				
			}
			if (heightSet && widthSet && initialized == false) {
				initialized = true;
				onDimensionsInitialized();
			}
			
		}
		
	}
	
	private class WidthListener implements ChangeListener<Number> {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			if (oldValue.doubleValue() == 0 && newValue.doubleValue() > 0) {
				widthSet = true;
				
			}
			if (heightSet && widthSet && initialized == false) {
				initialized = true;
				onDimensionsInitialized();
			}
			
		}
		
	}
	
	private class SceneListener implements ChangeListener<Scene>{

		@Override
		public void changed(ObservableValue<? extends Scene> observable, Scene oldScene, Scene newScene) {
			if (newScene != null) {

				requestFocus();
			}
			
		}
		
	}
	
	private class MyAnimationTimer extends AnimationTimer {

		@Override
		public void handle(long now) {
			act(now);
			List<Actor> actorList = getObjects(Actor.class);
			for (Actor a : actorList) {
				if (getChildren().contains(a)) {
					a.act(now);
				}
				
			}
			
		}
		
	}
	
}
