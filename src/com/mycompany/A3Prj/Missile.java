package com.mycompany.A3Prj;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Missile extends SpaceObjects implements IMovable, ICollider, ISelectable {
	
	private Point location;
	private int color;
	private int speed;
	private int direction;
	private int fuel;
	private Point trueLoc;
	private int width;
	private int height;
	private int count;
	private Point size;
	private boolean isDrawable;
	private boolean isCollidable;
	private boolean currentlySelected;
	
	public Missile(Point p, int dir, int spe) {
		// constructor
		currentlySelected = false;
		isCollidable = true;
		location = new Point(p.getX(), p.getY());
		trueLoc = new Point(p.getX(), p.getY());
		speed = spe + 3;
		direction = dir;
		fuel = 10;
		color = ColorUtil.rgb(0, 0, 255);
		width = 4;
		height = 10;
		count=0;
		size = new Point(width,height);
		isDrawable = true;
	}
	
	public String toString() {
		double rdX = Math.round(location.getX()*10.0)/10.0;
		double rdY = Math.round(location.getY()*10.0)/10.0;
		String s = "Missile: loc=" + rdX + ", " + rdY + " color= " + color + " speed=" + speed + " dir=" + direction + " fuel=" + fuel;
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
	
	public int getColor() {
		return color;
	}
	
	
	//IMovable required methods
	public int getDirection() {
		return direction;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void move(int elapsed) {
		
		if(trueLoc.getX() + width/2 >= 1024 && direction > 180){
			direction = direction-180;
		}

		if(trueLoc.getX() + width/2 >= 1024 && direction < 180){
			direction = direction+180;
		}
		
		if(trueLoc.getX() - width/2 <= 125){
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
		
		if (count==3) {
			count=0;
			fuel--;
		}
		else
			count++;

		
	}
	
	//missile-specific methods
	public int getFuel() {
		return fuel;
	}
	
	public void draw(Graphics g, Point origin) {
		g.setColor(color);
		trueLoc.setX(origin.getX() + location.getX());
		trueLoc.setY(origin.getY() + location.getY());
		if(isSelected())
			g.drawRoundRect(trueLoc.getX(), trueLoc.getY(), width, height, 3, 3);
		else
			g.fillRoundRect(trueLoc.getX(), trueLoc.getY(), width, height, 3, 3);
	}
	
	public Point getSize() {

		return size;
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
		if(o instanceof Asteroid) {
			return 2;
		}
		else
			return 0;
			
	}
	
	public void setDrawable() {
			isDrawable = false;
	}
	
	public boolean getDrawable() {
		return isDrawable;
	}
	
	public boolean getCollidable() {
		return isCollidable;
	}
	
	public void setCollidable() {
			isCollidable = false;
	}

	public void setSelected(boolean isSelectable) {
		// TODO Auto-generated method stub
		
	}

	public boolean isSelected() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean contains(Point pointerPosition, Point componentPosition) {
		int px = pointerPosition.getX();
		int py = pointerPosition.getY();
		int xLoc = componentPosition.getX() + location.getX();
		int yLoc = componentPosition.getY() + location.getY();
		if((px>=xLoc) && (px <= xLoc+size.getX()) && ((py>=yLoc) && (py<=yLoc+size.getY()))) 
			return true;
		else
			return false;
	}
	
	public void refuel() {
		fuel = 10;
	}

}

