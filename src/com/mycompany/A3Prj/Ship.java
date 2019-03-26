package com.mycompany.A3Prj;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Ship extends SpaceObjects implements IMovable, ICollider {
	
	private String name;
	private Point location;
	private int color;
	private int speed;
	private int direction;
	private Point top, bottomLeft, bottomRight;
	private int height, base;
	private Point trueLoc;
	private Point size;
	private int missiles;
	private boolean isDrawable;
	private boolean isCollidable;
	
	public Ship() {		
		//constructor
		isCollidable = true;
		isDrawable = true;
		missiles = 10;
		location = new Point(575, 375);
		trueLoc = new Point(location.getX(), location.getY());
		speed = 0;
		direction = 0;
		name = "Spaceship";
		color = ColorUtil.rgb(255, 0, 255);
		height = 20;
		base = 10;
		top = new Point(0, height/2);
		bottomLeft = new Point(-base/2, -height/2);
		bottomRight = new Point(base/2, -height/2);
		size = new Point(base,height);
	}
	
	public String toString() {
		double rdX = Math.round(location.getX()*10.0)/10.0;
		double rdY = Math.round(location.getY()*10.0)/10.0;
		String s = "Ship: loc=" + rdX + ", " + rdY + " color= " + color + " speed=" + speed + " dir=" + direction + " missiles=" + missiles;
		return s;
	}
	
	//ISpaceObjects required methods
	public Point getLocation() {
		return location;
	}
	
	public void setLocation(Point p) {
		location.setX(p.getX());
		location.setY(p.getY());
	}
	
	/*public float getLocationX() {
		return locationX;
	}

	public float getLocationY() {
		return locationY;
	}*/
	
	public int getColor() {
		return color;
	}
	
	public String getName(){
		return name;
	}
	

	
	
	//IMovable required methods
	public int getDirection() {
		return direction;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	
	public void move(int elapsed) {
		
		if(trueLoc.getX() + base/2 >= 1024 && direction > 180){
			direction = direction-180;
		}

		if(trueLoc.getX() + base/2 >= 1024 && direction < 180){
			direction = direction+180;
		}
		
		if(trueLoc.getX() - base/2 <= 125){
			direction = direction-180;
		}
		
		if(trueLoc.getY() - height/2 <= 63){
			direction = direction-180;
		}

		if(trueLoc.getY() + height/2 >= 703){
			direction = direction+180;
		}
		
		
		
		float dist = elapsed/10 * speed;
		location.setX(location.getX() + (int)(Math.cos(Math.toRadians(90-direction))*dist));
		location.setY(location.getY() + (int)(Math.sin(Math.toRadians(90-direction))*dist));
		

		
	}
	
	//ISteerable required methods
	public void setSpeed(char c) {
		switch(c) {
		case '+' :
			speed += 1;
			break;
		
		case '-' :
			speed -= 1;
			break;
		}
	}
	
	public void setDir(char c) {
		switch(c) {
		case '+' :
			if (direction <= 339) {
				direction += 20;
				break;
			}
			else {
				direction = 0 + (20-(359 - direction));				
				break;
			}
			
		case '-' :
			if (direction >= 20) {
				direction -= 20;
				break;
			}
			else {
				direction = 359 - (20-direction);
				break;
			}
		}
	}
	
	public void jump() {
		location.setX(575);
		location.setY(375);
	}
	
	public void draw(Graphics g, Point origin) {
		trueLoc.setX(origin.getX() + location.getX());
		trueLoc.setY(origin.getY() + location.getY());
		
		g.setColor(color);
		g.drawLine(trueLoc.getX() + top.getX(), trueLoc.getY() + top.getY(), trueLoc.getX() + bottomLeft.getX(), trueLoc.getY() + bottomLeft.getY());
		g.drawLine(trueLoc.getX() + bottomRight.getX(), trueLoc.getY() + bottomRight.getY(), trueLoc.getX() + bottomLeft.getX(), trueLoc.getY() + bottomLeft.getY());
		g.drawLine(trueLoc.getX() + top.getX(), trueLoc.getY() + top.getY(), trueLoc.getX() + bottomRight.getX(), trueLoc.getY() + bottomRight.getY());
	}
	
	public boolean collidesWith(ICollider o) {
		Point otherSize = o.getSize();
		Point otherLocation = o.getLocation();
		
		int thisCenterX = location.getX() + size.getX()/2;
		int thisCenterY = location.getY() + size.getY()/2;
		int otherCenterX = otherLocation.getX() + otherSize.getX()/2;
		int otherCenterY = otherLocation.getY() + otherSize.getY()/2;
		
		int dx = thisCenterX - otherCenterX;
		int dy = thisCenterY - otherCenterY;
		int dist = (dx*dx + dy*dy);
		
		int thisr = size.getY()/2;
		int thatr = otherSize.getY()/2;
		int radiiSqr = (thisr*thisr + 2*thisr*thatr + thatr*thatr);
		
		
		if (dist<=radiiSqr) {
			return true;
		}
		else
			return false;
	}
	//do nothing case = 0, asteroid-ship = 1, missile-asteroid = 2, asteroid-asteroid = 3
	public int handleCollision(ICollider o, boolean sound) {
		if(o instanceof Station) {
			if(((Station) o).blonkTest()==false) 
				reload();
			return 0;
		}
		if(o instanceof Asteroid) {
			return 1;
		}
		else
			return 0;
			
	}
	
	public Point getSize() {

		return size;
	}
	
	public void setDrawable() {
		isDrawable = false;
	}
	
	public boolean getDrawable() {
		return isDrawable;
	}
	
	public int getMissiles(){
		return missiles;
	}
	
	public void depleteMissiles() {
		missiles--;
	}
	
	public void reload() {
		missiles=10;
	}
	
	public boolean getCollidable() {
		return isCollidable;
	}
	
	public void setCollidable() {
		isCollidable = false;
	}

}

