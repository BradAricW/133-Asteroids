package com.mycompany.A3Prj;

//import statements
import java.util.Observable;
import java.util.Random;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextArea;
import com.codename1.ui.geom.Point;

public class GameWorld extends Observable implements IGameWorld {

	//define Collections
	private SpaceCollection go;
	
	private Game g;

	//declare IDs
	static int activeAsteroids;
	static int shipID;
	static int activeStations;
	static int missileCount;
	static int activeMissiles;
	static int lives;
	
	//create static variables for game state
	static int score;
	static int clock;
	static int revolution;
	static boolean sound = true;
	
	//initialize spaceship without creating it so that its location may be used in missile creation
	static Ship spaceship;
	
	//create a Random object
	static Random r = new Random();
	
	//create sound objects
	static Sound missileHitSound = new Sound("boom.WAV");
	static Sound shipCrashSound = new Sound("crash.wav");
	static Sound missileFireSound = new Sound("fire.wav");
	static Sound lose = new Sound("gameover.wav");
	static BGSound bgSound = new BGSound("bg.wav");
	static Sound asteroidCrashSound = new Sound("asteroid.wav");
	
	static boolean pause = false;
	
	public void init(Game g) {
		//initialize all variables to their starting positions
		this.g = g;
		missileCount = 10;
		activeMissiles = 0;
		lives = 3;
		activeAsteroids = 0;
		activeStations = 0;
		shipID = 0;
		score = 0;
		clock = 0;
		sound = true;
		revolution = 0;
		bgSound.run();		
		go = new SpaceCollection();
	}
	
	//methods to get data for score view
	public int getScore(){
		return score;
	}

	public int getTime(){
		return clock;
	}
	
	public int getMissiles(){
		return missileCount;
	}
	
	public boolean getSound(){
		return sound;
	}
	
	// code here to hold and manipulate world objects, handle observer registration,	
	// invoke observer callbacks by passing a GameWorld proxy, etc.
	
	//add objects
	public void addAsteroid() {
		activeAsteroids++;
		go.add(new Asteroid());
		/*this.setChanged();
		GameWorldProxy gwProxy = new GameWorldProxy(this);
		this.notifyObservers(gwProxy);*/
	}
	
	public void addStation() {
		activeStations++;
		go.add(new Station());
		/*this.setChanged();
		GameWorldProxy gwProxy = new GameWorldProxy(this);
		this.notifyObservers(gwProxy);*/
	}
	
	public void addShip() {
		if(shipID==0) {
			shipID++;
			spaceship = new Ship();
			go.add(spaceship);
			/*this.setChanged();
			GameWorldProxy gwProxy = new GameWorldProxy(this);
			this.notifyObservers(gwProxy);*/
		}
		else
			System.out.println("There is already a ship in play.");
	}
	
	//steering the ship
	public void speedUp() {
		if(shipID!=0) {
			spaceship.setSpeed('+');
			/*this.setChanged();
			GameWorldProxy gwProxy = new GameWorldProxy(this);
			this.notifyObservers(gwProxy);*/
		}
	}
	
	public void speedDown() {
		if(shipID!=0) {
			spaceship.setSpeed('-');
			/*this.setChanged();
			GameWorldProxy gwProxy = new GameWorldProxy(this);
			this.notifyObservers(gwProxy);*/
		}
	}
	
	public void turnLeft() {
		if(shipID!=0) {
			spaceship.setDir('-');
			this.setChanged();
			GameWorldProxy gwProxy = new GameWorldProxy(this);
			this.notifyObservers(gwProxy);
		}
	}
	
	public void turnRight() {
		if(shipID!=0) {
			spaceship.setDir('+');
			this.setChanged();
			GameWorldProxy gwProxy = new GameWorldProxy(this);
			this.notifyObservers(gwProxy);
		}
	}
	
	public void jump() {
		if(shipID!=0) {
			spaceship.jump();
			this.setChanged();
			GameWorldProxy gwProxy = new GameWorldProxy(this);
			this.notifyObservers(gwProxy);
		}
	}
	
	//missile interaction
	public void fireMissile() {
		if (shipID == 1) {
			if (missileCount >= 1){
				IIterator theElements = go.getIterator();
				while(theElements.hasNext()) {
					SpaceObjects o = (SpaceObjects) theElements.getNext();
					if(o instanceof Ship) {
						Ship s = (Ship) o;
						s.depleteMissiles();
						//missileCount = 10;
					}
				}
				if(sound) {
					missileFireSound.play();
				}
				activeMissiles++;
				go.add(new Missile(spaceship.getLocation(), spaceship.getDirection(), spaceship.getSpeed()));
				/*this.setChanged();
				GameWorldProxy gwProxy = new GameWorldProxy(this);
				this.notifyObservers(gwProxy);*/
			}
		}
	}
	
	public void refuel() {
		
		IIterator theElements = go.getIterator();
		while(theElements.hasNext()) {
			SpaceObjects o = (SpaceObjects) theElements.getNext();
			o.move(20);
			Point p = o.getLocation();
			o.setLocation(p);
			//System.out.println(o.toString());
			if(o instanceof Missile) {
				Missile m = (Missile) o;
				m.refuel();
			}
		}
	}
	
	//other collisions
	public void gameOver() {
		if(sound) {
			bgSound.pause();
			lose.play();
			bgSound.run();
		}
		System.out.println("Game Over \nFinal Results: ");
		print();
		map();
		System.out.println("Starting new game\n");
		go.destroy();
		missileCount = 10;
		lives = 3;
		activeAsteroids = 0;
		activeMissiles = 0;
		activeStations = 0;
		shipID = 0;
		score = 0;
		clock = 0;
		this.setChanged();
		GameWorldProxy gwProxy = new GameWorldProxy(this);
		this.notifyObservers(gwProxy);
	}
	
	
	//game world status
	public void time() {
		if (revolution==50) {
			clock++;
			revolution = 0;
		}
		else
			revolution++;
		//move ship
		/*if(shipID==1) {
			spaceship.move();
		}*/
		
		//move
		IIterator theElements = go.getIterator();
		while(theElements.hasNext()) {
			SpaceObjects o = (SpaceObjects) theElements.getNext();
			o.move(20);
			Point p = o.getLocation();
			o.setLocation(p);
			//System.out.println(o.toString());
			if(o instanceof Missile) {
				Missile m = (Missile) o;
				int i = m.getFuel();
				if (i<=0) {
					m.setDrawable();
					m.setCollidable();
					activeMissiles--;
					
				}
			}
			if(o instanceof Ship) {
				Ship s = (Ship) o;
				missileCount = s.getMissiles();
			}
		}
		
		//do nothing case = 0, asteroid-ship = 1, missile-asteroid = 2, asteroid-asteroid = 3
		IIterator it1 = go.getIterator();
		int collisionType = 0;
		while(it1.hasNext()) {
			ICollider curObj = (ICollider)it1.getNext();
			IIterator it2 = go.getIterator();
			while(it2.hasNext()) {
				ICollider otherObj = (ICollider)it2.getNext();
				if(curObj!=otherObj) {
					if(curObj.collidesWith(otherObj)) {
						if(curObj.getCollidable() && otherObj.getCollidable()) {
							collisionType = curObj.handleCollision(otherObj, sound);
							
							if(collisionType==1) {
								if (lives > 1) {
									curObj.setDrawable();
									otherObj.setDrawable();
									curObj.setCollidable();
									otherObj.setCollidable();
									lives--;
									shipID=0;
									activeAsteroids--;
									if(sound) {
										shipCrashSound.play();
									}
								}
								else g.gameOver(this);
							}
							
							else if(collisionType==2) {
								curObj.setDrawable();
								otherObj.setDrawable();
								curObj.setCollidable();
								otherObj.setCollidable();
								score+=5;
								activeMissiles--;
								activeAsteroids--;
								if(sound) {
									missileHitSound.play();
								}
							}
							else if(collisionType==3) {
								curObj.setDrawable();
								otherObj.setDrawable();
								curObj.setCollidable();
								otherObj.setCollidable();
								score+=2;
								activeAsteroids-=2;
								if(sound) {
									asteroidCrashSound.play();
								}
							}
						}
					}
				}
			}
		}
		
		this.setChanged();
		GameWorldProxy gwProxy = new GameWorldProxy(this);
		this.notifyObservers(gwProxy);
	}
	
	//used only for testing and game over in this version
	public void print() {
		System.out.println("Score: " + score);
		System.out.println("Missiles: " + missileCount);
		System.out.println("Time: " + clock);
		System.out.println();
	}
	
	//used only for testing and game over in this version
	public void map() {
		/*//display ship
		if(shipID==1) {
			System.out.println(spaceship.toString(missileCount));
		}
		
		//display
		IIterator theElements = go.getIterator();
		while(theElements.hasNext()) {
			SpaceObjects o = (SpaceObjects) theElements.getNext();
			System.out.println(o.toString());
		}
		
		System.out.println();*/
	}
	
	public void quit() {
		Dialog dlg = new Dialog("Confirm");
		TextArea ta = new TextArea("Are you sure you want to quit?");
		dlg.add(ta);
		Button confirm = new Button(new ConfirmCommand());
		dlg.add(confirm);
		Button cancel = new Button(new Command("No"));
		dlg.add(cancel);
		dlg.showDialog();
	}
	
	public void newGame() {
		init(g);
	}
	
	public void undo() {
		//not functional yet
	}
	
	public void save() {
		//not functional yet
	}
	
	public void setSound() {
		if (sound) {
			sound=false;
			bgSound.pause();
		}	
		else {
			sound=true;
			bgSound.play();
		}
		this.setChanged();
		GameWorldProxy gwProxy = new GameWorldProxy(this);
		this.notifyObservers(gwProxy);
	}
	
	public void about() {
		Dialog.show("About", "CSC 133 Asteroids Project\nPart 3\n\nBrad Waechter\n\nVersion Number: I want to cry\nThis project is killing me", "OK", null);
	}
	
	public void load() {
		//not functional yet
	}
	
	public IIterator getIterator() {
		return go.getIterator();
	}
	
	public int getLives() {
		return lives;
	}
	
}
