package com.mycompany.A3Prj;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Station extends SpaceObjects implements ISpaceObjects, ICollider {

	private Point location;
	private int color;
	private int blink;
	private int blinkCounter;
	private boolean blonk;
	private Point trueLoc;
	private int side = 40;
	private Point size;
	private boolean isDrawable;
	private boolean isCollidable;
	
	public Station() {
		//constructor
		isCollidable = true;
		isDrawable = true;
		Random r = new Random();
		int temp1 = r.nextInt(899) + 125;
		if (temp1<=side + 125)
			temp1 = temp1+side;
		if (temp1 + side >= 1024)
			temp1 = temp1-side;
		int temp2 = r.nextInt(640) + 63;
		if (temp2<=side + 63)
			temp2 = temp1+side;
		if (temp2 + side >= 703)
			temp2 = temp1-side;
		location = new Point(temp1, temp2);
		trueLoc = new Point(temp1, temp2);
		blink = r.nextInt(20) + 75;
		blinkCounter = 0;
		color = ColorUtil.rgb(0, 255, 0);
		size = new Point(side,side);
	}
	
	public String toString() {
		double rdX = Math.round(location.getX()*10.0)/10.0;
		double rdY = Math.round(location.getY()*10.0)/10.0;
		String s = "Station: loc=" + rdX + ", " + rdY + " color=[0, 255, 0] rate=" + blink;
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
	
	//Station-specific methods
	public int getBlink() {
		return blink;
	}
	
	public boolean blonkCheck() {
		if(blonk)
			blonk = false;
		else
			blonk = true;
		return blonk;
	}
	
	public boolean blonkTest() {
		return blonk;
	}
	
	public void move(int elapsed) {
		if(blinkCounter==blink) {
			blinkCounter=0;
			blonkCheck();
		}
		else blinkCounter++;
	}
	
	public void draw(Graphics g, Point origin) {
		g.setColor(color);;		
		trueLoc.setX(origin.getX() + location.getX());
		trueLoc.setY(origin.getY() + location.getY());
		if(blonk) {
			g.drawRect(trueLoc.getX(), trueLoc.getY(), side, side);
		}
		else {
			g.fillRect(trueLoc.getX(), trueLoc.getY(), side, side);
		}
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
		if(o instanceof Ship) {
			if(blonk==false) {
				((Ship) o).reload();
			}
			return 0;
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
	
	public boolean getCollidable() {
		return isCollidable;
	}
	
	public void setCollidable() {
		isCollidable = false;
	}
	
}
