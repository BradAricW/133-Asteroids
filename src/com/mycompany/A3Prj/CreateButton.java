package com.mycompany.A3Prj;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.plaf.Border;

public class CreateButton extends Button {
	
	private Button b;
	
	public CreateButton(String s) {
		
		b = new Button(s);
		b.getAllStyles().setBgTransparency(255);
		b.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
		b.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		b.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		b.getAllStyles().setBgColor(ColorUtil.rgb(0, 0, 0));
		b.getAllStyles().setPadding(TOP,  5);
		b.getAllStyles().setPadding(BOTTOM, 5);
		
	}

}