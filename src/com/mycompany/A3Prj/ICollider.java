package com.mycompany.A3Prj;

public interface ICollider extends ISpaceObjects {
	public boolean collidesWith(ICollider otherObject);
	public int handleCollision(ICollider otherObject, boolean sound);
	public boolean getCollidable();
	public void setCollidable();
}
