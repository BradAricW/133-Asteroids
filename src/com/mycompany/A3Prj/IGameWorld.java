package com.mycompany.A3Prj;

public interface IGameWorld {

	//specifications here for all GameWorld methods
	public int getScore();
	public int getTime();
	public int getMissiles();
	public boolean getSound();
	public void addAsteroid();
	public void addStation();
	public void addShip();
	public void speedUp();
	public void speedDown();
	public void turnLeft();
	public void turnRight();
	public void jump();
	public void fireMissile();
	public void refuel();
	public void time();
	
	//unnecessary in this version, kept for testing purposes
	public void print();
	
	//unnecessary in this version, kept for testing purposes
	public void map();
	
	public void quit();
	
	public void newGame();
	public void undo();
	public void save();
	public void about();
	public void setSound();
	public void load();
	public IIterator getIterator();
	
}