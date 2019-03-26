package com.mycompany.A3Prj;

import com.codename1.ui.geom.Point;

public interface ISpaceObjects extends IDrawable{
	
	//public String toString();
	
	abstract public Point getLocation();
	
	abstract public void setLocation(Point p);
	
	public int getColor();
	
	public Point getSize();
	
	public boolean getDrawable();
	
	public void setDrawable();

}
