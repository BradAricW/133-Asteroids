package com.mycompany.A3Prj;

//import statements
import java.util.Observable;

public class GameWorldProxy extends Observable implements IGameWorld {

	// code here to accept and hold a GameWorld, provide implementations
	// of all the public methods in a GameWorld, forward allowed
	// calls to the actual GameWorld, and reject calls to methods
	// which the outside should not be able to access in the GameWorld.

	private GameWorld realGameWorld;
	public GameWorldProxy(GameWorld gw) {
		realGameWorld=gw;
	}
	
	public int getScore() {
		return realGameWorld.getScore();
	}
	
	public int getTime() {
		return realGameWorld.getTime();
	}
	
	public int getMissiles() {
		return realGameWorld.getMissiles();
	}
	
	public boolean getSound() {
		return realGameWorld.getSound();
	}
	
	public void addAsteroid() {
		errorMessage();
	}

	public void addStation() {
		errorMessage();
	}
	
	public void addShip() {
		errorMessage();
	}
	
	public void speedUp() {
		errorMessage();
	}
	
	public void speedDown() {
		errorMessage();
	}
	
	public void turnLeft() {
		errorMessage();
	}
	
	public void turnRight() {
		errorMessage();
	}
	
	public void jump() {
		errorMessage();
	}
	
	public void fireMissile() {
		errorMessage();
	}
	
	public void refuel() {
		errorMessage();
	}
	
	public void missileHit() {
		errorMessage();
	}
	
	public void crash() {
		errorMessage();
	}
	
	public void asteroidHit() {
		errorMessage();
	}
	
	public void time() {
		errorMessage();
	}
	
	//unnecessary in this version, kept for testing purposes
	public void print() {
		realGameWorld.print();
	}

	//unnecessary in this version, kept for testing purposes
	public void map() {
		realGameWorld.print();
	}
	
	public void quit() {
		errorMessage();
	}
	
	public void newGame() {
		errorMessage();
	}
	
	public void save() {
		errorMessage();
	}
	
	public void load() {
		errorMessage();
	}
	
	public void undo() {
		errorMessage();
	}
	
	public void about() {
		realGameWorld.about();
	}
	
	public void setSound() {
		errorMessage();
	}
	
	//display error if proxy tries to alter game data
	public void errorMessage() {
		System.out.println("Error: Proxy cannot alter game data");
	}
	
	public IIterator getIterator() {
		return realGameWorld.getIterator();
	}
	
}
