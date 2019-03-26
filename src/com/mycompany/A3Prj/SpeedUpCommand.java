package com.mycompany.A3Prj;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class SpeedUpCommand extends Command {
	
	private GameWorld gw;
	
	public SpeedUpCommand(GameWorld gw) {
		super("Speed Up");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gw.speedUp();
	}

}
