package com.mycompany.A3Prj;

//import statements
import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
//import com.codename1.ui.Label;
import com.codename1.ui.plaf.Border;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class MapView extends Container implements Observer {

	private IIterator theElements;
	//private Label placeHolder;
	private Point origin = new Point(this.getX(), this.getY());
	
	public MapView() {
		this.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.MAGENTA));
		//placeHolder = new Label("This area for future use...");
		//this.add(placeHolder);
	}
	
	public void update (Observable o, Object arg) {

		// code here to output current map information (based on the data in the Observable)
		// to the console. Note that the received “Observable” is a GameWorld PROXY and can
		// be cast to type IGameWorld in order to access the GameWorld methods in it.
		theElements = ((IGameWorld) arg).getIterator();
		repaint();

	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		if(theElements==null) return;
		while(theElements.hasNext()) {
			SpaceObjects space = (SpaceObjects) theElements.getNext();
			if(space instanceof IDrawable) {
				if(space.getDrawable())
					space.draw(g, origin);
			}
		}
	}

}
