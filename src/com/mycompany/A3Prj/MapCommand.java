package com.mycompany.A3Prj;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class MapCommand extends Command {
	
	private GameWorld gw;
	
	public MapCommand(GameWorld gw) {
		super("Map");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.map();
	}

}
