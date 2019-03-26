package com.mycompany.A3Prj;

import java.util.Random;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Asteroid extends SpaceObjects implements IMovable, ICollider {
	
	private Point location;
	private int color;
	private int speed;
	private int direction;
	private int size;
	private Point trueLoc;
	private Point sizeP;
	private boolean isDrawable;
	private boolean isCollidable;
	
	public Asteroid() {
		//constructor
		isCollidable = true;
		isDrawable = true;
		Random r = new Random();
		size = r.nextInt(40) +10;
		int temp1 = r.nextInt(899) + 125;
		if (temp1<=size + 125)
			temp1 = temp1+size;
		if (temp1 + size >= 1024)
			temp1 = temp1-size;
		int temp2 = r.nextInt(640) + 63;
		if (temp2<=size + 63)
			temp2 = temp1+size;
		if (temp2 + size >= 703)
			temp2 = temp1-size;
		location = new Point(temp1, temp2);
		trueLoc = new Point(temp1,temp2);
		sizeP = new Point(size,size);
		
		speed = r.nextInt(5) + 1;
		direction = r.nextInt(359);

		color = ColorUtil.rgb(255, 0, 0);
	}

	public String toString() {
		double rdX = Math.round(location.getX()*10.0)/10.0;
		double rdY = Math.round(location.getY()*10.0)/10.0;
		String s = "Asteroid: loc=" + rdX + ", " + rdY + " color=[255, 0, 0] speed=" + speed + " dir=" + direction + " size=" + size;
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
		
		if(trueLoc.getX() + size >= 1024 && direction > 225){
			direction = direction-225;
		}

		if(trueLoc.getX() + size >= 1024 && direction < 225){
			direction = direction+225;
		}
		
		if(trueLoc.getX() - size/2 <= 110){
			direction = direction-225;
		}
		
		if(trueLoc.getY() - size/2 <= 50){
			direction = direction-225;
		}

		if(trueLoc.getY() + size >= 703){
			direction = direction+225;
		}
		
		
		
		float dist = elapsed/10 * speed;
		location.setX(location.getX() + (int)(Math.cos(Math.toRadians(90-direction))*dist));
		location.setY(location.getY() + (int)(Math.sin(Math.toRadians(90-direction))*dist));
		

		
	}

	public Point getSize() {
		return sizeP;
	}
	
	public void draw(Graphics g, Point origin) {
		
		g.setColor(color);
		trueLoc.setX(origin.getX() + location.getX());
		trueLoc.setY(origin.getY() + location.getY());
		g.drawArc(trueLoc.getX(), trueLoc.getY(), size, size, 0, 360);
		//g.drawRect(trueLoc.getX(), trueLoc.getY(), size, size);
	}
	
	public boolean collidesWith(ICollider o) {
		Point otherSize = o.getSize();
		Point otherLocation = o.getLocation();
		
		int thisCenterX = location.getX() + sizeP.getX()/2;
		int thisCenterY = location.getY() + sizeP.getY()/2;
		int otherCenterX = otherLocation.getX() + otherSize.getX()/2;
		int otherCenterY = otherLocation.getY() + otherSize.getY()/2;
		
		int dx = thisCenterX - otherCenterX;
		int dy = thisCenterY - otherCenterY;
		int dist = (dx*dx + dy*dy);
		
		int thisr = sizeP.getY()/2;
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
		if(o instanceof Ship) {
			return 1;
		}
		if(o instanceof Missile) {
			return 2;
		}
		if(o instanceof Asteroid) {
			return 3;
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
}

