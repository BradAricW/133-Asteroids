package com.mycompany.A3Prj;

import java.util.Vector;


public class SpaceCollection implements ICollection {

	private Vector<SpaceObjects> theCollection;
	
	public SpaceCollection() {
		theCollection = new Vector<SpaceObjects>();
	}
	
	public void add(SpaceObjects newObject) {
		theCollection.addElement(newObject);
	}
	
	public int getSize() {
		int s = theCollection.size();
		return s;
	}
	
	public int indexOf(SpaceObjects o) {
		Object i = (Object)o;
		return theCollection.indexOf(i);
	}
	
	public Object elementAt(int i){
		Object o = theCollection.elementAt(i);
		return o;
	}
	
	public void remove(int i) {
		theCollection.removeElementAt(i);
	}
	
	public void destroy() {
		theCollection.removeAllElements();
	}
	
	public IIterator getIterator() {
		return new SpaceVectorIterator();
	}
	
	private class SpaceVectorIterator implements IIterator {
		
		private int currElementIndex;
		
		public SpaceVectorIterator() {
			currElementIndex = -1;
		}
		
		public boolean hasNext() {
			if (theCollection.size() <= 0)
				return false;
			if (currElementIndex == theCollection.size() -1)
				return false;
			return true;
		}
		
		public SpaceObjects getNext() {
			currElementIndex++;
			return(theCollection.elementAt(currElementIndex));
		}

	}
}

