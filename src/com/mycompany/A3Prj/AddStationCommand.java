package com.mycompany.A3Prj;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class AddStationCommand extends Command {
	
	private GameWorld gw;
	
	public AddStationCommand(GameWorld gw) {
		super("Add Station");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.addStation();
	}

}

