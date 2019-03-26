package com.mycompany.A3Prj;

public interface IMovable extends ISpaceObjects {
	
	public int getSpeed();
	
	public int getDirection();
	
	public void move(int elapsed);

}
