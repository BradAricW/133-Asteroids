package com.mycompany.A3Prj;

import com.codename1.ui.geom.Point;

public interface ISelectable {

	public void setSelected(boolean isSelectable);
	
	public boolean isSelected();
	
	public boolean contains(Point pointerPosition, Point componentPosition);
	
}
