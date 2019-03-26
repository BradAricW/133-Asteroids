package com.mycompany.A3Prj;

public interface ICollection {
	
	public void add(SpaceObjects o);
	
	public IIterator getIterator();
	
	public int getSize();
	
	public void remove(int i);
	
	public void destroy();

}
