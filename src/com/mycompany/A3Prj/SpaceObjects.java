package com.mycompany.A3Prj;

import com.codename1.ui.geom.Point;

abstract public class SpaceObjects implements ISpaceObjects {

	//abstract public String toString();
	
	abstract public Point getLocation();
	
	abstract public void setLocation(Point p);
	
	abstract public int getColor();
	
	abstract public void move(int elapsed);
	
	abstract public Point getSize();
	
	abstract public boolean getDrawable();
	
	abstract public void setDrawable();
	
}
